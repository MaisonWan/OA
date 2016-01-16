package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.MeetingAttendDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Meeting;
import com.icss.hit.hibernate.vo.MeetingAttend;
import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.SysUser;

public class MeetingAttendBean implements MeetingAttendDao{
	public static int PAGE_SIZE = 10;
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MeetingAttendDao#getAllAssociateMeeting(int, long)
	 */
	public List<Meeting> getAllAssociateMeeting(int pageNo,long userId){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql="from Meeting m inner join m.sysUser u inner join m.room r where" +
				" m in ( select ma.meeting from" +
				" MeetingAttend ma inner join ma.sysUser mu where mu.suId = ?)";
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query=null;
			query = sess.createQuery(hql).setLong(0, userId);
		
			// 设置分页信息
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<Meeting> attendantsList = new ArrayList<Meeting>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				
				Meeting m = (Meeting)obj[0];
				SysUser u = (SysUser) obj[1];
				Room r = (Room) obj[2];
				
				m.setSysUser(u);
				m.setRoom(r);
				attendantsList.add(m);
			}
			if(attendantsList.size() == 0)
				return null;
			return attendantsList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	public int getPageCount(int count, int pageSize) {
		return ( count + pageSize - 1) / pageSize;
	}
	
	public int getAllAssociateMeetingCount(long userId){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql="select count(ma.maId) from MeetingAttend ma where "+
					"ma.sysUser.suId = ?";
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户参与的会议数量
			Query query=null;
			query = sess.createQuery(hql).setLong(0, userId);
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
	 * @see com.icss.hit.bean.interfaces.MeetingAttendDao#getAllAssociateSysUser(long)
	 */
	public List<SysUser> getAllAssociateSysUser(long mtId){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql="from MeetingAttend ma inner join ma.sysUser u1 inner join ma.meeting mt" +
				" where mt.mtId = ?";
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			Query query= sess.createQuery(hql).setLong(0, mtId);
		
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<SysUser> attendeeList = new ArrayList<SysUser>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				SysUser u = (SysUser) obj[1];
				attendeeList.add(u);
			}
			if(attendeeList.size() == 0)
				return null;
			return attendeeList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	public Meeting getMeetingDetails(long mtId){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Meeting m inner join m.sysUser u inner join m.room r where "+
					"m.mtId = ?";
		try {
			tx = sess.beginTransaction();
			List l = sess.createQuery(hql).setLong(0, mtId).list();
			tx.commit();
			Iterator it = l.iterator();
			if (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Meeting m = (Meeting) obj[0];
				SysUser u = (SysUser) obj[1];
				Room r = (Room) obj[2];
				m.setSysUser(u);
				m.setRoom(r);
				return m;
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
}
