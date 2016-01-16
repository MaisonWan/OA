package com.icss.hit.bean.interfaces;
import java.util.List;

import com.icss.hit.hibernate.vo.Schedule;
import com.icss.hit.hibernate.vo.ScheduleConfig;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author ����
 * 2009��8��3��
 */
/**
 * @author xw-pc
 *
 */
public interface ConfigWork {
	/**
	 * �õ�ָ�������������û�
	 * @param id �û�ID
	 * @param suDept ����ID
	 * @param pageNo ҳ��
	 * @return ָ���û��б�
	 */
	public List<SysUser> getAllOtherUserInfo(long id,int suDept, int pageNo);
	/**
	 * ����ָ�������������û�����
	 * @return
	 */
	public int getAllOtherUserInfoCount(long id,int suDept);
	/**
	 * ����ҳ��
	 * @param count ������
	 * @param pageSize ÿҳ����
	 * @return ��ҳ��
	 */
	public int getPageCount(int count, int pageSize);
	
	/**
	 * �õ�ĳ���û���Ȩ������û�
	 * @param id �û�ID
	 * @param suDept ����ID
	 * @return �û��б�
	 */
	
	/**
	 * �õ��Լ���Ȩ�����Ա��
	 * @param id ����ID
	 * @param pageNo ҳ��
	 * @return �����й�Ա��
	 */
	public List<ScheduleConfig> getAllConfigWorkUser(long id,int pageNo);
	
	/**
	 * ����ID�õ���Ӧ����Ȩ�û���
	 * @param id �û�ID
	 * @return �û���
	 */
	public int getAllConfigWorkUserCount(long id);
	
	/**
	 * ɾ��ָ������Ȩ��
	 * @param scId ��ϵ��ID
	 * @param userId �û�ID
	 * @return �Ƿ�ɾ��
	 */
	public boolean deleteAuthorizedUser(long scId,long userId);
	
	/**
	 * ��Ȩָ���û�Ϊ������
	 * @param sc ScheduleConfigʵ��
	 */
	public boolean authorize(ScheduleConfig sc);
	
}

