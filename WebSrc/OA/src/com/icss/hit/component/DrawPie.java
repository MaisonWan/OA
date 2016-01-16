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

	private String title = "��״ͼ"; 		// ͼƬ����
	private Color bgcolor = null;   // ͼƬ������ɫ
	private float foreAlpha=0.3F;   // ��ͼ͸����ֵ 0---1
	private int labelFontSize =10;  // ���������С
	private double factor=0.2;		// 3D��ͼ��Z��߶ȣ�0.0��1.0��
	private int width = 800; 		// Ҫ���ɵ�ͼƬ�Ŀ��
	private int height = 400; 		// Ҫ���ɵ�ͼƬ�ĸ߶�
	private String fileName = ""; 	// ͼƬ����(���Լ�·��)
	private DefaultPieDataset dataset = null; 	// ��ʾͼƬ���õ����ݼ�
	private FileOutputStream fosJpg = null; 	// ����ͼƬ���õ��������
	private static DrawPie instance = null;

	/**
	 * ��̬ģʽ���������
	 * @return �����һ������
	 */
	public static synchronized DrawPie getInstance() {
		if (instance == null)
			instance = new DrawPie();
		return instance;
	}
	
	/**
	 * �ı� ͼ�����
	 * @param str ͼ�����
	 */
	public void setTitle(String str) {
		this.title = str;
	}


	/**
	 * �ı䱳����ɫ
	 * @param color   ������ɫ
	 */
	public void setBgcolor(int red,int green,int blue) {
		this.bgcolor = new Color(red,green,blue);
	}

	/**
	 * �ı䱳����ɫ
	 * @param color   ������ɫ
	 */
	public void setBgcolor(Color color) {
		this.bgcolor = color;
	}


	/**
	 * �ı䱳����ɫ
	 * @param str  ������ɫ���� ����:BLACK black blue Blue ��
	 */
	public void setBgColor(String str){
		this.bgcolor=ChangeColor.getColor(str);
	}


	/**
	 * ���ñ�ͼ͸����
	 * @param value
	 */
	public void setForeAlpha(float value){
		this.foreAlpha=value;
	}


	/**
	 * �ı�ͼƬ���
	 *
	 * @param width ͼƬ���
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * �ı�ͼƬ�߶�
	 *
	 * @param height  ͼƬ�߶�
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * �ı�����ǩ����
	 *
	 * @param i  �����ǩ�����С
	 */
	public void setLabelFontSize(int i) {
		this.labelFontSize = i;
	}
	
	/**
	 * �ı��ͼ��Z��
	 *
	 * @param dbl  ��ͼ��Z��߶�
	 */
	public void setFactor(double dbl) {
		this.factor = dbl;
	}
	
	/**
	 * �ı��ļ�����
	 * @param str  �ļ�����
	 */
	private void setFileName(String str) {
		this.fileName = str;
	}

	/**
	 * ��ʼ������
	 */
	public void init() {
		setTitle("��״ͼ");
		setWidth(800);
		setHeight(450);
		setBgcolor(Color.white);
		setForeAlpha(0.5F);
		setFileName("d:\\temp.jpg");
	}

	/**
	 * ���Ҫ���л���״ͼ������
	 * @param value   ��Ԫֵ
	 * @param name   ��Ԫ������
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
         * �����ļ�·������ͼƬ
	 * @param fileName   Ҫ������ļ�����  �ļ�����Ϊ��ʹ��·��Ϊ��: d:\\web\test.jpg
	 * @return true �ɹ�  false ʧ��
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
            piechart.setBackgroundPaint(this.bgcolor); //���ñ�����ɫ
            
            PiePlot pieplot= (PiePlot) piechart.getPlot();
            pieplot.setLabelFont(new Font("black", Font.BOLD, this.labelFontSize));//�����ǩ����
            PiePlot3D pieplot3d = (PiePlot3D) piechart.getPlot();
            pieplot3d.setDepthFactor(factor);//3D��ͼ��Z��߶�
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
         * �����ļ�·������ͼƬ����ͼ�ļ�
         * @param fileName   Ҫ������ļ�����  �ļ�����Ϊ��ʹ��·��Ϊ��: d:\\web\test.jpg
         * @return true �ɹ�  false ʧ��
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
                      piechart.setBackgroundPaint(this.bgcolor); //���ñ�����ɫ
                      
                      PiePlot pieplot= (PiePlot) piechart.getPlot();
                      pieplot.setLabelFont(new Font("black", Font.BOLD, this.labelFontSize));//�����ǩ����
                      PiePlot3D pieplot3d = (PiePlot3D) piechart.getPlot();
                      pieplot3d.setDepthFactor(factor);//3D��ͼ��Z��߶�
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
         * ������վ�ĸ�վ��·������ͼƬ
         * @param request   ��jspҳ���е�request���� ���ڻ�ȡ�ļ�·��
         * @param fileName   �����ļ����� �ļ����Ʊ���ʹ��վ��ľ���·�� �� : ��/admin/test.jpg"
         * @return true �ɹ�  false ʧ��
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
            piechart.setBackgroundPaint(this.bgcolor); //���ñ�����ɫ
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
		//1.���ɻ���ͼ�������
		DrawPie test=DrawPie.getInstance();
		//2.��ӻ�ͼ�����ݼ�
		test.addData("����", 43.00);
		test.addData("����", 40.34);
		test.addData("����", 17.23);
		test.addData("΢��", 32.43);
		test.addData("��Ϊ", 45.23);
		test.addData("IBM",22.34);
		//3.����ͼƬ����
		test.init();
		test.setBgcolor(Color.white);
		test.setTitle("��ͼ����");
		test.setWidth(800);
		test.setHeight(450);
		test.setLabelFontSize(10);
		test.setFactor(0.2);
		//4.����ͼƬ
		test.saveAbs("E:\\pie.jpg");
		//5.ͬʱ����ͼƬ�ļ��͵�ͼ�ļ�
		//test.saveMap("E:\\pie.jpg","E:\\pie.map","http://localhost:8080/test/MyJsp.jsp");
	}
}
