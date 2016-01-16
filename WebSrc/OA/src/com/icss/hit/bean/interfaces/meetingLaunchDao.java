package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Meeting;
import com.icss.hit.hibernate.vo.MeetingAttend;
import com.icss.hit.hibernate.vo.Room;
/**
 * @author 赵颖申
 *
 */
public interface meetingLaunchDao {
	
	/**
	 * @param beginTime 开始时间（String类型）
	 * @param endTime 结束时间（String类型）
	 * @return 返回是否为空
	 */
	public int getConflict(String beginTime, String endTime,long uid, String meetingRoom);
	
	/**
	 * @param meetingAttend
	 * @return 是否成功
	 */
	public long saveMeetingUsers(MeetingAttend meetingAttend);
	
	/**
	 * @param meeting
	 * @return Meeting的ID
	 */
	public long saveMeetingMessage(Meeting meeting);
	
	/**
	 * @param uid
	 * @return 用于获得发起会议的权利 
	 */
	public long getAccess(long uid);
	
	public List<Room> getRooms(long uid);
	
	public int timeConflict(String beginTime, String endTime,long uid, String meetingRoom);
}
