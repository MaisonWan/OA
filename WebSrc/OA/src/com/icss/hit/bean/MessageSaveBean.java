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
 * @author ��ӱ��
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
			//����receiver
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
			//����message
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
			//�û�����
			int  size = message.getReceiverInfos().size();
			String[] receivers = new String[size];
			Iterator it  = message.getReceiverInfos().iterator();
			while (it.hasNext()) {
				ReceiverInfo obj = (ReceiverInfo) it.next();
				Query query = sess.createQuery(hql).setLong(0, obj.getRiId());
				//�������
				Object o = query.uniqueResult();
				String username = o.toString();
				receivers[m++] = username;
			}
			//��������
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
		// �õ�����õ�HQL���
		String hql = "from Message m inner join m.sysUser u where u.suId = ? and m.msBox='2'";
		String hqlUser = "select u.suUsername from SysUser u where u.suId = ?";
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// �������ʾҳ��ʼ���
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql).setLong(0, uid);
			int m = 0;
			// ���÷�ҳ��Ϣ
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
				// ��sysUser����ΪMessage������
				message.setSysUser(r);
				//�����û��ռ���
				//�û�����
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
				
				//����Ϣ���ý�������ȥ
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
		// �õ�����õ�HQL���
		String hql = "from Message m inner join m.sysUser u where u.suId = ? and m.msBox='1' and m.msDelete='0'";
		String hqlUser = "select u.suUsername from SysUser u where u.suId = ?";
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// �������ʾҳ��ʼ���
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql).setLong(0, uid);
			int m = 0;
			// ���÷�ҳ��Ϣ
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
				// ��sysUser����ΪMessage������
				message.setSysUser(r);
				//�����û��ռ���
				//�û�����
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
				
				//����Ϣ���ý�������ȥ
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
			// ��ѯָ��ID����Ϣ
			Query query = sess.createQuery(hql).setLong(0, mesID);
			List list = query.list();
			Iterator it = list.iterator();
			if (it.hasNext()) {
				Object obj = (Object) it.next();
				Message mes = (Message)obj;
				mes.setMsDelete("1");
				sess.saveOrUpdate(mes);
			}
			//û�з���Ϊ0
			else return 0;
			tx.commit();
			return 1;     //�ɹ��˷���1
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
			// ��ѯָ��ID���û���ӵ�е��ռ������Ϣ
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
			// ����ϵͳ���û�������
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
	 * ɾ���ݸ����е�ָ������Ϣ
	 */
	public boolean deleteDraftMessage(long mesId)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "delete ReceiverInfo u where u.message = (from Message m where m.msId=?)";
		String hqlMes = "delete Message m where m.msId=?";
		try {
			tx = sess.beginTransaction();
			// ɾ��ָ��ID���û�
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
	 * @return  ����ض��ʼ��Ĳݸ��ֵ����������ʾ����
	 */
	
	public MessageReceivers getDraftMessage(long mesId)
	{
		
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// �õ�����õ�HQL���
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
			//һ������Ϣ���ռ�����Ϣ
			MessageReceivers messageReceivers = new MessageReceivers();
			//������ĵ�һ����Ϣ
			while (it.hasNext()) {
				m = 0;
				Object obj = (Object) it.next();
				Message message = (Message) obj;
				
				//�û�����
				int  size = message.getReceiverInfos().size();
				//�����û�
				Long[] receiverIDs = new Long[size];
				String[] receivers = new String[size];
				String[] empno = new String[size];
				//ѭ���û�
				Iterator receiver  = message.getReceiverInfos().iterator();
				//ѭ�����û���ID��Ϣ
				while (receiver.hasNext()) {
					ReceiverInfo receiverInfo = (ReceiverInfo) receiver.next();
					//�û���Ϣ����Ϊ��
					if(receiverInfo != null)
					{
						//��ID�ĵط�
						receiverIDs[m] = receiverInfo.getSysUser().getSuId();
						//�����ֵĵط�
						query = sess.createQuery(hqlUser).setLong(0, receiverInfo.getSysUser().getSuId());
						Object o = query.uniqueResult();
						String username;
						if(o != null)username = o.toString();
						else username = "";
						receivers[m] = username;
						//��Ա���ŵĵط�
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
				
				//����Ϣ���ý�������ȥ
				messageReceivers.setMsId(message.getMsId());
				messageReceivers.setMsTitle(message.getMsTitle());
				messageReceivers.setMsContent(message.getMsContent());
				messageReceivers.setMsBox(message.getMsBox());
				messageReceivers.setMsDelete(message.getMsDelete());
				messageReceivers.setMsFile(message.getMsFile());
				messageReceivers.setMsSendtime(message.getMsSendtime());
				messageReceivers.setReceiverIDs(receiverIDs);   //ID
				messageReceivers.setEmpno(empno);               //Ա����
				messageReceivers.setReceiverInfos(receivers);   //����
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
			// ��ѯָ��ID���û���ӵ�е��ռ������Ϣ
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
			// ����ϵͳ���û�������
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
