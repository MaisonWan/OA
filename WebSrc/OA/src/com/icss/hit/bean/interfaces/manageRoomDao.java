package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Room;

public interface manageRoomDao {
	
	/**
	 * @return 返回所有的房间的信息
	 */
	public List<Room> getAllRooms(int pageNo);
	
	/**
	 * @return 房间的数量
	 */
	public int getRoomCount();
	
	/**
	 * @param count
	 * @param pageSize
	 * @return 房间的页数
	 */
	public int getPageCount(int count, int pageSize);
	
	/**
	 * @param roomID
	 * @return 返回是不是存在申请
	 */
	public long isExist(long roomID);
	
	/**
	 * @param roomID
	 * @return 是否删除房间成功
	 */
	public boolean deleteRoom(long roomID);
	
	/**
	 * @param roomID
	 * @return 修改房间是否成功
	 */
	public Room readRoom(long roomID);
	
	/**
	 * @param roomID
	 * @param rname
	 * @param rcontain
	 * @param rInfo
	 * @return  返回是否成功
	 */
	public boolean saveRoom(Room room);
}
