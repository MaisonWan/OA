/*
 * Created on 2005-10-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.hit.component;

import java.awt.Color;
import com.lowagie.text.Image;

public class PdfImage {

	private Image image=null;   //ͼƬ����
	/**
	 * ����ͼƬ����
	 * @param fileName ͼƬ����
	 */
	public PdfImage(String fileName) {
		try{
		this.image=Image.getInstance(fileName);
		
		}catch(Exception e){
			System.out.println(e);
		}
	}
	/**
	 * ����ͼƬ����
	 * @param value ͼƬ����
	 */
	public void setBorder(int value){
		this.setBorder(value);
	}
	/**
	 * ����ͼƬ����ɫ
	 * @param color  ͼƬ����ɫ
	 */
	public void setBorderColor(Color color){
		this.image.setBorderColor(color);
	}
	/**
	 * ����ͼƬ����ɫ
	 * @param red   ��ɫɫ��ֵ
	 * @param green ��ɫɫ��ֵ
	 * @param blue  ��ɫɫ��ֵ
	 */
	public void setBorderColor(int red,int green,int blue){
		this.image.setBorderColor(new Color(red,green,blue));
	}
	/**
	 * ����ͼƬ������ɫ
	 * @param color ͼƬ������ɫ
	 */
	public void setBackgroundColor(Color color){
		this.image.setBackgroundColor(color);
		//this.image.setBorderColor(Color color);
		//this.image.setBorder(int value);
	}
	/**
	 * ����ͼƬ������ɫ
	 * @param red   ��ɫɫ��ֵ
	 * @param green ��ɫɫ��ֵ
	 * @param blue  ��ɫɫ��ֵ
	 */
	public void setBackgroundColor(int red,int green,int blue){
		this.image.setBackgroundColor(new Color(red,green,blue));
	}
	/**
	 * ����ͼƬ���Դ�С
	 * @param width   ͼƬ���
	 * @param height  ͼƬ�߶�
	 */
	public void scaleAbsolute(float width,float height){
		this.image.scaleAbsolute(width,height);
	}
	/**
	 * ����ͼƬ��Դ�С
	 * @param width  ͼƬ���
	 * @param height ͼƬ�߶�
	 */
	public void scalePercent(float width,float height){
		this.image.scalePercent(width,height);	
	}
	/**
	 * ����ͼƬ���뷽ʽ
	 * @param value ͼƬ���뷽ʽ
	 */
	public void setAlignment(int value){
		this.image.setAlignment(value);
		//this.image
	}
	/**
	 * ����ͼƬ���뷽ʽ
	 * @param value ͼƬ���뷽ʽ
	 */
	public void setAlignment(String value){
		if(value.equalsIgnoreCase("center")){
			this.image.setAlignment(Image.ALIGN_CENTER);
		}
		if(value.equalsIgnoreCase("left")){
			this.image.setAlignment(Image.ALIGN_LEFT);
		}
		if(value.equalsIgnoreCase("right")){
			this.image.setAlignment(Image.ALIGN_RIGHT);
		}
	}
	/**
	 * ���ͼƬ����
	 * @return image ͼƬ����
	 */ 
	public Image getImage(){
		return this.image;
	}
	
}
