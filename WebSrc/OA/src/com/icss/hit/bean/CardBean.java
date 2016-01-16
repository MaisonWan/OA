/**
 * 
 */
package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.hibernate.HibernateSessionFactory;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.CardDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Card;
import com.icss.hit.hibernate.vo.CardType;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysPosition;
import com.icss.hit.hibernate.vo.SysRole;
import com.icss.hit.hibernate.vo.SysUser;


/**
 * @author ����
 * 2009��8��1��
 */

public class CardBean implements CardDao{
	public static int PAGE_SIZE = 10;
	
	public boolean addCard(Card card) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// ����ϵͳ���û�������
			sess.saveOrUpdate(card);
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
	
	public List<Card> getSearchedCardInfo(String userName,int userSex,String company,int cardType,int pageNo,long id)

	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// �õ�����õ�HQL���
		String hql = getSearchHQL(userName,userSex,company,cardType,id);
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// �������ʾҳ��ʼ���
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql);
			
			if( userName != null && !userName.equals("")){
				query.setString(count++, "%" + userName + "%");
			}
			if( company != null && !company.equals("")){
				query.setString(count++, "%" + company + "%");
			}
			
			//������ʼҳ�Լ�����ҳ
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<Card> cardList = new ArrayList<Card>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Card c = (Card) obj[0];
				CardType r = (CardType) obj[1];
				// ��CardType����Ϊcard������
				c.setCardType(r);
				cardList.add(c);
			}
			if(cardList.size() == 0)
				return null;
			return cardList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	private String getSearchHQL(String name,
			int sex, String comp,int cardType,long id){
		String hql = "from Card c inner join c.cardType t inner join t.sysUser s where ";
		
		hql+="s.suId = "+id+" ";
		if( name != null && !name.equals("")){
			hql+="and c.cdName like ? ";
		}
		if( comp != null && !comp.equals("")){
			hql+="and c.cdCompany like ? ";
		}
		
		// ����Ա�����������
		if (sex!=0) {
			if(sex ==1){
				hql += "and c.cdSex = '��' ";
			}
			else if(sex ==2){
				hql += "and c.cdSex = 'Ů' ";
			}
		}
		
		// �����Ƭ�з�������������
		if (cardType != 0) {
			hql += "and t.ctId = " + cardType;
		}
		
		return hql;
	}
	
	
	public int getPageCount(int count, int pageSize) {
		return ( count + pageSize - 1) / pageSize;
	}
	
	public int getSearchedCardCount(String userName,int userSex,String company,int cardType,long id) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(c.cdId) from Card c inner join c.cardType t inner join t.sysUser s where ";
		
		hql+="c.cdName like ? ";
		hql+="and c.cdCompany like ? ";
		hql+="and s.suId = "+id+" ";
		// ����Ա�����������
		if (userSex!=0) {
			if(userSex ==1){
				hql += "and c.cdSex = '��' ";
			}
			else if(userSex ==2){
				hql += "and c.cdSex = 'Ů' ";
			}
		}
		
		// �����Ƭ�з�������������
		if (cardType != 0) {
			hql += "and t.ctId = " + cardType;
		}
		
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// ����ϵͳ����Ƭ�е�����
			Query query = sess.createQuery(hql).setString(count, "%" + userName + "%").setString(count+1, "%" + company + "%");
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
	
	public List<Card> getShareCard(long id,int pageNo)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// �õ�����õ�HQL���
		String hql = "from Card c inner join c.cardType t inner join t.sysUser s where c.cdShare='1' and s.suId="+id;
		try {
			tx = sess.beginTransaction();
			// �������ʾҳ��ʼ���
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql);
			//������ʼҳ�Լ�����ҳ
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<Card> cardList = new ArrayList<Card>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Card c = (Card) obj[0];
				CardType r = (CardType) obj[1];
				// ��CardType����Ϊcard������
				c.setCardType(r);
				cardList.add(c);
			}
			if(cardList.size() == 0)
				return null;
			return cardList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	public int getShareCardCount(long id)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(c.cdId) from Card c inner join c.cardType t inner join t.sysUser s where c.cdShare='1' and s.suId = "+id;
		
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// ����ϵͳ����Ƭ�е�����
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
	public boolean updateCard(Card card) {
		//���ҵĸо������û�оͲ���һ���µĶ���
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			
			sess.saveOrUpdate(card);
			
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
	 * @see com.icss.hit.bean.interfaces.CardDao#get(long)
	 */
	public Card getCard(long id) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Card c inner join c.cardType ct where c.cdId=?";
		try {
			tx = sess.beginTransaction();
			
			List l = sess.createQuery(hql).setLong(0, id).list();
			tx.commit();
			if(l!=null&&l.size()>0)
			{
			Iterator it = l.iterator();
			if (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				Card c = (Card) obj[0];
				CardType ct = (CardType) obj[1];
				c.setCardType(ct);
				return c;
			}
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
