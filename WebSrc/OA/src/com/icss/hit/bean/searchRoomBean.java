package com.icss.hit.bean;

import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.searchRoomDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.ReceiverInfo;
import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.RoomReg;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author 赵颖申
 *
 */
public class searchRoomBean implements searchRoomDao {

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.searchRoomDao#findConflict(java.util.Date, java.util.Date, long)
	 */
	public int findConflict(Date beginTime, Date endTime,long roomNO) {
		// TODO Auto-generated method stub
		int count = 0;
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		System.out.println(beginTime.toLocaleString());
		String hqlBegin = "select count(r.rrId) from RoomReg r inner join r.room m where m.RId =? and r.rrPass = '1' and r.rrBegintime between to_date(?,'yyyy-mm-dd hh24:mi:ss') and to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		String hqlEnd = "select count(r.rrId) from RoomReg r inner join r.room m where m.RId =? and r.rrPass = '1' and r.rrEndtime between to_date(?,'yyyy-mm-dd hh24:mi:ss') and to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		String hqlMidd = "select count(r.rrId) from RoomReg r inner join r.room m where m.RId =? and r.rrPass = '1' and r.rrBegintime < to_date(?,'yyyy-mm-dd hh24:mi:ss') and r.rrEndtime > to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		String hqlOut = "select count(r.rrId) from RoomReg r inner join r.room m where m.RId =? and r.rrPass = '1' and r.rrBegintime > to_date(?,'yyyy-mm-dd hh24:mi:ss') and r.rrEndtime < to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		try {
			//判断是不是他们的时间夹在这个之间
			tx = sess.beginTransaction();
			//第一种情况
			Query query = sess.createQuery(hqlBegin).setLong(count++, roomNO).setString(count++, beginTime.toLocaleString()).setString(count++, endTime.toLocaleString());
			Object o1 = query.uniqueResult();  //开始时间夹在两个之间
			//第二种情况
			count = 0;
			Query query1 = sess.createQuery(hqlEnd).setLong(count++, roomNO).setString(count++, beginTime.toLocaleString()).setString(count++, endTime.toLocaleString());
			Object o2 = query1.uniqueResult();  //结束时间夹在两个之间
			//第三种情况
			count = 0;
			Query query2 = sess.createQuery(hqlMidd).setLong(count++, roomNO).setString(count++, beginTime.toLocaleString()).setString(count++, endTime.toLocaleString());
			Object o3 = query2.uniqueResult();  //在夹在两个之间
			//第四种情况
			count = 0;
			Query query3 = sess.createQuery(hqlOut).setLong(count++, roomNO).setString(count++, beginTime.toLocaleString()).setString(count++, endTime.toLocaleString());
			Object o4 = query3.uniqueResult();  //在两个外面
			tx.commit();
			//转化成为Integer型的进行判断
			int a = Integer.parseInt(o1.toString());
			int b = Integer.parseInt(o2.toString());
			int c = Integer.parseInt(o3.toString());
			int d = Integer.parseInt(o4.toString());
			if(a == 0&&b == 0&&c == 0&&d == 0)return 0;//没有发生冲突的项目
			else return 1;                             //有发生冲突的项目
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;								   //发生数据库的查询错误或者转化出错
		} finally {
			sess.close();
		}
	}
	
	public int saveRoomReg(RoomReg roomReg) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			
			 sess.save(roomReg);
			
			tx.commit();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			sess.close();
		}
	}
}
