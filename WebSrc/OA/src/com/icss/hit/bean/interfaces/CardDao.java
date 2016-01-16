/**
 * 
 */
package com.icss.hit.bean.interfaces;
import java.util.List;
import com.icss.hit.hibernate.vo.Card;

/**
 * @author ����
 * 2009��8��1��
 */
/**
 * @author xw-pc
 *
 */
/**
 * @author xw-pc
 *
 */
/**
 * @author xw-pc
 *
 */
public interface CardDao {
	/**
	 * ����������������
	 * @param userName ����
	 * @param userSex �Ա�
	 * @param cardType ��Ƭ�з���
	 * @param company ��˾
	 * @param pageNo ҳ��
	 * @param id �û�Id
	 * @return ���ط�����������Ƭ��Ϣ
	*/
	public List<Card> getSearchedCardInfo(String userName,int userSex,String company,int cardType,int pageNo,long id); 
	
	/**
	 * ��������������Ƭ����
	 * @param userName ����
	 * @param userSex �Ա�
	 * @param company ��˾
	 * @param cardType ��Ƭ�з���
	 * @param id �û�Id
	 * @return ������Ƭ������
	 */
	public int getSearchedCardCount(String userName,int userSex,String company,int cardType,long id);
	
	/**
	 * �������ҳ��ҳ��
	 * @param count ����
	 * @param pageSize ÿҳ������
	 * @return ������ҳ��
	 */

	public int getPageCount(int count, int pageSize);
	
	/**
	 * ����Ա�����г���Ա�����й�����Ƭ
	 * @param id Ա��ID
	 * @param pageNo ҳ�� 
	 * @return ���й�����Ƭ��
	 */
	public List<Card> getShareCard(long id,int pageNo);
	
	/**
	 * ����ָ���û�������Ƭ������
	 * @param id Ա��ID
	 * @return ��ҳ��
	 */
	public int getShareCardCount(long id);

	
	/**
	 * ���һ����Ƭ
	 * @param card ��Ƭ��ʵ��
	 * @return �Ƿ���ӳɹ�
	 */
	public boolean addCard(Card card);
	/**
	 * ����һ����Ƭ
	 * @param card ��Ƭ��ʵ��
	 * @return �Ƿ���³ɹ�
	 */
	public boolean updateCard(Card card);
	/**
	 * ����һ����Ƭ����Ϣ
	 * @param id ��Ƭ������ID
	 * @return ��Ƭ��ʵ��
	 */
	public Card getCard( long id );
}
