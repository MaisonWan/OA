package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Schedule;

public interface WorkList {
	/**
	 * �õ������嵥
	 * @param id �û�ID
	 * @param pageNo ҳ��
	 * @return �����嵥
	 */
	public List<Schedule> getWorkList(long id,int pageNo);
	
	/**
	 * �õ������嵥����
	 * @param id �û�ID
	 * @return ��������
	 */
	public int getWorkListCount(long id);
	
	/**
	 * �������ҳ��ҳ��
	 * @param count ����
	 * @param pageSize ÿҳ������
	 * @return ������ҳ��
	 */
	public int getPageCount(int count, int pageSize);
	
	/**
	 * �õ�5����������
	 * @param id �û�ID
	 * @return 5����������
	 */
	public List<Schedule> getFiveWorkPlan(long id);
	
	/**
	 * �õ�ָ��ҳ������д�������
	 * @param id �û�ID
	 * @param pageNo ҳ��
	 * @return ���д�������
	 */
	public List<Schedule> getAllWorkPlan(long id,int pageNo);
	
	/**
	 * �õ����д������������
	 * @param id �û�ID
	 * @return ����
	 */
	public int getAllWorkPlanCount(long id);
	
	/**
	 * �õ��Լ����Լ����ŵĴ�������
	 * @param schId Schedule��ID
	 * @param userId �û�ID
	 * @return
	 */
	public Schedule getSelfWorkPlan(long schId, long userId );
}
