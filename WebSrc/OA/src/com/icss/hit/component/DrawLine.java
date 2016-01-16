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

	private static DrawLine instance = null; 	// ������ͼ��ʵ��
	private String title = "����ͼʵ��"; 		// ����ͼ����
	private String XTitle = "��������"; 		// ����ͼ��������Ŀ
	private String YTitle = "��������"; 		// ����ͼ��������Ŀ
	private int width = 746; 			// ͼƬ���
	private int height = 300; 			// ͼƬ�߶�
	private String fileName = "d:\\temp.jpg"; 	// ��ʱ�����ͼƬ����
	private boolean isV = true; 			// �Ƿ�ֱ����ʾ
	private Color bgColor = Color.WHITE; 		// ͼ�񱳾���ɫ
	private Color foreColor = Color.lightGray; 	// ������ͼ���򱳾���ɫ
	private Color spaceLineColor = Color.white; // �������ɫ

	private XYPointList xyList=null;
	private FileOutputStream fosJpg = null; 			// ��������ͼ�������
	private	XYSeriesCollection xyseriescollection = new XYSeriesCollection(); // �����м���


	/**
	 * ��ȡ���൥һ����
	 * @return ��������
	 */
	public static synchronized DrawLine getInstance() {
		if (instance == null)
			instance = new DrawLine();
		return instance;
	}
	/**
	 * �������꼯������ ���ڴ�Ż�����ͼ�ĵ�
	 * @param str ���꼯������
	 * @return �ɹ� true
	 */
	public boolean setXYPointList(String str){
		if(this.xyList!=null){
			this.xyList=null;
		}
		this.xyList=new XYPointList(str);
		return true;
	}
	/**
	 * ��ӵ㼯
	 * @return �ɹ� true
	 */
	public boolean addPointList(){
		if(this.xyList==null){
			return false;
		}
		this.xyseriescollection.addSeries(this.xyList.getXYSeries());
		return true;
	}


	/**
	 * �����꼯����ӵ�����
	 * @param xValue x����ֵ
	 * @param yValue y����ֵ
	 * @return �ɹ� true ʧ�� false
	 */
	public boolean addPoint(double xValue,double yValue){
		if(this.xyList==null){
			return false;
		}
		this.xyList.add(xValue,yValue);
		return true;
	}
	/**
	 * ���ó�ʼ������
	 */
	public void init(){
		setTitle("����ͼʵ��");
		setXTitle("��������");
		setYTitle("��������");
		setWidth(746);
		setHeight(300);
		setFileName("d:\\temp.jpg");
		setIsV(true);
		setBgColor(Color.lightGray);
		setForeColor(Color.white);
		setSpaceLineColor(Color.lightGray);
	}

	/**
	 * �ָ�Ĭ��ֵ
	 */
	public void reset(){
		init();
	}
	/**
	 * ����ͼ���������
	 * @param title  ͼ���������
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ����ͼ���ˮƽ��������
	 * @param title ˮƽ��������
	 */
	public void setXTitle(String title) {
		this.XTitle = title;
	}

	/**
	 * ����ͼ��Ĵ�ֱ���������
	 * @param title  ͼ��Ĵ�ֱ���������
	 */
	public void setYTitle(String title) {
		this.YTitle = title;
	}

	/**
	 * ����ͼ��Ŀ��
	 * @param width ͼ��Ŀ��
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * ����ͼ��Ŀ��
	 * @param width ͼ��Ŀ��
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * ����ͼ��߶�
	 * @param height
	 */
	public void setXTitle(int height) {
		this.height = height;
	}

	/**
	 * ���ñ����ļ�����
	 * @param fileName �����ļ�����
	 */
	private void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * ���û�ˮƽͼ���Ǵ�ֱͼ
	 * @param isV ˮƽͼ���Ǵ�ֱͼ
	 */
	public void setIsV(boolean isV) {
		this.isV = isV;
	}


	/**
	 * ���ñ�����ɫ
	 * @param color ������ɫ
	 */
	public void setBgColor(Color color){
		this.bgColor=color;
	}


	/**
	 * ���ñ�����ɫ
	 * @param red   redɫ��ֵ
	 * @param green greenɫ��ֵ
	 * @param blue  blueɫ��ֵ
	 */
	public void setBgColor(int red,int green,int blue){
		this.bgColor=new Color(red,green,blue);
	}


	/**
	 * �ı䱳����ɫ
	 * @param str  ������ɫ���� ����:BLACK black blue Blue ��
	 */
	public void setBgcolor(String str){
		this.bgColor=ChangeColor.getColor(str);
	}


	/**
	 * ���û�����ͼ����ı�����ɫ
	 * @param color ������ͼ����ı�����ɫ
	 */
	public void setForeColor(Color color){
		this.foreColor=color;
	}


	/**
	 * ���û�����ͼ����ı�����ɫ
	 * @param red   redɫ��ֵ
	 * @param green greenɫ��ֵ
	 * @param blue  blueɫ��ֵ
	 */
	public void setForeColor(int red,int green,int blue){
		this.foreColor=new Color(red,green,blue);
	}

	/**
	 * ���û�����ͼ����ı�����ɫ
	 * @param str  ������ɫ���� ����:BLACK black blue Blue ��
	 */
	public void setForeColor(String str){
		this.foreColor=ChangeColor.getColor(str);
	}


	/**
	 * ���ü���ߵ���ɫ
	 * @param red   redɫ��ֵ
	 * @param green greenɫ��ֵ
	 * @param blue  blueɫ��ֵ
	 */
	public void setSpaceLineColor(int red,int green,int blue){
		this.spaceLineColor=new Color(red,green,blue);
	}


	/**
	 * ���ü���ߵ���ɫ
	 * @param color ����ߵ���ɫ
	 */
	public void setSpaceLineColor(Color color){
		this.spaceLineColor=color;
	}


	/**
	 * ���û�����ͼ����ı�����ɫ
	 * @param str  ������ɫ���� ����:BLACK black blue Blue ��
	 */
	public void setSpaceLineColor(String str){
		this.spaceLineColor=ChangeColor.getColor(str);
	}

	/**
	 * ��ӵ㼯
	 * @param xyPointList �㼯
	 */
//	public void addXYPointList(XYPointList xyPointList) {
//		this.xyseriescollection.addSeries(xyPointList.getXYSeries());
//	}

	/**
	 * ���ļ�·���������ɵ�����ͼ
	 * @param fileName  �����ļ����� �ļ�����Ϊ��ʹ��·��Ϊ��: d:\\web\test.jpg
	 * @return �ɹ�:true ʧ��:false
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
        * ����վ·���������ɵ�����ͼ
        * @param request   ��jspҳ���е�request���� ���ڻ�ȡ�ļ�·��
        * @param fileName   �����ļ����� �ļ����Ʊ���ʹ��վ��ľ���·�� �� : ��/admin/test.jpg"
        * @return true �ɹ�  false ʧ��
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
		//1.���ɻ�����ͼ�������
		DrawLine drawLine = DrawLine.getInstance();
		//2.��ӵ㼯
		drawLine.setXYPointList("�㼯һ");
		drawLine.addPoint(1.0D, 1.0D);
		drawLine.addPoint(2D, 4D);
		drawLine.addPoint(3D, 3D);
		drawLine.addPoint(4D, 5D);
		drawLine.addPoint(6D, 7D);
		drawLine.addPoint(7D, 7D);
		drawLine.addPoint(8D, 8D);
		drawLine.addPointList();
		drawLine.setXYPointList("�㼯��");
		drawLine.addPoint(5.0D, 1.0D);
		drawLine.addPoint(2D, 3D);
		drawLine.addPoint(5D, 3D);
		drawLine.addPoint(2D, 5D);
		drawLine.addPoint(3D, 2D);
		drawLine.addPoint(7D, 1D);
		drawLine.addPoint(3D, 8D);
		drawLine.addPointList();
		//3.����ͼƬ����
		drawLine.init();
		drawLine.setTitle("��������ͼ");
		drawLine.setXTitle("��������ͼ������");
		drawLine.setYTitle("��������ͼ������");
		drawLine.setBgColor(Color.lightGray);
		drawLine.setForeColor(Color.white);
		drawLine.setSpaceLineColor(Color.lightGray);
		drawLine.setWidth(746);
		drawLine.setHeight(300);
		//4.����ͼƬ
		drawLine.saveAbs("e:\\DrawLine.jpg");
	}
}
