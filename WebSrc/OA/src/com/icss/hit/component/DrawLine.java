package com.icss.hit.component;

import java.awt.Color;
import java.io.FileOutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;
import javax.servlet.http.*;
/**
 * @author Sychallenger
 */
public class DrawLine {

	private static DrawLine instance = null; 	// 画折线图的实例
	private String title = "折线图实例"; 		// 折线图标题
	private String XTitle = "横柱标题"; 		// 折线图横坐标题目
	private String YTitle = "纵柱标题"; 		// 折线图纵坐标题目
	private int width = 746; 			// 图片宽度
	private int height = 300; 			// 图片高度
	private String fileName = "d:\\temp.jpg"; 	// 暂时保存的图片名称
	private boolean isV = true; 			// 是否垂直线显示
	private Color bgColor = Color.WHITE; 		// 图像背景颜色
	private Color foreColor = Color.lightGray; 	// 会折线图区域背景颜色
	private Color spaceLineColor = Color.white; // 间隔线颜色

	private XYPointList xyList=null;
	private FileOutputStream fosJpg = null; 			// 生成折线图的输出流
	private	XYSeriesCollection xyseriescollection = new XYSeriesCollection(); // 点序列集合


	/**
	 * 获取该类单一对象
	 * @return 该类事例
	 */
	public static synchronized DrawLine getInstance() {
		if (instance == null)
			instance = new DrawLine();
		return instance;
	}
	/**
	 * 设置坐标集合名称 用于存放画折线图的点
	 * @param str 坐标集合名称
	 * @return 成功 true
	 */
	public boolean setXYPointList(String str){
		if(this.xyList!=null){
			this.xyList=null;
		}
		this.xyList=new XYPointList(str);
		return true;
	}
	/**
	 * 添加点集
	 * @return 成功 true
	 */
	public boolean addPointList(){
		if(this.xyList==null){
			return false;
		}
		this.xyseriescollection.addSeries(this.xyList.getXYSeries());
		return true;
	}


	/**
	 * 给坐标集合添加点坐标
	 * @param xValue x坐标值
	 * @param yValue y坐标值
	 * @return 成功 true 失败 false
	 */
	public boolean addPoint(double xValue,double yValue){
		if(this.xyList==null){
			return false;
		}
		this.xyList.add(xValue,yValue);
		return true;
	}
	/**
	 * 设置初始化参数
	 */
	public void init(){
		setTitle("折线图实例");
		setXTitle("横柱标题");
		setYTitle("纵柱标题");
		setWidth(746);
		setHeight(300);
		setFileName("d:\\temp.jpg");
		setIsV(true);
		setBgColor(Color.lightGray);
		setForeColor(Color.white);
		setSpaceLineColor(Color.lightGray);
	}

	/**
	 * 恢复默认值
	 */
	public void reset(){
		init();
	}
	/**
	 * 设置图像标题名称
	 * @param title  图像标题名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 设置图像的水平坐标名称
	 * @param title 水平坐标名称
	 */
	public void setXTitle(String title) {
		this.XTitle = title;
	}

	/**
	 * 设置图像的垂直坐标的名称
	 * @param title  图像的垂直坐标的名称
	 */
	public void setYTitle(String title) {
		this.YTitle = title;
	}

	/**
	 * 设置图像的宽度
	 * @param width 图像的宽度
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * 设置图像的宽度
	 * @param width 图像的宽度
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * 设置图像高度
	 * @param height
	 */
	public void setXTitle(int height) {
		this.height = height;
	}

	/**
	 * 设置保存文件名称
	 * @param fileName 保存文件名称
	 */
	private void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 设置绘水平图还是垂直图
	 * @param isV 水平图还是垂直图
	 */
	public void setIsV(boolean isV) {
		this.isV = isV;
	}


	/**
	 * 设置背景颜色
	 * @param color 背景颜色
	 */
	public void setBgColor(Color color){
		this.bgColor=color;
	}


	/**
	 * 设置背景颜色
	 * @param red   red色素值
	 * @param green green色素值
	 * @param blue  blue色素值
	 */
	public void setBgColor(int red,int green,int blue){
		this.bgColor=new Color(red,green,blue);
	}


	/**
	 * 改变背景颜色
	 * @param str  背景颜色描述 比如:BLACK black blue Blue 等
	 */
	public void setBgcolor(String str){
		this.bgColor=ChangeColor.getColor(str);
	}


	/**
	 * 设置绘折线图区域的背景颜色
	 * @param color 绘折线图区域的背景颜色
	 */
	public void setForeColor(Color color){
		this.foreColor=color;
	}


	/**
	 * 设置绘折线图区域的背景颜色
	 * @param red   red色素值
	 * @param green green色素值
	 * @param blue  blue色素值
	 */
	public void setForeColor(int red,int green,int blue){
		this.foreColor=new Color(red,green,blue);
	}

	/**
	 * 设置绘折线图区域的背景颜色
	 * @param str  背景颜色描述 比如:BLACK black blue Blue 等
	 */
	public void setForeColor(String str){
		this.foreColor=ChangeColor.getColor(str);
	}


	/**
	 * 设置间隔线的颜色
	 * @param red   red色素值
	 * @param green green色素值
	 * @param blue  blue色素值
	 */
	public void setSpaceLineColor(int red,int green,int blue){
		this.spaceLineColor=new Color(red,green,blue);
	}


	/**
	 * 设置间隔线的颜色
	 * @param color 间隔线的颜色
	 */
	public void setSpaceLineColor(Color color){
		this.spaceLineColor=color;
	}


	/**
	 * 设置绘折线图区域的背景颜色
	 * @param str  背景颜色描述 比如:BLACK black blue Blue 等
	 */
	public void setSpaceLineColor(String str){
		this.spaceLineColor=ChangeColor.getColor(str);
	}

	/**
	 * 添加点集
	 * @param xyPointList 点集
	 */
//	public void addXYPointList(XYPointList xyPointList) {
//		this.xyseriescollection.addSeries(xyPointList.getXYSeries());
//	}

	/**
	 * 按文件路径保存生成的折线图
	 * @param fileName  保存文件名称 文件名称为（使用路径为）: d:\\web\test.jpg
	 * @return 成功:true 失败:false
	 */
        public boolean saveAbs(String fileName) {
          JFreeChart jfreechart = null;
          if (fileName != null) {
            this.setFileName(fileName);
          }
          if (isV == true) {
            jfreechart = ChartFactory.createXYLineChart(this.title,
                                                        this.XTitle, this.YTitle,
                                                        xyseriescollection,
                                                        PlotOrientation.VERTICAL, true, true, false);
          }
          else {
            jfreechart = ChartFactory.createXYLineChart(this.title,
                                                        this.XTitle, this.YTitle,
                                                        xyseriescollection,
                                                        PlotOrientation.HORIZONTAL, true, true, false);
          }
          jfreechart.setBackgroundPaint(this.bgColor);
          XYPlot xyplot = (XYPlot) jfreechart.getPlot();
          xyplot.setBackgroundPaint(this.foreColor);
          xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
          xyplot.setDomainGridlinePaint(this.spaceLineColor);
          xyplot.setRangeGridlinePaint(this.spaceLineColor);
          XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)
              xyplot
              .getRenderer();
          xylineandshaperenderer.setShapesVisible(true);
          xylineandshaperenderer.setShapesFilled(true);
          NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
          numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
          FileOutputStream fosJpg = null;
          try {
            fosJpg = new FileOutputStream(fileName);
            ChartUtilities.writeChartAsJPEG(fosJpg, 100, jfreechart, this.width, this.height,
                                            null);

          }
          catch (Exception e) {

          }
          finally {
            this.xyseriescollection.removeAllSeries();
            try {
              fosJpg.close();
            }
            catch (Exception e) {
            }
          }
          return true;
        }



        /**
        * 按网站路径保存生成的折线图
        * @param request   在jsp页面中的request对象 用于获取文件路径
        * @param fileName   保存文件名称 文件名称必须使用站点的绝对路径 如 : “/admin/test.jpg"
        * @return true 成功  false 失败
        */
        public boolean saveWebFile(HttpServletRequest request, String fileName) {
          JFreeChart jfreechart = null;
          if (fileName != null) {
            this.setFileName(fileName);
          }
          fileName = request.getRealPath("") + fileName;
          if (isV == true) {
            jfreechart = ChartFactory.createXYLineChart(this.title,
                                                        this.XTitle, this.YTitle,
                                                        xyseriescollection,
                                                        PlotOrientation.VERTICAL, true, true, false);
          }
          else {
            jfreechart = ChartFactory.createXYLineChart(this.title,
                                                        this.XTitle, this.YTitle,
                                                        xyseriescollection,
                                                        PlotOrientation.HORIZONTAL, true, true, false);
          }
          jfreechart.setBackgroundPaint(this.bgColor);
          XYPlot xyplot = (XYPlot) jfreechart.getPlot();
          xyplot.setBackgroundPaint(this.foreColor);
          xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
          xyplot.setDomainGridlinePaint(this.spaceLineColor);
          xyplot.setRangeGridlinePaint(this.spaceLineColor);
          XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)
              xyplot
              .getRenderer();
          xylineandshaperenderer.setShapesVisible(true);
          xylineandshaperenderer.setShapesFilled(true);
          NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
          numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
          FileOutputStream fosJpg = null;
          try {
            fosJpg = new FileOutputStream(fileName);
            ChartUtilities.writeChartAsJPEG(fosJpg, 100, jfreechart, this.width, this.height,
                                            null);

          }
          catch (Exception e) {

          }
          finally {
            this.xyseriescollection.removeAllSeries();
            try {
              fosJpg.close();
            }
            catch (Exception e) {
            }
          }
          return true;

        }

	public static void main(String[] args) {
		//1.生成画折线图的类对象
		DrawLine drawLine = DrawLine.getInstance();
		//2.添加点集
		drawLine.setXYPointList("点集一");
		drawLine.addPoint(1.0D, 1.0D);
		drawLine.addPoint(2D, 4D);
		drawLine.addPoint(3D, 3D);
		drawLine.addPoint(4D, 5D);
		drawLine.addPoint(6D, 7D);
		drawLine.addPoint(7D, 7D);
		drawLine.addPoint(8D, 8D);
		drawLine.addPointList();
		drawLine.setXYPointList("点集二");
		drawLine.addPoint(5.0D, 1.0D);
		drawLine.addPoint(2D, 3D);
		drawLine.addPoint(5D, 3D);
		drawLine.addPoint(2D, 5D);
		drawLine.addPoint(3D, 2D);
		drawLine.addPoint(7D, 1D);
		drawLine.addPoint(3D, 8D);
		drawLine.addPointList();
		//3.设置图片属性
		drawLine.init();
		drawLine.setTitle("测试折线图");
		drawLine.setXTitle("测试折线图横坐标");
		drawLine.setYTitle("测试折线图纵坐标");
		drawLine.setBgColor(Color.lightGray);
		drawLine.setForeColor(Color.white);
		drawLine.setSpaceLineColor(Color.lightGray);
		drawLine.setWidth(746);
		drawLine.setHeight(300);
		//4.保存图片
		drawLine.saveAbs("e:\\DrawLine.jpg");
	}
}
