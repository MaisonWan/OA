package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.RoomReg;

public interface MeetingOrder {
	/**
	 * �õ����������Ļ������б�
	 * @param pageNo ҳ��
	 * @param rName ����������
	 * @param containNum ��������
	 * @param beginTime ��ʼʱ��
	 * @param endTime ����ʱ��
	 * @return �������б�
	 */
	public List<Room> getAvailableRoom(int pageNo,String rName,int containNum,String beginTime,String endTime);
	
	/**
	 * ������������Ļ���������
	 * @param rName ����������
	 * @param containNum ��������
	 * @param beginTime ��ʼʱ��
	 * @param endTime ����ʱ��
	 * @return ����
	 */
	public int getAvailableRoomCount(String rName,int containNum,String beginTime,String endTime);
	
	/**
	 * ������ҳ��
	 * @param count ������
	 * @param pageSize ҳ���С
	 * @return ��ҳ��
	 */
	public int getPageCount(int count, int pageSize); 
	
}
