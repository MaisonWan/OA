package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Meeting;
import com.icss.hit.hibernate.vo.MeetingAttend;
import com.icss.hit.hibernate.vo.SysUser;

public interface MeetingAttendDao {
	/**
	 * ����ID��ѯ�û�����Ļ��鼯��
	 * @param pageNo ҳ��
	 * @param userId �û�ID
	 * @return ָ��ҳȫ�����鼯��
	 */
	public List<Meeting> getAllAssociateMeeting(int pageNo,long userId);
	
	/**
	 * ����ָ���û�����Ļ��鼯������
	 * @param userId �û�ID
	 * @return ����
	 */
	public int getAllAssociateMeetingCount(long userId);
	
	/**
	 * �����ܷ�ҳ��
	 * @param count ������
	 * @param pageSize ҳ���С
	 * @return �ܷ�ҳ��
	 */
	public int getPageCount(int count, int pageSize);
	
	/**
	 * �õ�ָ���˷������Ĳ������б�
	 * @param mtId ����ID
	 * @return �������б�
	 */
	public List<SysUser> getAllAssociateSysUser(long mtId);
	
	/**
	 * �õ�ָ���������ϸ��Ϣ
	 * @param mtId ����ID
	 * @return ��ϸ��Ϣ
	 */
	public Meeting getMeetingDetails(long mtId);
}
