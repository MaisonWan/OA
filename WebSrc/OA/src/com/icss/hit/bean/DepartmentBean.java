/**
 * 
 */
package com.icss.hit.bean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.Department;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.Message;
import com.icss.hit.hibernate.vo.ReceiverInfo;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysUser;
/**
 * 封装对部门管理的一些操作
 * @author 万里鹏
 * 
 */
public class DepartmentBean implements Department{

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.Department#deleteDept(long)
	 */
	public static int PAGE_SIZE = 10;
	public boolean deleteDept(long id) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction delDtTrans = null;
		try {
			delDtTrans = sess.beginTransaction();
			
			sess.delete((SysDept) sess.get(SysDept.class, id));
			delDtTrans.commit();
			return true;
		} catch (Exception e) {
			delDtTrans.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.Department#getAllDept()
	 */
/*	public ArrayList<SysDept> getAllDept() {
		// 实例化数据库操作的类
		DBTools db = new DBTools();
		String sql = "select * from sys_dept";
		db.excuteQuery(sql);
		ArrayList<SysDept> list = new ArrayList<SysDept>();
		//System.out.println("开始查询");
		try {
			while(db.next()){
				//System.out.println("有结果数据");
				SysDept dept = new SysDept();
				dept.setSd_id(db.getLong("sd_id"));
				dept.setSd_name(db.getString("sd_name"));
				dept.setSd_tel(db.getString("sd_tel"));
				//dept.setSd_info(db.getString("sd_info"));
				list.add(dept);
			}
			//System.out.println("查询完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return list;
	}*/
	public List<SysDept> getAllDept() {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = sess.beginTransaction();
		List<SysDept> list = null;
		try{
			list = sess.createQuery("from SysDept").list();
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
	 * @see com.icss.hit.bean.interfaces.Department#getAllDeptByPage(int)
	 */
	public List<SysDept> getAllDeptByPage(int pageNo) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysDept";
		try {
			tx = sess.beginTransaction();
			int offset = ( pageNo -1 ) * PAGE_SIZE;
			// 查询指定ID的用户添加的日程安排的数量
			List list = sess.createQuery(hql).setFirstResult(offset).setMaxResults(PAGE_SIZE).list();
			tx.commit();
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
	 * @see com.icss.hit.bean.interfaces.Department#getDept(long)
	 */
	public SysDept getDept(long id) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysDept sd where sd.sdId=?";
		try {
			tx = sess.beginTransaction();
			
			List l = sess.createQuery(hql).setLong(0, id).list();
			tx.commit();
			/*Iterator it = l.iterator();
			if (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				SysDept sd = (SysDept) obj[0];
				return sd;
			}*/
			if(l!=null&&l.size()>0)
			{
				return (SysDept)l.get(0);
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
	 * @see com.icss.hit.bean.interfaces.Department#getDeptCount()
	 */
	public int getDeptCount() {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysDept";
		try {
			tx = sess.beginTransaction();
			// 查询指定ID的用户其拥有的收件箱的信息
			List list = sess.createQuery(hql).list();
			tx.commit();
			return list.size();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return 0;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.Department#getPageCount(int, int)
	 */
	public int getPageCount(int count, int pageSize) {
		// TODO Auto-generated method stub
		return ( count + pageSize - 1) / pageSize;
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.Department#updateDept(com.icss.hit.table.SysDept)
	 */
	public boolean updateDept(SysDept dept) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			sess.update(dept);
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

	public boolean addDept(SysDept dept) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// 返回系统中用户的数量
			sess.saveOrUpdate(dept);
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

	public boolean checkDept(long sdId) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(s.suId) from SysUser s inner join s.sysDept sd where sd.sdId = ?";
		try {
			tx = sess.beginTransaction();
			//判断部门中已经有使用者
			Object o = sess.createQuery(hql).setLong(0, sdId).uniqueResult();
			tx.commit();
			if(o!=null)
			{
				if(Integer.parseInt(o.toString()) > 0 )
				{
					return false;
				}
				return true;
			}
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sess.close();
		}
	}
	
	
}
