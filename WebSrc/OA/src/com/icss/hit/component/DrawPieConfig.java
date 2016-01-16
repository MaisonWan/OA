package com.icss.hit.component;
import java.awt.Color;

public class DrawPieConfig {
	DrawPie test = DrawPie.getInstance();
	private long tkid = 0;
	private String title = "";
	private int width = 500;
	private int height = 280;
	private int fontSize = 15;
	private String path;
	
	private double space;
	private double used;
	
	public double getSpace() {
		return space;
	}

	public void setSpace(double space) {
		this.space = space;
	}

	public double getUsed() {
		return used;
	}

	public void setUsed(double used) {
		this.used = used;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	public long getTkid() {
		return tkid;
	}

	public void setTkid(long tkid) {
		this.tkid = tkid;
		this.init();
	}
	
	public void init()
	{
		//2.��ӻ�ͼ�����ݼ�
		test.addData("���ÿռ�", space);
		test.addData("���ÿռ�", used);
		//3.����ͼƬ����
		test.init();
		test.setBgcolor(204,204,204);
		test.setTitle(this.title);
		test.setWidth(this.width);
		test.setHeight(this.height);
		test.setLabelFontSize(this.fontSize);
		test.setFactor(0.2);
		//4.����ͼƬ
		test.saveAbs(path);
	}
}
