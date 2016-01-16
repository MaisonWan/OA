package com.icss.hit.bean.interfaces;

import java.util.Date;

import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.RoomReg;
import com.icss.hit.hibernate.vo.SysUser;

public interface searchRoomDao {
	/**
	 * @param beginTime
	 * @param endTime
	 * @return true/false 用与显示是否和数据库里的冲突了
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
	 * @return   成功或失败
	 */
	public int saveRoomReg(RoomReg roomReg);
}
