package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.WorkList;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Schedule;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysPosition;
import com.icss.hit.hibernate.vo.SysRole;
import com.icss.hit.hibernate.vo.SysUser;

public class WorkListBean implements WorkList{
	public static int PAGE_SIZE = 10;
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkList#getWorkList(long, int)
	 */
	public List<Schedule> getWorkList(long id,int pageNo)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s inner join s.sysUserBySuIdTo to inner " +
				"join s.sysUserBySuIdFrom fr where s.schComplete='1' and to.suId=?" +
			    " order by s.schBegintime desc";
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户添加的日程安排的数量
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql).setLong(0, id);
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List list = query.list();
			Iterator it = list.iterator();
			tx.commit();
			List<Schedule> reList = new ArrayList<Schedule>();
			while( it.hasNext()){
				Object[] o = (Object[])it.next();
				Schedule s = (Schedule)o[0];
				SysUser to = (SysUser)o[1];
				SysUser fr = (SysUser)o[2];
				s.setSysUserBySuIdTo(to);
				s.setSysUserBySuIdFrom(fr);
				reList.add(s);
			}
			if( reList.size() == 0 )
				return null;
			return reList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkList#getWorkListCount(long)
	 */
	public int getWorkListCount(long id)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// 此处的u.suId 被老万改了，原来是s.sysUserBySuIdFrom.suId
		String hql = "select count(s.schId) from Schedule s inner join s.sysUserBySuIdTo u where s.schComplete='1' and u.suId=?";
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Query query = sess.createQuery(hql).setLong(0, id);
			Object o = query.uniqueResult();
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
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkList#getPageConut(int, int)
	 */
	public int getPageCount(int count, int pageSize) {
		return ( count + pageSize - 1) / pageSize;
	}
	
	public List<Schedule> getFiveWorkPlan(long id)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s inner join s.sysUserBySuIdTo to inner join " +
				"s.sysUserBySuIdFrom fr where s.schComplete='0' and to.suId=?"+
				" order by s.schRead,s.schBegintime desc";
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户添加的日程安排的数量
			Query query = sess.createQuery(hql).setLong(0, id);
			query.setFirstResult(0).setMaxResults(5);
			tx.commit();
			List list = query.list();
			Iterator it = list.iterator();
			List<Schedule> reList = new ArrayList<Schedule>();
			while( it.hasNext()){
				Object[] o = (Object[])it.next();
				Schedule s = (Schedule)o[0];
				SysUser to = (SysUser)o[1];
				SysUser fr = (SysUser)o[2];
				s.setSysUserBySuIdTo(to);
				s.setSysUserBySuIdFrom(fr);
				reList.add(s);
			}
			if( reList.size() == 0 )
				return null;
			return reList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkList#getAllWorkPlan(long, int)
	 */
	public List<Schedule> getAllWorkPlan(long id,int pageNo)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s inner join s.sysUserBySuIdTo to inner join s.sysUserBySuIdFrom fr where s.schComplete='0' and s.sysUserBySuIdFrom.suId=?"
			+ " order by s.schRead,s.schBegintime desc";
		int count = 0;
		try {
			tx = sess.beginTransaction();
			int offset = (pageNo - 1) * PAGE_SIZE;
			
			// 查询指定ID的用户添加的日程安排的数量
			Query query = sess.createQuery(hql).setLong(0, id);
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List list = query.list();
			Iterator it = list.iterator();
			tx.commit();
			List<Schedule> workPlanList = new ArrayList<Schedule>();
			while( it.hasNext()){
				Object[] o = (Object[])it.next();
				Schedule s = (Schedule)o[0];
				SysUser to = (SysUser)o[1];
				SysUser fr = (SysUser)o[2];
				s.setSysUserBySuIdTo(to);
				s.setSysUserBySuIdFrom(fr);
				workPlanList.add(s);
			}
			if( workPlanList.size() == 0 )
				return null;
			return workPlanList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkList#getAllWorkPlanCount(long)
	 */
	public int getAllWorkPlanCount(long id)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s where s.schComplete='0' and s.sysUserBySuIdFrom.suId=?";
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户添加的日程安排的数量
			List list = sess.createQuery(hql).setLong(0, id).list();
			tx.commit();
			return list.size();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return 0;
		} finally {
			sess.close();
		}
	}
	
	public Schedule getSelfWorkPlan(long schId, long userId ) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s inner join fetch s.sysUserBySuIdFrom f"
			+ " inner join fetch s.sysUserBySuIdTo t where s.schId=? and f.suId=? and t.suId=?";
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户添加的日程安排的数量
			List list = sess.createQuery(hql).setLong(0, schId).setLong(1, userId).setLong(2,userId).list();
			tx.commit();
			if( list != null && list.size() > 0 )
				return (Schedule)list.get(0);
			return null;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		} 
	}
}
