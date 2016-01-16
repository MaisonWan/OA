/**
 * 
 */
package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.*;

/**
 * @author 许达
 * CardTypeBean的接口类 包含了获取名片夹信息 返回特定页面所有名片夹的信息
 * 得到某个用户的所有名片夹的数量 得到名片夹分页的数量 更新名片夹的信息 删除某个名片夹信息6个方法
 *
 */

public interface CardTypeDao {
	
	/**
	 * 得到某用户某名片夹的信息
	 * @param suId 某个用户的Id
	 * @param ctId 该用户下的名片夹Id
	 * @return 名片夹信息
	 */
	public CardType getCardType(long suId, long ctId);
	
	
	/**
	 * 返回某个用户的所有名片夹
	 * @param id 用户的ID
	 * @return 所有名片夹
	 */
	public List<CardType> getAllCardType(long id);
		
	
	
	/**
	 * 返回特定页面所有名片夹的信息
	 * @param pageNo 指定页码
	 * @param userId 指定用户的Id
	 * @return 指定页的全部名片夹信息
	 */
	public List<CardType> getAllCardTypeByPage(int pageNo, long userId);
	
	
	/**
	 * 得到某个用户的所有名片夹的数量 供分页显示用
	 * @param id 某个用户的ID
	 * @return 名片夹数量
	 */
	public int getCardTypeCount(long id);
	
	
	/**
	 * 得到名片夹分页的数量
	 * @param count 某个用户的名片夹的总数量
	 * @param pageSize 每页显示的大小
	 * @return 总页码数量
	 */
	public int getPageCount(int count, int pageSize);
	
	
	/**
	 * 更新/插入名片夹的信息
	 * @param cType 需要更新/插入的名片夹信息
	 * @return 是否成功更新数据 0代表成功执行更新/插入 1代表有重名 2代表未知异常
	 */
	public int updateCardType(CardType cType);
	
	
	/**
	 * 删除某个名片夹信息
	 * @param id 要删除的名片夹的id
	 * @return 是否删除成功
	 */
	public boolean deleteCardType(long id);
	
	/**
	 * @param uid
	 * @param cardTypeID
	 * @return 是不是属于你的文件夹
	 */
	public boolean isUsersType(long uid,long cardTypeID);
	
	
	/**
	 * @param cardTypeID
	 * @return  返回是否有名片
	 */
	public boolean hasCard(long cardTypeID);
	
	}
