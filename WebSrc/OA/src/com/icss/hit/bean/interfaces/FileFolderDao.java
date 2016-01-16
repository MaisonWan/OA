/**
 * 
 */
package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.FileFolder;

/**
 * @author ������
 * ��װ�����ݿ����ļ��У��Լ�Ӳ�����ļ��еĲ���
 */
public interface FileFolderDao {
	/**
	 * �õ�ĳһ�û��������ļ�����Ϣ
	 * @param uid �û�ID
	 * @return �����ļ��м���
	 */
	public List<FileFolder> getAllFolders( long uid );
	/**
	 * ����ָ��ID�õ��ļ��е���Ϣ
	 * @param ffId �ļ���Id
	 * @return �����ļ��е���Ϣ
	 */
	public FileFolder getFileFolder( long ffId, long uid );
	/**
	 * �����ݿ�������ļ�����Ϣ
	 * @param folder �ļ�����Ϣ
	 * @return �Ƿ���ӳɹ�
	 */
	public boolean addFolder( FileFolder folder );
	/**
	 * ��Ӳ��������ļ�����Ϣ
	 * @param folderPath �ļ��е�·��
	 * @return �Ƿ���ӳɹ�
	 */
	public boolean addFolder( String folderPath );
	
	/**
	 * �����ļ��еĹ���
	 * @param ffId �ļ�����Ϣ
	 * @param share �Ƿ���
	 * @return �Ƿ���ĳɹ�
	 */
	public boolean setFolderShare( long ffId, boolean share );
	/**
	 * ɾ��һ��Ӳ���е��ļ���
	 * @param folderPath �ļ���·��
	 * @return �ļ���ɾ���Ƿ�ɹ�
	 */
	public boolean deleteFolder( String folderPath ) throws Exception;
	/**
	 * ɾ��һ�������е��ļ���
	 * @param folder �ļ���ʵ��
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public boolean deleteFolder( FileFolder folder );
	/**
	 * �����ļ��е�����
	 * @param folderPath �ļ���·��
	 * @param newName �µ��ļ�������
	 * @return �Ƿ��޸ĳɹ�
	 */
	public boolean updateFolder( String folderPath, String newName );
	/**
	 * �޸����ݿ��е��ļ�����Ϣ
	 * @param folder �ļ���ʵ��
	 * @return �Ƿ���³ɹ�
	 */
	public boolean updateFolder( FileFolder folder );
}
