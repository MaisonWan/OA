package com.icss.hit.bean.interfaces;

import java.util.Date;

import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.RoomReg;
import com.icss.hit.hibernate.vo.SysUser;

public interface searchRoomDao {
	/**
	 * @param beginTime
	 * @param endTime
	 * @return true/false ������ʾ�Ƿ�����ݿ���ĳ�ͻ��
	 */
	public int findConflict(Date beginTime,Date endTime,long roomNO);
	
	/**
	 * @param meetingName
	 * @param meetingContent
	 * @param beginTime
	 * @param endTime
	 * @param room
	 * @param user
	 * @param attendSum
	 * @return   �ɹ���ʧ��
	 */
	public int saveRoomReg(RoomReg roomReg);
}
