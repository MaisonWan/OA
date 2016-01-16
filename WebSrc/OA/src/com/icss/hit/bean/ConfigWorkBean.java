package com.icss.hit.bean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.ConfigWork;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.ScheduleConfig;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysPosition;
import com.icss.hit.hibernate.vo.SysRole;
import com.icss.hit.hibernate.vo.SysUser;

public class ConfigWorkBean implements ConfigWork{
	public static int PAGE_SIZE = 10;
	public List<SysUser> getAllOtherUserInfo(long id,int suDept, int pageNo)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysUser u inner join u.sysRole r inner join u.sysDept d inner join u.sysPosition p "+
		"where u.suId!="+id;
		if(suDept!=0){
			hql+=" and d.sdId="+suDept;
		}
		hql+=" and u.suId not in (select to.suId from ScheduleConfig sc "+
			"inner join sc.sysUserBySuIdTo to inner join sc.sysUserBySuIdFrom fr "+
			"where fr.suId="+id+")";
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql);
			
			// 设置分页信息
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<SysUser> userInfoList = new ArrayList<SysUser>();
			while (it.hasNext()) {
				
				Object[] obj = (Object[]) it.next();
				SysUser u = (SysUser) obj[0];
				SysRole r = (SysRole) obj[1];
				SysDept d = (SysDept) obj[2];
				SysPosition p = (SysPosition) obj[3];
				
				// 将role，dept，position设置为user的属性
				u.setSysRole(r);
				u.setSysDept(d);
				u.setSysPosition(p);
				userInfoList.add(u);
			}
			if(userInfoList.size() == 0)
				return null;
			return userInfoList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	public int getPageCount(int count, int pageSize) {
		return (count + pageSize - 1) / pageSize;
	}
	
	
	public int getAllOtherUserInfoCount(long id,int suDept) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(u.suId) from SysUser u inner join u.sysRole r inner join u.sysDept d inner join u.sysPosition p "+
		"where u.suId!="+id;
		if(suDept!=0){
			hql+=" and d.sdId="+suDept;
		}
		hql+=" and u.suId not in (select to.suId from ScheduleConfig sc "+
			"inner join sc.sysUserBySuIdTo to inner join sc.sysUserBySuIdFrom fr "+
			"where fr.suId="+id+")";
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Object o = sess.createQuery(hql).uniqueResult();
			tx.commit();
			return Integer.parseInt(o.toString());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return 0;
		} finally {
			sess.close();
		}
	}
	public List<ScheduleConfig> getAllConfigWorkUser(long id,int pageNo)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from ScheduleConfig sc inner join sc.sysUserBySuIdTo to inner join to.sysDept d inner join to.sysRole r inner join to.sysPosition p inner join sc.sysUserBySuIdFrom fr where fr.suId="+id;
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql);
			
			// 设置分页信息
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<ScheduleConfig> authorizedUserList = new ArrayList<ScheduleConfig>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				ScheduleConfig sc = (ScheduleConfig) obj[0];
				SysUser u2 = (SysUser) obj[1];
				SysDept d = (SysDept) obj[2];
				SysRole r = (SysRole) obj[3];
				SysPosition p = (SysPosition) obj[4];
				u2.setSysDept(d);
				u2.setSysRole(r);
				u2.setSysPosition(p);
				// 将role，dept，position设置为user的属性
				sc.setSysUserBySuIdTo(u2);
				authorizedUserList.add(sc);
			}
			if(authorizedUserList.size() == 0)
				return null;
			return authorizedUserList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	public int getAllConfigWorkUserCount(long id)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(sc.scId) from ScheduleConfig sc inner join sc.sysUserBySuIdTo to inner join to.sysDept d inner join to.sysRole r inner join to.sysPosition p inner join sc.sysUserBySuIdFrom fr  inner join fr.sysDept fd inner join fr.sysRole fsr inner join fr.sysPosition fp where fr.suId="+id;		
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Object o = sess.createQuery(hql).uniqueResult();
			tx.commit();
			return Integer.parseInt(o.toString());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return 0;
		} finally {
			sess.close();
		}
	}
	
	public boolean deleteAuthorizedUser(long scId,long userId)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx1 = null;
		Transaction tx2 = null;
		String searchHql="from ScheduleConfig sc inner join sc.sysUserBySuIdTo t inner join sc.sysUserBySuIdFrom f where sc.scId ="+scId+" and f.suId="+userId;
		String deleteHql = "delete ScheduleConfig sc where sc.scId ="+scId;
		try {
			tx1 = sess.beginTransaction();
			Query searchQuery = sess.createQuery(searchHql);
			List i = searchQuery.list();
			Iterator it = i.iterator();
			tx1.commit();
			if(it.hasNext()){
				tx2=sess.beginTransaction();
				Query deleteQuery = sess.createQuery(deleteHql);
				deleteQuery.executeUpdate();
				tx2.commit();
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			tx1.rollback();
			tx2.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sess.close();
		}
	}
	public boolean authorize(ScheduleConfig sc)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			sess.saveOrUpdate(sc);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sess.close();
		}
	}

}
