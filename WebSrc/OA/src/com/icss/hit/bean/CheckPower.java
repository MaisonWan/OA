/**
 * 
 */
package com.icss.hit.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.SysPower;

/**
 * �����������û�ID���ж��Ƿ����ĳ��Ȩ��
 * @author ������
 *
 */
public class CheckPower {

	/**
	 * �û�ID
	 */
	private long userId; 
	
	/**
	 * ���ŵ�Ȩ��
	 */
	public static long ADMIN_DEPT = 41; 
	/**
	 * Ȩ�޹���
	 */
	public static long ADMIN_POWER = 42;
	/**
	 * �����ɫ��Ȩ��
	 */
	public static long ADMIN_ROLE = 43; 
	/**
	 * �Ի����ҵĹ���
	 */
	public static long ADMIN_ROOM = 1; 
	/**
	 * �Ի��������������
	 */
	public static long ADMIN_ROOM_REG = 2;  
	/**
	 * Ա����Ϣ����
	 */
	public static long ADMIN_STUFF_INFO = 44;  
	/**
	 * ����µ�Ա��
	 */
	public static long ADMIN_STUFF_ADD = 4; 
	
	// ��ȡ�û�Ȩ��
	private ArrayList<Long> powerList = new ArrayList<Long>();
	public CheckPower( long userId ){
		this.userId = userId;
	}
	public CheckPower(){
		
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	

	/**
	 * �ж��û��Ƿ���ĳһȨ��
	 * @param userId �û�ID
	 * @param powerID Ȩ��ID
	 * @return �Ƿ��д�Ȩ��
	 */
	public boolean check( long userId, long powerID ){
		this.userId = userId;
		// �������û�Ȩ�޵��б�
		for( Long pw : powerList){
			if( pw.compareTo(powerID) == 0 ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �˲��Ƿ���һ��Ȩ��
	 * @param powerID Ȩ�޵�ID
	 * @return ��/��
	 */
	public boolean check( long powerID ){
		
		// �������û�Ȩ�޵��б�
		for( Long pw : powerList){
			if( pw.compareTo(powerID) == 0 ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �����û���Ȩ��
	 */
	public void getPower(){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select srp.sysPower from SysRolePower srp where srp.sysRole = " +
				"(select su.sysRole from SysUser su where su.suId=?)";
		try {
			tx = sess.beginTransaction();
			
			List list = sess.createQuery(hql).setLong(0, this.userId).list();
			tx.commit();
			for( int i = 0; i < list.size(); i++ ){
				SysPower sp = (SysPower)list.get(i);
				powerList.add(sp.getSpId());
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			sess.close();
		}
	}
	
	/**
	 * �ж��û�Ȩ���Ƿ�Ϊ��
	 * @return Ȩ���б��Ƿ�Ϊ��
	 */
	public boolean isEmpty(){
		if( powerList.size() == 0 )
			return true;
		return false;
	}
}
