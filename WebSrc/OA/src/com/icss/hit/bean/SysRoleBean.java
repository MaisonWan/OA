package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.SysRoleDao;
import com.icss.hit.hibernate.HibernateSessionFactory;

import com.icss.hit.hibernate.vo.RolePower;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysPosition;
import com.icss.hit.hibernate.vo.SysPower;
import com.icss.hit.hibernate.vo.SysPowerType;
import com.icss.hit.hibernate.vo.SysRole;
import com.icss.hit.hibernate.vo.SysRolePower;
import com.icss.hit.hibernate.vo.SysUser;

public class SysRoleBean implements SysRoleDao {

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
				SysPowerType spt = (SysPowerType) obj[1];
				sp.setSysPowerType(spt);
				spList.add(sp);
			}
			if (spList.size() == 0)
				return null;
			return spList;
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
	 * @see com.icss.hit.bean.interfaces.SysRoleDao#addSysRolePower(com.icss.hit.hibernate.vo.SysRolePower)
	 */
	public boolean addSysRolePower(SysRolePower sysRolePower) {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();

			sess.save(sysRolePower);

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

	public List<SysPowerType> getAllsysPowerType() {
		// TODO Auto-generated method stub
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = sess.beginTransaction();
		List<SysPowerType> list = null;
		try {
			list = sess.createQuery("from SysPowerType").list();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.icss.hit.bean.interfaces.SysRoleDao#addSysRole(com.icss.hit.hibernate.vo.SysRole)
	 */
	public long addSysRole(SysRole role) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = sess.beginTransaction();
		List<SysPowerType> list = null;
		try {
			sess.save(role);
			tx.commit();
			return role.getSrId();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return -1;
		} finally {
			sess.close();
		}
	}

	public List<SysRole> getAllSysRoleName() {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = sess.beginTransaction();
		List<SysRole> list = null;
		try {
			list = sess.createQuery("from SysRole").list();
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

	public SysRole getRoleInfo(long id) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from SysRole sr where sr.srId=?";
		try {
			tx = sess.beginTransaction();
			// �������ʾҳ��ʼ���
			List l = sess.createQuery(hql).setLong(0, id).list();
			tx.commit();
			Iterator it = l.iterator();
			if (it.hasNext()) {
				Object obj = (Object) it.next();
				SysRole sr = (SysRole) obj;
				return sr;
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

	public List<Long> getRoleID() {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select sr.srId from SysRole sr";
		// ����long�͵�List���г���ɫ��ID�������
		List<Long> IDList = new ArrayList<Long>();
		try {
			tx = sess.beginTransaction();
			// �������ʾҳ��ʼ���,ѭ�����SysRole��ID
			List l = sess.createQuery(hql).list();
			tx.commit();
			Iterator it = l.iterator();
			while (it.hasNext()) {
				Object obj = (Object) it.next();
				long srID = (Long) obj;
				IDList.add(srID);
			}
			return IDList;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}

	public List<RolePower> getRoleList(List<Long> IDList) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		Query query = null;
		List<RolePower> rolePowers = new ArrayList<RolePower>();
		String spName = "";
		String hqlRoleName = "select sr.srName from SysRole sr where sr.srId=?";
		String hqlPowerID = "select srp.srpId from SysRolePower srp inner join srp.sysRole sr where sr.srId=?";
		String hqlPowerName = "select sp.spName from SysRolePower srp inner join srp.sysPower sp where srp.srpId=?";
		try {
			tx = sess.beginTransaction();
			// ѭ����ɫ����ÿ����ɫ��Ȩ��
			for (int i = 0; i < IDList.size(); i++) {
				RolePower rolePower = new RolePower();
				long srID = IDList.get(i);
				// ���ڻ�ý�ɫ������
				query = sess.createQuery(hqlRoleName).setLong(0, srID);
				Object o = query.uniqueResult();
				// ���ý�ɫ������
				rolePower.setRoleName(o.toString());

				// ���ڻ�����ɫ���Ӧ��Ȩ�޵����⡤������������
				Query query1 = sess.createQuery(hqlPowerID).setLong(0, srID);
				List l = query1.list();
				Iterator it = l.iterator();
				while (it.hasNext()) {
					Object obj = (Object) it.next();
					long srpID = (Long) obj; // ��õ����Ӧ�Ľ�ɫȨ�ޱ��ֵ��������������
					Query query2 = sess.createQuery(hqlPowerName).setLong(0, srpID);
					Object power = query2.uniqueResult();
					// ���Ȩ�޵�����
					String Name = (String) power;
					spName += Name + "--";
				}
				// ����ɫ����Ȩ��
				rolePower.setPowers(spName);
				rolePowers.add(rolePower);
				spName = "";
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
		return rolePowers;
	}
}
