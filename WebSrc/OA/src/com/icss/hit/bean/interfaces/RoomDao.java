package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.RoomReg;

public interface RoomDao {
	/**
	 * 返回指定用户的指定页
	 * @param userId 用户ID
	 * @param pageNo 页码
	 * @return 指定页所有信息
	 */
	public List<RoomReg> getRoomRegInfo(long userId,int pageNo);
	
	/**
	 * 计算指定用户所有申请会议室的数量
	 * @param userId 用户ID
	 * @return 总数
	 */
	public int getRoomRegInfoCount(long userId);
	
	/**
	 * 计算总页数
	 * @param count 总行数	
	 * @param pageSize 页面大小
	 * @return 总页数
	 */
	public int getPageCount(int count, int pageSize);
	
	
	/**
	 * 取消会议申请
	 * @param rrId 申请ID
	 * @param userId 用户ID
	 * @return 是否取消成功
	 */
	public boolean delete(long rrId, long userId);
	
	/**
	 * 得到指定ID的会议室申请记录
	 * @param rrId RoomReg表ID
	 * @param userId 用户ID
	 * @return 会议室申请记录
	 */
	public RoomReg getRoomRegDetails(long rrId,long userId);
}
