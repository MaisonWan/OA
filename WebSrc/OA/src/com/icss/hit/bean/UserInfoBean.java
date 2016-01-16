package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.UserInfo;
import com.icss.hit.component.MD5;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysPosition;
import com.icss.hit.hibernate.vo.SysRole;
import com.icss.hit.hibernate.vo.SysUser;

public class UserInfoBean implements UserInfo {
	public final int PAGE_SIZE = 10;
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.UserInfo#getAllUsers()
	 */
	public List<SysUser> getAllUsers() {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysUser u inner join u.sysDept d";
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			List l = sess.createQuery(hql).list();
			tx.commit();
			Iterator it = l.iterator();
			List<SysUser> list = new ArrayList<SysUser>();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				SysUser u = (SysUser) obj[0];
				SysDept d = (SysDept) obj[1];
				// 将role，dept，position设置为user的属性
				u.setSysDept(d);
				list.add(u);
			}
			if( list.size() == 0 )
				return null;
			return list;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.icss.hit.bean.interfaces.UserInfo#deleteUserInfo(long)
	 */
	public boolean deleteUserInfo(long id) {

		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// 删除指定ID的用户
			sess.delete((SysUser) sess.get(SysUser.class, id));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.icss.hit.bean.interfaces.UserInfo#getAllUserInfoByPage(int)
	 */
	public List<SysUser> getAllUserInfoByPage(int pageNo) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysUser u inner join u.sysRole r inner join u.sysDept d inner join u.sysPosition p order by u.suId";
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			int offset = (pageNo - 1) * PAGE_SIZE;
			List l = sess.createQuery(hql).setFirstResult(offset)
					.setMaxResults(PAGE_SIZE).list();
			tx.commit();
			Iterator it = l.iterator();
			List<SysUser> list = new ArrayList<SysUser>();
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
				list.add(u);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.icss.hit.bean.interfaces.UserInfo#getPageCount(int, int)
	 */
	public int getPageCount(int count, int pageSize) {
		return (count + pageSize - 1) / pageSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.icss.hit.bean.interfaces.UserInfo#getUserInfo(long)
	 */
	public SysUser getUserInfo(long id) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysUser u inner join u.sysRole r inner join u.sysDept"
				+ " inner join u.sysPosition p where u.suId=?";
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			List l = sess.createQuery(hql).setLong(0, id).list();
			tx.commit();
			Iterator it = l.iterator();
			if (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				SysUser u = (SysUser) obj[0];
				SysRole r = (SysRole) obj[1];
				SysDept d = (SysDept) obj[2];
				SysPosition p = (SysPosition) obj[3];
				// 将role，dept，position设置为user的属性
				u.setSysRole(r);
				u.setSysDept(d);
				u.setSysPosition(p);
				return u;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.icss.hit.bean.interfaces.UserInfo#getUserInfoCount()
	 */
	public int getUserInfoCount() {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(u.suId) from SysUser as u";
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Object o = sess.createQuery(hql).uniqueResult();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.icss.hit.bean.interfaces.UserInfo#updateUserInfo(com.icss.hit.hibernate.vo.SysUser)
	 */
	public boolean updateUserInfo(SysUser user) {
		//给我的感觉是如果没有就插入一个新的对象
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		System.out.println("舒服的"+user.getSuUid());
		try {
			tx = sess.beginTransaction();
			sess.saveOrUpdate(user);
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
	 * @see com.icss.hit.bean.interfaces.UserInfo#validata(java.lang.String, java.lang.String)
	 */
	public long validata(String username, String password) {
		
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysUser u where u.suUid=?";
		// 用MD5加密密码
		password = new MD5().getMD5Base32(password);
		try {
			tx = sess.beginTransaction();
			List list = sess.createQuery(hql).setString(0, username).list();
			tx.commit();
			// 验证是否返回结果
			if( list != null && list.size() > 0 ){
				SysUser user = (SysUser)list.get(0);
				// 验证密码是否匹配
				if( user.getSuPassword().equals(password)){
					return user.getSuId();
				}
				return -1;
			}
			return -1;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.UserInfo#validata(long, java.lang.String)
	 */
	public boolean validata(long userId, String password) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysUser u where u.suId=?";
		
		// 用MD5加密密码
		password = new MD5().getMD5Base32(password);
		try {
			tx = sess.beginTransaction();
			List list = sess.createQuery(hql).setLong(0, userId).list();
			tx.commit();
			// 验证是否返回结果
			if( list != null && list.size() > 0 ){
				SysUser user = (SysUser)list.get(0);
				// 验证密码是否匹配
				if( user.getSuPassword().equals(password)){
					return true;
				}
				return false;
			}
			return false;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.UserInfo#modifyPassword(long, java.lang.String)
	 */
	public boolean modifyPassword(long userId, String newpassword) {
		SysUser user = this.getUserInfo(userId);
		newpassword = new MD5().getMD5Base32(newpassword);
		user.setSuPassword(newpassword);
		return this.updateUserInfo(user);
	}
	
}
