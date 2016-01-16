package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.SysPosition;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author 朱金彪
 * 2009年8月12日
 */
public interface SysPositionDao {
	/**
	 * 得到所有职位列表
	 * @return 职位列表
	 */
	public List<SysPosition> getAllPosition();
	
	/**
	 * 根据ID得到指定职位信息
	 * @param id 职位ID
	 * @return 职位信息
	 */
	public SysPosition getPosition(long id);
	
	/**
	 * 根据部门返回指定页的员工信息
	 * @param suDept 部门ID
	 * @param pageNo 页码
	 * @return 员工信息列表
	 */
	public List<SysUser> getAllUserByDept(long suDept, int pageNo);
	
	/**
	 * 根据部门返回所有员工数量
	 * @param suDept 部门ID
	 * @return 数量
	 */
	public int getAllUserByDeptCount(long suDept);
	
	/**
	 * 分页数量
	 * @param count 总数量
	 * @param pageSize 页面大小
	 * @return 总页数
	 */
	public int getPageCount(int count, int pageSize);
}
