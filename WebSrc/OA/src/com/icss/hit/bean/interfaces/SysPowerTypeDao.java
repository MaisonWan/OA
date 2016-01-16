package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.SysPower;
import com.icss.hit.hibernate.vo.SysPowerType;

public interface SysPowerTypeDao {
	/**
	 * @return 所有的分类列表
	 */
	public List<SysPowerType> getAllSysPowerType();
	/**
	 * @param sysPower 系统权限表
	 * @return 添加是否成功
	 */
	public boolean addSysPower(SysPower sysPower);
	/**
	 * @return 返回所有分权限列表
	 */
	public List<SysPower> getAllSysPower();
	/**
	 * @param spid 权限的id主键
	 * @return 是否已经存在用户使用该权限
	 */
	public boolean checkSysPower(long spid);
	
	/**
	 * @param spid 权限的id主键
	 * @return 是否删除成功
	 */
	public boolean deleteSysPower(long spid);
}
