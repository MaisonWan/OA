package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.SysPositionDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysPosition;
import com.icss.hit.hibernate.vo.SysRole;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author 朱金彪
 * 2009年8月12日
 */
public class SysPositionBean implements SysPositionDao{
	public static int PAGE_SIZE = 10;
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.SysPositionDao#getAllPosition()
	 */
	public List<SysPosition> getAllPosition(){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = sess.beginTransaction();
		List<SysPosition> list = null;
		try{
			list = sess.createQuery("from SysPosition").list();
			tx.commit();
			return list;
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
			return null;
		}
		finally
		{
			sess.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.SysPositionDao#getPosition(long)
	 */
	public SysPosition getPosition(long id) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysPosition sp where sp.spsId=?";
		try {
			tx = sess.beginTransaction();
			
			List l = sess.createQuery(hql).setLong(0, id).list();
			tx.commit();
			if(l!=null&&l.size()>0)
			{
				return (SysPosition)l.get(0);
			}
			return null ;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.SysPositionDao#getAllUserByDept(int, int)
	 */
	public List<SysUser> getAllUserByDept(long suDept, int pageNo)
	{
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysUser u inner join u.sysRole r inner join u.sysDept d inner join u.sysPosition p ";
		if(suDept!=0){
			hql+=" where d.sdId=?";
		}
		try {
			tx = sess.beginTransaction();
			// 计算出显示页起始编号
			int offset = (pageNo - 1) * PAGE_SIZE;
			Query query = sess.createQuery(hql);
			if(suDept!=0){
				query.setLong(0, suDept);
			}
			// 设置分页信息
			query.setFirstResult(offset).setMaxResults(PAGE_SIZE);
			List i = query.list();
			Iterator it = i.iterator();
			tx.commit();
			List<SysUser> userInfoList = new ArrayList<SysUser>();
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
				userInfoList.add(u);
			}
			if(userInfoList.size() == 0)
				return null;
			return userInfoList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.SysPositionDao#getPageConut(int, int)
	 */
	public int getPageCount(int count, int pageSize) {
		
		return ( count + pageSize - 1) / pageSize;
	}
	
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.SysPositionDao#getAllUserByDeptCount(long)
	 */
	public int getAllUserByDeptCount(long suDept){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(u.suId) from SysUser u inner join u.sysRole r inner join u.sysDept d inner join u.sysPosition p ";
		if(suDept!=0){
			hql+=" where d.sdId=?";
		}
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			Query query = sess.createQuery(hql);
			if(suDept!=0){
				query.setLong(0, suDept);
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
}
