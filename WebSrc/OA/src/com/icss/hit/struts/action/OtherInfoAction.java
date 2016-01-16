package com.icss.hit.struts.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.icss.hit.bean.DepartmentBean;
import com.icss.hit.bean.OtherInfoBean;
import com.icss.hit.bean.interfaces.Department;
import com.icss.hit.bean.interfaces.OtherInfo;
import com.icss.hit.component.PageBean;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author 朱金彪
 * 根据条件搜索他人信息
 */
public class OtherInfoAction  extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Department deptm = new DepartmentBean();
		// 得到所有的部门信息
		List<SysDept> deptList = deptm.getAllDept();
		request.setAttribute("deptList",deptList);
		
		String type = null;
		String key = null;
		String sex = null;
		int dept = -1;
		int pageNo = 1;
		
		// 得到参数
		type = request.getParameter("type");
		key = request.getParameter("key");
		sex = request.getParameter("sex");

		try {
			if( key != null){
				key = URLDecoder.decode(key,"UTF-8");
			}
			if( sex != null)
				sex = URLDecoder.decode(sex, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			key = null;
		}
		
		try{
			dept = Integer.parseInt(request.getParameter("dept"));
			pageNo = Integer.parseInt(request.getParameter("page"));
		}catch(Exception e){
			// 如果数据异常
			dept = -1;
			pageNo = 1;
		}
		// 所有人员的数量
		int count = 0;
		// 分页的数量
		int pageCount = 0;
		// 得到搜索的数据
		List<SysUser> list = null;
		if( type != null && key != null && sex != null && dept != -1){
			OtherInfo info = new OtherInfoBean();
			
			// 得到总数和分页
			count = info.getSearchedUserCount(type, key, sex, dept);
			pageCount = info.getPageConut(count, OtherInfoBean.PAGE_SIZE);
			
			// 设置页面的范围
			pageNo = pageNo < 1? 1:pageNo;
			pageNo = pageNo > count? count: pageNo;
			
			list = info.getSearchedUserInfo(type, key, sex, dept, pageNo);
			request.setAttribute("searchOtherInfo", list);
						
			// 生成前面的页面显示
			PageBean pagebean = new PageBean();
			pagebean.addParam("type=" + type );
			
			pagebean.addParam("key=" + URLEncoder.encode(request.getParameter("key")));
			pagebean.addParam("sex=" + URLEncoder.encode(request.getParameter("sex")));
			
			pagebean.addParam("dept=" + dept );
			pagebean.setLink("OtherInfo.do");
			pagebean.setTotal(pageCount);
			pagebean.setThispage(pageNo);
			
			request.setAttribute("pageString", pagebean.getPageString());
			request.setAttribute("searchKey",key);
			request.setAttribute("searchType",type);
			request.setAttribute("searchSex",sex);
			request.setAttribute("searchDept",dept);
			request.setAttribute("search", "searchFlag");//ddd同学在这里添加了一个标记用来表示搜索不到时候的信息
			if( request.getParameter("pdf") != null ){
				request.setAttribute("pdfName", request.getParameter("pdf"));
			}
		}
		return mapping.findForward("OtherInfo.succeed");
	}

}
