package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.RoomReg;

public interface outputGraphDao {
	
	/**
	 * @param year ���
	 * @param month �·�
	 * @return ���ص���ָ�����·ݵ�ÿһ��Ļ����������һ��List
	 */
	public List<Integer> getRoomsRegistCount(String year,String month);
	
	
	
	/**
	 * @param Year
	 * @param Month
	 * @return  ���ر��·ݵĻ��������������
	 */
	public int getRoomCount(int Year,int Month);
	
	/**
	 * @param Year
	 * @param Month
	 * @return ���ط��������Ļ����ҵ�����
	 */
	public List<RoomReg> getRoomRegs(int Year,int Month);
}
