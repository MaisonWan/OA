/**
 * 
 */
package com.icss.hit.bean.interfaces;
import java.util.List;

import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author ������
 *
 */
public interface UserInfo {
	/**
	 * ���������û�����Ϣ
	 * @return ��Ҫ���û���ID������
	 */
	public List<SysUser> getAllUsers();
	/**
	 * ����һ���û�����ϸ��Ϣ
	 * @param id �û�
	 * @return �����û���Ϣ��ʵ����
	 */
	public SysUser getUserInfo( long id );
	
	
	/**
	 * �޸�һ���û�����Ϣ
	 * @param user �û���Ϣ��ʵ����
	 * @return �Ƿ��޸ĳɹ�
	 */
	public boolean updateUserInfo(SysUser user);
	
	
	/**
	 * ɾ��һ���û�
	 * @param id �û�
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public boolean deleteUserInfo(long id);
	
	
	/**
	 * @return �����û�������
	 */
	public int getUserInfoCount();
	
	
	/**
	 * �õ���ҳ������
	 * @param count �û���Ա��������
	 * @param pageSize ÿҳ��ʾ�Ĵ�С
	 * @return ��ҳ������
	 */
	public int getPageCount(int count, int pageSize);
	
	
	/**
	 * �����ض�ҳ�������û�����Ϣ
	 * @param pageNo ָ��ҳ��
	 * @return ָ��ҳ��ȫ���û���Ϣ
	 */
	public List<SysUser> getAllUserInfoByPage(int pageNo);
	
	/**
	 * ��֤�û����������Ƿ�ƥ��
	 * @param username �û���
	 * @param password ����
	 * @return ��֤�Ľ��,-1Ϊ��֤ʧ�ܣ����򷵻��û�������ID
	 */
	public long validata(String username, String password);
	/**
	 * ��֤�û����ݿ��е������������Ƿ�ƥ��
	 * @param userId �û�����
	 * @param password ����
	 * @return ������֤���
	 */
	public boolean validata(long userId, String password);
	
	/**
	 * �޸��û�������
	 * @param userId �û�������ID
	 * @param newpassword ������
	 * @return �����޸Ľ�����ɹ�����TRUE��ʧ�ܷ���FALSE
	 */
	public boolean modifyPassword( long userId, String newpassword);
}
