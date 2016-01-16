package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.SysPosition;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author ����
 * 2009��8��12��
 */
public interface SysPositionDao {
	/**
	 * �õ�����ְλ�б�
	 * @return ְλ�б�
	 */
	public List<SysPosition> getAllPosition();
	
	/**
	 * ����ID�õ�ָ��ְλ��Ϣ
	 * @param id ְλID
	 * @return ְλ��Ϣ
	 */
	public SysPosition getPosition(long id);
	
	/**
	 * ���ݲ��ŷ���ָ��ҳ��Ա����Ϣ
	 * @param suDept ����ID
	 * @param pageNo ҳ��
	 * @return Ա����Ϣ�б�
	 */
	public List<SysUser> getAllUserByDept(long suDept, int pageNo);
	
	/**
	 * ���ݲ��ŷ�������Ա������
	 * @param suDept ����ID
	 * @return ����
	 */
	public int getAllUserByDeptCount(long suDept);
	
	/**
	 * ��ҳ����
	 * @param count ������
	 * @param pageSize ҳ���С
	 * @return ��ҳ��
	 */
	public int getPageCount(int count, int pageSize);
}
