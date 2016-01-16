/**
 * 
 */
package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.FileFolder;

/**
 * @author 万里鹏
 * 封装对数据库中文件夹，以及硬盘中文件夹的操作
 */
public interface FileFolderDao {
	/**
	 * 得到某一用户的所有文件夹信息
	 * @param uid 用户ID
	 * @return 返回文件夹集合
	 */
	public List<FileFolder> getAllFolders( long uid );
	/**
	 * 根据指定ID得到文件夹的信息
	 * @param ffId 文件夹Id
	 * @return 返回文件夹的信息
	 */
	public FileFolder getFileFolder( long ffId, long uid );
	/**
	 * 在数据库中添加文件夹信息
	 * @param folder 文件夹信息
	 * @return 是否添加成功
	 */
	public boolean addFolder( FileFolder folder );
	/**
	 * 在硬盘中添加文件夹信息
	 * @param folderPath 文件夹的路径
	 * @return 是否添加成功
	 */
	public boolean addFolder( String folderPath );
	
	/**
	 * 设置文件夹的共享
	 * @param ffId 文件夹信息
	 * @param share 是否共享
	 * @return 是否更改成功
	 */
	public boolean setFolderShare( long ffId, boolean share );
	/**
	 * 删除一个硬盘中的文件夹
	 * @param folderPath 文件夹路径
	 * @return 文件夹删除是否成功
	 */
	public boolean deleteFolder( String folderPath ) throws Exception;
	/**
	 * 删除一个数据中的文件夹
	 * @param folder 文件夹实例
	 * @return 是否删除成功
	 */
	public boolean deleteFolder( FileFolder folder );
	/**
	 * 更改文件夹的名字
	 * @param folderPath 文件夹路径
	 * @param newName 新的文件夹名字
	 * @return 是否修改成功
	 */
	public boolean updateFolder( String folderPath, String newName );
	/**
	 * 修改数据库中的文件夹信息
	 * @param folder 文件夹实例
	 * @return 是否更新成功
	 */
	public boolean updateFolder( FileFolder folder );
}
