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

	private Image image=null;   //图片对象
	/**
	 * 生成图片对象
	 * @param fileName 图片名称
	 */
	public PdfImage(String fileName) {
		try{
		this.image=Image.getInstance(fileName);
		
		}catch(Exception e){
			System.out.println(e);
		}
	}
	/**
	 * 设置图片框宽度
	 * @param value 图片框宽度
	 */
	public void setBorder(int value){
		this.setBorder(value);
	}
	/**
	 * 设置图片框颜色
	 * @param color  图片框颜色
	 */
	public void setBorderColor(Color color){
		this.image.setBorderColor(color);
	}
	/**
	 * 设置图片框颜色
	 * @param red   红色色素值
	 * @param green 绿色色素值
	 * @param blue  蓝色色素值
	 */
	public void setBorderColor(int red,int green,int blue){
		this.image.setBorderColor(new Color(red,green,blue));
	}
	/**
	 * 设置图片背景颜色
	 * @param color 图片背景颜色
	 */
	public void setBackgroundColor(Color color){
		this.image.setBackgroundColor(color);
		//this.image.setBorderColor(Color color);
		//this.image.setBorder(int value);
	}
	/**
	 * 设置图片背景颜色
	 * @param red   红色色素值
	 * @param green 绿色色素值
	 * @param blue  蓝色色素值
	 */
	public void setBackgroundColor(int red,int green,int blue){
		this.image.setBackgroundColor(new Color(red,green,blue));
	}
	/**
	 * 设置图片绝对大小
	 * @param width   图片宽度
	 * @param height  图片高度
	 */
	public void scaleAbsolute(float width,float height){
		this.image.scaleAbsolute(width,height);
	}
	/**
	 * 设置图片相对大小
	 * @param width  图片宽度
	 * @param height 图片高度
	 */
	public void scalePercent(float width,float height){
		this.image.scalePercent(width,height);	
	}
	/**
	 * 设置图片对齐方式
	 * @param value 图片对齐方式
	 */
	public void setAlignment(int value){
		this.image.setAlignment(value);
		//this.image
	}
	/**
	 * 设置图片对齐方式
	 * @param value 图片对齐方式
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
	 * 获得图片对象
	 * @return image 图片对象
	 */ 
	public Image getImage(){
		return this.image;
	}
	
}
