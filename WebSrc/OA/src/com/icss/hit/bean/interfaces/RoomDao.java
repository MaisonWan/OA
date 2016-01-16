package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.RoomReg;

public interface RoomDao {
	/**
	 * ����ָ���û���ָ��ҳ
	 * @param userId �û�ID
	 * @param pageNo ҳ��
	 * @return ָ��ҳ������Ϣ
	 */
	public List<RoomReg> getRoomRegInfo(long userId,int pageNo);
	
	/**
	 * ����ָ���û�������������ҵ�����
	 * @param userId �û�ID
	 * @return ����
	 */
	public int getRoomRegInfoCount(long userId);
	
	/**
	 * ������ҳ��
	 * @param count ������	
	 * @param pageSize ҳ���С
	 * @return ��ҳ��
	 */
	public int getPageCount(int count, int pageSize);
	
	
	/**
	 * ȡ����������
	 * @param rrId ����ID
	 * @param userId �û�ID
	 * @return �Ƿ�ȡ���ɹ�
	 */
	public boolean delete(long rrId, long userId);
	
	/**
	 * �õ�ָ��ID�Ļ����������¼
	 * @param rrId RoomReg��ID
	 * @param userId �û�ID
	 * @return �����������¼
	 */
	public RoomReg getRoomRegDetails(long rrId,long userId);
}
