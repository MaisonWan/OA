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
 * @author 许达
 * 与名片夹有关的一些操作 除了接口里面的6个方法 还有一个检查名片夹是否重名的方法
 */
public class CardTypeBean implements CardTypeDao{
	
	public final int PAGE_SIZE = 10;//默认的页面大小为10
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.CardType#deleteCardType(long)
	 */
	
	/**
	 * 检查数据库中是否已经存在相同名称的文件夹
	 * @param id 用户的id
	 * @param ct_Name 准备插入的名片夹名称
	 * @return 是否已经存在同名文件夹 true为已经有同名文件夹 false为没有同名文件夹
	 */
	private boolean checkExistence(long id, String ct_Name){
		
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(ct.ctId) from CardType ct inner join ct.sysUser cu where cu.suId = ? and ct.ctName = ?";
		try {
			tx = sess.beginTransaction();
			//返回重复的名片夹数量
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
			return true;//如果出错的话就默认为有重名的 新名片夹就插入不进去
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
			// 删除指定ID的CardType
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
			// 计算出显示页起始编号
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
			// 计算出显示页起始编号
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
			// 返回某个用户的名片夹数量
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
		boolean eon = checkExistence(type.getSysUser().getSuId(), type.getCtName());//检查是否存在同名的文件夹
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			if(eon){
				return 1;//有重名
			}
			else{
				sess.saveOrUpdate(type);
				tx.commit();
				return 0;//成功执行
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return 2;//未知错误
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
			//返回对于这个用户的对应的卡片是不是有
			Object o = sess.createQuery(hql).setLong(0, uid).setLong(1, cardTypeID).uniqueResult();
			tx.commit();
			//查出来的话是有的
			if(Integer.parseInt(o.toString()) > 0 ){
				return true;
			}
			else{
				return false;        //否则就是没有的
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;//如果出错的话认为不是这个用户的
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
			//返回对应的卡片有,存在
			Object o = sess.createQuery(hql).setLong(0, cardTypeID).uniqueResult();
			tx.commit();
			//查出来的话是有的,存在
			System.out.println(o.toString());
			if(Integer.parseInt(o.toString()) > 0 ){
				return true;
			}
			else{
				return false;        //否则就是没有的
			}
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return true;//如果出错的话，没有
		} finally {
			sess.close();
		}
	}
	

}
