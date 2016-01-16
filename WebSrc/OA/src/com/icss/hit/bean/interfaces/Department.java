/**
 * 
 */
package com.icss.hit.bean.interfaces;
import java.util.List;

import com.icss.hit.hibernate.vo.SysDept;
/**
 * 部门管理的一些操作的集合
 * @author 万里鹏
 */
public interface Department {
	/**
	 * @param id 部门ID
	 * @return 返回一个部门信息
	 */
	public SysDept getDept( long id );
	
	
	/**
	 * @param dept 部门信息实体类
	 * @return 是否更新成功
	 */
	public boolean updateDept( SysDept dept);
	
	
	/**
	 * @param id 部门ID
	 * @return 是否删除成功
	 */
	public boolean deleteDept( long id );
	
	
	/**
	 * @return 返回所有部门的数量
	 */
	public int getDeptCount();
	
	
	/**
	 * @param count 部门的总数
	 * @param pageSize 每页显示的数量
	 * @return 页面的数量
	 */
	public int getPageCount(int count, int pageSize);
	
	
	/**
	 * @param pageNo 指定页码
	 * @return 指定页码的部门信息
	 */
	public List<SysDept> getAllDeptByPage(int pageNo);
	
	
	/**
	 * @return 所有部门信息
	 */
	public List<com.icss.hit.hibernate.vo.SysDept> getAllDept ();
	/**
	 * @param dept 部门实例	
	 * @return  添加是否成功
	 */
	public boolean addDept(SysDept dept);
	/**
	 * @param sdId 部门主键ID
	 * @return  是否有员工属于该部门
	 */
	public boolean checkDept(long sdId);
}
