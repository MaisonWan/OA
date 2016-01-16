package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.MeetingOrder;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.RoomReg;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author ��ӱ��
 *
 */
public class MeetingOrderBean implements MeetingOrder{
	public static int PAGE_SIZE = 10;//Ĭ��ҳ���СΪ10
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MeetingOrder#getAvailableRoom(int, java.lang.String, int, java.lang.String, java.lang.String)
	 */
	public List<Room> getAvailableRoom(int pageNo,String rName,int containNum,String beginTime,String endTime){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql;
		if(rName==null&&containNum==-1&&beginTime==null&&endTime==null)
		{
			hql="from Room r";
			try {
				tx = sess.beginTransaction();
				// �������ʾҳ��ʼ���
				int offset = (pageNo - 1) * PAGE_SIZE;
				Query query = sess.createQuery(hql);
				
				// ���÷�ҳ��Ϣ
				query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
				List i = query.list();
				Iterator it = i.iterator();
				tx.commit();
				List<Room> availableRoomList = new ArrayList<Room>();
				while (it.hasNext()) {
					Object obj = (Object) it.next();
					Room r = (Room) obj;
					availableRoomList.add(r);
				}
				if(availableRoomList.size() == 0)
					return null;
				return availableRoomList;
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				return null;
			} finally {
				sess.close();
			}
		}
		//�������ݵ�ʱ��
		else
		{
			int m = 0;
			try {
				tx = sess.beginTransaction();
				// �������ʾҳ��ʼ���
				int offset = (pageNo - 1) * PAGE_SIZE;
				hql = getSearchHQL(rName,containNum);
				Query query = sess.createQuery(hql);
				query = createQuery(rName,containNum,query);
				System.out.println(hql);
				List i = query.list();
				Iterator it = i.iterator();
				
				//RoomID�ǵ�ѡ��
				Long[] roomIDs = new Long[i.size()];
				List<Room> availableRoomList = new ArrayList<Room>();
				while (it.hasNext()) {
					Object obj = (Object) it.next();
					Long r = (Long) obj;
					roomIDs[m++] = r;
				}
				//ʱ��û��ѡ��ʱ��
				if(beginTime==null)
				{
					for(int j = 0;j<roomIDs.length;j++)
					{
						String hqlRoom = "from Room r where r.RId=?";
						Query query1 = sess.createQuery(hqlRoom);
						// ���÷�ҳ��Ϣ
						query1.setFirstResult(offset).setMaxResults(PAGE_SIZE);
						query1.setLong(0, roomIDs[j]);
						Object Obj = query1.uniqueResult();
						Room room = (Room)Obj;
						availableRoomList.add(room);
					}
				}
				//��ʱ���ѡ���ʱ��
				else 
				{
					for(int j = 0;j<roomIDs.length;j++)
					{
						int s = findConflict(beginTime,endTime,roomIDs[j]);
						sess = HibernateSessionFactory.getSession();
						tx = sess.beginTransaction();
						if(s==0)
						{
							String hqlRoom = "from Room r where r.RId=?";
							Query query1 = sess.createQuery(hqlRoom);
							// ���÷�ҳ��Ϣ
							query1.setFirstResult(offset).setMaxResults(PAGE_SIZE);
							query1.setLong(0, roomIDs[j]);
							Object Obj = query1.uniqueResult();
							Room room = (Room)Obj;
							availableRoomList.add(room);
						}
					}
					
				}
				//�����ύ
				tx.commit();
				return availableRoomList;
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				return null;
			} finally {
				sess.close();
			}
		}
	}
	
	
	public int findConflict(String beginTime, String endTime,long roomNO) {
		// TODO Auto-generated method stub
		int count = 0;
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		System.out.println(beginTime);
		String hqlBegin = "select count(r.rrId) from RoomReg r inner join r.room m where m.RId =? and r.rrPass = '1' and r.rrBegintime between to_date(?,'yyyy-mm-dd hh24:mi:ss') and to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		String hqlEnd = "select count(r.rrId) from RoomReg r inner join r.room m where m.RId =? and r.rrPass = '1' and r.rrEndtime between to_date(?,'yyyy-mm-dd hh24:mi:ss') and to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		String hqlMidd = "select count(r.rrId) from RoomReg r inner join r.room m where m.RId =? and r.rrPass = '1' and r.rrBegintime < to_date(?,'yyyy-mm-dd hh24:mi:ss') and r.rrEndtime > to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		String hqlOut = "select count(r.rrId) from RoomReg r inner join r.room m where m.RId =? and r.rrPass = '1' and r.rrBegintime > to_date(?,'yyyy-mm-dd hh24:mi:ss') and r.rrEndtime < to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		try {
			//�ж��ǲ������ǵ�ʱ��������֮��
			tx = sess.beginTransaction();
			//��һ�����
			Query query = sess.createQuery(hqlBegin).setLong(count++, roomNO).setString(count++, beginTime).setString(count++, endTime);
			Object o1 = query.uniqueResult();  //��ʼʱ���������֮��
			//�ڶ������
			count = 0;
			Query query1 = sess.createQuery(hqlEnd).setLong(count++, roomNO).setString(count++, beginTime).setString(count++, endTime);
			Object o2 = query1.uniqueResult();  //����ʱ���������֮��
			//���������
			count = 0;
			Query query2 = sess.createQuery(hqlMidd).setLong(count++, roomNO).setString(count++, beginTime).setString(count++, endTime);
			Object o3 = query2.uniqueResult();  //�ڼ�������֮��
			//���������
			count = 0;
			Query query3 = sess.createQuery(hqlOut).setLong(count++, roomNO).setString(count++, beginTime).setString(count++, endTime);
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
	
	
	/**
	 * ���������õ�HQL���
	 * @param rName ����������
	 * @param containNum ��������
	 * @param beginTime ��ʼʱ��
	 * @param endTime ����ʱ��
	 * @return HQL���
	 */
	//��������Ϊ-1ʱ�൱��û��
	//����Ķ��������˵�
	private String getSearchHQL(String rName,long containNum){
		if(rName!=null||containNum!=-1)
		{
			//�в�ѯ������ʱ�����ڲ�ѯ�ķ�������
			String hql = "select r.RId from Room r where ";
			//���������ĵط���Ϊ�յ�ʱ��
			if(containNum!=-1)
			{
				hql+="r.RContain>=? ";
				//�����뷿��ŵĵط���Ϊ�յ�ʱ��
				if(rName != null)
				{
					hql+="and r.RName like ? ";
				}
			}
			//���������ĵط�Ϊ�յ�ʱ��
			else
			{
				//�����뷿��ŵĵط���Ϊ�յ�ʱ��
				if(rName != null)
				{
					hql+="r.RName like ? ";
				}
			}
			return hql;
		}
		else
		{
			String hql = "select r.RId from Room r";
			return hql;
		}	
	}
	
	
	private Query createQuery(String rName,long containNum,Query query){
		int count = 0;
		if(rName!=null||containNum!=-1)
		{
			if(containNum!=-1)
			{
				query.setLong(count++, containNum);
				//�����뷿��ŵĵط���Ϊ�յ�ʱ��
				if(rName != null)
				{
					query.setString(count++, "%"+rName+"%");
				}
			}
			//���������ĵط�Ϊ�յ�ʱ��
			else
			{
				//�����뷿��ŵĵط���Ϊ�յ�ʱ��
				if(rName != null)
				{
					query.setString(count++, "%"+rName+"%");
				}
			}
			return query;
		}
		return query;
	}
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MeetingOrder#getAvailableRoomCount(java.lang.String, int, java.lang.String, java.lang.String)
	 */
	public int getAvailableRoomCount(String rName,int containNum,String beginTime,String endTime){
		
		if(rName==null&&containNum==-1&&beginTime==null&&endTime==null)
		{
			Session sess = HibernateSessionFactory.getSession();
			Transaction tx = null;
			String hql;
			hql="select count(r.RId) from Room r";
			try {
				tx = sess.beginTransaction();
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
		else
		{
			Session sess = HibernateSessionFactory.getSession();
			Transaction tx = null;
			String hql;
			int m = 0;
			try {
				tx = sess.beginTransaction();
				hql = getSearchHQL(rName,containNum);
				Query query = sess.createQuery(hql);
				query = createQuery(rName,containNum,query);
				List i = query.list();
				Iterator it = i.iterator();
				
				//RoomID�ǵ�ѡ��
				Long[] roomIDs = new Long[i.size()];
				List<Room> availableRoomList = new ArrayList<Room>();
				while (it.hasNext()) {
					Object obj = (Object) it.next();
					Long r = (Long) obj;
					roomIDs[m++] = r;
				}
				//ʱ��û��ѡ��ʱ��
				if(beginTime==null)
				{
					for(int j = 0;j<roomIDs.length;j++)
					{
						String hqlRoom = "from Room r where r.RId=?";
						Query query1 = sess.createQuery(hqlRoom);

						query1.setLong(0, roomIDs[j]);
						Object Obj = query1.uniqueResult();
						Room room = (Room)Obj;
						availableRoomList.add(room);
					}
				}
				//��ʱ���ѡ���ʱ��
				else 
				{
					for(int j = 0;j<roomIDs.length;j++)
					{
						int s = findConflict(beginTime,endTime,roomIDs[j]);
						sess = HibernateSessionFactory.getSession();
						tx = sess.beginTransaction();
						if(s==0)
						{
							String hqlRoom = "from Room r where r.RId=?";
							Query query1 = sess.createQuery(hqlRoom);
							
							query1.setLong(0, roomIDs[j]);
							Object Obj = query1.uniqueResult();
							Room room = (Room)Obj;
							availableRoomList.add(room);
						}
					}
					
				}
				//�����ύ
				tx.commit();
				return availableRoomList.size();
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				return 0;
			} finally {
				sess.close();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MeetingOrder#getPageCount(int, int)
	 */
	public int getPageCount(int count, int pageSize) {
		return ( count + pageSize - 1) / pageSize;
	}
	
}
