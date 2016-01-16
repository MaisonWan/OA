package com.icss.hit.component;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.urls.StandardPieURLGenerator;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import javax.servlet.http.*;
/**
 * @author sychallenger
 */
public class DrawPie {

	private String title = "饼状图"; 		// 图片标题
	private Color bgcolor = null;   // 图片背景颜色
	private float foreAlpha=0.3F;   // 饼图透明度值 0---1
	private int labelFontSize =10;  // 分类字体大小
	private double factor=0.2;		// 3D饼图的Z轴高度（0.0～1.0）
	private int width = 800; 		// 要生成的图片的宽度
	private int height = 400; 		// 要生成的图片的高度
	private String fileName = ""; 	// 图片名称(可以加路经)
	private DefaultPieDataset dataset = null; 	// 显示图片需用的数据集
	private FileOutputStream fosJpg = null; 	// 生成图片是用到的输出流
	private static DrawPie instance = null;

	/**
	 * 单态模式生成类对象
	 * @return 该类的一个对象
	 */
	public static synchronized DrawPie getInstance() {
		if (instance == null)
			instance = new DrawPie();
		return instance;
	}
	
	/**
	 * 改变 图表标题
	 * @param str 图表标题
	 */
	public void setTitle(String str) {
		this.title = str;
	}


	/**
	 * 改变背景颜色
	 * @param color   背景颜色
	 */
	public void setBgcolor(int red,int green,int blue) {
		this.bgcolor = new Color(red,green,blue);
	}

	/**
	 * 改变背景颜色
	 * @param color   背景颜色
	 */
	public void setBgcolor(Color color) {
		this.bgcolor = color;
	}


	/**
	 * 改变背景颜色
	 * @param str  背景颜色描述 比如:BLACK black blue Blue 等
	 */
	public void setBgColor(String str){
		this.bgcolor=ChangeColor.getColor(str);
	}


	/**
	 * 设置饼图透明度
	 * @param value
	 */
	public void setForeAlpha(float value){
		this.foreAlpha=value;
	}


	/**
	 * 改变图片宽度
	 *
	 * @param width 图片宽度
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 改变图片高度
	 *
	 * @param height  图片高度
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * 改变分类标签字体
	 *
	 * @param i  分类标签字体大小
	 */
	public void setLabelFontSize(int i) {
		this.labelFontSize = i;
	}
	
	/**
	 * 改变饼图的Z轴
	 *
	 * @param dbl  饼图的Z轴高度
	 */
	public void setFactor(double dbl) {
		this.factor = dbl;
	}
	
	/**
	 * 改变文件名称
	 * @param str  文件名称
	 */
	private void setFileName(String str) {
		this.fileName = str;
	}

	/**
	 * 初始化参数
	 */
	public void init() {
		setTitle("饼状图");
		setWidth(800);
		setHeight(450);
		setBgcolor(Color.white);
		setForeAlpha(0.5F);
		setFileName("d:\\temp.jpg");
	}

	/**
	 * 添加要进行画柱状图的数据
	 * @param value   单元值
	 * @param name   单元项名称
	 */
	public void addData(String name, double value) {
		if (this.dataset != null) {
			this.dataset.setValue(name, value);
		} else {
			init();
			this.dataset = new DefaultPieDataset();
			this.dataset.setValue(name, value);
		}
	}
	/**
         * 按照文件路径保存图片
	 * @param fileName   要保存的文件名称  文件名称为（使用路径为）: d:\\web\test.jpg
	 * @return true 成功  false 失败
	 */
        public boolean saveAbs(String fileName) {
          if (!fileName.equals("temp.jpg")) {
            this.setFileName(fileName);
          }
          if (dataset == null) {
            return false;
          }
          else {
            JFreeChart piechart = ChartFactory.createPieChart3D(this.title,
                this.dataset, true, false, false);
            piechart.setBackgroundPaint(this.bgcolor); //设置背景颜色
            
            PiePlot pieplot= (PiePlot) piechart.getPlot();
            pieplot.setLabelFont(new Font("black", Font.BOLD, this.labelFontSize));//分类标签字体
            PiePlot3D pieplot3d = (PiePlot3D) piechart.getPlot();
            pieplot3d.setDepthFactor(factor);//3D饼图的Z轴高度
            pieplot3d.setStartAngle(290D);
            pieplot3d.setDirection(Rotation.CLOCKWISE);
            pieplot3d.setForegroundAlpha(this.foreAlpha);
            pieplot3d.setNoDataMessage("No data to display");
            try {
              fosJpg = new FileOutputStream(fileName);
              ChartUtilities.writeChartAsJPEG(fosJpg, 100, piechart, this.width,
                                              this.height, null);

            }
            catch (Exception e) {}
            finally {
              try {
                fosJpg.close();
              }
              catch (Exception e) {}
            }
            return true;
          }

        }
        
        /**
         * 按照文件路径保存图片及地图文件
         * @param fileName   要保存的文件名称  文件名称为（使用路径为）: d:\\web\test.jpg
         * @return true 成功  false 失败
         */
        public boolean saveMap(String imgName,String mapName,String url) {
            PrintWriter w = null;
    		FileOutputStream fosCri = null;
    		ChartRenderingInfo info=new ChartRenderingInfo();
    		
    		if (!fileName.equals("temp.jpg")) {
                this.setFileName(fileName);
              }
              if (dataset == null) {
                return false;
              }else {
                  JFreeChart piechart = ChartFactory.createPieChart3D(this.title,
                          this.dataset, true, false, false);
                      piechart.setBackgroundPaint(this.bgcolor); //设置背景颜色
                      
                      PiePlot pieplot= (PiePlot) piechart.getPlot();
                      pieplot.setLabelFont(new Font("black", Font.BOLD, this.labelFontSize));//分类标签字体
                      PiePlot3D pieplot3d = (PiePlot3D) piechart.getPlot();
                      pieplot3d.setDepthFactor(factor);//3D饼图的Z轴高度
                      pieplot3d.setStartAngle(290D);
                      pieplot3d.setDirection(Rotation.CLOCKWISE);
                      pieplot3d.setForegroundAlpha(this.foreAlpha);
                      pieplot3d.setNoDataMessage("No data to display");
                      pieplot3d.setURLGenerator(new StandardPieURLGenerator(url));
    		try{
    			fosJpg = new FileOutputStream(imgName);
    			ChartUtilities.writeChartAsJPEG(fosJpg,100,piechart,this.width,this.height,info);
    			fosCri = new FileOutputStream(mapName);
    			w = new PrintWriter(fosCri);
    			ChartUtilities.writeImageMap(w, "testName", info,true);
    			w.flush();
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}finally{
    			try{
    				w.close();
    			}catch(Exception e){}	
    			try{
    				fosCri.close();
    			}catch(Exception e){}	
    			try{
    				fosJpg.close();
    			}catch(Exception e){}
    		}
    		
    		return true;
            }
    		
          }
        /**
         * 根据网站的根站点路径保存图片
         * @param request   在jsp页面中的request对象 用于获取文件路径
         * @param fileName   保存文件名称 文件名称必须使用站点的绝对路径 如 : “/admin/test.jpg"
         * @return true 成功  false 失败
         */
      public boolean saveWebFile(HttpServletRequest request, String fileName) {
          if (!fileName.equals("")) {
            this.setFileName(fileName);
          }
          fileName = request.getRealPath("") + fileName;
          if (dataset == null) {
            return false;
          }
          else {
            JFreeChart piechart = ChartFactory.createPieChart3D(this.title,
                this.dataset, true, false, false);
            piechart.setBackgroundPaint(this.bgcolor); //设置背景颜色
            PiePlot3D pieplot3d = (PiePlot3D) piechart.getPlot();
            pieplot3d.setStartAngle(290D);
            pieplot3d.setDirection(Rotation.CLOCKWISE);
            pieplot3d.setForegroundAlpha(this.foreAlpha);
            pieplot3d.setNoDataMessage("No data to display");
            try {
              fosJpg = new FileOutputStream(fileName);
              ChartUtilities.writeChartAsJPEG(fosJpg, 100, piechart, this.width,
                                              this.height, null);

            }
            catch (Exception e) {}
            finally {

              try {
                fosJpg.close();
              }
              catch (Exception e) {}
            }
            return true;
          }

        }
        
        
	public static void main(String[] args) {
		//1.生成画饼图的类对象
		DrawPie test=DrawPie.getInstance();
		//2.添加绘图的数据集
		test.addData("杰软", 43.00);
		test.addData("宇信", 40.34);
		test.addData("中软", 17.23);
		test.addData("微软", 32.43);
		test.addData("华为", 45.23);
		test.addData("IBM",22.34);
		//3.设置图片属性
		test.init();
		test.setBgcolor(Color.white);
		test.setTitle("饼图标题");
		test.setWidth(800);
		test.setHeight(450);
		test.setLabelFontSize(10);
		test.setFactor(0.2);
		//4.保存图片
		test.saveAbs("E:\\pie.jpg");
		//5.同时保存图片文件和地图文件
		//test.saveMap("E:\\pie.jpg","E:\\pie.map","http://localhost:8080/test/MyJsp.jsp");
	}
}
