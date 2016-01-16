package com.icss.hit.bean.interfaces;

import java.util.List;
import com.icss.hit.hibernate.vo.SysUser;
import com.icss.hit.hibernate.vo.SysDept;

/**
 * @author ����
 *
 */
public interface OtherInfo {
	
	/**
	 * ����������������
	 * @param type ��������
	 * @param suUser �ؼ���
	 * @param suSex �Ա�
	 * @param suDept ����
	 * @param pageNo ҳ��
	 * @return ���ط�����������Ϣ����
	 */
	public List<SysUser> getSearchedUserInfo(String type,String suUser,String suSex,int suDept, int pageNo);
	/**
	 * �������������������õ���������������
	 * @param type ��������
	 * @param suUser �ؼ���
	 * @param suSex �Ա�
	 * @param suDept ����
	 * @return ��������������
	 */
	public int getSearchedUserCount(String type,String suUser,String suSex,int suDept);
	/**
	 * �������ҳ��ҳ��
	 * @param count ����
	 * @param pageSize ÿҳ������
	 * @return ������ҳ��
	 */
	public int getPageConut(int count, int pageSize);

	/**
	 * @param pageNo ҳ��
	 * @return ȫ�����˵���Ϣ
	 */
	public List<SysUser> getAllUserInfo(String suSex,int suDept,int pageNo);
	/**
	 * @return ȫ��������
	 */
	public int getAllUserCount(String suSex,int suDept);

	/**
	 * �����������������������������˵���Ϣ
	 * @param type ��������
	 * @param suUser �ؼ���
	 * @param suSex �Ա�
	 * @param suDept ����ID
	 * @return �����������˵ļ���
	 */
	public List<SysUser> getAllSearchedUserInfo(String type, String suUser,
			String suSex, int suDept);
	/**
	 * �����������������������������˵���Ϣ
	 * @param suSex �Ա�
	 * @param suDept ����ID
	 * @return �����������˵ļ���
	 */
	public List<SysUser> getUserInfo(String suSex, int suDept);
}
