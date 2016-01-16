package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.RolePower;
import com.icss.hit.hibernate.vo.SysPower;
import com.icss.hit.hibernate.vo.SysPowerType;
import com.icss.hit.hibernate.vo.SysRole;
import com.icss.hit.hibernate.vo.SysRolePower;


public interface SysRoleDao {
	
	/**
	 * @return 所有的权限列表
	 */
	public List<SysPower> getAllSysPower();
	/**
	 * @param sysRolePower 角色权限
	 * @return 添加是否成功
	 */
	public boolean addSysRolePower(SysRolePower sysRolePower);
	/**
	 * @return 所有的权限类别列表
	 */
	public List<SysPowerType> getAllsysPowerType();
	/**
	 * 添加一个新的角色
	 * @param role 角色名称
	 * @return 返回数据库的Id
	 */
	public long addSysRole( SysRole role );
	
	/**
	 * 得到所有角色列表
	 * @return 角色列表
	 */
	public List<SysRole> getAllSysRoleName();
	
	/**
	 * 得到指定ID的系统角色
	 * @param id 角色ID
	 * @return 系统角色
	 */
	public SysRole getRoleInfo(long id);
	
	/**
	 * @return 返回角色ＩＤＳ
	 */
	public List<Long> getRoleID();
	
	/**
	 * @param IDList
	 * @return　返回关系
	 */
	public List<RolePower> getRoleList(List<Long> IDList);
}
