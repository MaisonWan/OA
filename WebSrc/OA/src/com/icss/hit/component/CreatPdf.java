package com.icss.hit.component;
import java.awt.Color;
import java.io.FileOutputStream;

import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author Administrator
 */
public class CreatPdf {
	private Rectangle pageSize=PageSize.A4;        	//页面型号
	private String fileName="e:\\sychallenger.pdf";	//生成pdf文件名称
	private PdfDocument document=null;			    //控制生成pdf文件的文档对象
	private PdfWriter writer=null;				    //控制生成pdf文件的Writer对象
	private BaseFont bfChinese=null;			   	//基本字体用于支持中文
	private Font FontChinese=null;				    //生成pdf的内容的字体
	private PdfParagraph Paragraph=null;		    //按段方式输入文章内容
	private PdfPart part=null;					    //按部分方式输入文章内容
	private PdfElement element=null;			    //短语方式输入文章内容
	
	private PdfImage image=null;					//要插入的图片
	private PdfTable table=null;					//要插入的表格
	private PdfCell cell=null;						//要插入的表格基本元素
	
	/**
	 * 构造函数同时初始化字体对象
	 */
	public CreatPdf(){
		try{
		this.bfChinese  = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		this.FontChinese = new Font(this.bfChinese, 32, Font.BOLD);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	private void setFamily(String str){
		this.FontChinese.setFamily(str);
	}
	/**
	 * 设置字体大小
	 * @param value 字体大小值
	 */
	private void setFontSize(float value){
		this.FontChinese.setSize(value);
	}
	/**
	 * 设置字体类型
	 * @param str 字体类型
	 */
	private void setFontStyle(String str){
		this.FontChinese.setStyle(str);
	}
	
	private void setFontColor(Color color){
		this.FontChinese.setColor(color);
		
	}
	/**
	 * 设置页面型号
	 * @param value  页面型号
	 */
	public void setRectangle(Rectangle value){
		this.pageSize=value;
	}
	/**
	 * 设置生成的文件名称
	 * @param str 生成pdf文件的名称
	 */
	public void setFileName(String str){
		this.fileName=str;
	}
	/**
	 * 设置基本字体 用于支持中文
	 * @param baseFont 基本字体
	 */
	private void setBfChinese(BaseFont baseFont){
		this.bfChinese=baseFont;
	}
	/**
	 * 设置字体
	 * @param font 字体对象
	 */
	private void setFontChinese(Font font){
		this.FontChinese=font;
	}
	
	/**
	 * 给pdf文章添加内容
	 * @param arg0 文章内容
	 */
	
	private void add(Element arg0){
		if(this.document==null){
			this.document =new PdfDocument(pageSize);
		}
		if(this.writer==null){	
			try{
				writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
				document.open();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		try{
			this.document.add(arg0);
		}catch(Exception e){
			System.out.println(e);
		}
	
	}
	
	private boolean addParagraph(){
		if(this.Paragraph==null){
			return false;
		}
		if(this.document==null){
			this.document =new PdfDocument(pageSize);
		}
		if(this.writer==null){	
			try{
				writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
				document.open();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		this.add(this.Paragraph);
		return true;
	}
	private boolean addPart(){
		if(this.part==null){
			return false;
		}
		if(this.document==null){
			this.document =new PdfDocument(pageSize);
		}
		if(this.writer==null){	
			try{
				writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
				document.open();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		try{
			this.document.add(this.part);
		}catch(Exception e){
			System.out.println("sychallenger");
			System.out.println(e);
		}
		return true;
	}
	private boolean addElement(){
		if(this.element==null){
			return false;
		}
		if(this.document==null){
			this.document =new PdfDocument(pageSize);
		}
		if(this.writer==null){	
			try{
				writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
				document.open();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		try{
			this.document.add(this.element);
		}catch(Exception e){
			System.out.println(e);
		}
		return true;
	}
	private boolean addImage(){
		if(this.image==null){
			return false;
		}
		if(this.document==null){
			this.document =new PdfDocument(pageSize);
		}
		if(this.writer==null){	
			try{
				writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
				document.open();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		try{
			this.document.add(this.image.getImage());
		}catch(Exception e){
			System.out.println(e);
		}
		return true;
	}
	
	public boolean  addTable(){
		if(this.table==null){
			return false;
		}
		if(this.document==null){
			this.document =new PdfDocument(pageSize);
		}
		if(this.writer==null){	
			try{
				writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
				document.open();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		try{
			this.document.add(this.table);
		}catch(Exception e){
			System.out.println(e);
		}
		return true;
	}
	public boolean  addTableCell(){
		if(this.table==null || this.cell==null){
			return false;
		}
		
		if(this.document==null){
			this.document =new PdfDocument(pageSize);
		}
		if(this.writer==null){	
			try{
				writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
				document.open();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		try{
			this.table.addCell(this.cell);
		}catch(Exception e){
			System.out.println(e);
		}
		return true;
	}
	/**
	 * 设置文章端内容
	 * @param str 文章端内容
	 */
	private void setParagraph(String str){
		if(this.Paragraph!=null){
			this.Paragraph=null;
		}
		this.Paragraph =new PdfParagraph(str,this.FontChinese);
	}	
	/**
	 * 设置文章部分内容
	 * @param str 文章部分内容
	 */
	private void setPart(String str){
		if(this.part!=null){
			this.part=null;
		}
		this.part =new PdfPart(str,this.FontChinese);
	}
	/**
	 * 设置文章短语内容
	 * @param str 文章短语内容
	 */
	public void setElement(String str){
		if(this.element!=null){
			this.element=null;
		}
		this.element =new PdfElement(str,this.FontChinese);
	}
	
	public void setCell(String str){
		if(this.cell!=null){
			this.cell=null;
		}
		try{
			this.part=new PdfPart(str,this.FontChinese);
			this.cell=new PdfCell(this.part);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public boolean  setCellAlign(String str){
		if(this.cell==null){
			return false;
		}
		this.cell.setHorizontalAlignment(str);
		return true;
	}
	public boolean setCellVAlign(String str){
		if(this.cell==null){
			return false;
		}
		this.cell.setVerticalAlignment(str);
		return true;
	}
	public boolean setHeader(boolean value){
		if(this.cell==null){
			return false;
		}
		this.cell.setHeader(value);
		return true;
	}
	public boolean setCellColspan(int value){
		if(this.cell==null){
			return false;
		}
		this.cell.setColspan(value);
		return true;
	}
	public boolean setCellRowSpan(int value){
		if(this.cell==null){
			return false;
		}
		this.cell.setRowspan(value);
		return true;
	}
	public boolean setCellbC(String str){
		if(this.cell==null){
			return false;
		}
		this.cell.setBorderColor(ChangeColor.getColor(str));
		return true;
	}
	public void setTable(int row,int col){
		try{
			this.table=new PdfTable(row,col);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public boolean setTableBW(int value){
		return true;
	}
	/**
	 * 设置表格边框颜色
	 * @param str 颜色
	 * @return 是否成功
	 */
	public boolean setTableBC(String str){
		if(this.table==null){
			return false;
		}
		this.table.setBorderColor(ChangeColor.getColor(str));
		return true;
	}
	public boolean setTablePadd(float value){
		if(this.table==null){
			return false;
		}
		this.table.setPadding(value);
		return true;
	}
	public boolean setTableSpac(float value){
		if(this.table==null){
			return false;
		}
		this.table.setSpacing(value);
		return true;
	}
	public boolean setTableEndHead(){
		if(this.table==null){
			return false;
		}
		this.table.endHeaders();
		return true;
	}
	/**
	 * 设置文章段对齐方式
	 * @param str 文章段对齐方式
	 */
	public void setParagraphAlign(String str){
		this.Paragraph.setAlignment(str);
	}
	
	private void setImage(String str){
		if(this.image!=null){
			this.image=null;
		}
		this.image=new PdfImage(str);
	}
	private void setImgAbsSize(float width,float height){
		this.image.scaleAbsolute(width,height);
		
	}
	private void setImgPerSize(float width,float height)
	{
		this.image.scalePercent(width,height);
	}
	
	/**
	 * 给文章添加作者
	 * @param arg0 文章添加作者
	 */
	public void addAuthor(String arg0){
		if(this.document==null){
			this.document =new PdfDocument(pageSize);
		}
		if(this.writer==null){	
			try{
				writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
				document.open();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		try{
			this.document.addAuthor(arg0);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	
	/**
	 *给文章添加创建日期 
	 */
	public void addCreationDate(){
		if(this.document==null){
			this.document =new PdfDocument(pageSize);
		}
		if(this.writer==null){	
			try{
				writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
				document.open();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		try{
			this.document.addCreationDate();
		}catch(Exception e){
			System.out.println(e);
		}

	}
	/**
	 * 保存文章
	 *
	 */
	public void save(){
		this.document.close();
	}
	
	public void setFont(String fontFamily,float size,String fontColor,String fontStyle){
		if(this.FontChinese==null){
			try{
			this.bfChinese  = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			this.FontChinese = new Font(this.bfChinese, 32, Font.BOLD);
			}catch(Exception e){
				System.out.println(e);
			}
		}
		this.FontChinese.setFamily(fontFamily);
		this.FontChinese.setSize(size);
		this.FontChinese.setColor(ChangeColor.getColor(fontColor));
		this.FontChinese.setStyle(fontStyle);
	}
	public void addContent(String str){
		this.setPart(str);
		this.addPart();
	}

	public void addAlignContent(String str,String align){
		this.setParagraph(str);
		this.setParagraphAlign(align);
		this.addParagraph();
	}
	
	public void addContentLn(String str){
		this.setParagraph(str);
		this.addParagraph();
	}
	public void addAbsImage(String path,float width,float height,String align){
		this.setImage(path);
		this.image.setAlignment(align);
		this.setImgAbsSize(width,height);
		this.addImage();
	}
	public void addPerImage(String path,float width,float height,String align){
		this.setImage(path);
		this.image.setAlignment(align);
		this.setImgPerSize(width,height);
		this.addImage();
	}
	public void addAbsImage(String path,float width,float height){
		this.setImage(path);
		this.setImgAbsSize(width,height);
		this.addImage();
	}
	public void addPerImage(String path,float width,float height){
		this.setImage(path);
		this.setImgPerSize(width,height);
		this.addImage();
	}

	public static void main(String[] args){		
		String titleStr="项目总体开发计划";
		String para1Str="\n\n甲方：宇信鸿泰科技发展有限公司";
		String para2Str="乙方：杰软公司";
		String para3Str="1.编写目的";
		String para4Str="    本文叙述了BI Platform(JAVA平台)开发过程中的有关项目管理、项目软件开发计划以及项目评审等方面的具体方案，其目的在于为该项目的开发提供一个指导性的计划，并为项目开发过程中的具体运作制定相应的规范，同时也为项目的领导机构、开发机构以及评审机构提供一个对该项目开发过程的概括性描述。\n";
		
		try{
			//1。生成要产生pdf文件的对象--------------开始	
			CreatPdf createPdf=new CreatPdf();
			createPdf.setRectangle(PageSize.A4);
			createPdf.setFont("STSong-Light",32,"blue","Font.BOLD");
			createPdf.setFileName("e:\\pdf.pdf");
			//1.生成要产生pdf文件的对象--------------结束
			//2.给文章添加内容-----------------开始
			//A。添加段内容-----------开始			
			createPdf.addAlignContent(titleStr,"center");
			createPdf.setFont("宋体",16,"black","Font.NORMAL");
			createPdf.addContentLn(para1Str);
			createPdf.addContentLn(para2Str);
			createPdf.addContentLn(para3Str);
			createPdf.addContentLn(para4Str);
			//B。添加图片--------------开始
			createPdf.addPerImage("e:\\DrawBar.jpg",80,80,"Center");
			createPdf.addAlignContent("添加表格测试","center");
			//B。添加图片--------------结束		
			
			//C。添加表单-----------------开始						
			createPdf.setTable(6,2);
			createPdf.setTableBW(1);
			createPdf.setTableBC("green");
			createPdf.setTablePadd(5);
			createPdf.setTableSpac(3);
			
			createPdf.setCell("中guoren");
			createPdf.setCellColspan(3);
			createPdf.addTableCell();
			
			createPdf.setCell("hah中");
			createPdf.setCellColspan(2);
			createPdf.addTableCell();
			
			createPdf.setCell("zhong");
			createPdf.addTableCell();
			createPdf.setTableEndHead();
			createPdf.setCell("zhong");
			createPdf.addTableCell();
			createPdf.setCell("zhong");
			createPdf.addTableCell();
			createPdf.setCell("zhong");
			createPdf.addTableCell();
			createPdf.setCell("zhong");
			createPdf.addTableCell();
			createPdf.addTable();
			
			//C。添加表单-----------------结束
			//2.给文章添加内容-----------------结束
			
			//3.保存文件
			createPdf.save();
			
		}catch(Exception e){
			System.out.println(e);
		}

	}
}
