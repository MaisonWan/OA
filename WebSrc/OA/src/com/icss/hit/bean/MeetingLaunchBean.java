package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.meetingLaunchDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Meeting;
import com.icss.hit.hibernate.vo.MeetingAttend;
import com.icss.hit.hibernate.vo.Message;
import com.icss.hit.hibernate.vo.ReceiverInfo;
import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author 赵颖申
 * 用于发起会议使用的
 */
public class MeetingLaunchBean implements meetingLaunchDao {

	public int getConflict(String beginTime, String endTime,long uid, String meetingRoom) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		long rid = Long.parseLong(meetingRoom);
		int timeOut=0;
		String hql1 = "select count(rr.rrId) from RoomReg rr inner join rr.sysUser s inner join rr.room r where s.suId=? and r.RId=? and rr.rrBegintime<=to_date(?,'yyyy-mm-dd hh24:mi:ss') and rr.rrEndtime>=to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			Query query = sess.createQuery(hql1).setLong(0, uid).setLong(1, rid).setString(2, beginTime).setString(3, endTime);
			Object obj1 = query.uniqueResult();
			timeOut = Integer.parseInt(obj1.toString());
			
			tx.commit();
			if( timeOut>0 )
			{
				return 0;     //没有冲突
			}
			else return 1;    //有冲突
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;        //有错误
		} finally {
			sess.close();
		}
	}
	
	public long saveMeetingUsers(MeetingAttend meetingAttend) {
		// TODO Auto-generated method stub
		String mesID = null;
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			
			 sess.save(meetingAttend);
			
			tx.commit();
			return meetingAttend.getMaId();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			sess.close();
		}
	}

	public long saveMeetingMessage(Meeting meeting) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			
			sess.save(meeting);
			
			tx.commit();
			return meeting.getMtId();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			sess.close();
		}
	}
	
	public long getAccess(long uid){
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx=null;
		String hql="select count(rr.rrId) from RoomReg rr inner join rr.sysUser s where s.suId=? and rr.rrPass='1'";
		try {
			tx = sess.beginTransaction();
			Query query = sess.createQuery(hql).setLong(0, uid);
			tx.commit();
			Object o = query.uniqueResult();
			return Long.parseLong(o.toString());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			sess.close();
		}
	}
	
	public List<Room> getRooms(long uid)
	{
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx=null;
		List<Room> rooms= new ArrayList<Room>();
		String hql="select r.RId from RoomReg rr inner join rr.sysUser s inner join rr.room r where s.suId=? and rr.rrPass='1'";
		String hql1 = "from Room r where r.RId=?";
		try {
			tx = sess.beginTransaction();
			Query query = sess.createQuery(hql).setLong(0, uid);
			List l = query.list();
			Iterator it = l.iterator();
			while (it.hasNext()) {
				Object obj = (Object) it.next();
				long rid = (Long)obj;
				System.out.println(rid);
				Room room = new Room();
				Query query1 = sess.createQuery(hql1).setLong(0, rid);
				Object o=  query1.uniqueResult();
				room=(Room)o;
				System.out.println(room);
				rooms.add(room);
			}
			tx.commit();
			return rooms;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	
	public int timeConflict(String beginTime, String endTime,long uid, String meetingRoom) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		long rid = Long.parseLong(meetingRoom);
		int timeOut=0;
		int sum=0;
		String hqlNull ="select count(rr.rrId) from RoomReg rr inner join rr.sysUser s inner join rr.room r " +
				"where r.RId=? and rr.rrPass = '1'";
		String hql1 = "select count(rr.rrId) from RoomReg rr inner join rr.sysUser s inner join" +
				" rr.room r where r.RId=? and rr.rrPass = '1' and (rr.rrBegintime>=to_date(?,'yyyy-mm-dd hh24:mi:ss')" +
				" or rr.rrEndtime<=to_date(?,'yyyy-mm-dd hh24:mi:ss'))";
		
		try {
			tx = sess.beginTransaction();
			Query query1 = sess.createQuery(hqlNull).setLong(0, rid);
			Object obj2 = query1.uniqueResult();
			sum = Integer.parseInt(obj2.toString());
			// 计算出显示页起始编号
			Query query = sess.createQuery(hql1).setLong(0, rid).setString(1, endTime).setString(2, beginTime);
			Object obj1 = query.uniqueResult();
			timeOut = Integer.parseInt(obj1.toString());
				System.out.println(sum +"----------"+timeOut);
			tx.commit();
			if( timeOut==sum )
			{
				return 0;     //没有冲突
			}
			else return 1;    //有冲突
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;        //有错误
		} finally {
			sess.close();
		}
	}
}
