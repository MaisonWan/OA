/**
 * 
 */
package com.icss.hit.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.FileFolderDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.FileFolder;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * 封装对数据库中文件夹的操作，以及对硬盘中文件夹结构的操作
 * @author 万里鹏
 * 
 */
public class FileFolderBean implements FileFolderDao{

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileFolderDao#getAllFolders(long)
	 */
	public List<FileFolder> getAllFolders(long uid) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from FileFolder ff inner join ff.sysUser u where u.suId=?";
		try {
			tx = sess.beginTransaction();
			// 返回系统中指定用户的文件夹的信息
			List l = sess.createQuery(hql).setLong(0, uid).list();
			tx.commit();
			Iterator it = l.iterator();
			List<FileFolder> list = new ArrayList<FileFolder>();
			while( it.hasNext()){
				Object[] obj = (Object[])it.next();
				FileFolder ff = (FileFolder)obj[0];
				SysUser u = (SysUser)obj[1];
				ff.setSysUser(u);
				list.add(ff);
			}
			// 如果为空则返回NULL
			if( list.size() == 0){
				return null;
			}
			return list;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileFolderDao#getFileFolder(long)
	 */
	public FileFolder getFileFolder(long ffId, long uid) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		
		try {
			tx = sess.beginTransaction();
			// 得到某一指定Id的文件夹
			FileFolder ff = (FileFolder)sess.get(FileFolder.class, ffId);
			tx.commit();
			if( ff.getSysUser().getSuId() == uid )
				return ff;
			return null;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileFolderDao#addFolder(com.icss.hit.hibernate.vo.FileFolder)
	 */
	public boolean addFolder(FileFolder folder) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// 添加一个新的文件夹
			sess.save(folder);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileFolderDao#addFolder(java.lang.String)
	 */
	public boolean addFolder(String folderPath) {
		File fileFolder = new File( folderPath );
		if( !fileFolder.exists()){
			return fileFolder.mkdir();
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileFolderDao#setFolderShare(com.icss.hit.hibernate.vo.FileFolder)
	 */
	public boolean setFolderShare(long ffId, boolean share ) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "update FileFolder ff set ff.ffShare=? where ff.ffId=?";
		try {
			tx = sess.beginTransaction();
			// 添加一个新的文件夹
			String shareInfo = null;
			if( share ){
				shareInfo = "1";
			}else{
				shareInfo = "0";
			}
			int result = sess.createQuery(hql).setString(0, shareInfo).setLong(1, ffId).executeUpdate();
			tx.commit();
			if( result > 0 )
				return true;
			return false;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileFolderDao#deleteFolder(java.lang.String)
	 */
	public boolean deleteFolder(String folderPath) throws Exception{
		File folder = new File(folderPath);
		if( !folder.exists()){
			throw new Exception("该路径不存在");
		}
		if( folder.isFile()){
			
			throw new Exception("该路径不是文件夹路径");
		}
		// 递归删除该文件夹下的所有文件和文件夹
		System.out.println("文件夹名称："+folder.getName());
		deleteFileByFolder(folder);
		folder.delete();
		return true;
	}
	/**
	 * @param folder
	 */
	private void deleteFileByFolder(File folder){
		File[] dirInfo = folder.listFiles();
		for( File f : dirInfo ){
			if( f.isFile()){
				System.out.println("文件名称："+f.getName());
				f.delete();
			}else{
				deleteFileByFolder(f);
			}
		}
	}
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileFolderDao#updateFolder(java.lang.String, java.lang.String)
	 */
	public boolean updateFolder(String folderPath, String newfolderPath) {
		File file = new File(folderPath);
		if( !file.exists()){
			return false;
		}
		if( file.isFile()){
			return false;
		}
		return file.renameTo(new File(newfolderPath));
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileFolderDao#deleteFolder(com.icss.hit.hibernate.vo.FileFolder, long)
	 */
	public boolean deleteFolder(FileFolder folder) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "delete Files f where f.fileFolder = (from FileFolder ff where ff.ffId=?)";
		try {
			tx = sess.beginTransaction();
			// 删除一个新的文件夹
			sess.createQuery(hql).setLong(0, folder.getFfId()).executeUpdate();
			sess.delete(folder);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileFolderDao#updateFolder(com.icss.hit.hibernate.vo.FileFolder, long)
	 */
	public boolean updateFolder(FileFolder folder) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			// 更新一个新的文件夹
			sess.update(folder);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sess.close();
		}
	}
	
}
