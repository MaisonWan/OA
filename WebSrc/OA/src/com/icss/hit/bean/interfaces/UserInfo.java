/**
 * 
 */
package com.icss.hit.bean.interfaces;
import java.util.List;

import com.icss.hit.hibernate.vo.SysUser;

/**
 * @author 万里鹏
 *
 */
public interface UserInfo {
	/**
	 * 返回所有用户的信息
	 * @return 主要是用户的ID和名字
	 */
	public List<SysUser> getAllUsers();
	/**
	 * 返回一个用户的详细信息
	 * @param id 用户
	 * @return 返回用户信息的实体类
	 */
	public SysUser getUserInfo( long id );
	
	
	/**
	 * 修改一个用户的信息
	 * @param user 用户信息的实体类
	 * @return 是否修改成功
	 */
	public boolean updateUserInfo(SysUser user);
	
	
	/**
	 * 删除一个用户
	 * @param id 用户
	 * @return 是否删除成功
	 */
	public boolean deleteUserInfo(long id);
	
	
	/**
	 * @return 返回用户的数量
	 */
	public int getUserInfoCount();
	
	
	/**
	 * 得到分页的数量
	 * @param count 用户人员的总数量
	 * @param pageSize 每页显示的大小
	 * @return 总页码数量
	 */
	public int getPageCount(int count, int pageSize);
	
	
	/**
	 * 返回特定页面所有用户的信息
	 * @param pageNo 指定页码
	 * @return 指定页的全部用户信息
	 */
	public List<SysUser> getAllUserInfoByPage(int pageNo);
	
	/**
	 * 验证用户名与密码是否匹配
	 * @param username 用户名
	 * @param password 密码
	 * @return 验证的结果,-1为验证失败，否则返回用户的主键ID
	 */
	public long validata(String username, String password);
	/**
	 * 验证用户数据库中的主键与密码是否匹配
	 * @param userId 用户主键
	 * @param password 密码
	 * @return 返回验证结果
	 */
	public boolean validata(long userId, String password);
	
	/**
	 * 修改用户的密码
	 * @param userId 用户的主键ID
	 * @param newpassword 新密码
	 * @return 返回修改结果，成功返回TRUE，失败返回FALSE
	 */
	public boolean modifyPassword( long userId, String newpassword);
}
