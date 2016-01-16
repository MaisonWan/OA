package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Files;

/**
 * @author 万里鹏
 * 封装对文件，以及文件夹的操作
 */
public interface FileDao {
	
	/**
	 * 得到某一个指定位置的文件夹大小
	 * @param path 指定硬盘目录
	 * @return 文件夹空间，以字节为单位。
	 */
	public long getFileLength(String path);
	/**
	 * 返回指定用户，指定文件夹的文件分页信息
	 * @param ffId 文件夹ID
	 * @param uid 用户ID
	 * @param page 指定页码
	 * @return 符合条件的文件信息
	 */
	public List<Files> getFilesByPage( long ffId, long uid, int page );
	/**
	 * 返回该文件夹下文件的数量
	 * @param ffId 文件夹ID
	 * @param uid 用户ID
	 * @return 文件的数量
	 */
	public int getFilesCount( long ffId, long uid );
	
	/**
	 * 添加一个新的文件信息到数据库
	 * @param file 文件类型
	 * @return 是否成功插入
	 */
	public boolean addFile( Files file );
	/**
	 * 返回分页的数量
	 * @param count 数据大小
	 * @param pageSize 每页分页大小
	 * @return 分页数量
	 */
	public int getPageCount(int count, int pageSize);
	/**
	 * 根据指定的ID来返回文件的信息
	 * @param fId 文件ID
	 * @return 文件的信息
	 */
	public Files getFile( long fId );
	/**
	 * 删除一个文件
	 * @param file 文件的实例
	 * @return 删除的结果
	 */
	public boolean delete( Files file );
	
	/**
	 * 根据所给的路径删除硬盘中的文件
	 * @param filePath 文件路径
	 * @return 删除的结果
	 */
	public boolean delete( String filePath );
	
	/**
	 * 得到符合条件的数量
	 * @param type 搜索类型
	 * @param key 关键词
	 * @param filename 文件名
	 * @return 数量
	 */
	public int searchShareFileCount(String type, String key, String filename );
	/**
	 * 得到符合条件的集合
	 * @param type 类型
	 * @param key 关键词
	 * @param filename 文件名
	 * @param page 页码
	 * @return 集合
	 */
	public List<Files> searchShareFile(String type, String key, String filename, int page );
	
}
