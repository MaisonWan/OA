package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.ScheduletimeDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Schedule;
import com.icss.hit.hibernate.vo.SysUser;

public class SchduletimeBean implements ScheduletimeDao {

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
	public int getSchduleWorkCount(long userId,Date beginDate,Date endDate) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s where s.sysUserBySuIdFrom.suId=? and s.schBegintime between ? and ?";
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户添加的日程安排的数量
			List list = sess.createQuery(hql).setLong(0, userId).setDate(1, beginDate).setDate(2, endDate).list();
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
	
	public List<Schedule> getSchduleWorkByPage(long userId, int page,Date beginDate,Date endDate) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Schedule s inner join s.sysUserBySuIdTo u where s.sysUserBySuIdFrom.suId=? and s.schBegintime between ? and ?"
			+ " order by s.schComplete,s.schBegintime desc";
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户，指定的时间，添加的日程安排的数量
			List list = sess.createQuery(hql).setLong(0, userId).setDate(1, beginDate).setDate(2, endDate).list();
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

}
