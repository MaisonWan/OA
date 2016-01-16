package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Message;
import com.icss.hit.hibernate.vo.MessageReceivers;
import com.icss.hit.hibernate.vo.ReceiverInfo;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author 赵颖申
 *
 */
/**
 * @author xw-pc
 *
 */
public interface MessageDao {
	//For sendMessage收件箱用滴
	/**
	 * @return true/false 
	 */
	public long saveReceiveMessage(Message message);
	
	//给草稿箱使用滴
	/**
	 * @param userIds     用户的ID的所有 
	 * @param mesTitle    标题
	 * @param mesContent  信息内容
	 * @return true/false
	 */
	public long saveReceiveUsers(ReceiverInfo receiver);

	/**
	 * @param userid 用户的id
	 * @return 该用户的收件箱里所有的信息的数量
	 */
	public int getInboxCount(long userid);

	/**
	 * 得到分页的数量
	 * @param count 数据的大小
	 * @param pageSize 每页显示的数量
	 * @return 分页的总页数
	 */
	public int getPageCount( int count, int pageSize );

	/**
	 * 按照指定页码返回发件箱的集合
	 * @param userId 用户ID
	 * @param page 页码
	 * @return 发件箱的集合
	 */
	public List<MessageReceivers> getOutboxByPage(long uid, int pageNo);
	
	/**
	 * @param mesID 信息ID
	 * @return  是不是成功删除
	 */
	public int delSendMes(long mesID);

	
	/**
	 * @param uid
	 * @return 草稿箱的集合，特定用户的草稿的集合
	 */
	public List<MessageReceivers> getAllDraftMessage(long uid,int pageNo);
	
	/**
	 * @param uid
	 * @return
	 */
	public int getDraftMessageCount(long uid);
	
	
	/**
	 * @param count
	 * @param pageSize
	 * @return
	 */
	public int getPageConut(int count, int pageSize);
	
	/**
	 * @param message
	 * @return
	 */
	public String[] getReceiverInfo(Message message);
	
	/**
	 * @param mesId
	 * @return
	 */
	public boolean deleteDraftMessage(long mesId);
	
	/**
	 * @param mesId
	 * @return
	 */
	public MessageReceivers getDraftMessage(long mesId);
	
	/**
	 * 得到指定用户的未读信息的数量
	 * @param uid 用户ID
	 * @return 符合条件的数量
	 */
	public int getMessageNotRead( long uid );
	
	/**
	 * 得到发件箱中的数量
	 * @param uid 用户ID
	 * @return 返回数量
	 */
	public int getOutBoxCount( long uid );
}
