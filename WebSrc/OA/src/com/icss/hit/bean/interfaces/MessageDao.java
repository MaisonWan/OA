package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Message;
import com.icss.hit.hibernate.vo.MessageReceivers;
import com.icss.hit.hibernate.vo.ReceiverInfo;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author ��ӱ��
 *
 */
/**
 * @author xw-pc
 *
 */
public interface MessageDao {
	//For sendMessage�ռ����õ�
	/**
	 * @return true/false 
	 */
	public long saveReceiveMessage(Message message);
	
	//���ݸ���ʹ�õ�
	/**
	 * @param userIds     �û���ID������ 
	 * @param mesTitle    ����
	 * @param mesContent  ��Ϣ����
	 * @return true/false
	 */
	public long saveReceiveUsers(ReceiverInfo receiver);

	/**
	 * @param userid �û���id
	 * @return ���û����ռ��������е���Ϣ������
	 */
	public int getInboxCount(long userid);

	/**
	 * �õ���ҳ������
	 * @param count ���ݵĴ�С
	 * @param pageSize ÿҳ��ʾ������
	 * @return ��ҳ����ҳ��
	 */
	public int getPageCount( int count, int pageSize );

	/**
	 * ����ָ��ҳ�뷵�ط�����ļ���
	 * @param userId �û�ID
	 * @param page ҳ��
	 * @return ������ļ���
	 */
	public List<MessageReceivers> getOutboxByPage(long uid, int pageNo);
	
	/**
	 * @param mesID ��ϢID
	 * @return  �ǲ��ǳɹ�ɾ��
	 */
	public int delSendMes(long mesID);

	
	/**
	 * @param uid
	 * @return �ݸ���ļ��ϣ��ض��û��Ĳݸ�ļ���
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
	 * �õ�ָ���û���δ����Ϣ������
	 * @param uid �û�ID
	 * @return ��������������
	 */
	public int getMessageNotRead( long uid );
	
	/**
	 * �õ��������е�����
	 * @param uid �û�ID
	 * @return ��������
	 */
	public int getOutBoxCount( long uid );
}
