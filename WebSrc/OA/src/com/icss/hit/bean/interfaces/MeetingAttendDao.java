package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Meeting;
import com.icss.hit.hibernate.vo.MeetingAttend;
import com.icss.hit.hibernate.vo.SysUser;

public interface MeetingAttendDao {
	/**
	 * 根据ID查询用户参与的会议集合
	 * @param pageNo 页码
	 * @param userId 用户ID
	 * @return 指定页全部会议集合
	 */
	public List<Meeting> getAllAssociateMeeting(int pageNo,long userId);
	
	/**
	 * 计算指定用户参与的会议集合总数
	 * @param userId 用户ID
	 * @return 总数
	 */
	public int getAllAssociateMeetingCount(long userId);
	
	/**
	 * 计算总分页数
	 * @param count 总行数
	 * @param pageSize 页面大小
	 * @return 总分页数
	 */
	public int getPageCount(int count, int pageSize);
	
	/**
	 * 得到指定人发起会议的参与人列表
	 * @param mtId 会议ID
	 * @return 参与人列表
	 */
	public List<SysUser> getAllAssociateSysUser(long mtId);
	
	/**
	 * 得到指定会议的详细信息
	 * @param mtId 会议ID
	 * @return 详细信息
	 */
	public Meeting getMeetingDetails(long mtId);
}
