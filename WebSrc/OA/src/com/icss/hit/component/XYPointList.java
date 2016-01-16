package com.icss.hit.component;

import org.jfree.data.xy.XYSeries;

/**
 * @author sychallenger
 */
public class XYPointList {

	private  XYSeries xySeries=null;
	/**
	 * 构造函数 生成一个点集
	 * @param seriesName 点集名称
	 */
	public 	 XYPointList(String seriesName) {
		this.xySeries = new XYSeries(seriesName);
	}
	/**
	 * 给该点集添加点
	 * @param value1 点的横坐标
	 * @param value2 点的纵坐标
	 */
	public  void add(double value1,double value2){
		this.xySeries.add(value1,value2);
	}
	/**
	 * 获取点集
	 * @return 点集
	 */
	public XYSeries getXYSeries(){
		return this.xySeries;
	}
}
