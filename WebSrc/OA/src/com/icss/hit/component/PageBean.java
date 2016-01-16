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
		this.end = total; // 最后一页等于总数
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
	 * 构造函数，初始化
	 */
	public PageBean() {
		this.begin = 1;
		this.pageString = "";
		list = new ArrayList<String>();
	}
	
	/**
	 * 添加要显示的参数
	 * @param param 要显示的参数
	 */
	public void addParam( String param ){
		list.add(param);
	}
	/**
	 * 得到分页的HTML代码
	 * @author 万里鹏
	 * @return 返回代码
	 */
	public String getPageString() {
		pageString = "<div style=\"text-align:center;font-size:13px;\">\n";
		pageString += "第" + this.thispage + "页　共" + this.total + "页　";
		pageString += this.getFirstPage();
		pageString += this.getUpPage();
		
		pageString += this.getDownPage();
		
		pageString += this.getLastPage();
		pageString += this.getDropDownList();
		pageString += "</div>";
		return pageString;
	}
	
	private String getFirstPage(){
		return "<a href=\"" + this.link + "?page=" + this.begin + getParams() + "\">首页</a>";
	}
	
	private String getUpPage(){
		String upPage = "";
		//如果是第一页则"上一页"没有链接
		if( this.thispage == begin ){
			upPage += "　上一页　";
		}
		else{
			upPage += "　<a href=\"" + this.link + "?page=" + (this.thispage - 1 )+ getParams() + "\">上一页</a>　";
		}
		return upPage;
	}
	
	private String getDownPage(){
		String downPage = "";
		// 如果当前是最后一页则"下一页"没有链接
		if( this.thispage == end ){
			downPage += "　下一页　";
		}
		else{
			downPage += "　<a href=\"" + this.link + "?page=" + (this.thispage + 1 )+ getParams() + "\">下一页</a>　";
		}
		return downPage;
	}
	
	private String getLastPage(){
		return "<a href=\"" + this.link + "?page=" + this.end + getParams() + "\">尾页</a>\n";
	}
	
	private String getDropDownList(){
		String dropList = "";
		// 添加上跳转的下拉列表
		dropList += "转至<select id=\"pageNo\" size=\"1\" onChange=\"window.location='"
			+ this.link + "?page='+this.options(this.selectedIndex).value+'" + getParams() + "'\">\n";
		// 下拉列表
		for( int i = begin; i <= end; i++ ){
			if( i == this.thispage ){
				dropList += "<option value=\"" + i + "\" selected>" + i + "</option>\n";
			}
			else{
				dropList += "<option value=\"" + i + "\">" + i + "</option>\n";
			}
		}
		dropList += "</select>页";
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