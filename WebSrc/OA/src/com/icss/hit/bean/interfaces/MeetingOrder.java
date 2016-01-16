package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.RoomReg;

public interface MeetingOrder {
	/**
	 * 得到符合条件的会议室列表
	 * @param pageNo 页码
	 * @param rName 会议室名称
	 * @param containNum 容纳人数
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return 会议室列表
	 */
	public List<Room> getAvailableRoom(int pageNo,String rName,int containNum,String beginTime,String endTime);
	
	/**
	 * 计算符合条件的会议室总数
	 * @param rName 会议室名称
	 * @param containNum 容纳人数
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return 总数
	 */
	public int getAvailableRoomCount(String rName,int containNum,String beginTime,String endTime);
	
	/**
	 * 计算总页数
	 * @param count 总行数
	 * @param pageSize 页面大小
	 * @return 总页数
	 */
	public int getPageCount(int count, int pageSize); 
	
}
