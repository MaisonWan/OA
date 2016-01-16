/**
 * 
 */
package com.icss.hit.bean.interfaces;
import java.util.List;
import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.RoomReg;

import java.util.*;
/**
 * @author 许达
 * 实现了关于会议室的一些操作
 */
public interface MeetingRoomDao {
	
	
	
	/**
	 * 获得所有会议室信息
	 * @return 返回所有会议室信息
	 */
	public List<Room> getAllRoomInfo();
	
	
	/**
	 * 取得某个会议室信息
	 * @return 返回一个会议室的信息
	 */
	public Room getRoomInfo(long room_id);
	
	
	/**
	 * 更改会议室
	 * @param room 会议室信息
	 * @return 0表示添加成功 1表示有重名 2表示添加失败遇到未知错误
	 */
	public int updateRoomInfo(Room room);
	
	/**
	 * 删除会议室
	 * @param room_id 会议室ID
	 * @return 返回是否删除成功 true表示成功 false表示失败
	 */
	public boolean deleteRoom(long room_id);
	
	
	/**
	 * 添加会议室
	 * @param room 添加的会议室信息
	 * @return 0表示添加成功 1表示有重名 2表示添加失败遇到未知错误
	 */
	public int addRoom(Room room);
	
	
	/**
	 * 得到所有会议室的数量
	 * @return 返回会议室的数量
	 */
	public int getRoomAmount();
	
	
	/**
	 * 得到会议室分页的数量
	 * @param count 会议室总数量
	 * @param pageSize 每页显示的大小
	 * @return 总页码数量
	 */
	public int getPageCount(int count, int pageSize);
	
	
	/**
	 * 返回特定页面所有会议室的信息
	 * @param pageNo 指定页码
	 * @return 指定页的会议室信息
	 */
	public List<Room> getAllUserInfoByPage(int pageNo);
	
	/**
	 * 得到所有未被处理的会议室申请记录
	 * @return 所有记录
	 */
	public List<RoomReg> getAllUnsettledRoom();
	
	/**
	 * 得到所有未被处理的会议室申请记录的总数
	 * @return 总数
	 */
	public int getAllUnsettledRoomCount();
	
	/**
	 * 更新指定的会议室申请记录
	 * @param rr 会议室申请记录
	 * @return 是否更新成功
	 */
	public boolean update(RoomReg rr);
	
	/**
	 * 得到指定ID的会议记录
	 * @param rrId ID
	 * @return 会议记录
	 */
	public RoomReg getRoomRegByID(long rrId);
}
