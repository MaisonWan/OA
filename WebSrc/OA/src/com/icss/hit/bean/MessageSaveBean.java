package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.MessageDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Message;
import com.icss.hit.hibernate.vo.MessageReceivers;
import com.icss.hit.hibernate.vo.ReceiverInfo;

import com.icss.hit.hibernate.vo.SysUser;

import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysPosition;
import com.icss.hit.hibernate.vo.SysRole;



/**
 * @author 赵颖申
 *
 */
public class MessageSaveBean implements MessageDao {

	public static int PAGE_SIZE = 10;
	
	public long saveReceiveUsers(ReceiverInfo receiver) {
		// TODO Auto-generated method stub
		String mesID = null;
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			//保存receiver
			 sess.save(receiver);
			
			tx.commit();
			return receiver.getRiId();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			sess.close();
		}
	}

	public long saveReceiveMessage(Message message) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			//保存message
			sess.save(message);
			
			tx.commit();
			return message.getMsId();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			sess.close();
		}
	}
	
	public String[] getReceiverInfo(Message message)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select u.suUsername from sysUser u where u.suId = ?";
		try {
			int m = 0;
			tx = sess.beginTransaction();
			//用户多少
			int  size = message.getReceiverInfos().size();
			String[] receivers = new String[size];
			Iterator it  = message.getReceiverInfos().iterator();
			while (it.hasNext()) {
				ReceiverInfo obj = (ReceiverInfo) it.next();
				Query query = sess.createQuery(hql).setLong(0, obj.getRiId());
				//产生结果
				Object o = query.uniqueResult();
				String username = o.toString();
				receivers[m++] = username;
			}
			//发挥数组
			return receivers;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	public List<MessageReceivers> getAllDraftMessage(long uid,int pageNo)
	{
		
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// 得到构造好的HQL语句
		String hql = "from Message m inner join m.sysUser u where u.suId = ? and m.msBox='2'";
		String hqlUser = "select u.suUsername from SysUser u where u.suId = ?";
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql).setLong(0, uid);
			int m = 0;
			// 设置分页信息
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			
			
			List i = query.list();
			Iterator it = i.iterator();
			List<MessageReceivers> messageReceivers = new ArrayList<MessageReceivers>();
			while (it.hasNext()) {
				m = 0;
				MessageReceivers draftMessage = new MessageReceivers();
				Object[] obj = (Object[]) it.next();
				Message message = (Message) obj[0];
				SysUser r = (SysUser) obj[1];
				// 将sysUser设置为Message的属性
				message.setSysUser(r);
				//设置用户收件人
				//用户多少
				int  size = message.getReceiverInfos().size();
				System.out.println(size);
				String[] receivers = new String[size];
				Iterator receiver  = message.getReceiverInfos().iterator();
				while (receiver.hasNext()) {
					ReceiverInfo receiverInfo = (ReceiverInfo) receiver.next();
					query = sess.createQuery(hqlUser).setLong(0, receiverInfo.getSysUser().getSuId());
					Object o = query.uniqueResult();
					String username;
					if(o != null)username = o.toString();
					else username = "";
					receivers[m++] = username;
				}
				
				//将信息设置进新类中去
				draftMessage.setMsId(message.getMsId());
				draftMessage.setMsTitle(message.getMsTitle());
				draftMessage.setMsContent(message.getMsContent());
				draftMessage.setMsBox(message.getMsBox());
				draftMessage.setMsDelete(message.getMsDelete());
				draftMessage.setMsFile(message.getMsFile());
				draftMessage.setMsSendtime(message.getMsSendtime());
				draftMessage.setReceiverInfos(receivers);
				messageReceivers.add(draftMessage);
				
			}
			tx.commit();
			if(messageReceivers.size() == 0)
				return null;
			return messageReceivers;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	

	public int getPageConut(int count, int pageSize) {
		
		return ( count + pageSize - 1) / pageSize;
	}

	public List<MessageReceivers> getOutboxByPage(long uid, int pageNo) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// 得到构造好的HQL语句
		String hql = "from Message m inner join m.sysUser u where u.suId = ? and m.msBox='1' and m.msDelete='0'";
		String hqlUser = "select u.suUsername from SysUser u where u.suId = ?";
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql).setLong(0, uid);
			int m = 0;
			// 设置分页信息
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			
			
			List i = query.list();
			Iterator it = i.iterator();
			List<MessageReceivers> messageReceivers = new ArrayList<MessageReceivers>();
			while (it.hasNext()) {
				m = 0;
				MessageReceivers draftMessage = new MessageReceivers();
				Object[] obj = (Object[]) it.next();
				Message message = (Message) obj[0];
				SysUser r = (SysUser) obj[1];
				// 将sysUser设置为Message的属性
				message.setSysUser(r);
				//设置用户收件人
				//用户多少
				int  size = message.getReceiverInfos().size();
				String[] receivers = new String[size];
				Iterator receiver  = message.getReceiverInfos().iterator();
				while (receiver.hasNext()) {
					ReceiverInfo receiverInfo = (ReceiverInfo) receiver.next();
					query = sess.createQuery(hqlUser).setLong(0, receiverInfo.getSysUser().getSuId());
					Object o = query.uniqueResult();
					String username;
					if(o != null)username = o.toString();
					else username = "";
					receivers[m++] = username;
				}
				
				//将信息设置进新类中去
				draftMessage.setMsId(message.getMsId());
				draftMessage.setMsTitle(message.getMsTitle());
				draftMessage.setMsContent(message.getMsContent());
				draftMessage.setMsBox(message.getMsBox());
				draftMessage.setMsDelete(message.getMsDelete());
				draftMessage.setMsFile(message.getMsFile());
				draftMessage.setMsSendtime(message.getMsSendtime());
				draftMessage.setReceiverInfos(receivers);
				messageReceivers.add(draftMessage);
				
			}
			tx.commit();
			if(messageReceivers.size() == 0)
				return null;
			return messageReceivers;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	
	public int delSendMes(long mesID)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Message m where m.msId=?";
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的信息
			Query query = sess.createQuery(hql).setLong(0, mesID);
			List list = query.list();
			Iterator it = list.iterator();
			if (it.hasNext()) {
				Object obj = (Object) it.next();
				Message mes = (Message)obj;
				mes.setMsDelete("1");
				sess.saveOrUpdate(mes);
			}
			//没有返回为0
			else return 0;
			tx.commit();
			return 1;     //成功了返回1
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			sess.close();
		}
	}
	
	
	
	
	public int getInboxCount(long userid) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(re.riId) from ReceiverInfo re inner join re.sysUser s where s.suId=? and re.riBox='3' and re.riDelete='0'";
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户其拥有的收件箱的信息
			Object obj = sess.createQuery(hql).setLong(0, userid).uniqueResult();
			tx.commit();
			if( obj == null )
				return 0;
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return 0;
		} finally {
			sess.close();
		}
	}

	public int getPageCount(int count, int pageSize) {
		// TODO Auto-generated method stub
		return ( count + pageSize - 1) / pageSize;
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.OtherInfo#getSearchedUserCount(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int getDraftMessageCount(long uid) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(m.msId) from Message m inner join m.sysUser u where u.suId = ? and m.msBox='2'";
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Query query = sess.createQuery(hql).setLong(count, uid);
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
	
	/**
	 * @param mesId
	 * 删除草稿箱中的指定的信息
	 */
	public boolean deleteDraftMessage(long mesId)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "delete ReceiverInfo u where u.message = (from Message m where m.msId=?)";
		String hqlMes = "delete Message m where m.msId=?";
		try {
			tx = sess.beginTransaction();
			// 删除指定ID的用户
			sess.createQuery(hql).setLong(0, mesId).executeUpdate();
			
			Query query = sess.createQuery(hqlMes).setLong(0, mesId);
			query.executeUpdate();
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
	
	/**
	 * @param uid
	 * @param pageNo
	 * @return  获得特定邮件的草稿的值···而显示出来
	 */
	
	public MessageReceivers getDraftMessage(long mesId)
	{
		
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// 得到构造好的HQL语句
		String hql = "from Message m where m.msId = ?";
		String hqlUser = "select u.suUsername from SysUser u where u.suId = ?";
		String hqlUid = "select u.suUid from SysUser u where u.suId = ?";
		int count = 0;
		hql += " order by m.msId";
		try {
			tx = sess.beginTransaction();
			
			Query query = sess.createQuery(hql).setLong(0, mesId);
			int m = 0;
			
			List i = query.list();
			Iterator it = i.iterator();
			//一条的信息和收件人信息
			MessageReceivers messageReceivers = new MessageReceivers();
			//读查出的第一条信息
			while (it.hasNext()) {
				m = 0;
				Object obj = (Object) it.next();
				Message message = (Message) obj;
				
				//用户多少
				int  size = message.getReceiverInfos().size();
				//保存用户
				Long[] receiverIDs = new Long[size];
				String[] receivers = new String[size];
				String[] empno = new String[size];
				//循环用户
				Iterator receiver  = message.getReceiverInfos().iterator();
				//循环读用户的ID信息
				while (receiver.hasNext()) {
					ReceiverInfo receiverInfo = (ReceiverInfo) receiver.next();
					//用户信息不能为空
					if(receiverInfo != null)
					{
						//放ID的地方
						receiverIDs[m] = receiverInfo.getSysUser().getSuId();
						//放名字的地方
						query = sess.createQuery(hqlUser).setLong(0, receiverInfo.getSysUser().getSuId());
						Object o = query.uniqueResult();
						String username;
						if(o != null)username = o.toString();
						else username = "";
						receivers[m] = username;
						//放员工号的地方
						query = sess.createQuery(hqlUid).setLong(0, receiverInfo.getSysUser().getSuId());
						Object a = query.uniqueResult();
						String uid;
						if(a != null)uid = a.toString();
						else uid = "";
						empno[m++] = uid;
					}
					else 
					{
						receiverIDs = null;
						receivers = null;
						empno = null;
						break;
					}
					
				}
				
				//将信息设置进新类中去
				messageReceivers.setMsId(message.getMsId());
				messageReceivers.setMsTitle(message.getMsTitle());
				messageReceivers.setMsContent(message.getMsContent());
				messageReceivers.setMsBox(message.getMsBox());
				messageReceivers.setMsDelete(message.getMsDelete());
				messageReceivers.setMsFile(message.getMsFile());
				messageReceivers.setMsSendtime(message.getMsSendtime());
				messageReceivers.setReceiverIDs(receiverIDs);   //ID
				messageReceivers.setEmpno(empno);               //员工号
				messageReceivers.setReceiverInfos(receivers);   //名字
			}
			tx.commit();
			if(messageReceivers == null)
				return null;
			return messageReceivers;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MessageDao#getMessageNotRead(long)
	 */
	public int getMessageNotRead(long uid) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(re.riId) from ReceiverInfo re inner join re.sysUser s where s.suId=? and re.riBox='3' and re.riDelete='0' and re.riRead='0'";
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户其拥有的收件箱的信息
			Object obj = sess.createQuery(hql).setLong(0, uid).uniqueResult();
			tx.commit();
			if( obj == null )
				return 0;
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return 0;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.MessageDao#getOutBoxCount(long)
	 */
	public int getOutBoxCount(long uid) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(m.msId) from Message m inner join m.sysUser" +
				" u where u.suId = ? and m.msBox='1' and m.msDelete='0'";
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Query query = sess.createQuery(hql).setLong(count, uid);
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
	
}
