package com.icss.hit.bean.interfaces;

import java.util.List;

import com.icss.hit.hibernate.vo.Schedule;

public interface WorkList {
	/**
	 * 得到工作清单
	 * @param id 用户ID
	 * @param pageNo 页码
	 * @return 工作清单
	 */
	public List<Schedule> getWorkList(long id,int pageNo);
	
	/**
	 * 得到工作清单总数
	 * @param id 用户ID
	 * @return 返回总数
	 */
	public int getWorkListCount(long id);
	
	/**
	 * 计算出分页的页数
	 * @param count 总数
	 * @param pageSize 每页的数量
	 * @return 返回总页数
	 */
	public int getPageCount(int count, int pageSize);
	
	/**
	 * 得到5个待办事项
	 * @param id 用户ID
	 * @return 5个代办事项
	 */
	public List<Schedule> getFiveWorkPlan(long id);
	
	/**
	 * 得到指定页码的所有待办事项
	 * @param id 用户ID
	 * @param pageNo 页码
	 * @return 所有代办事项
	 */
	public List<Schedule> getAllWorkPlan(long id,int pageNo);
	
	/**
	 * 得到所有待办事项的总数
	 * @param id 用户ID
	 * @return 总数
	 */
	public int getAllWorkPlanCount(long id);
	
	/**
	 * 得到自己给自己安排的待办事项
	 * @param schId Schedule表ID
	 * @param userId 用户ID
	 * @return
	 */
	public Schedule getSelfWorkPlan(long schId, long userId );
}
