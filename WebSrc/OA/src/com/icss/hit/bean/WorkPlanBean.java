/**
 * 
 */
package com.icss.hit.bean;

import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.WorkPlanDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Card;
import com.icss.hit.hibernate.vo.CardType;
import com.icss.hit.hibernate.vo.Schedule;
import com.icss.hit.hibernate.vo.ScheduleConfig;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * 封装了对日程安排的一些操作
 * @author <font color="blue">万里鹏</font>
 * 
 */
public class WorkPlanBean implements WorkPlanDao{
	public static int PAGE_SIZE = 10;
	
	public Schedule getScheduleInfo(long schId) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s inner join s.sysUserBySuIdTo sto inner join s.sysUserBySuIdFrom sfr where s.schId=?";
		try {
			tx = sess.beginTransaction();

			List l = sess.createQuery(hql).setLong(0, schId).list();
			tx.commit();
			Iterator it = l.iterator();
			if (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Schedule s = (Schedule) obj[0];
				SysUser suTo = (SysUser) obj[1];
				SysUser suFr =(SysUser)obj[2];
				s.setSysUserBySuIdFrom(suFr);
				s.setSysUserBySuIdTo(suTo);
			
				return s;
			}
			return null;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}

	public Schedule getSchdule(long id) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s inner join s.sysUserBySuIdTo sto inner join s.sysUserBySuIdFrom sfr where sto.suId=?";
		try {
			tx = sess.beginTransaction();

			List l = sess.createQuery(hql).setLong(0, id).list();
			tx.commit();
			Iterator it = l.iterator();
			if (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Schedule s = (Schedule) obj[0];
				SysUser suTo = (SysUser) obj[1];
				SysUser suFr =(SysUser)obj[2];
				s.setSysUserBySuIdFrom(suFr);
				s.setSysUserBySuIdTo(suTo);
				return s;
			}
			return null;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkPlanDao#getPageCount(int, int)
	 */
	public int getPageCount(int count, int pageSize) {
		// TODO Auto-generated method stub
		return ( count + pageSize - 1) / pageSize;
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkPlanDao#getWordPlanCount(long)
	 */
	public int getWordPlanCount(long userId) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s where s.sysUserBySuIdFrom.suId=?";
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户添加的日程安排的数量
			List list = sess.createQuery(hql).setLong(0, userId).list();
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

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkPlanDao#getWorkPlanByPage(long, int)
	 */
	public List<Schedule> getWorkPlanByPage(long userId, int page) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s inner join s.sysUserBySuIdTo u where s.sysUserBySuIdFrom.suId=?"
			+ " order by s.schComplete,s.schBegintime desc";
		try {
			tx = sess.beginTransaction();
			int offset = ( page -1 ) * PAGE_SIZE;
			// 查询指定ID的用户添加的日程安排的数量
			List list = sess.createQuery(hql).setLong(0, userId).setFirstResult(offset).setMaxResults(PAGE_SIZE).list();
			tx.commit();
			
			Iterator it = list.iterator();
			List<Schedule> reList = new ArrayList<Schedule>();
			while( it.hasNext()){
				Object[] o = (Object[])it.next();
				Schedule s = (Schedule)o[0];
				SysUser u = (SysUser)o[1];
				s.setSysUserBySuIdTo(u);
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
	 * @see com.icss.hit.bean.interfaces.WorkPlanDao#getWorkPlan(long)
	 */
	public Schedule getWorkPlan(long schId, long userId ) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s inner join fetch s.sysUserBySuIdFrom f"
			+ " inner join fetch s.sysUserBySuIdTo t where s.schId=? and f.suId=?";
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户添加的日程安排的数量
			List list = sess.createQuery(hql).setLong(0, schId).setLong(1, userId).list();
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
	
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkPlanDao#getWorkPlanByTo(long, long)
	 */
	public Schedule getWorkPlanByTo(long schId, long userId) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s inner join fetch s.sysUserBySuIdFrom f"
			+ " inner join fetch s.sysUserBySuIdTo t where s.schId=? and t.suId=?";
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户添加的日程安排的数量
			List list = sess.createQuery(hql).setLong(0, schId).setLong(1, userId).list();
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

	public List<ScheduleConfig> getScheuleConfig(long id) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from ScheduleConfig sc inner join sc.sysUserBySuIdFrom fr" +
				" inner join sc.sysUserBySuIdTo sto where sto.suId=?";
		try {
			tx = sess.beginTransaction();
			List l = sess.createQuery(hql).setLong(0, id).list();
			tx.commit();
			Iterator it = l.iterator();
			List<ScheduleConfig> list = new ArrayList<ScheduleConfig>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				ScheduleConfig sc = (ScheduleConfig) obj[0];
				SysUser su =  (SysUser) obj[2];
				sc.setSysUserBySuIdTo(su);
				list.add(sc);
			}
			if(list.size()==0)
			{
				return null;
			}
			return list;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	public boolean saveSchedule(Schedule sch){
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			sess.saveOrUpdate(sch);
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


	public List<Schedule> getSchedule(long id) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s inner join s.sysUserBySuIdFrom" +
				" inner join s.sysUserBySuIdTo sto where sto.suId=?";
		try {
			tx = sess.beginTransaction();
			List l = sess.createQuery(hql).setLong(0, id).list();
			tx.commit();
			Iterator it = l.iterator();
			List<Schedule> list = new ArrayList<Schedule>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Schedule sc = (Schedule) obj[0];
				SysUser su =  (SysUser) obj[2];
				sc.setSysUserBySuIdTo(su);
				list.add(sc);
			}
			if(list.size()==0)
			{
				return null;
			}
			return list;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkPlanDao#delete(long, long)
	 */

	public boolean delete(long schId, long userId) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "delete Schedule s where s.schComplete=0 and s.schId=? and s.sysUserBySuIdFrom.suId=?";
		try {
			tx = sess.beginTransaction();
			// 删除指定ID的日程
			Query query = sess.createQuery(hql).setLong(0, schId).setLong(1, userId);
			int re = query.executeUpdate();
			tx.commit();
			System.out.println(re);
			if( re > 0 )
				return true;
			return false;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkPlanDao#update(com.icss.hit.hibernate.vo.Schedule)
	 */
	public boolean update(Schedule schedule) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// 更新指定的日程
			sess.update(schedule);
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

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkPlanDao#allSearch(java.lang.String, java.sql.Date, java.sql.Date, java.lang.String, long)
	 */
	public List<Schedule> allSearch(String name, Date begin, Date end,
			String complete, long userId, int page) {
		Session sess = HibernateSessionFactory.getSession();
		String hql = "from Schedule sch inner join sch.sysUserBySuIdTo uto"
			+ " inner join sch.sysUserBySuIdFrom ufrom where uto.suId=?";
		// 生成搜索的条件HQL
		if( name != null && !name.equals("")){
			hql += " and ufrom.suUsername like ?";
		}
		if( begin != null ){
			hql += " and sch.schBegintime > ?";
		}
		if( end != null ){
			hql += " and sch.schEndtime < ?";
		}
		if( complete != null && !complete.equals("2")){
			hql += " and sch.schComplete = ?";
		}
		// 按未完成和时间顺序排序
		hql += " order by sch.schComplete,sch.schBegintime desc";
		
		Transaction tx = null;
		int num = 0;
		try {
			tx = sess.beginTransaction();
			// 搜索
			Query query = sess.createQuery(hql).setLong(num++, userId);
			if( name != null && !name.equals("")){
				query.setString(num++, "%" + name + "%");
			}
			if( begin != null ){
				query.setDate(num++, begin);
			}
			if( end != null ){
				query.setDate(num++, end);
			}
			if( complete != null && !complete.equals("2")){
				query.setString(num++, complete);
			}
			// 计算起始的条数
			int offset = ( page - 1) * PAGE_SIZE;
			List l = query.setFirstResult(offset).setMaxResults(PAGE_SIZE).list();
			tx.commit();
			List<Schedule> list = new ArrayList<Schedule>();
			Iterator it = l.iterator();
			while( it.hasNext()){
				Object[] o = (Object[])it.next();
				Schedule sch = (Schedule)o[0];
				SysUser suTo = (SysUser)o[1];
				SysUser suFrom = (SysUser)o[2];
				
				sch.setSysUserBySuIdTo(suTo);
				sch.setSysUserBySuIdFrom(suFrom);
				list.add(sch);
			}
			if( list.size() == 0)
				return null;
			return list;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.WorkPlanDao#allSearchCount(java.lang.String, java.sql.Date, java.sql.Date, java.lang.String, long)
	 */
	public int allSearchCount(String name, Date begin, Date end,
			String complete, long userId) {
		Session sess = HibernateSessionFactory.getSession();
		String hql = "from Schedule sch inner join sch.sysUserBySuIdTo uto"
			+ " inner join sch.sysUserBySuIdFrom ufrom where uto.suId=?";
		// 生成搜索的条件HQL
		if( name != null && !name.equals("")){
			hql += " and ufrom.suUsername like ?";
		}
		if( begin != null ){
			hql += " and sch.schBegintime > ?";
		}
		if( end != null ){
			hql += " and sch.schEndtime < ?";
		}
		if( complete != null && !complete.equals("2")){
			hql += " and sch.schComplete = ?";
		}
		
		Transaction tx = null;
		int num = 0;
		try {
			tx = sess.beginTransaction();
			// 搜索
			Query query = sess.createQuery(hql).setLong(num++, userId);
			if( name != null && !name.equals("")){
				query.setString(num++, "%" + name + "%");
			}
			if( begin != null ){
				query.setDate(num++, begin);
			}
			if( end != null ){
				query.setDate(num++, end);
			}
			if( complete != null && !complete.equals("2")){
				query.setString(num++, complete);
			}
			List list = query.list();
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
	}
	

