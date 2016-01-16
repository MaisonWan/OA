/**
 * 
 */
package com.icss.hit.bean;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.icss.hit.bean.interfaces.CardTypeDao;

import com.icss.hit.hibernate.HibernateSessionFactory;

import com.icss.hit.hibernate.vo.*;

import org.hibernate.Query; 

/**
 * @author ���
 * ����Ƭ���йص�һЩ���� ���˽ӿ������6������ ����һ�������Ƭ���Ƿ������ķ���
 */
public class CardTypeBean implements CardTypeDao{
	
	public final int PAGE_SIZE = 10;//Ĭ�ϵ�ҳ���СΪ10
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.CardType#deleteCardType(long)
	 */
	
	/**
	 * ������ݿ����Ƿ��Ѿ�������ͬ���Ƶ��ļ���
	 * @param id �û���id
	 * @param ct_Name ׼���������Ƭ������
	 * @return �Ƿ��Ѿ�����ͬ���ļ��� trueΪ�Ѿ���ͬ���ļ��� falseΪû��ͬ���ļ���
	 */
	private boolean checkExistence(long id, String ct_Name){
		
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(ct.ctId) from CardType ct inner join ct.sysUser cu where cu.suId = ? and ct.ctName = ?";
		try {
			tx = sess.beginTransaction();
			//�����ظ�����Ƭ������
			Object o = sess.createQuery(hql).setLong(0, id).setString(1, ct_Name).uniqueResult();
			tx.commit();
			if(Integer.parseInt(o.toString()) > 0 ){
				return true;
			}
			else{
				return false;
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return true;//�������Ļ���Ĭ��Ϊ�������� ����Ƭ�оͲ��벻��ȥ
		} finally {
			sess.close();
		}
		
	}
	
	public boolean deleteCardType(long id) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction delCtTrans = null;
		try {
			delCtTrans = sess.beginTransaction();
			// ɾ��ָ��ID��CardType
			sess.delete((CardType) sess.get(CardType.class, id));
			delCtTrans.commit();
			return true;
		} catch (Exception e) {
			delCtTrans.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.CardTypeDao#getAllCardType(long)
	 */
	public List<CardType> getAllCardType(long id) {
		// TODO Auto-generated method stub
		
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from CardType ct inner join ct.sysUser cu where cu.suId = ?";
		try {
			tx = sess.beginTransaction();
			//int offset = (pageNo - 1) * PAGE_SIZE;
			List l = sess.createQuery(hql).setLong(0, id).list();
			tx.commit();
			Iterator it = l.iterator();
			List<CardType> list = new ArrayList<CardType>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				CardType c = (CardType) obj[0];
				SysUser u = (SysUser) obj[1];
				c.setSysUser(u);
				list.add(c);
			}
			if(list.size() == 0 ){
				return null;
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
	 * @see com.icss.hit.bean.interfaces.CardType#getAllCardTypeByPage(int)
	 */
	public List<CardType> getAllCardTypeByPage(int pageNo, long userId) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from CardType ct inner join ct.sysUser cu where cu.suId = ?";
		try {
			tx = sess.beginTransaction();
			// �������ʾҳ��ʼ���
			int offset = (pageNo - 1) * PAGE_SIZE;
			List l = sess.createQuery(hql).setLong(0, userId).setFirstResult(offset)
					.setMaxResults(PAGE_SIZE).list();
			tx.commit();
			Iterator it = l.iterator();
			List<CardType> list = new ArrayList<CardType>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				CardType c = (CardType) obj[0];
				SysUser u = (SysUser) obj[1];
				c.setSysUser(u);
				list.add(c);
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
	 * @see com.icss.hit.bean.interfaces.CardType#getCardType(long)
	 */
	public CardType getCardType(long suId, long ctId) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from CardType ct inner join ct.sysUser cu where cu.suId = ? and ct.ctId = ?";
		try {
			tx = sess.beginTransaction();
			// �������ʾҳ��ʼ���
			List l = sess.createQuery(hql).setLong(0, suId).setLong(1, ctId).list();
			tx.commit();
			Iterator it = l.iterator();
			if (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				CardType c = (CardType) obj[0];
				SysUser u = (SysUser) obj[1];
				
				c.setSysUser(u);
				return c;
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
	 * @see com.icss.hit.bean.interfaces.CardType#getCardTypeCount(long)
	 */
	public int getCardTypeCount(long id) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(ct.ctId) from CardType ct inner join ct.sysUser cu where cu.suId = ?";
		try {
			tx = sess.beginTransaction();
			// ����ĳ���û�����Ƭ������
			Object o = sess.createQuery(hql).setLong(0, id).uniqueResult();
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
	 * @see com.icss.hit.bean.interfaces.CardType#getPageCount(int, int)
	 */
	public int getPageCount(int count, int pageSize) {
		// TODO Auto-generated method stub
		return (count + pageSize - 1) / pageSize;
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.CardType#updateCardType(com.icss.hit.bean.interfaces.CardType)
	 */
	public int updateCardType(CardType type) {
		// TODO Auto-generated method stub
		boolean eon = checkExistence(type.getSysUser().getSuId(), type.getCtName());//����Ƿ����ͬ�����ļ���
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// ����ϵͳ���û�������
			if(eon){
				return 1;//������
			}
			else{
				sess.saveOrUpdate(type);
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
	
	public boolean isUsersType(long uid,long cardTypeID)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(ct.ctId) from CardType ct inner join ct.sysUser cu where cu.suId = ? and ct.ctId = ?";
		try {
			tx = sess.beginTransaction();
			//���ض�������û��Ķ�Ӧ�Ŀ�Ƭ�ǲ�����
			Object o = sess.createQuery(hql).setLong(0, uid).setLong(1, cardTypeID).uniqueResult();
			tx.commit();
			//������Ļ����е�
			if(Integer.parseInt(o.toString()) > 0 ){
				return true;
			}
			else{
				return false;        //�������û�е�
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;//�������Ļ���Ϊ��������û���
		} finally {
			sess.close();
		}
	}
	
	public boolean hasCard(long cardTypeID)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(c.cdId) from Card c inner join c.cardType ct where ct.ctId = ?";
		try {
			tx = sess.beginTransaction();
			//���ض�Ӧ�Ŀ�Ƭ��,����
			Object o = sess.createQuery(hql).setLong(0, cardTypeID).uniqueResult();
			tx.commit();
			//������Ļ����е�,����
			System.out.println(o.toString());
			if(Integer.parseInt(o.toString()) > 0 ){
				return true;
			}
			else{
				return false;        //�������û�е�
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return true;//�������Ļ���û��
		} finally {
			sess.close();
		}
	}
	

}
