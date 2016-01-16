package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.outputGraphDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.RoomReg;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author 赵颖申
 *
 */
public class OutputGraphBean implements outputGraphDao {

	public List<Integer> getRoomsRegistCount(String year,String month){
		// TODO Auto-generated method stub
		List<Integer> dayList = new ArrayList<Integer>();
		int[] monthDay = new int[13];
		System.out.println(month);
		int Month = Integer.parseInt(month);
		int Year = Integer.parseInt(year);
		Date beginDate = new Date();
		Date endDate = new Date();
		monthDay[0] = 0;
		monthDay[1] = 31;monthDay[2] = 28;monthDay[3] = 31;monthDay[4] = 30;monthDay[5] = 31;monthDay[6] = 30;
		monthDay[7] = 31;monthDay[8] = 31;monthDay[9] = 30;monthDay[10] = 31;monthDay[11] = 30;monthDay[12] = 31;
		
		//一个月的天数的集合
		for(int i=1;i<= monthDay[Month];i++)
		{
			GregorianCalendar calendar = new GregorianCalendar(Year,Month-1,i,0,0,0);
			beginDate = calendar.getTime();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			endDate = calendar.getTime();
			Session sess = HibernateSessionFactory.getSession();
			Transaction tx = null;
			String hql = "select count(rr.rrId) from RoomReg rr where rr.rrPass='1' and rr.rrBegintime between ? and ?";
			try {
				tx = sess.beginTransaction();
				// 返回系统中用户的数量
				Object o = sess.createQuery(hql).setDate(0, beginDate).setDate(1, endDate).uniqueResult();
				tx.commit();
				//将数据添加到List中去，以便查找
				dayList.add(Integer.parseInt(o.toString()));
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				return null;
			} finally {
				sess.close();
			}
		}
		return dayList;
	}
	
	
	public int getRoomCount(int Year,int Month)
	{
		Date beginDate = new Date();
		Date endDate = new Date();
		GregorianCalendar calendar = new GregorianCalendar(Year,Month-1,1,0,0,0);
		//开始时间
		beginDate = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		//结束时间
		endDate = calendar.getTime();
		
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(rr.rrId) from RoomReg rr where rr.rrPass='1' and rr.rrBegintime between ? and ?";
		
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Object o = sess.createQuery(hql).setDate(0, beginDate).setDate(1, endDate).uniqueResult();
			tx.commit();
			//将数据添加到List中去，以便查找
			return (Integer.parseInt(o.toString()));
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			sess.close();
		}
	}
	
	public List<RoomReg> getRoomRegs(int Year,int Month)
	{
		Date beginDate = new Date();
		Date endDate = new Date();
		GregorianCalendar calendar = new GregorianCalendar(Year,Month-1,1,0,0,0);
		GregorianCalendar calendar1 = new GregorianCalendar(Year,Month,1,0,0,0);
		//开始时间
		beginDate = calendar.getTime();
		//calendar.add(Calendar.MONTH, 1);
		//结束时间
		endDate = calendar1.getTime();
		
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from RoomReg rr inner join rr.room r inner join rr.sysUser s where rr.rrPass='1' and rr.rrBegintime between ? and ?";
		
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			List list = sess.createQuery(hql).setDate(0, beginDate).setDate(1, endDate).list();
			tx.commit();
			Iterator it = list.iterator();
			//用于保存查出来的数据
			List<RoomReg> roomReglist = new ArrayList<RoomReg>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				RoomReg u = (RoomReg) obj[0];
				Room r = (Room) obj[1];
				SysUser s = (SysUser) obj[2];
				// 将room，sysUser设置为RoomReg的属性
				u.setRoom(r);
				u.setSysUser(s);
				roomReglist.add(u);
			}
			//将数据添加到List中去，以便查找
			return roomReglist;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
}
