package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.SysPower;
import com.icss.hit.hibernate.vo.SysPowerType;

public interface SysPowerTypeDao {
	/**
	 * @return ���еķ����б�
	 */
	public List<SysPowerType> getAllSysPowerType();
	/**
	 * @param sysPower ϵͳȨ�ޱ�
	 * @return ����Ƿ�ɹ�
	 */
	public boolean addSysPower(SysPower sysPower);
	/**
	 * @return �������з�Ȩ���б�
	 */
	public List<SysPower> getAllSysPower();
	/**
	 * @param spid Ȩ�޵�id����
	 * @return �Ƿ��Ѿ������û�ʹ�ø�Ȩ��
	 */
	public boolean checkSysPower(long spid);
	
	/**
	 * @param spid Ȩ�޵�id����
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public boolean deleteSysPower(long spid);
}
