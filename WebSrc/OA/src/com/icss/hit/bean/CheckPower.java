/**
 * 
 */
package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.SysPower;

/**
 * 根据所给的用户ID来判断是否存在某个权限
 * @author 万里鹏
 *
 */
public class CheckPower {

	/**
	 * 用户ID
	 */
	private long userId; 
	
	/**
	 * 部门的权限
	 */
	public static long ADMIN_DEPT = 41; 
	/**
	 * 权限管理
	 */
	public static long ADMIN_POWER = 42;
	/**
	 * 管理角色的权限
	 */
	public static long ADMIN_ROLE = 43; 
	/**
	 * 对会议室的管理
	 */
	public static long ADMIN_ROOM = 1; 
	/**
	 * 对会议室申请的审批
	 */
	public static long ADMIN_ROOM_REG = 2;  
	/**
	 * 员工信息管理
	 */
	public static long ADMIN_STUFF_INFO = 44;  
	/**
	 * 添加新的员工
	 */
	public static long ADMIN_STUFF_ADD = 4; 
	
	// 获取用户权限
	private ArrayList<Long> powerList = new ArrayList<Long>();
	public CheckPower( long userId ){
		this.userId = userId;
	}
	public CheckPower(){
		
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	

	/**
	 * 判断用户是否有某一权限
	 * @param userId 用户ID
	 * @param powerID 权限ID
	 * @return 是否有此权限
	 */
	public boolean check( long userId, long powerID ){
		this.userId = userId;
		// 遍历该用户权限的列表
		for( Long pw : powerList){
			if( pw.compareTo(powerID) == 0 ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 核查是否有一个权限
	 * @param powerID 权限的ID
	 * @return 是/否
	 */
	public boolean check( long powerID ){
		
		// 遍历该用户权限的列表
		for( Long pw : powerList){
			if( pw.compareTo(powerID) == 0 ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 返回用户的权限
	 */
	public void getPower(){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select srp.sysPower from SysRolePower srp where srp.sysRole = " +
				"(select su.sysRole from SysUser su where su.suId=?)";
		try {
			tx = sess.beginTransaction();
			
			List list = sess.createQuery(hql).setLong(0, this.userId).list();
			tx.commit();
			for( int i = 0; i < list.size(); i++ ){
				SysPower sp = (SysPower)list.get(i);
				powerList.add(sp.getSpId());
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			sess.close();
		}
	}
	
	/**
	 * 判断用户权限是否为空
	 * @return 权限列表是否为空
	 */
	public boolean isEmpty(){
		if( powerList.size() == 0 )
			return true;
		return false;
	}
}
