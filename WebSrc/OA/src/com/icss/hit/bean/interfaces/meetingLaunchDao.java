package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Meeting;
import com.icss.hit.hibernate.vo.MeetingAttend;
import com.icss.hit.hibernate.vo.Room;
/**
 * @author ��ӱ��
 *
 */
public interface meetingLaunchDao {
	
	/**
	 * @param beginTime ��ʼʱ�䣨String���ͣ�
	 * @param endTime ����ʱ�䣨String���ͣ�
	 * @return �����Ƿ�Ϊ��
	 */
	public int getConflict(String beginTime, String endTime,long uid, String meetingRoom);
	
	/**
	 * @param meetingAttend
	 * @return �Ƿ�ɹ�
	 */
	public long saveMeetingUsers(MeetingAttend meetingAttend);
	
	/**
	 * @param meeting
	 * @return Meeting��ID
	 */
	public long saveMeetingMessage(Meeting meeting);
	
	/**
	 * @param uid
	 * @return ���ڻ�÷�������Ȩ�� 
	 */
	public long getAccess(long uid);
	
	public List<Room> getRooms(long uid);
	
	public int timeConflict(String beginTime, String endTime,long uid, String meetingRoom);
}
