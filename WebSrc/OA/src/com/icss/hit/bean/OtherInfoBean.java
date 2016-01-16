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
		// 得到构造好的HQL语句
		//System.out.println(type + "--" + suUser + "--" + suSex + "--" + suDept + "--" + pageNo);
		String hql = getSearchHQL(type,suSex,suDept);
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql).setString(count++, "%" + suUser + "%");
			if (!suSex.equals("0")) {
				query.setString(count++, suSex);
			}
			
			// 如果部门有条件限制
			if (suDept != 0) {
				query.setLong(count++, suDept);
			}
			// 设置分页信息
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
				// 将role，dept，position设置为user的属性
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
	 * 构造HQL语句
	 * @param type 搜索的类型
	 * @param suSex 性别
	 * @param suDept 部门
	 * @return 根据条件构造的HQL条件语句
	 */
	private String getSearchHQL(String type,
			String suSex, int suDept){
		String hql = "from SysUser u inner join u.sysRole r"
			+ " inner join u.sysDept d inner join u.sysPosition p where ";
		
		// 如果按员工号查询
		if (type.equals("id")) {
			hql += "u.suUid like ? ";
		}// 否则按照姓名查询
		else if (type.equals("name")) {
			hql += "u.suUsername like ? ";
		}
		
		// 如果性别有条件限制
		if (!suSex.equals("0")) {
			hql += "and u.suSex = ? ";
		}
		
		// 如果部门有条件限制
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
		
		// 如果按员工号查询
		if (type.equals("id")) {
			hql += "u.suUid like ? ";
		}// 否则按照姓名查询
		else if (type.equals("name")) {
			hql += "u.suUsername like ? ";
		}
		
		// 如果性别有条件限制
		if (!suSex.equals("0")) {
			hql += "and u.suSex = ?";
		}
		
		// 如果部门有条件限制
		if (suDept != 0) {
			hql += "and d.sdId = ?";
		}
		
		int count = 0;
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Query query = sess.createQuery(hql).setString(count++, "%" + suUser + "%");
			// 如果性别有条件限制
			if (!suSex.equals("0")) {
				query.setString(count++, suSex);
			}
			
			// 如果部门有条件限制
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
	 * 根据条件搜索出符合条件的所有人的信息
	 * @param type 搜索类型
	 * @param suUser 关键词
	 * @param suSex 性别
	 * @param suDept 部门ID
	 * @return 符合条件的人的集合
	 */
	public List<SysUser> getAllSearchedUserInfo(String type, String suUser,
			String suSex, int suDept) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// 得到构造好的HQL语句
		//System.out.println(type + "--" + suUser + "--" + suSex + "--" + suDept + "--" + pageNo);
		String hql = getSearchHQL(type,suSex,suDept);
		int count = 0;
		try {
			tx = sess.beginTransaction();
			Query query = sess.createQuery(hql).setString(count++, "%" + suUser + "%");
			if (!suSex.equals("0")) {
				query.setString(count++, suSex);
			}
			
			// 如果部门有条件限制
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
				// 将role，dept，position设置为user的属性
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
	 * @return integer 用于显示所有用户的，在那个公司通讯录里面使用的东东，ＨＯＨＯＨＯＨＯ
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
		// 如果性别有条件限制
		if (!suSex.equals("0")) {
			hql += "u.suSex = ?";
			// 如果部门有条件限制
			if (suDept != 0) {
				hql += "and d.sdId = ?";
			}
		}
		else{
			// 如果部门有条件限制
			if (suDept != 0) {
				hql += "d.sdId = ?";
			}
		}
		
		hql += " order by u.suId";
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Query query = sess.createQuery(hql);
			
			// 如果性别有条件限制
			if (!suSex.equals("0")) {
				query.setString(count++, suSex);
			}
			
			// 如果部门有条件限制
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
	 * @return  用于回显示所有的用户的信息
	 */
	public List<SysUser> getAllUserInfo(String suSex,int suDept,int pageNo) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// 得到构造好的HQL语句
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
			// 如果部门有条件限制
			if (suDept != 0) {
				hql += "and d.sdId = ?";
			}
		}
		else{
			// 如果部门有条件限制
			if (suDept != 0) {
				hql += "d.sdId = ?";
			}
		}
		hql += " order by u.suId";
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql);
			// 如果性别有限制
			if (!suSex.equals("0")) {
				query.setString(count++, suSex);
			}
			
			// 如果部门有条件限制
			if (suDept != 0) {
				query.setLong(count++, suDept);
			}
			// 设置分页信息
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
				// 将role，dept，position设置为user的属性
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
	 * 根据条件搜索出符合条件的所有人的信息
	 * @param suSex 性别
	 * @param suDept 部门ID
	 * @return 符合条件的人的集合
	 */
	public List<SysUser> getUserInfo(String suSex, int suDept) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		// 得到构造好的HQL语句
		String hql = "from SysUser u inner join u.sysRole r"
			+ " inner join u.sysDept d inner join u.sysPosition p where ";
		
		if(suSex.equals("0")&&suDept == 0)
		{
			hql = "from SysUser u inner join u.sysRole r"
				+ " inner join u.sysDept d inner join u.sysPosition p ";
		}
		if (!suSex.equals("0")) {
			hql += "u.suSex = ?";
			// 如果部门有条件限制
			if (suDept != 0) {
				hql += "and d.sdId = ?";
			}
		}
		else{
			// 如果部门有条件限制
			if (suDept != 0) {
				hql += "d.sdId = ?";
			}
		}
		hql += " order by u.suId";
		
		int count = 0;
		try {
			tx = sess.beginTransaction();
			Query query = sess.createQuery(hql);
			//性别
			if (!suSex.equals("0")) {
				query.setString(count++, suSex);
			}
			
			// 如果部门有条件限制
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
				// 将role，dept，position设置为user的属性
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
