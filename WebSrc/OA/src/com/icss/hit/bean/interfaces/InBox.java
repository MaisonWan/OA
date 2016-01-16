package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.ReceiverInfo;

public interface InBox {
	/**
	 * �����ռ���ID�õ����û������ռ�������
	 * @param searchType ��������
	 * @param content ��������
	 * @param reciverId �ռ���ID
	 * @param pageNo ҳ��
	 * @return ��������
	 */
	public List<ReceiverInfo> getInBoxMessages(String searchType,String content,long receiverId,int pageNo);	
	/**
	 * �����ռ�����������
	 * @param receiverId �ռ���ID
	 * @return ����
	 */
	public int getInBoxMessagesCount(String searchType,String content,long receiverId);
	
	/**
	 * ������ҳ��
	 * @param count ������
	 * @param pageSize ҳ���С
	 * @return ��ҳ��
	 */
	public int getPageCount(int count, int pageSize);
	
	/**
	 * �����û�ID�Լ��ռ���ID�õ��ռ�����Ϣ
	 * @param riId �ռ���ID
	 * @param userId �û�ID
	 * @return �ռ�����Ϣ
	 */
	public ReceiverInfo getReceiverInfo(long riId,long userId);
	
	/**
	 * �����ռ�����Ϣ
	 * @param ri Ҫ���µĶ���
	 * @return �Ƿ���³ɹ�
	 */
	public boolean update(ReceiverInfo ri);
	
	/**
	 * ɾ��ReceiverInfo����ָ��ID����Ϣ
	 * @param riId ID
	 * @param userId �û�ID
	 * @return �Ƿ�ɹ�ɾ��
	 */
	public boolean delete(long riId, long userId);
	
	/**
	 * ɾ���ռ�����Ϣ
	 * @param ri Ҫɾ���Ķ���
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public boolean delete(ReceiverInfo ri);
}
