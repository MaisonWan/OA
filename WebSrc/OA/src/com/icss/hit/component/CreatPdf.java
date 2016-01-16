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
	private Rectangle pageSize=PageSize.A4;        	//ҳ���ͺ�
	private String fileName="e:\\sychallenger.pdf";	//����pdf�ļ�����
	private PdfDocument document=null;			    //��������pdf�ļ����ĵ�����
	private PdfWriter writer=null;				    //��������pdf�ļ���Writer����
	private BaseFont bfChinese=null;			   	//������������֧������
	private Font FontChinese=null;				    //����pdf�����ݵ�����
	private PdfParagraph Paragraph=null;		    //���η�ʽ������������
	private PdfPart part=null;					    //�����ַ�ʽ������������
	private PdfElement element=null;			    //���﷽ʽ������������
	
	private PdfImage image=null;					//Ҫ�����ͼƬ
	private PdfTable table=null;					//Ҫ����ı��
	private PdfCell cell=null;						//Ҫ����ı�����Ԫ��
	
	/**
	 * ���캯��ͬʱ��ʼ���������
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
	 * ���������С
	 * @param value �����Сֵ
	 */
	private void setFontSize(float value){
		this.FontChinese.setSize(value);
	}
	/**
	 * ������������
	 * @param str ��������
	 */
	private void setFontStyle(String str){
		this.FontChinese.setStyle(str);
	}
	
	private void setFontColor(Color color){
		this.FontChinese.setColor(color);
		
	}
	/**
	 * ����ҳ���ͺ�
	 * @param value  ҳ���ͺ�
	 */
	public void setRectangle(Rectangle value){
		this.pageSize=value;
	}
	/**
	 * �������ɵ��ļ�����
	 * @param str ����pdf�ļ�������
	 */
	public void setFileName(String str){
		this.fileName=str;
	}
	/**
	 * ���û������� ����֧������
	 * @param baseFont ��������
	 */
	private void setBfChinese(BaseFont baseFont){
		this.bfChinese=baseFont;
	}
	/**
	 * ��������
	 * @param font �������
	 */
	private void setFontChinese(Font font){
		this.FontChinese=font;
	}
	
	/**
	 * ��pdf�����������
	 * @param arg0 ��������
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
	 * �������¶�����
	 * @param str ���¶�����
	 */
	private void setParagraph(String str){
		if(this.Paragraph!=null){
			this.Paragraph=null;
		}
		this.Paragraph =new PdfParagraph(str,this.FontChinese);
	}	
	/**
	 * �������²�������
	 * @param str ���²�������
	 */
	private void setPart(String str){
		if(this.part!=null){
			this.part=null;
		}
		this.part =new PdfPart(str,this.FontChinese);
	}
	/**
	 * �������¶�������
	 * @param str ���¶�������
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
	 * ���ñ��߿���ɫ
	 * @param str ��ɫ
	 * @return �Ƿ�ɹ�
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
	 * �������¶ζ��뷽ʽ
	 * @param str ���¶ζ��뷽ʽ
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
	 * �������������
	 * @param arg0 �����������
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
	 *��������Ӵ������� 
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
	 * ��������
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
		String titleStr="��Ŀ���忪���ƻ�";
		String para1Str="\n\n�׷������ź�̩�Ƽ���չ���޹�˾";
		String para2Str="�ҷ�������˾";
		String para3Str="1.��дĿ��";
		String para4Str="    ����������BI Platform(JAVAƽ̨)���������е��й���Ŀ������Ŀ��������ƻ��Լ���Ŀ����ȷ���ľ��巽������Ŀ������Ϊ����Ŀ�Ŀ����ṩһ��ָ���Եļƻ�����Ϊ��Ŀ���������еľ��������ƶ���Ӧ�Ĺ淶��ͬʱҲΪ��Ŀ���쵼���������������Լ���������ṩһ���Ը���Ŀ�������̵ĸ�����������\n";
		
		try{
			//1������Ҫ����pdf�ļ��Ķ���--------------��ʼ	
			CreatPdf createPdf=new CreatPdf();
			createPdf.setRectangle(PageSize.A4);
			createPdf.setFont("STSong-Light",32,"blue","Font.BOLD");
			createPdf.setFileName("e:\\pdf.pdf");
			//1.����Ҫ����pdf�ļ��Ķ���--------------����
			//2.�������������-----------------��ʼ
			//A����Ӷ�����-----------��ʼ			
			createPdf.addAlignContent(titleStr,"center");
			createPdf.setFont("����",16,"black","Font.NORMAL");
			createPdf.addContentLn(para1Str);
			createPdf.addContentLn(para2Str);
			createPdf.addContentLn(para3Str);
			createPdf.addContentLn(para4Str);
			//B�����ͼƬ--------------��ʼ
			createPdf.addPerImage("e:\\DrawBar.jpg",80,80,"Center");
			createPdf.addAlignContent("��ӱ�����","center");
			//B�����ͼƬ--------------����		
			
			//C����ӱ�-----------------��ʼ						
			createPdf.setTable(6,2);
			createPdf.setTableBW(1);
			createPdf.setTableBC("green");
			createPdf.setTablePadd(5);
			createPdf.setTableSpac(3);
			
			createPdf.setCell("��guoren");
			createPdf.setCellColspan(3);
			createPdf.addTableCell();
			
			createPdf.setCell("hah��");
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
			
			//C����ӱ�-----------------����
			//2.�������������-----------------����
			
			//3.�����ļ�
			createPdf.save();
			
		}catch(Exception e){
			System.out.println(e);
		}

	}
}
