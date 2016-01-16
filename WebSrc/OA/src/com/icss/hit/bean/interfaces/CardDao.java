/**
 * 
 */
package com.icss.hit.bean.interfaces;
import java.util.List;
import com.icss.hit.hibernate.vo.Card;

/**
 * @author 朱金彪
 * 2009年8月1日
 */
/**
 * @author xw-pc
 *
 */
/**
 * @author xw-pc
 *
 */
/**
 * @author xw-pc
 *
 */
public interface CardDao {
	/**
	 * 根据条件进行搜索
	 * @param userName 姓名
	 * @param userSex 性别
	 * @param cardType 名片夹分类
	 * @param company 公司
	 * @param pageNo 页码
	 * @param id 用户Id
	 * @return 返回符合条件的名片信息
	*/
	public List<Card> getSearchedCardInfo(String userName,int userSex,String company,int cardType,int pageNo,long id); 
	
	/**
	 * 根据条件计算名片总数
	 * @param userName 姓名
	 * @param userSex 性别
	 * @param company 公司
	 * @param cardType 名片夹分类
	 * @param id 用户Id
	 * @return 返回名片夹总数
	 */
	public int getSearchedCardCount(String userName,int userSex,String company,int cardType,long id);
	
	/**
	 * 计算出分页的页数
	 * @param count 总数
	 * @param pageSize 每页的数量
	 * @return 返回总页数
	 */

	public int getPageCount(int count, int pageSize);
	
	/**
	 * 根据员工号列出该员工所有共享名片
	 * @param id 员工ID
	 * @param pageNo 页码 
	 * @return 所有共享名片夹
	 */
	public List<Card> getShareCard(long id,int pageNo);
	
	/**
	 * 返回指定用户共享名片的数量
	 * @param id 员工ID
	 * @return 总页数
	 */
	public int getShareCardCount(long id);

	
	/**
	 * 添加一个名片
	 * @param card 名片的实例
	 * @return 是否添加成功
	 */
	public boolean addCard(Card card);
	/**
	 * 更新一个名片
	 * @param card 名片的实例
	 * @return 是否更新成功
	 */
	public boolean updateCard(Card card);
	/**
	 * 返回一个名片的信息
	 * @param id 名片的主键ID
	 * @return 名片的实例
	 */
	public Card getCard( long id );
}
