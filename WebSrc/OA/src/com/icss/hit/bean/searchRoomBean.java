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
 * @author ��ӱ��
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
			//�ж��ǲ������ǵ�ʱ��������֮��
			tx = sess.beginTransaction();
			//��һ�����
			Query query = sess.createQuery(hqlBegin).setLong(count++, roomNO).setString(count++, beginTime.toLocaleString()).setString(count++, endTime.toLocaleString());
			Object o1 = query.uniqueResult();  //��ʼʱ���������֮��
			//�ڶ������
			count = 0;
			Query query1 = sess.createQuery(hqlEnd).setLong(count++, roomNO).setString(count++, beginTime.toLocaleString()).setString(count++, endTime.toLocaleString());
			Object o2 = query1.uniqueResult();  //����ʱ���������֮��
			//���������
			count = 0;
			Query query2 = sess.createQuery(hqlMidd).setLong(count++, roomNO).setString(count++, beginTime.toLocaleString()).setString(count++, endTime.toLocaleString());
			Object o3 = query2.uniqueResult();  //�ڼ�������֮��
			//���������
			count = 0;
			Query query3 = sess.createQuery(hqlOut).setLong(count++, roomNO).setString(count++, beginTime.toLocaleString()).setString(count++, endTime.toLocaleString());
			Object o4 = query3.uniqueResult();  //����������
			tx.commit();
			//ת����ΪInteger�͵Ľ����ж�
			int a = Integer.parseInt(o1.toString());
			int b = Integer.parseInt(o2.toString());
			int c = Integer.parseInt(o3.toString());
			int d = Integer.parseInt(o4.toString());
			if(a == 0&&b == 0&&c == 0&&d == 0)return 0;//û�з�����ͻ����Ŀ
			else return 1;                             //�з�����ͻ����Ŀ
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;								   //�������ݿ�Ĳ�ѯ�������ת������
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
