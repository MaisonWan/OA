package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.RolePower;
import com.icss.hit.hibernate.vo.SysPower;
import com.icss.hit.hibernate.vo.SysPowerType;
import com.icss.hit.hibernate.vo.SysRole;
import com.icss.hit.hibernate.vo.SysRolePower;


public interface SysRoleDao {
	
	/**
	 * @return ���е�Ȩ���б�
	 */
	public List<SysPower> getAllSysPower();
	/**
	 * @param sysRolePower ��ɫȨ��
	 * @return ����Ƿ�ɹ�
	 */
	public boolean addSysRolePower(SysRolePower sysRolePower);
	/**
	 * @return ���е�Ȩ������б�
	 */
	public List<SysPowerType> getAllsysPowerType();
	/**
	 * ���һ���µĽ�ɫ
	 * @param role ��ɫ����
	 * @return �������ݿ��Id
	 */
	public long addSysRole( SysRole role );
	
	/**
	 * �õ����н�ɫ�б�
	 * @return ��ɫ�б�
	 */
	public List<SysRole> getAllSysRoleName();
	
	/**
	 * �õ�ָ��ID��ϵͳ��ɫ
	 * @param id ��ɫID
	 * @return ϵͳ��ɫ
	 */
	public SysRole getRoleInfo(long id);
	
	/**
	 * @return ���ؽ�ɫ�ɣģ�
	 */
	public List<Long> getRoleID();
	
	/**
	 * @param IDList
	 * @return�����ع�ϵ
	 */
	public List<RolePower> getRoleList(List<Long> IDList);
}
