package com.icss.hit.bean.interfaces;

import java.util.List;
import com.icss.hit.hibernate.vo.SysUser;
import com.icss.hit.hibernate.vo.SysDept;

/**
 * @author 朱金彪
 *
 */
public interface OtherInfo {
	
	/**
	 * 根据条件进行搜索
	 * @param type 搜索类型
	 * @param suUser 关键词
	 * @param suSex 性别
	 * @param suDept 部门
	 * @param pageNo 页码
	 * @return 返回符合条件的信息集合
	 */
	public List<SysUser> getSearchedUserInfo(String type,String suUser,String suSex,int suDept, int pageNo);
	/**
	 * 根据条件进行搜索，得到符合条件的人数
	 * @param type 搜索类型
	 * @param suUser 关键词
	 * @param suSex 性别
	 * @param suDept 部门
	 * @return 符合条件的人数
	 */
	public int getSearchedUserCount(String type,String suUser,String suSex,int suDept);
	/**
	 * 计算出分页的页数
	 * @param count 总数
	 * @param pageSize 每页的数量
	 * @return 返回总页数
	 */
	public int getPageConut(int count, int pageSize);

	/**
	 * @param pageNo 页数
	 * @return 全部的人的信息
	 */
	public List<SysUser> getAllUserInfo(String suSex,int suDept,int pageNo);
	/**
	 * @return 全部的人数
	 */
	public int getAllUserCount(String suSex,int suDept);

	/**
	 * 根据条件搜索出符合条件的所有人的信息
	 * @param type 搜索类型
	 * @param suUser 关键词
	 * @param suSex 性别
	 * @param suDept 部门ID
	 * @return 符合条件的人的集合
	 */
	public List<SysUser> getAllSearchedUserInfo(String type, String suUser,
			String suSex, int suDept);
	/**
	 * 根据条件搜索出符合条件的所有人的信息
	 * @param suSex 性别
	 * @param suDept 部门ID
	 * @return 符合条件的人的集合
	 */
	public List<SysUser> getUserInfo(String suSex, int suDept);
}
