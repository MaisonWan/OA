/**
 * 
 */
package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.*;

/**
 * @author ���
 * CardTypeBean�Ľӿ��� �����˻�ȡ��Ƭ����Ϣ �����ض�ҳ��������Ƭ�е���Ϣ
 * �õ�ĳ���û���������Ƭ�е����� �õ���Ƭ�з�ҳ������ ������Ƭ�е���Ϣ ɾ��ĳ����Ƭ����Ϣ6������
 *
 */

public interface CardTypeDao {
	
	/**
	 * �õ�ĳ�û�ĳ��Ƭ�е���Ϣ
	 * @param suId ĳ���û���Id
	 * @param ctId ���û��µ���Ƭ��Id
	 * @return ��Ƭ����Ϣ
	 */
	public CardType getCardType(long suId, long ctId);
	
	
	/**
	 * ����ĳ���û���������Ƭ��
	 * @param id �û���ID
	 * @return ������Ƭ��
	 */
	public List<CardType> getAllCardType(long id);
		
	
	
	/**
	 * �����ض�ҳ��������Ƭ�е���Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param userId ָ���û���Id
	 * @return ָ��ҳ��ȫ����Ƭ����Ϣ
	 */
	public List<CardType> getAllCardTypeByPage(int pageNo, long userId);
	
	
	/**
	 * �õ�ĳ���û���������Ƭ�е����� ����ҳ��ʾ��
	 * @param id ĳ���û���ID
	 * @return ��Ƭ������
	 */
	public int getCardTypeCount(long id);
	
	
	/**
	 * �õ���Ƭ�з�ҳ������
	 * @param count ĳ���û�����Ƭ�е�������
	 * @param pageSize ÿҳ��ʾ�Ĵ�С
	 * @return ��ҳ������
	 */
	public int getPageCount(int count, int pageSize);
	
	
	/**
	 * ����/������Ƭ�е���Ϣ
	 * @param cType ��Ҫ����/�������Ƭ����Ϣ
	 * @return �Ƿ�ɹ��������� 0����ɹ�ִ�и���/���� 1���������� 2����δ֪�쳣
	 */
	public int updateCardType(CardType cType);
	
	
	/**
	 * ɾ��ĳ����Ƭ����Ϣ
	 * @param id Ҫɾ������Ƭ�е�id
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public boolean deleteCardType(long id);
	
	/**
	 * @param uid
	 * @param cardTypeID
	 * @return �ǲ�����������ļ���
	 */
	public boolean isUsersType(long uid,long cardTypeID);
	
	
	/**
	 * @param cardTypeID
	 * @return  �����Ƿ�����Ƭ
	 */
	public boolean hasCard(long cardTypeID);
	
	}
