/**
 * 
 */
package com.icss.hit.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.icss.hit.bean.interfaces.FileDao;
import com.icss.hit.hibernate.HibernateSessionFactory;
import com.icss.hit.hibernate.vo.FileFolder;
import com.icss.hit.hibernate.vo.Files;
import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author ������
 * ��װ�˶��ļ��Լ��ļ��еĲ���
 */
/**
 * @author Administrator
 *
 */
public class FileBean implements FileDao{
	
	public static int PAGE_SIZE = 10;
	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileDao#getFileLength(java.lang.String)
	 */
	public long getFileLength(String path) {
		File file = new File(path);
		// �����·�������ڣ����½�
		if( !file.exists() ){
			file.mkdir();
		}
		// �����path���ļ�·�����򷵻�0
		if( !file.isDirectory()){
			return 0;
		}
		// ���ļ��еĴ�С
		long length = 0;
		// �õ���Ŀ¼�µ������ļ���·����Ϣ
		File[] dirInfo = file.listFiles();
		for( File f : dirInfo ){
			if( f.isFile()){
				length += f.length();
			}else{
				// ���ΪĿ¼����������ݹ�
				length += getFileLength( path + f.getName());
			}
		}
		return length;
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileDao#getFilesByPage(long, long, int)
	 */
	public List<Files> getFilesByPage(long ffId, long uid, int page) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Files f inner join f.fileFolder ff inner join ff.sysUser u "
			+ "where ff.ffId=? and u.suId=?";
		// �����ҳ��ʼ������
		int offset = ( page - 1 ) * PAGE_SIZE;
		try {
			tx = sess.beginTransaction();
			// ����ϵͳ��ָ���û����ļ��е���Ϣ
			List l = sess.createQuery(hql).setLong(0, ffId).setLong(1, uid)
			.setFirstResult(offset).setMaxResults(PAGE_SIZE).list();
			tx.commit();
			Iterator it = l.iterator();
			List<Files> list = new ArrayList<Files>();
			while( it.hasNext()){
				Object[] obj = (Object[])it.next();
				Files f = (Files)obj[0];
				FileFolder ff = (FileFolder)obj[1];
				SysUser u = (SysUser)obj[2];
				ff.setSysUser(u);
				f.setFileFolder(ff);
				list.add(f);
			}
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
	 * @see com.icss.hit.bean.interfaces.FileDao#getFilesCount(long, long)
	 */
	public int getFilesCount(long ffId, long uid) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(f.FId) from Files f inner join f.fileFolder ff inner join ff.sysUser u "
			+ "where ff.ffId=? and u.suId=?";
		try {
			tx = sess.beginTransaction();
			// ����ϵͳ��ָ���û����ļ����е��ļ���Ϣ
			Object obj = sess.createQuery(hql).setLong(0, ffId).setLong(1, uid)
			.uniqueResult();
			tx.commit();
			if( obj == null){
				return 0;
			}
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return 0;
		} finally {
			sess.close();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileDao#addFile(com.icss.hit.hibernate.vo.Files)
	 */
	public boolean addFile(Files file) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			sess.save(file);
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
	 * @see com.icss.hit.bean.interfaces.FileDao#getPageCount(int, int)
	 */
	public int getPageCount(int count, int pageSize){
		return ( count + pageSize - 1) / pageSize;
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileDao#getFile(long)
	 */
	public Files getFile(long id) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Files f inner join f.fileFolder ff inner join ff.sysUser u where f.FId=?";
		try {
			tx = sess.beginTransaction();
			List list = sess.createQuery(hql).setLong(0, id).list();
			tx.commit();
			if( list == null )
				return null;
			Iterator it = list.iterator();
			if( it.hasNext()){
				Object[] obj = (Object[])it.next();
				Files f = (Files)obj[0];
				FileFolder ff = (FileFolder)obj[1];
				SysUser u = (SysUser)obj[2];
				ff.setSysUser(u);
				f.setFileFolder(ff);
				return f;
			}
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
	 * @see com.icss.hit.bean.interfaces.FileDao#delete(com.icss.hit.hibernate.vo.Files)
	 */
	public boolean delete(Files file) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			sess.delete(file);
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
	 * @see com.icss.hit.bean.interfaces.FileDao#delete(java.lang.String)
	 */
	public boolean delete(String filePath) {
		if( filePath == null )
			return false;
		File file = new File(filePath);
		// ����ļ������ڣ�����·�������ļ�
		if( !file.exists() || !file.isFile() ){
			return false;
		}
		
		if( file.delete() ){
			return true;
		}
		return false;
	}
	
	/**
	 * �����ļ��У����ظ��ļ����µ������ļ���Ϣ
	 * @param ffId �ļ���ID
	 * @return �ļ�����Ϣ����
	 */
	public List<Files> getFiles( long ffId ){
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Files f inner join f.fileFolder ff where ff.ffId=?";
		try {
			tx = sess.beginTransaction();
			List l = sess.createQuery(hql).setLong(0, ffId).list();
			tx.commit();
			Iterator it = l.iterator();
			List<Files> list = new ArrayList<Files>();
			while( it.hasNext()){
				Object[] obj = (Object[])it.next();
				Files f = (Files)obj[0];
				FileFolder ff = (FileFolder)obj[1];
				f.setFileFolder(ff);
				list.add(f);
			}
			if( list.size() == 0 ){
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
	 * @see com.icss.hit.bean.interfaces.FileDao#searchShareFile(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	public List<Files> searchShareFile(String type, String key,
			String filename, int page) {
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "from Files f inner join f.fileFolder ff inner join ff.sysUser u where"
			+ " ff.ffShare='1'";
		if( key != null ){
			if( type.equals("id")){
				hql += " and u.suId like ?";
			}else if( type.equals("name")){
				hql += " and u.suUsername like ?";
			}
		}
		
		if( filename != null ){
			hql += " and f.FName like ?";
		}
		try {
			tx = sess.beginTransaction();
			Query query = sess.createQuery(hql);
			int num = 0;
			if( key != null ){
				query.setString(num++, "%" + key + "%");
			}
			if( filename != null ){
				query.setString(num++, "%" + filename + "%");
			}
			List l = query.list();
			tx.commit();
			
			Iterator it = l.iterator();
			List<Files> list = new ArrayList<Files>();
			while( it.hasNext()){
				Object[] obj = (Object[])it.next();
				Files f = (Files)obj[0];
				FileFolder ff = (FileFolder)obj[1];
				SysUser u = (SysUser)obj[2];
				ff.setSysUser(u);
				f.setFileFolder(ff);
				list.add(f);
			}
			if( list.size() == 0 ){
				return null;
			}
			return list;
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			sess.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.icss.hit.bean.interfaces.FileDao#searchShareFileCount(java.lang.String, java.lang.String, java.lang.String)
	 */
	public int searchShareFileCount(String type, String key, String filename) {
		
		Session sess = HibernateSessionFactory.getSession();
		Transaction tx = null;
		String hql = "select count(f.FId) from Files f inner join f.fileFolder ff inner join ff.sysUser u where"
			+ " ff.ffShare='1'";
		if( key != null ){
			if( type.equals("id")){
				hql += " and u.suId like ?";
			}else if( type.equals("name")){
				hql += " and u.suUsername like ?";
			}
		}
		
		if( filename != null ){
			hql += " and f.FName like ?";
		}
		try {
			tx = sess.beginTransaction();
			Query query = sess.createQuery(hql);
			int num = 0;
			if( key != null ){
				query.setString(num++, "%" + key + "%");
			}
			if( filename != null ){
				query.setString(num++, "%" + filename + "%");
			}
			Object obj = query.uniqueResult();
			tx.commit();
			if( obj == null ){
				return 0;
			}
			return Integer.parseInt(obj.toString());
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return 0;
		} finally {
			sess.close();
		}
	}
	
	
}
