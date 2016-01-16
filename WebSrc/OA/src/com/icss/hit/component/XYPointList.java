package com.icss.hit.component;

import org.jfree.data.xy.XYSeries;

/**
 * @author sychallenger
 */
public class XYPointList {

	private  XYSeries xySeries=null;
	/**
	 * ���캯�� ����һ���㼯
	 * @param seriesName �㼯����
	 */
	public 	 XYPointList(String seriesName) {
		this.xySeries = new XYSeries(seriesName);
	}
	/**
	 * ���õ㼯��ӵ�
	 * @param value1 ��ĺ�����
	 * @param value2 ���������
	 */
	public  void add(double value1,double value2){
		this.xySeries.add(value1,value2);
	}
	/**
	 * ��ȡ�㼯
	 * @return �㼯
	 */
	public XYSeries getXYSeries(){
		return this.xySeries;
	}
}
