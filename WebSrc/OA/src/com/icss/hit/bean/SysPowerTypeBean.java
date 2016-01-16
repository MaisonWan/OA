package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.SysPowerTypeDao;
import com.icss.hit.hibernate.HibernateSessionFactory;

import com.icss.hit.hibernate.vo.ScheduleConfig;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysPower;
import com.icss.hit.hibernate.vo.SysPowerType;

public class SysPowerTypeBean implements SysPowerTypeDao {

	public List<SysPowerType> getAllSysPowerType() {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		
		Transaction tx = sess.beginTransaction();
		List<SysPowerType> list = null;
		try{
			list = sess.createQuery("from SysPowerType").list();
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

	public boolean addSysPower(SysPower sysPower) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			
			sess.save(sysPower);
			
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

	public List<SysPower> getAllSysPower() {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysPower sp inner join sp.sysPowerType spt";
		try {
			tx = sess.beginTransaction();
			
			List l = sess.createQuery(hql).list();
			tx.commit();
			List<SysPower> spList = new ArrayList<SysPower>();
			Iterator it = l.iterator();
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				SysPower sp = (SysPower) obj[0];
				SysPowerType spt =(SysPowerType)obj[1];
				sp.setSysPowerType(spt);
				spList.add(sp);
			}
			if(spList.size()==0)
			return null ;
			return spList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}

	public boolean checkSysPower(long spid) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(srp.srpId) from SysRolePower srp inner join srp.sysPower sp where sp.spId = ?";
		try {
			tx = sess.beginTransaction();
			//判断权限已经被使用
			Object o = sess.createQuery(hql).setLong(0, spid).uniqueResult();
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

	public boolean deleteSysPower(long spid) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction delDtTrans = null;
		try {
			delDtTrans = sess.beginTransaction();
			
			sess.delete((SysPower) sess.get(SysPower.class, spid));
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

}
