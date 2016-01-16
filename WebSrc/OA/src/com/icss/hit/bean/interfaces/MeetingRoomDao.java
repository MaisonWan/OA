/**
 * 
 */
package com.icss.hit.bean.interfaces;
import java.util.List;
import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.RoomReg;

import java.util.*;
/**
 * @author ���
 * ʵ���˹��ڻ����ҵ�һЩ����
 */
public interface MeetingRoomDao {
	
	
	
	/**
	 * ������л�������Ϣ
	 * @return �������л�������Ϣ
	 */
	public List<Room> getAllRoomInfo();
	
	
	/**
	 * ȡ��ĳ����������Ϣ
	 * @return ����һ�������ҵ���Ϣ
	 */
	public Room getRoomInfo(long room_id);
	
	
	/**
	 * ���Ļ�����
	 * @param room ��������Ϣ
	 * @return 0��ʾ��ӳɹ� 1��ʾ������ 2��ʾ���ʧ������δ֪����
	 */
	public int updateRoomInfo(Room room);
	
	/**
	 * ɾ��������
	 * @param room_id ������ID
	 * @return �����Ƿ�ɾ���ɹ� true��ʾ�ɹ� false��ʾʧ��
	 */
	public boolean deleteRoom(long room_id);
	
	
	/**
	 * ��ӻ�����
	 * @param room ��ӵĻ�������Ϣ
	 * @return 0��ʾ��ӳɹ� 1��ʾ������ 2��ʾ���ʧ������δ֪����
	 */
	public int addRoom(Room room);
	
	
	/**
	 * �õ����л����ҵ�����
	 * @return ���ػ����ҵ�����
	 */
	public int getRoomAmount();
	
	
	/**
	 * �õ������ҷ�ҳ������
	 * @param count ������������
	 * @param pageSize ÿҳ��ʾ�Ĵ�С
	 * @return ��ҳ������
	 */
	public int getPageCount(int count, int pageSize);
	
	
	/**
	 * �����ض�ҳ�����л����ҵ���Ϣ
	 * @param pageNo ָ��ҳ��
	 * @return ָ��ҳ�Ļ�������Ϣ
	 */
	public List<Room> getAllUserInfoByPage(int pageNo);
	
	/**
	 * �õ�����δ������Ļ����������¼
	 * @return ���м�¼
	 */
	public List<RoomReg> getAllUnsettledRoom();
	
	/**
	 * �õ�����δ������Ļ����������¼������
	 * @return ����
	 */
	public int getAllUnsettledRoomCount();
	
	/**
	 * ����ָ���Ļ����������¼
	 * @param rr �����������¼
	 * @return �Ƿ���³ɹ�
	 */
	public boolean update(RoomReg rr);
	
	/**
	 * �õ�ָ��ID�Ļ����¼
	 * @param rrId ID
	 * @return �����¼
	 */
	public RoomReg getRoomRegByID(long rrId);
}
