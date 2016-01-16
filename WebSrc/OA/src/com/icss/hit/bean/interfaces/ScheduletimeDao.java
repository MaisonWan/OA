package com.icss.hit.bean.interfaces;

import java.util.Date;
import java.util.List;

import com.icss.hit.hibernate.vo.Schedule;

public interface ScheduletimeDao {
public static int PAGE_SIZE = 10;
	
	/**
	 * �����û���ӵ��ճ̰��ŵ�����
	 * @param userId �û�ID
	 * @return ����ָ���û���ӵ��ճ̰��ŵ�����
	 */
	public int getSchduleWorkCount(long userId,Date beginDate,Date endDate);
	
	/**
	 * �õ���ҳ������
	 * @param count ���ݵĴ�С
	 * @param pageSize ÿҳ��ʾ������
	 * @return ��ҳ����ҳ��
	 */
	public int getPageCount( int count, int pageSize );
	
	
	/**
	 * ����ָ��ҳ�뷵���ճ̰��ŵļ���
	 * @param userId �û�ID
	 * @param page ҳ��
	 * @return �ճ̰��ŵļ���
	**/
	public List<Schedule> getSchduleWorkByPage(long userId, int page,Date beginDate,Date endDate);
}
