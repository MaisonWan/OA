package com.icss.hit.bean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.RoomDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Message;
import com.icss.hit.hibernate.vo.ReceiverInfo;
import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.RoomReg;
import com.icss.hit.hibernate.vo.SysUser;

public class RoomBean implements RoomDao{
	public static int PAGE_SIZE = 10;
	public Room getRoom( long id ){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			Room room = (Room)sess.get(Room.class, id);
			return room;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.RoomDao#getRoomRegInfo(long, int)
	 */
	public List<RoomReg> getRoomRegInfo(long userId,int pageNo){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql="from RoomReg rr inner join rr.sysUser u inner join rr.room r where "+
					"u.suId =?";
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query=sess.createQuery(hql).setLong(0, userId);
			// 设置分页信息
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<RoomReg> room = new ArrayList<RoomReg>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				RoomReg rr = (RoomReg) obj[0];
				SysUser u = (SysUser) obj[1];
				Room r = (Room) obj[2];
				// 将role，dept，position设置为user的属性
				rr.setRoom(r);
				rr.setSysUser(u);
				room.add(rr);
			}
			if(room.size() == 0)
				return null;
			return room;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.RoomDao#getRoomRegInfoCount(long)
	 */
	public int getRoomRegInfoCount(long userId){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql="select count(rr.rrId) from RoomReg rr inner join rr.sysUser u where "+
					"u.suId =?";
		try {
			tx = sess.beginTransaction();
			Query query = sess.createQuery(hql).setLong(0,userId);
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
	 * @see com.icss.hit.bean.interfaces.RoomDao#getPageCount(int, int)
	 */
	public int getPageCount(int count, int pageSize) {
		return ( count + pageSize - 1) / pageSize;
	}
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.RoomDao#delete(long, long)
	 */
	public boolean delete(long rrId, long userId) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "delete RoomReg rr "+
					 "where rr.rrPass = null and rr.rrId = ? and rr.sysUser.suId=?";
		try {
			tx = sess.beginTransaction();
			// 删除指定ID的会议室申请记录
			Query query = sess.createQuery(hql).setLong(0, rrId).setLong(1,userId);
			int re = query.executeUpdate();
			tx.commit();
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
	
	public RoomReg getRoomRegDetails(long rrId,long userId){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql ="from RoomReg rr inner join rr.sysUser u inner join rr.room r where "+
					"u.suId =? and rr.rrId = ?";
		try {
			tx = sess.beginTransaction();

			List l = sess.createQuery(hql).setLong(0, userId).setLong(1, rrId).list();
			tx.commit();
			Iterator it = l.iterator();
			if (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				RoomReg rr = (RoomReg) obj[0];
				SysUser u = (SysUser) obj[1];
				Room r = (Room) obj[2];
				rr.setRoom(r);
				rr.setSysUser(u);
				return rr;
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
