package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.manageRoomDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author 赵颖申
 *
 */
public class ManageRoomBean implements manageRoomDao {
	
	public static int PAGE_SIZE = 10;
	
	public List<Room> getAllRooms(int pageNo) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		int offset = (pageNo - 1) * PAGE_SIZE;
		String hql = "from Room r";
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			Query query =sess.createQuery(hql);
			// 设置分页信息
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List l = query.list();
			tx.commit();
			Iterator it = l.iterator();
			List<Room> list = new ArrayList<Room>();
			while (it.hasNext()) {
				Object obj = (Object) it.next();
				Room u = (Room) obj;
				list.add(u);
			}
			if( list.size() == 0 )
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
	
	
	public int getPageCount(int count, int pageSize) {
		// TODO Auto-generated method stub
		return ( count + pageSize - 1) / pageSize;
	}
	
	public int getRoomCount() {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(r.RId) from Room r";
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Query query = sess.createQuery(hql);
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
	
	public long isExist(long roomID)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(rr.rrId) from RoomReg rr inner join rr.room r where r.RId=?";
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Query query = sess.createQuery(hql).setLong(0, roomID);
			Object o = query.uniqueResult();
			tx.commit();
			return Long.parseLong(o.toString());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			sess.close();
		}
	}
	
	public boolean deleteRoom(long roomID)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			// 删除指定ID的用户
			sess.delete((Room) sess.get(Room.class, roomID));
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
	
	public Room readRoom(long roomID)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Room r where r.RId=?";
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Query query = sess.createQuery(hql).setLong(0, roomID);
			Object o = query.uniqueResult();
			Room room = (Room)o;
			tx.commit();
			return room;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	public boolean saveRoom(Room room)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			
			sess.saveOrUpdate(room);
			
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
