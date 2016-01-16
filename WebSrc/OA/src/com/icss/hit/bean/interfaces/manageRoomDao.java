package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Room;

public interface manageRoomDao {
	
	/**
	 * @return �������еķ������Ϣ
	 */
	public List<Room> getAllRooms(int pageNo);
	
	/**
	 * @return ���������
	 */
	public int getRoomCount();
	
	/**
	 * @param count
	 * @param pageSize
	 * @return �����ҳ��
	 */
	public int getPageCount(int count, int pageSize);
	
	/**
	 * @param roomID
	 * @return �����ǲ��Ǵ�������
	 */
	public long isExist(long roomID);
	
	/**
	 * @param roomID
	 * @return �Ƿ�ɾ������ɹ�
	 */
	public boolean deleteRoom(long roomID);
	
	/**
	 * @param roomID
	 * @return �޸ķ����Ƿ�ɹ�
	 */
	public Room readRoom(long roomID);
	
	/**
	 * @param roomID
	 * @param rname
	 * @param rcontain
	 * @param rInfo
	 * @return  �����Ƿ�ɹ�
	 */
	public boolean saveRoom(Room room);
}
