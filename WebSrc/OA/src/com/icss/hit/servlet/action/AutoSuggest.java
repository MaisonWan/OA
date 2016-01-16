package com.icss.hit.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hit.bean.OtherInfoBean;
import com.icss.hit.bean.interfaces.OtherInfo;
import com.icss.hit.hibernate.vo.SysUser;

public class AutoSuggest extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	private OtherInfo userbean = null;
	public AutoSuggest() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/plain;charset=utf-8");
		String result="";
		String id=new String(request.getParameter("id").getBytes("ISO-8859-1"),"GB2312");
		String type=request.getParameter("type");
		String searchType = null;
		if( type.equals("login")){
			searchType = "id";
		}else{
			searchType = type;
		}
		List<SysUser> list = userbean.getSearchedUserInfo(searchType, id, "0", 0, 1);

		for( int i = 0; i < list.size(); i++ ){
			SysUser user = list.get(i);
		    if( i > 0 ){
		   		result += "|";
		    }
		    if(type.equals("id")){
			    result += user.getSuUid() + "&" + user.getSuUsername()
			   		+ "&" + user.getSysDept().getSdName();
		    }
		    else if(type.equals("name")){
			    result += user.getSuUsername() + "&" + user.getSuUid() 
			   			+ "&" + user.getSysDept().getSdName();
		    }else{
		    	result += user.getSuUid();
		    }
		}
		PrintWriter out = response.getWriter();
		out.print(result.trim());
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		userbean = new OtherInfoBean();
	}

}
