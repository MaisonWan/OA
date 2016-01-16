package com.icss.hit.bean;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.FindAllRoomDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.SysDept;

public class FindAllRoom implements FindAllRoomDao {

	public List<Room> getAllRoom() {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = sess.beginTransaction();
		List<Room> list = null;
		try{
			list = sess.createQuery("from Room").list();
			tx.commit();
			return list;
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
			return null;
		}
		finally
		{
			sess.close();
		}
	}

}
