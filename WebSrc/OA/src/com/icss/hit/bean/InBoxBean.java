package com.icss.hit.bean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.InBox;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Message;
import com.icss.hit.hibernate.vo.ReceiverInfo;
import com.icss.hit.hibernate.vo.Schedule;
import com.icss.hit.hibernate.vo.SysUser;
public class InBoxBean implements InBox{
	public static int PAGE_SIZE = 10;
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.InBox#getInboxMessages(long, int)
	 */
	public List<ReceiverInfo> getInBoxMessages(String searchType,String content,long receiverId,int pageNo){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql=getSearchHQL(searchType,content);
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query=null;
			// 如果搜索内容为空，默认为搜索全部
			if(searchType==null||content==null||content.equals("")){
				query = sess.createQuery(hql).setLong(0, receiverId);
			}
			else{
				query = sess.createQuery(hql).setLong(0, receiverId).setString(1, "%"+content+"%");
			}
			
			// 设置分页信息
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<ReceiverInfo> inBoxList = new ArrayList<ReceiverInfo>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				ReceiverInfo r = (ReceiverInfo) obj[0];
				Message m = (Message) obj[1];
				SysUser u = (SysUser) obj[2];
				
				// 将role，dept，position设置为user的属性
				r.setMessage(m);
				r.setSysUser(u);
				inBoxList.add(r);
			}
			if(inBoxList.size() == 0)
				return null;
			return inBoxList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	private String getSearchHQL(String searchType,String content){
		String hql = "from ReceiverInfo r inner join r.message m inner join r.sysUser s inner join m.sysUser ms "+
			"where r.riDelete = '0' and r.riBox ='3' and s.suId=? ";
		if(searchType==null || content==null || content.equals("")){
			return hql;
		}
		if(searchType.equals("name")){
			hql+="and ms.suUsername like ?";
		}
		else if(searchType.equals("title")){
			hql+="and m.msTitle like ?";
		}
		return hql;
	}
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.InBox#getInBoxMessagesCount(long)
	 */
	public int getInBoxMessagesCount(String searchType,String content,long receiverId){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql="select count(r.riId)"+getSearchHQL(searchType,content);
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户添加的日程安排的数量
			Query query=null;
			List list=null;
			// 如果搜索内容为空，默认为搜索全部
			if(searchType == null || content == null || content.equals("")){
				query = sess.createQuery(hql).setLong(0, receiverId);
			}
			else{
				query = sess.createQuery(hql).setLong(0, receiverId).setString(1, "%"+content+"%");
			}
			
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
	 * @see com.icss.hit.bean.interfaces.InBox#getPageCount(int, int)
	 */
	public int getPageCount(int count, int pageSize) {
		return ( count + pageSize - 1) / pageSize;
	}
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.InBox#getReceiverInfo(long, long)
	 */
	public ReceiverInfo getReceiverInfo(long riId,long userId){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from ReceiverInfo r inner join r.message m inner join r.sysUser s inner join m.sysUser ms "+
					 "where r.riDelete = '0' and r.riBox ='3' and s.suId=? and r.riId=?";
		try {
			tx = sess.beginTransaction();

			List l = sess.createQuery(hql).setLong(0, userId).setLong(1, riId).list();
			tx.commit();
			Iterator it = l.iterator();
			if (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				ReceiverInfo ri = (ReceiverInfo) obj[0];
				Message m = (Message) obj[1];
				SysUser ru = (SysUser) obj[2];
				SysUser mu = (SysUser) obj[3];
				m.setSysUser(ru);
				ri.setMessage(m);
				ri.setSysUser(mu);
			
				return ri;
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
	 * @see com.icss.hit.bean.interfaces.InBox#update(com.icss.hit.hibernate.vo.ReceiverInfo)
	 */
	public boolean update(ReceiverInfo ri) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// 更新指定的日程
			sess.update(ri);
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
	
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.InBox#delete(com.icss.hit.hibernate.vo.ReceiverInfo)
	 */
	public boolean delete(ReceiverInfo ri) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// 更新指定的日程
			sess.delete(ri);
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
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.InBox#delete(long, long)
	 */
	public boolean delete(long riId, long userId) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "delete ReceiverInfo r "+
					 "where r.riDelete = '1' and r.message.msDelete='1' and r.riBox ='3' and r.sysUser.suId=? and r.riId=?";
		try {
			tx = sess.beginTransaction();
			// 删除指定ID的日程
			Query query = sess.createQuery(hql).setLong(0, userId).setLong(1, riId);
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
}
