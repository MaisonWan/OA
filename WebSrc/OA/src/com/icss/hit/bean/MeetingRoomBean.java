package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.MeetingRoomDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.RoomReg;
import com.icss.hit.hibernate.vo.SysUser;

public class MeetingRoomBean implements MeetingRoomDao {
	public static int PAGE_SIZE = 10;//Ĭ��ҳ���СΪ10

	
	/**
	 * @param room_id ������ID
	 * @param room_name ����������
	 * @return true��ʾ������ false��ʾû������
	 */
	private boolean checkExistence( String room_name){
		
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(rm.RId) from Room rm where rm.RName = ?";
		try {
			tx = sess.beginTransaction();
			//�����ظ��Ļ���������
			Object o = sess.createQuery(hql).setString(0, room_name).uniqueResult();
			tx.commit();
			if(Integer.parseInt(o.toString()) > 0 ){
				return true;//������
			}
			else{
				return false;//û������
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return true;//�������Ļ���Ĭ��Ϊ�������� �»����ҾͲ��벻��ȥ
		} finally {
			sess.close();
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MeetingRoomDao#addRoom(com.icss.hit.hibernate.vo.Room)
	 */
	public int addRoom(Room room) {
		// TODO Auto-generated method stub
		
		boolean eon = checkExistence(room.getRName());//����Ƿ����ͬ�����ļ���
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// ����ϵͳ���û�������
			if(eon){
				return 1;//������
			}
			else{
				sess.save(room);
				tx.commit();
				return 0;//�ɹ�ִ��
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return 2;//δ֪����
		} finally {
			sess.close();
		}
		

	}

	
	

	
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MeetingRoomDao#deleteRoom(long)
	 */
	public boolean deleteRoom(long room_id) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction delRmTrans = null;
		try {
			delRmTrans = sess.beginTransaction();
			// ɾ��ָ��ID�Ļ�����
			sess.delete((MeetingRoomDao) sess.get(MeetingRoomDao.class, room_id));
			delRmTrans.commit();
			return true;
		} catch (Exception e) {
			delRmTrans.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MeetingRoomDao#getAllRoomInfo()
	 */
	public List<Room> getAllRoomInfo() {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Room rm";
		try {
			tx = sess.beginTransaction();
			//int offset = (pageNo - 1) * PAGE_SIZE;
			List l = sess.createQuery(hql).list();
			tx.commit();
			Iterator it = l.iterator();
			List<Room> list = new ArrayList<Room>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Room r = (Room) obj[0];
				list.add(r);
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
	 * @see com.icss.hit.bean.interfaces.MeetingRoomDao#getAllUserInfoByPage(int)
	 */
	public List<Room> getAllUserInfoByPage(int pageNo) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Room rm";
		try {
			tx = sess.beginTransaction();
			// �������ʾҳ��ʼ���
			int offset = (pageNo - 1) * PAGE_SIZE;
			List l = sess.createQuery(hql).setFirstResult(offset)
					.setMaxResults(PAGE_SIZE).list();
			tx.commit();
			Iterator it = l.iterator();
			List<Room> list = new ArrayList<Room>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Room r = (Room) obj[0];
				
				list.add(r);
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
	 * @see com.icss.hit.bean.interfaces.MeetingRoomDao#getPageCount(int, int)
	 */
	public int getPageCount(int count, int pageSize) {
		// TODO Auto-generated method stub
		return (count + pageSize - 1) / pageSize;
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MeetingRoomDao#getRoomAmount()
	 */
	public int getRoomAmount() {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(rm.RId) from Room rm";
		try {
			tx = sess.beginTransaction();
			// ���ػ����ҵ�����
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

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MeetingRoomDao#getRoomInfo()
	 */
	public Room getRoomInfo(long room_id) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Room rm where rm.RId = ?";
		try {
			tx = sess.beginTransaction();
			// �������ʾҳ��ʼ���
			List l = sess.createQuery(hql).setLong(0, room_id).list();
			tx.commit();
			Iterator it = l.iterator();
			if (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Room r = (Room) obj[0];
				return r;
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
	 * @see com.icss.hit.bean.interfaces.MeetingRoomDao#updateRoomInfo(com.icss.hit.hibernate.vo.Room)
	 */
	public int updateRoomInfo(Room room) {
		// TODO Auto-generated method stub
		boolean eon = checkExistence( room.getRName());//����Ƿ����ͬ�����ļ���
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			if(eon){
				return 1;//������
			}
			else{
				sess.update(room);
				tx.commit();
				return 0;//�ɹ�ִ��
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return 2;//δ֪����
		} finally {
			sess.close();
		}
		
	}
	
	public List<RoomReg> getAllUnsettledRoom(){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from RoomReg rr inner join rr.room r inner join rr.sysUser u where rr.rrPass = null";
		try {
			tx = sess.beginTransaction();
			List l = sess.createQuery(hql).list();
			tx.commit();
			Iterator it = l.iterator();
			List<RoomReg> list = new ArrayList<RoomReg>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				RoomReg rr = (RoomReg) obj[0];
				Room r = (Room) obj[1];
				SysUser u = (SysUser) obj[2];
				rr.setRoom(r);
				rr.setSysUser(u);
				list.add(rr);
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
	
	public int getAllUnsettledRoomCount(){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(rr.rrId) from RoomReg rr where rr.rrPass = null";
		try {
			tx = sess.beginTransaction();
			// ���ػ����ҵ�����
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
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MeetingRoomDao#getRoomReg(long)
	 */
	public RoomReg getRoomRegByID(long rrId){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql ="from RoomReg rr inner join rr.sysUser u inner join rr.room r where rr.rrId = ?";
		try {
			tx = sess.beginTransaction();
			List l = sess.createQuery(hql).setLong(0, rrId).list();
			tx.commit();
			Iterator it = l.iterator();
			if (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				RoomReg rr = (RoomReg) obj[0];
				SysUser u = (SysUser) obj[1];
				Room r = (Room) obj[2];
				rr.setSysUser(u);
				rr.setRoom(r);
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
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MeetingRoomDao#update(com.icss.hit.hibernate.vo.RoomReg)
	 */
	public boolean update(RoomReg rr){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// ����ָ���Ļ����������¼
			sess.update(rr);
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
