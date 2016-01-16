package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.OtherInfo;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysPosition;
import com.icss.hit.hibernate.vo.SysRole;
import com.icss.hit.hibernate.vo.SysUser;


/**
 * @author Administrator
 *
 */
public class OtherInfoBean implements OtherInfo {
	public static int PAGE_SIZE = 10;

	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.OtherInfo#getSearchedUserInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	public List<SysUser> getSearchedUserInfo(String type, String suUser,
			String suSex, int suDept, int pageNo) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// �õ�����õ�HQL���
		//System.out.println(type + "--" + suUser + "--" + suSex + "--" + suDept + "--" + pageNo);
		String hql = getSearchHQL(type,suSex,suDept);
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// �������ʾҳ��ʼ���
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql).setString(count++, "%" + suUser + "%");
			if (!suSex.equals("0")) {
				query.setString(count++, suSex);
			}
			
			// �����������������
			if (suDept != 0) {
				query.setLong(count++, suDept);
			}
			// ���÷�ҳ��Ϣ
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<SysUser> otherUserInfoList = new ArrayList<SysUser>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				SysUser u = (SysUser) obj[0];
				SysRole r = (SysRole) obj[1];
				SysDept d = (SysDept) obj[2];
				SysPosition p = (SysPosition) obj[3];
				// ��role��dept��position����Ϊuser������
				u.setSysRole(r);
				u.setSysDept(d);
				u.setSysPosition(p);
				otherUserInfoList.add(u);
			}
			if(otherUserInfoList.size() == 0)
				return null;
			return otherUserInfoList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	/**
	 * ����HQL���
	 * @param type ����������
	 * @param suSex �Ա�
	 * @param suDept ����
	 * @return �������������HQL�������
	 */
	private String getSearchHQL(String type,
			String suSex, int suDept){
		String hql = "from SysUser u inner join u.sysRole r"
			+ " inner join u.sysDept d inner join u.sysPosition p where ";
		
		// �����Ա���Ų�ѯ
		if (type.equals("id")) {
			hql += "u.suUid like ? ";
		}// ������������ѯ
		else if (type.equals("name")) {
			hql += "u.suUsername like ? ";
		}
		
		// ����Ա�����������
		if (!suSex.equals("0")) {
			hql += "and u.suSex = ? ";
		}
		
		// �����������������
		if (suDept != 0) {
			hql += "and d.sdId = ?";
		}
		hql += " order by u.suUid";
		return hql;
	}


	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.OtherInfo#getPageConut(int, int)
	 */
	public int getPageConut(int count, int pageSize) {
		
		return ( count + pageSize - 1) / pageSize;
	}


	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.OtherInfo#getSearchedUserCount(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int getSearchedUserCount(String type, String suUser, String suSex,
			int suDept) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(u.suId) from SysUser u inner join u.sysRole r"
			+ " inner join u.sysDept d inner join u.sysPosition p where ";
		
		// �����Ա���Ų�ѯ
		if (type.equals("id")) {
			hql += "u.suUid like ? ";
		}// ������������ѯ
		else if (type.equals("name")) {
			hql += "u.suUsername like ? ";
		}
		
		// ����Ա�����������
		if (!suSex.equals("0")) {
			hql += "and u.suSex = ?";
		}
		
		// �����������������
		if (suDept != 0) {
			hql += "and d.sdId = ?";
		}
		
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// ����ϵͳ���û�������
			Query query = sess.createQuery(hql).setString(count++, "%" + suUser + "%");
			// ����Ա�����������
			if (!suSex.equals("0")) {
				query.setString(count++, suSex);
			}
			
			// �����������������
			if (suDept != 0) {
				query.setLong(count++, suDept);
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
	
	
	/**
	 * �����������������������������˵���Ϣ
	 * @param type ��������
	 * @param suUser �ؼ���
	 * @param suSex �Ա�
	 * @param suDept ����ID
	 * @return �����������˵ļ���
	 */
	public List<SysUser> getAllSearchedUserInfo(String type, String suUser,
			String suSex, int suDept) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// �õ�����õ�HQL���
		//System.out.println(type + "--" + suUser + "--" + suSex + "--" + suDept + "--" + pageNo);
		String hql = getSearchHQL(type,suSex,suDept);
		int count = 0;
		try {
			tx = sess.beginTransaction();
			Query query = sess.createQuery(hql).setString(count++, "%" + suUser + "%");
			if (!suSex.equals("0")) {
				query.setString(count++, suSex);
			}
			
			// �����������������
			if (suDept != 0) {
				query.setLong(count++, suDept);
			}
			
			
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<SysUser> otherUserInfoList = new ArrayList<SysUser>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				SysUser u = (SysUser) obj[0];
				SysRole r = (SysRole) obj[1];
				SysDept d = (SysDept) obj[2];
				SysPosition p = (SysPosition) obj[3];
				// ��role��dept��position����Ϊuser������
				u.setSysRole(r);
				u.setSysDept(d);
				u.setSysPosition(p);
				otherUserInfoList.add(u);
			}
			if(otherUserInfoList.size() == 0)
				return null;
			return otherUserInfoList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}

	/**
	 * @return integer ������ʾ�����û��ģ����Ǹ���˾ͨѶ¼����ʹ�õĶ������ȣϣȣϣȣϣȣ�
	 */
	public int getAllUserCount(String suSex,int suDept) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(u.suId) from SysUser u inner join u.sysRole r"
			+ " inner join u.sysDept d inner join u.sysPosition p where ";
		if(suSex.equals("0")&&suDept == 0)
		{
			hql = "select count(u.suId) from SysUser u inner join u.sysRole r"
				+ " inner join u.sysDept d inner join u.sysPosition p ";
		}
		int count = 0;
		// ����Ա�����������
		if (!suSex.equals("0")) {
			hql += "u.suSex = ?";
			// �����������������
			if (suDept != 0) {
				hql += "and d.sdId = ?";
			}
		}
		else{
			// �����������������
			if (suDept != 0) {
				hql += "d.sdId = ?";
			}
		}
		
		hql += " order by u.suId";
		try {
			tx = sess.beginTransaction();
			// ����ϵͳ���û�������
			Query query = sess.createQuery(hql);
			
			// ����Ա�����������
			if (!suSex.equals("0")) {
				query.setString(count++, suSex);
			}
			
			// �����������������
			if (suDept != 0) {
				query.setLong(count++, suDept);
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
	/**
	 * @param pageNo
	 * @return  ���ڻ���ʾ���е��û�����Ϣ
	 */
	public List<SysUser> getAllUserInfo(String suSex,int suDept,int pageNo) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// �õ�����õ�HQL���
		String hql = "from SysUser u inner join u.sysRole r"
			+ " inner join u.sysDept d inner join u.sysPosition p where ";
		if(suSex.equals("0")&&suDept == 0)
		{
			hql = "from SysUser u inner join u.sysRole r"
				+ " inner join u.sysDept d inner join u.sysPosition p ";
		}
		int count = 0;
		if (!suSex.equals("0")) {
			hql += "u.suSex = ?";
			// �����������������
			if (suDept != 0) {
				hql += "and d.sdId = ?";
			}
		}
		else{
			// �����������������
			if (suDept != 0) {
				hql += "d.sdId = ?";
			}
		}
		hql += " order by u.suId";
		try {
			tx = sess.beginTransaction();
			// �������ʾҳ��ʼ���
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql);
			// ����Ա�������
			if (!suSex.equals("0")) {
				query.setString(count++, suSex);
			}
			
			// �����������������
			if (suDept != 0) {
				query.setLong(count++, suDept);
			}
			// ���÷�ҳ��Ϣ
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<SysUser> otherUserInfoList = new ArrayList<SysUser>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				SysUser u = (SysUser) obj[0];
				SysRole r = (SysRole) obj[1];
				SysDept d = (SysDept) obj[2];
				SysPosition p = (SysPosition) obj[3];
				// ��role��dept��position����Ϊuser������
				u.setSysRole(r);
				u.setSysDept(d);
				u.setSysPosition(p);
				otherUserInfoList.add(u);
			}
			if(otherUserInfoList.size() == 0)
				return null;
			return otherUserInfoList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	/**
	 * �����������������������������˵���Ϣ
	 * @param suSex �Ա�
	 * @param suDept ����ID
	 * @return �����������˵ļ���
	 */
	public List<SysUser> getUserInfo(String suSex, int suDept) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// �õ�����õ�HQL���
		String hql = "from SysUser u inner join u.sysRole r"
			+ " inner join u.sysDept d inner join u.sysPosition p where ";
		
		if(suSex.equals("0")&&suDept == 0)
		{
			hql = "from SysUser u inner join u.sysRole r"
				+ " inner join u.sysDept d inner join u.sysPosition p ";
		}
		if (!suSex.equals("0")) {
			hql += "u.suSex = ?";
			// �����������������
			if (suDept != 0) {
				hql += "and d.sdId = ?";
			}
		}
		else{
			// �����������������
			if (suDept != 0) {
				hql += "d.sdId = ?";
			}
		}
		hql += " order by u.suId";
		
		int count = 0;
		try {
			tx = sess.beginTransaction();
			Query query = sess.createQuery(hql);
			//�Ա�
			if (!suSex.equals("0")) {
				query.setString(count++, suSex);
			}
			
			// �����������������
			if (suDept != 0) {
				query.setLong(count++, suDept);
			}
			
			
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<SysUser> otherUserInfoList = new ArrayList<SysUser>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				SysUser u = (SysUser) obj[0];
				SysRole r = (SysRole) obj[1];
				SysDept d = (SysDept) obj[2];
				SysPosition p = (SysPosition) obj[3];
				// ��role��dept��position����Ϊuser������
				u.setSysRole(r);
				u.setSysDept(d);
				u.setSysPosition(p);
				otherUserInfoList.add(u);
			}
			if(otherUserInfoList.size() == 0)
				return null;
			return otherUserInfoList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
}
