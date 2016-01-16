package com.icss.hit.bean.interfaces;
import java.util.List;

import com.icss.hit.hibernate.vo.Schedule;
import com.icss.hit.hibernate.vo.ScheduleConfig;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author 朱金彪
 * 2009年8月3日
 */
/**
 * @author xw-pc
 *
 */
public interface ConfigWork {
	/**
	 * 得到指定条件的所有用户
	 * @param id 用户ID
	 * @param suDept 部门ID
	 * @param pageNo 页码
	 * @return 指定用户列表
	 */
	public List<SysUser> getAllOtherUserInfo(long id,int suDept, int pageNo);
	/**
	 * 计算指定条件的所有用户数量
	 * @return
	 */
	public int getAllOtherUserInfoCount(long id,int suDept);
	/**
	 * 计算页数
	 * @param count 总行数
	 * @param pageSize 每页行数
	 * @return 总页数
	 */
	public int getPageCount(int count, int pageSize);
	
	/**
	 * 得到某个用户授权代办的用户
	 * @param id 用户ID
	 * @param suDept 部门ID
	 * @return 用户列表
	 */
	
	/**
	 * 得到自己授权代办的员工
	 * @param id 本人ID
	 * @param pageNo 页码
	 * @return 所有有关员工
	 */
	public List<ScheduleConfig> getAllConfigWorkUser(long id,int pageNo);
	
	/**
	 * 根据ID得到相应的授权用户组
	 * @param id 用户ID
	 * @return 用户组
	 */
	public int getAllConfigWorkUserCount(long id);
	
	/**
	 * 删除指定的授权人
	 * @param scId 关系表ID
	 * @param userId 用户ID
	 * @return 是否删除
	 */
	public boolean deleteAuthorizedUser(long scId,long userId);
	
	/**
	 * 授权指定用户为代办人
	 * @param sc ScheduleConfig实例
	 */
	public boolean authorize(ScheduleConfig sc);
	
}

