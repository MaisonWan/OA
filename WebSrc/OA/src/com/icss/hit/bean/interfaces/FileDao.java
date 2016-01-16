package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Files;

/**
 * @author ������
 * ��װ���ļ����Լ��ļ��еĲ���
 */
public interface FileDao {
	
	/**
	 * �õ�ĳһ��ָ��λ�õ��ļ��д�С
	 * @param path ָ��Ӳ��Ŀ¼
	 * @return �ļ��пռ䣬���ֽ�Ϊ��λ��
	 */
	public long getFileLength(String path);
	/**
	 * ����ָ���û���ָ���ļ��е��ļ���ҳ��Ϣ
	 * @param ffId �ļ���ID
	 * @param uid �û�ID
	 * @param page ָ��ҳ��
	 * @return �����������ļ���Ϣ
	 */
	public List<Files> getFilesByPage( long ffId, long uid, int page );
	/**
	 * ���ظ��ļ������ļ�������
	 * @param ffId �ļ���ID
	 * @param uid �û�ID
	 * @return �ļ�������
	 */
	public int getFilesCount( long ffId, long uid );
	
	/**
	 * ���һ���µ��ļ���Ϣ�����ݿ�
	 * @param file �ļ�����
	 * @return �Ƿ�ɹ�����
	 */
	public boolean addFile( Files file );
	/**
	 * ���ط�ҳ������
	 * @param count ���ݴ�С
	 * @param pageSize ÿҳ��ҳ��С
	 * @return ��ҳ����
	 */
	public int getPageCount(int count, int pageSize);
	/**
	 * ����ָ����ID�������ļ�����Ϣ
	 * @param fId �ļ�ID
	 * @return �ļ�����Ϣ
	 */
	public Files getFile( long fId );
	/**
	 * ɾ��һ���ļ�
	 * @param file �ļ���ʵ��
	 * @return ɾ���Ľ��
	 */
	public boolean delete( Files file );
	
	/**
	 * ����������·��ɾ��Ӳ���е��ļ�
	 * @param filePath �ļ�·��
	 * @return ɾ���Ľ��
	 */
	public boolean delete( String filePath );
	
	/**
	 * �õ���������������
	 * @param type ��������
	 * @param key �ؼ���
	 * @param filename �ļ���
	 * @return ����
	 */
	public int searchShareFileCount(String type, String key, String filename );
	/**
	 * �õ����������ļ���
	 * @param type ����
	 * @param key �ؼ���
	 * @param filename �ļ���
	 * @param page ҳ��
	 * @return ����
	 */
	public List<Files> searchShareFile(String type, String key, String filename, int page );
	
}
