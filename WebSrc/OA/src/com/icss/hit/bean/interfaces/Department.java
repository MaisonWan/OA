/**
 * 
 */
package com.icss.hit.bean.interfaces;
import java.util.List;

import com.icss.hit.hibernate.vo.SysDept;
/**
 * ���Ź����һЩ�����ļ���
 * @author ������
 */
public interface Department {
	/**
	 * @param id ����ID
	 * @return ����һ��������Ϣ
	 */
	public SysDept getDept( long id );
	
	
	/**
	 * @param dept ������Ϣʵ����
	 * @return �Ƿ���³ɹ�
	 */
	public boolean updateDept( SysDept dept);
	
	
	/**
	 * @param id ����ID
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public boolean deleteDept( long id );
	
	
	/**
	 * @return �������в��ŵ�����
	 */
	public int getDeptCount();
	
	
	/**
	 * @param count ���ŵ�����
	 * @param pageSize ÿҳ��ʾ������
	 * @return ҳ�������
	 */
	public int getPageCount(int count, int pageSize);
	
	
	/**
	 * @param pageNo ָ��ҳ��
	 * @return ָ��ҳ��Ĳ�����Ϣ
	 */
	public List<SysDept> getAllDeptByPage(int pageNo);
	
	
	/**
	 * @return ���в�����Ϣ
	 */
	public List<com.icss.hit.hibernate.vo.SysDept> getAllDept ();
	/**
	 * @param dept ����ʵ��	
	 * @return  ����Ƿ�ɹ�
	 */
	public boolean addDept(SysDept dept);
	/**
	 * @param sdId ��������ID
	 * @return  �Ƿ���Ա�����ڸò���
	 */
	public boolean checkDept(long sdId);
}
