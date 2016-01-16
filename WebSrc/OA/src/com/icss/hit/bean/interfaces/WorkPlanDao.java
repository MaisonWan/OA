/**
 * 
 */
package com.icss.hit.bean.interfaces;

import java.util.Date;
import java.util.List;

import com.icss.hit.hibernate.vo.Schedule;
import com.icss.hit.hibernate.vo.ScheduleConfig;

/**
 * @author Administrator
 *
 */
/**
 * @author xw-pc
 *
 */
public interface WorkPlanDao {
	
	public static int PAGE_SIZE = 10;
	
	/**
	 * �����û���ӵ��ճ̰��ŵ�����
	 * @param userId �û�ID
	 * @return ����ָ���û���ӵ��ճ̰��ŵ�����
	 */
	public int getWordPlanCount(long userId );
	
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
	 */
	public List<Schedule> getWorkPlanByPage( long userId, int page );
	
	/**
	 * ����ID�õ�ָ�����ճ̵���ϸ����
	 * @param schId �ճ̵�ID
	 * @param userId �����û�ID
	 * @return �ճ̵���ϸ����
	 */
	public Schedule getWorkPlan(long schId, long userId );
	
	/**
	 * ����ID�õ�ָ�����ճ̵���ϸ����
	 * @param schId �û�ID
	 * @param userId �����ճ̵��˵���Ϣ
	 * @return �ճ̵���ϸ��Ϣ
	 */
	public Schedule getWorkPlanByTo(long schId, long userId );
	/**
	 * @param id ����id 
	 * @return ���еı���Ȩ��
	 */
	public List<ScheduleConfig> getScheuleConfig(long id);
	/**
	 * @param sch �ճ̰��ű�
	 * @return �����Ƿ�ɹ�
	 */
	public boolean saveSchedule(Schedule sch);
	/**
	 * @param id ��������id
	 * @return ���е��ճ̰���
	 */
	public List<Schedule> getSchedule(long id);

	
	/**
	 * ɾ��ָ��ID���ճ̰��ţ������û���ID���жϺϷ���
	 * @param schId �ճ̵�ID
	 * @param userId �û�ID
	 * @return ɾ���Ľ�����Ƿ�ɹ�
	 */
	public boolean delete( long schId, long userId );

	/**
	 * ����һ���ճ̰���
	 * @param schedule �ճ̵�ʵ��
	 * @return ���µĽ�����Ƿ���³ɹ�
	 */
	public boolean update( Schedule schedule );
	
	/**
	 * �����������о�ȷ����
	 * @param name ����������
	 * @param begin ��ʼʱ��
	 * @param end ����ʱ��
	 * @param comlete �Ƿ����
	 * @param userId �û�ID
	 * @return ���������Ľ��
	 */
	public List<Schedule> allSearch( String name, Date begin, Date end, 
			String complete, long userId, int page );
	
	/**
	 * �����������ط�������������
	 * @param name �����˵�����
	 * @param begin ��ʼʱ��
	 * @param end ����ʱ��
	 * @param complete �Ƿ����
	 * @param userId �û�ID
	 * @return �������������ݴ�С
	 */
	public int allSearchCount( String name, Date begin, Date end, 
			String complete, long userId);
	/**
	 * @param id �ճ̰��ŵ�su_to_id
	 * @return ���ظ�id���ճ̰����б�
	 */
	public Schedule getSchdule(long id);
	/**
	 * @param schId �ճ̰��ŵ�sch_id����
	 * @return	���ظ����ճ̰��ŵ��б�
	 */
	public Schedule getScheduleInfo(long schId);
	
	
}
