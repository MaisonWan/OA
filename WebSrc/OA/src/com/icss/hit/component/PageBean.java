package com.icss.hit.component;

import java.util.ArrayList;

public class PageBean {

	private int total;
	private int begin;
	private int end;
	private String pageString = null;
	private int thispage;
	private String link = null;
	private ArrayList<String> list = null;
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		this.end = total; // ���һҳ��������
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getThispage() {
		return thispage;
	}

	public void setThispage(int thispage) {
		this.thispage = thispage;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * ���캯������ʼ��
	 */
	public PageBean() {
		this.begin = 1;
		this.pageString = "";
		list = new ArrayList<String>();
	}
	
	/**
	 * ���Ҫ��ʾ�Ĳ���
	 * @param param Ҫ��ʾ�Ĳ���
	 */
	public void addParam( String param ){
		list.add(param);
	}
	/**
	 * �õ���ҳ��HTML����
	 * @author ������
	 * @return ���ش���
	 */
	public String getPageString() {
		pageString = "<div style=\"text-align:center;font-size:13px;\">\n";
		pageString += "��" + this.thispage + "ҳ����" + this.total + "ҳ��";
		pageString += this.getFirstPage();
		pageString += this.getUpPage();
		
		pageString += this.getDownPage();
		
		pageString += this.getLastPage();
		pageString += this.getDropDownList();
		pageString += "</div>";
		return pageString;
	}
	
	private String getFirstPage(){
		return "<a href=\"" + this.link + "?page=" + this.begin + getParams() + "\">��ҳ</a>";
	}
	
	private String getUpPage(){
		String upPage = "";
		//����ǵ�һҳ��"��һҳ"û������
		if( this.thispage == begin ){
			upPage += "����һҳ��";
		}
		else{
			upPage += "��<a href=\"" + this.link + "?page=" + (this.thispage - 1 )+ getParams() + "\">��һҳ</a>��";
		}
		return upPage;
	}
	
	private String getDownPage(){
		String downPage = "";
		// �����ǰ�����һҳ��"��һҳ"û������
		if( this.thispage == end ){
			downPage += "����һҳ��";
		}
		else{
			downPage += "��<a href=\"" + this.link + "?page=" + (this.thispage + 1 )+ getParams() + "\">��һҳ</a>��";
		}
		return downPage;
	}
	
	private String getLastPage(){
		return "<a href=\"" + this.link + "?page=" + this.end + getParams() + "\">βҳ</a>\n";
	}
	
	private String getDropDownList(){
		String dropList = "";
		// �������ת�������б�
		dropList += "ת��<select id=\"pageNo\" size=\"1\" onChange=\"window.location='"
			+ this.link + "?page='+this.options(this.selectedIndex).value+'" + getParams() + "'\">\n";
		// �����б�
		for( int i = begin; i <= end; i++ ){
			if( i == this.thispage ){
				dropList += "<option value=\"" + i + "\" selected>" + i + "</option>\n";
			}
			else{
				dropList += "<option value=\"" + i + "\">" + i + "</option>\n";
			}
		}
		dropList += "</select>ҳ";
		return dropList;
	}
	
	private String getParams(){
		String param = "";
		for( String i : list){
			param += "&" + i;
		}
		return param;
	}
}