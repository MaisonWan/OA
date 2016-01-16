package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.ReceiverInfo;

public interface InBox {
	/**
	 * 根据收件人ID得到该用户所有收件箱留言
	 * @param searchType 搜索类型
	 * @param content 搜索内容
	 * @param reciverId 收件人ID
	 * @param pageNo 页码
	 * @return 所有留言
	 */
	public List<ReceiverInfo> getInBoxMessages(String searchType,String content,long receiverId,int pageNo);	
	/**
	 * 计算收件人总留言数
	 * @param receiverId 收件人ID
	 * @return 总数
	 */
	public int getInBoxMessagesCount(String searchType,String content,long receiverId);
	
	/**
	 * 计算总页数
	 * @param count 总行数
	 * @param pageSize 页面大小
	 * @return 总页数
	 */
	public int getPageCount(int count, int pageSize);
	
	/**
	 * 根据用户ID以及收件箱ID得到收件箱信息
	 * @param riId 收件箱ID
	 * @param userId 用户ID
	 * @return 收件箱信息
	 */
	public ReceiverInfo getReceiverInfo(long riId,long userId);
	
	/**
	 * 更新收件箱信息
	 * @param ri 要更新的对象
	 * @return 是否更新成功
	 */
	public boolean update(ReceiverInfo ri);
	
	/**
	 * 删除ReceiverInfo表中指定ID的信息
	 * @param riId ID
	 * @param userId 用户ID
	 * @return 是否成功删除
	 */
	public boolean delete(long riId, long userId);
	
	/**
	 * 删除收件箱信息
	 * @param ri 要删除的对象
	 * @return 是否删除成功
	 */
	public boolean delete(ReceiverInfo ri);
}
