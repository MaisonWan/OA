/**
 * 
 */
package com.icss.hit.bean.interfaces;

import java.util.Date;
import java.util.List;

import com.icss.hit.hibernate.vo.Schedule;
import com.icss.hit.hibernate.vo.ScheduleConfig;

/**
 * @author Administrator
 *
 */
/**
 * @author xw-pc
 *
 */
public interface WorkPlanDao {
	
	public static int PAGE_SIZE = 10;
	
	/**
	 * 返回用户添加的日程安排的数量
	 * @param userId 用户ID
	 * @return 返回指定用户添加的日程安排的数量
	 */
	public int getWordPlanCount(long userId );
	
	/**
	 * 得到分页的数量
	 * @param count 数据的大小
	 * @param pageSize 每页显示的数量
	 * @return 分页的总页数
	 */
	public int getPageCount( int count, int pageSize );
	
	
	/**
	 * 按照指定页码返回日程安排的集合
	 * @param userId 用户ID
	 * @param page 页码
	 * @return 日程安排的集合
	 */
	public List<Schedule> getWorkPlanByPage( long userId, int page );
	
	/**
	 * 根据ID得到指定的日程的详细内容
	 * @param schId 日程的ID
	 * @param userId 发起用户ID
	 * @return 日程的详细内容
	 */
	public Schedule getWorkPlan(long schId, long userId );
	
	/**
	 * 根据ID得到指定的日程的详细内容
	 * @param schId 用户ID
	 * @param userId 接受日程的人的信息
	 * @return 日程的详细信息
	 */
	public Schedule getWorkPlanByTo(long schId, long userId );
	/**
	 * @param id 本人id 
	 * @return 所有的被授权人
	 */
	public List<ScheduleConfig> getScheuleConfig(long id);
	/**
	 * @param sch 日程安排表
	 * @return 更新是否成功
	 */
	public boolean saveSchedule(Schedule sch);
	/**
	 * @param id 被安排人id
	 * @return 所有的日程安排
	 */
	public List<Schedule> getSchedule(long id);

	
	/**
	 * 删除指定ID的日程安排，根据用户的ID来判断合法性
	 * @param schId 日程的ID
	 * @param userId 用户ID
	 * @return 删除的结果，是否成功
	 */
	public boolean delete( long schId, long userId );

	/**
	 * 更新一个日程安排
	 * @param schedule 日程的实例
	 * @return 更新的结果，是否更新成功
	 */
	public boolean update( Schedule schedule );
	
	/**
	 * 根据条件进行精确搜索
	 * @param name 安排人名字
	 * @param begin 开始时间
	 * @param end 结束时间
	 * @param comlete 是否完成
	 * @param userId 用户ID
	 * @return 符合条件的结果
	 */
	public List<Schedule> allSearch( String name, Date begin, Date end, 
			String complete, long userId, int page );
	
	/**
	 * 根据条件返回符合条件的数量
	 * @param name 安排人的名字
	 * @param begin 开始时间
	 * @param end 结束时间
	 * @param complete 是否完成
	 * @param userId 用户ID
	 * @return 符合条件的数据大小
	 */
	public int allSearchCount( String name, Date begin, Date end, 
			String complete, long userId);
	/**
	 * @param id 日程安排的su_to_id
	 * @return 返回该id的日程安排列表
	 */
	public Schedule getSchdule(long id);
	/**
	 * @param schId 日程安排的sch_id主键
	 * @return	返回该条日程安排的列表
	 */
	public Schedule getScheduleInfo(long schId);
	
	
}
