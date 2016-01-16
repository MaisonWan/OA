package com.icss.hit.bean.interfaces;

import java.util.Date;
import java.util.List;

import com.icss.hit.hibernate.vo.Schedule;

public interface ScheduletimeDao {
public static int PAGE_SIZE = 10;
	
	/**
	 * 返回用户添加的日程安排的数量
	 * @param userId 用户ID
	 * @return 返回指定用户添加的日程安排的数量
	 */
	public int getSchduleWorkCount(long userId,Date beginDate,Date endDate);
	
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
	**/
	public List<Schedule> getSchduleWorkByPage(long userId, int page,Date beginDate,Date endDate);
}
