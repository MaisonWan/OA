package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.RoomReg;

public interface outputGraphDao {
	
	/**
	 * @param year 年份
	 * @param month 月份
	 * @return 返回的是指定的月份的每一天的会议的数量的一个List
	 */
	public List<Integer> getRoomsRegistCount(String year,String month);
	
	
	
	/**
	 * @param Year
	 * @param Month
	 * @return  返回本月份的会议室申请的数量
	 */
	public int getRoomCount(int Year,int Month);
	
	/**
	 * @param Year
	 * @param Month
	 * @return 返回符合条件的会议室的申请
	 */
	public List<RoomReg> getRoomRegs(int Year,int Month);
}
