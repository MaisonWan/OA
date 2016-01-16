package com.icss.hit.component;

import java.io.*;
import jxl.*;
import jxl.write.*;
import jxl.format.*;
import java.util.*;


public class Excel {

  private jxl.write.WritableWorkbook WorkBuk=null ;
  private jxl.write.WritableSheet ws=null;
  private FileOutputStream fos = null;
  private InputStream is = null;
  private jxl.Workbook rwb = null;
  private jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.ARIAL,10, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
  private jxl.write.WritableCellFormat wcfFC= null;


  /**
   * 在指定路径下新建一个Excel文件。
   * @parma String FileName 文件名称+文件路径（文件绝对路径 如：D:\\sheet1.xls）
   * @return boolean
  */
 public boolean createExcel(String FileName) throws Exception {
   boolean Rtn = false;
   try {
     fos = new FileOutputStream(FileName);
     WorkBuk = Workbook.createWorkbook(fos);
     Rtn = true;
   }
   catch (Exception e) {
     Rtn = false;
   }
   finally {
     try {

     }
     catch (Exception e) {

     }

   }
   return Rtn;
 }


  /**
   * 设置当前sheet工作薄的名称及sheet号
   * @parma int SheetIdx(sheet号) String SheetName(工作薄的名称)
   * @return boolean
  */
 public boolean setCurSheet(int SheetIdx, String SheetName) {
   boolean Rtn = false;
   try {
     ws = WorkBuk.createSheet(SheetName, SheetIdx);
     Rtn = true;
   }
   catch (Exception e) {
     Rtn = false;
   }
   finally {
     try {

     }
     catch (Exception e) {
     }
   }
   return Rtn;
 }


   /**
   * 向cell单元格中写入String型数据并可以设置单元格背景
   * @parma int col(列号) int row(行号) Txt 内容
   * @return boolean
   */
  public boolean setCellText(int col, int row, String Txt, String bgcolor) {
    boolean Rtn = false;
    try {
      wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 10,
                                       WritableFont.NO_BOLD, false,
                                       UnderlineStyle.NO_UNDERLINE,
                                       jxl.format.Colour.BLACK);
      setCellBgcolor(bgcolor);
      jxl.write.Label labelC = new jxl.write.Label(col, row, Txt, wcfFC);
      ws.addCell(labelC);
      Rtn = true;
    }
    catch (Exception e) {
      Rtn = false;
    }
    finally {
      try {

      }
      catch (Exception e) {

      }
    }
    return Rtn;
  }



  /**
   * 向cell单元格中写入Date型数据并可以设置单元格背景
   * @parma int col(列号) int row(行号) Txt 内容
   * @return boolean
  */
 public boolean setCellDate(int col, int row, Date Txt, String bgcolor) {
   boolean Rtn = false;
   try {
     wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 10,
                                      WritableFont.NO_BOLD, false,
                                      UnderlineStyle.NO_UNDERLINE,
                                      jxl.format.Colour.BLACK);
     setCellBgcolor(bgcolor);
     jxl.write.DateTime labelC = new jxl.write.DateTime(col, row, Txt, wcfFC);
     ws.addCell(labelC);
     Rtn = true;
   }
   catch (Exception e) {
     Rtn = false;
   }
   finally {
     try {

     }
     catch (Exception e) {

     }

   }
   return Rtn;

 }


  /**
   * 向cell单元格中写入数字：double型数字并可以设置单元格背景
   * @parma int col(列号) int row(行号) Txt 内容
   * @return boolean
  */
  public boolean setCellNumber(int col,int row,double Txt,String bgcolor)
  {
    boolean Rtn = false;
    try {
      setCellBgcolor(bgcolor);
      jxl.write.Number labelC = new jxl.write.Number(col, row, Txt, wcfFC);
      ws.addCell(labelC);
      Rtn = true;
    }
    catch (Exception e) {
      Rtn = false;
    }
    finally {
      try {

      }
      catch (Exception e) {

      }

    }
    return Rtn;

  }


  /**
   * 向cell单元格中写入String型数据并可以设置字体样式
   * @parma int col(列号) int row(行号) Txt 内容 int format 字体样式编号 int fontSize 字体大小
   * @return boolean
  */
  public boolean setCellText(int col,int row,String Txt,int format,int fontSize)
  {
      boolean Rtn=false;
      try
      {
        setCellFont(format,fontSize);
        wcfFC=new jxl.write.WritableCellFormat(wfc);
        jxl.write.Label labelC = new jxl.write.Label(col, row, Txt,wcfFC);
        ws.addCell(labelC);
        Rtn=true;
      }
      catch(Exception e)
      {
        Rtn=false;
      }
      finally
      {
        try
        {

        }
        catch(Exception e)
        {

        }
      }
      return Rtn;
    }



  /**
   * 向cell单元格中写入Date型数据并可以设置字体样式
   * @parma int col(列号) int row(行号) Txt 内容 int format 字体样式编号 int fontSize 字体大小
   * @return boolean
  */
  public boolean setCellDate(int col,int row,Date Txt,int format,int fontSize)
  {
      boolean Rtn=false;
      try
      {
          setCellFont(format,fontSize);
          wcfFC=new jxl.write.WritableCellFormat(wfc);
          jxl.write.DateTime  labelC = new jxl.write.DateTime (col, row, Txt,wcfFC);
          ws.addCell(labelC);
          Rtn=true;
      }
      catch(Exception e)
      {
          Rtn=false;
      }
      finally
      {
          try
          {

          }
          catch(Exception e)
          {

          }

      }
      return Rtn;
  }


  /**
   * 向cell单元格中写入数字：double型数字并可以设置字体样式
   * @parma int col(列号) int row(行号) Txt 内容 int format 字体样式编号 int fontSize 字体大小
   * @return boolean
  */
  public boolean setCellNumber(int col,int row,double Txt,int format,int fontSize)
  {
      boolean Rtn=false;
      try
      {
          setCellFont(format,fontSize);
          wcfFC=new jxl.write.WritableCellFormat(wfc);
          jxl.write.Number  labelC = new jxl.write.Number (col, row, Txt,wcfFC);
          ws.addCell(labelC);
          Rtn=true;
      }
      catch(Exception e)
      {
         Rtn=false;
      }
      finally
      {
          try
          {

          }
          catch(Exception e)
          {

          }

      }
      return Rtn;
 }


  /**
   * 向cell单元格中写入String型数据
   * @parma int col(列号) int row(行号) Txt 内容
   * @return boolean
  */
  public boolean setCellText(int col,int row,String Txt)
  {
      boolean Rtn=false;
      try
      {
          jxl.write.Label labelC = new jxl.write.Label(col, row, Txt);
          ws.addCell(labelC);
          Rtn=true;
      }
      catch(Exception e)
      {
          Rtn=false;
      }
      finally
      {
          try
          {

          }
          catch(Exception e)
          {

          }
      }
      return Rtn;
    }


  /**
   * 向cell单元格中写入Date型数据
   * @parma int col(列号) int row(行号) Txt 内容
   * @return boolean
  */
  public boolean setCellDate(int col,int row,Date Txt)
  {
      boolean Rtn=false;
      try
      {
              //String dd=DateUtils.getInstance().format(Txt,"yyyy-mm-dd");
              //System.out.println(dd);
              jxl.write.DateTime  labelC = new jxl.write.DateTime (col, row, Txt);
              ws.addCell(labelC);
              Rtn=true;
      }
      catch(Exception e)
      {
        Rtn=false;
      }
      finally
      {
              try
              {

              }
              catch(Exception e)
              {

              }

      }
      return Rtn;

  }


  /**
   * 向cell单元格中写入数字：double型数字。
   * @parma int col(列号) int row(行号) Txt 内容
   * @return boolean
  */
  public boolean setCellNumber(int col,int row,double Txt)
  {
      boolean Rtn=false;
      try
      {
              jxl.write.Number  labelC = new jxl.write.Number (col, row, Txt);
              ws.addCell(labelC);
              Rtn=true;
      }
      catch(Exception e)
      {
        Rtn=false;
      }
      finally
      {
              try
              {

              }
              catch(Exception e)
              {

              }

      }
      return Rtn;

  }

  /**
   * 向cell单元格中写入数字：float型数字。
   * @parma int col(列号) int row(行号) Txt 内容
   * @return boolean
  */
  public boolean setCellNumber(int col,int row,float Txt)
  {
      boolean Rtn=false;
      try
      {
              jxl.write.Number  labelC = new jxl.write.Number (col, row, Txt);
              ws.addCell(labelC);
              Rtn=true;
      }
      catch(Exception e)
      {
        Rtn=false;
      }
      finally
      {
              try
              {

              }
              catch(Exception e)
              {

              }

      }
      return Rtn;

  }

          /**
   * 将写入的cell的内容保存到Excel文件中.
   * @parma
   * @return boolean
  */
  public boolean saveExcel()
  {
    boolean Rtn=false;
    try
    {
        //写入Exel工作表
        WorkBuk.write();
        //关闭Excel工作薄对象
        WorkBuk.close();
        Rtn=true;
    }
    catch(Exception e)
    {
       Rtn=false;
    }
    finally
    {
        try
        {

        }
        catch(Exception e)
        {

        }

    }
    return Rtn;

  }

          /**
   * 打开Excel.
   * @parma fileName Excel文件名+文件路径（绝对路径）
   * @return boolean
  */
  public boolean openExcel(String fileName)
  {
      boolean Rtn=false;
      try
      {
          is = new FileInputStream(fileName);
          rwb = Workbook.getWorkbook(is);
          Rtn=true;
      }
      catch(Exception e)
      {
         Rtn=false;
      }
      finally
      {
          try
          {

          }
          catch(Exception e)
          {

          }

      }
      return Rtn;
  }


          /**
   * 关闭打开的Excel.
   * @
   * @return boolean
  */
  public boolean closeExcel()
  {
    boolean Rtn=false;
    try
    {
      rwb.close();
      Rtn=true;
    }
    catch(Exception e)
    {
      Rtn=false;
    }
    finally
    {
      try
      {
              rwb.close();
      }
      catch(Exception e)
      {
              Rtn=false;
      }

    }
    return Rtn;

  }


 /**
   * 得到工作薄的名称
   * @parma sheetIndex 工作薄号
   * @return String
  */

  public String getSheetName(int sheetIndex) {

    String sheetNam = "";
    try {
      jxl.Sheet rs = rwb.getSheet(sheetIndex);
      sheetNam = rs.getName();
    }
    catch (Exception e) {
      sheetNam = "";
    }
    finally {
      try {

      }
      catch (Exception e) {
        sheetNam = "";
      }
    }
    return sheetNam;

  }

  /**
   * 得到当前工作薄的总列数
   * @parma sheetIndex 工作薄号
   * @return int
   */

  public int getColCount(int sheetIndex) {

    int colCnt = 0;
    try {
      jxl.Sheet rs = rwb.getSheet(sheetIndex);
      colCnt = rs.getColumns();
    }
    catch (Exception e) {
      colCnt = 0;
    }
    finally {
      try {

      }
      catch (Exception e) {
        colCnt = 0;
      }

    }
    return colCnt ;

  }


  /**
   * 得到当前工作薄的总行数
   * @parma sheetIndex 工作薄号
   * @return int
   */

  public int getRowCount(int sheetIndex) {

    int colCnt = 0;
    try {
      jxl.Sheet rs = rwb.getSheet(sheetIndex);
      colCnt = rs.getRows();
    }
    catch (Exception e) {
      colCnt = 0;
    }
    finally {
      try {

      }
      catch (Exception e) {
        colCnt = 0;
      }

    }
    return colCnt ;

  }


  /**
   * 获取某一列的所有单元格
   * @parma col 列数
   * @return String[]
   */

  public String[] getColArray(int col) {

      Sheet rs = rwb.getSheet(0);
      Cell[] getArray = rs.getColumn(col);
      String Str[] = new String[getArray.length];
      try {
        for(int i=0;i<getArray.length;i++)
        {
          Cell c00 = rs.getCell(col, i);
          Str[i] = c00.getContents();
        }
    }
    catch (Exception e) {
      Str = null;
    }
    finally {
      try {

      }
      catch (Exception e) {
        Str = null;
      }

    }
    return Str ;

  }


  /**
   * 获取某一行的所有单元格
   * @parma col 列数
   * @return String[]
   */

  public String[] getRowArray(int row) {

      Sheet rs = rwb.getSheet(0);
      Cell[] getArray = rs.getRow(row);
      String Str[] = new String[getArray.length];

    try
    {
      for(int i=0;i<getArray.length;i++)
      {
        Cell c00 = rs.getCell(i,row);
        Str[i] = c00.getContents();
      }
    }
    catch (Exception e) {
      Str = null;
    }
    finally {
      try {

      }
      catch (Exception e) {
        Str = null;
      }

    }
    return Str ;

  }

  /**
   * 设置字体（共11中样式） 包括字体样式、颜色、大小、加粗、斜体、下划线等的设置。
   * @parma int format 样式编号 int fontSize 字体大小
   * @return boolean
   */
  public void  setCellFont(int format,int fontSize)
  {
    if(format==0)
    {
      //字体 ARIAL 颜色红
      wfc = new jxl.write.WritableFont(WritableFont.ARIAL, fontSize,
                                       WritableFont.NO_BOLD,false,
                                       UnderlineStyle.NO_UNDERLINE,
                                       jxl.format.Colour.RED);
    }
    else if(format==1)
    {
      //字体 ARIAL 颜色红,加粗,单下划线,斜体
      wfc = new jxl.write.WritableFont(WritableFont.ARIAL, fontSize,
                                       WritableFont.BOLD,true,
                                       UnderlineStyle.SINGLE,
                                       jxl.format.Colour.RED);
    }
    else if(format==2)
    {
      //字体 TAHOMA 颜色绿
      wfc = new jxl.write.WritableFont(WritableFont.TAHOMA, fontSize,
                                       WritableFont.NO_BOLD,false,
                                       UnderlineStyle.NO_UNDERLINE,
                                       jxl.format.Colour.GREEN);
    }
    else if(format==3)
    {
      //字体 TAHOMA 颜色绿,加粗,单下划线,斜体
      wfc = new jxl.write.WritableFont(WritableFont.TAHOMA, fontSize,
                                       WritableFont.BOLD,true,
                                       UnderlineStyle.SINGLE,
                                       jxl.format.Colour.GREEN);
      String kk="";
    }
    else if(format==4)
    {
      //字体 TIMES 颜色蓝
      wfc = new jxl.write.WritableFont(WritableFont.TIMES, fontSize,
                                       WritableFont.NO_BOLD,false,
                                       UnderlineStyle.NO_UNDERLINE,
                                       jxl.format.Colour.BLUE);
      String kk="";
    }
    else if(format==5)
    {
      //字体 TAHOMA 颜色蓝,加粗,单下划线,斜体
      wfc = new jxl.write.WritableFont(WritableFont.TIMES, fontSize,
                                       WritableFont.BOLD,true,
                                       UnderlineStyle.SINGLE,
                                       jxl.format.Colour.BLUE);
    }
    else if(format==6)
    {
      //字体 COURIER 颜色深蓝
      wfc = new jxl.write.WritableFont(WritableFont.COURIER, fontSize,
                                       WritableFont.NO_BOLD,false,
                                       UnderlineStyle.NO_UNDERLINE,
                                       jxl.format.Colour.DARK_BLUE);
    }
    else if(format==7)
    {
      //字体 COURIER 颜色深蓝,加粗,单下划线,斜体
      wfc = new jxl.write.WritableFont(WritableFont.COURIER, fontSize,
                                       WritableFont.BOLD,true,
                                       UnderlineStyle.SINGLE,
                                       jxl.format.Colour.DARK_BLUE);
    }
    else if(format==8)
    {
       //字体 ARIAL 颜色深红,加粗,单下划线,斜体
      wfc = new jxl.write.WritableFont(WritableFont.ARIAL, fontSize,
                                       WritableFont.BOLD,true,
                                       UnderlineStyle.SINGLE,
                                       jxl.format.Colour.DARK_RED);
    }
    else if(format==9)
    {
       //字体 TAHOMA 颜色黑,加粗,单下划线,斜体
      wfc = new jxl.write.WritableFont(WritableFont.TAHOMA, fontSize,
                                       WritableFont.BOLD,true,
                                       UnderlineStyle.SINGLE,
                                       jxl.format.Colour.BLACK);
    }
    else
    {
       //字体 TAHOMA 颜色黑
      wfc = new jxl.write.WritableFont(WritableFont.TAHOMA, fontSize,
                                       WritableFont.NO_BOLD,false,
                                       UnderlineStyle.NO_UNDERLINE,
                                       jxl.format.Colour.BLACK);
    }
  }




  /**
   * 设置单个cell的背景色
   * @parma int col 列号 int row 行号 String colorStr 背景色
   * @return boolean
   */
  public void setCellBgcolor(String colorStr) {
    boolean Rtn = false;
    try {

      wcfFC = new jxl.write.WritableCellFormat(wfc);
      if (colorStr == "red") {
        wcfFC.setBackground(jxl.format.Colour.RED);

      }
      else if (colorStr == "green") {
        wcfFC.setBackground(jxl.format.Colour.GREEN);

      }
      else if (colorStr == "blue") {
        wcfFC.setBackground(jxl.format.Colour.BLUE);

      }
      else if (colorStr == "yellow") {
        wcfFC.setBackground(jxl.format.Colour.YELLOW);

      }
      else if (colorStr == "brown") {
        wcfFC.setBackground(jxl.format.Colour.BROWN);

      }
      else if (colorStr == "dark_blue") {
        wcfFC.setBackground(jxl.format.Colour.DARK_BLUE);
      }
      else {
        wcfFC.setBackground(jxl.format.Colour.BLACK);
      }

    }
    catch (Exception e) {
      Rtn = false;
    }
    finally {
      try {

      }
      catch (Exception e) {}
    }

  }



  /**
   * 取得某个单元格的内容。不论单元格是何种数据类型都将返回字符型。
   * @parma int col 列号 int row 行号
   * @return String
   */
  public String getCellContent(int col, int row) {
    String cellContent = "";
    try {
      //默认打开第一张工作薄。
      Sheet rs = rwb.getSheet(0);
      //取得某一单元格的内容
      Cell c00 = rs.getCell(col, row);
      cellContent = c00.getContents();
    }
    catch (Exception e) {
      cellContent = "";
    }
    finally {
      try {

      }
      catch (Exception e) {
        cellContent = "";
      }
    }
    return cellContent;
  }


  /**
   * 取得某个单元格数据类型。没传入参数sheetIndex,默认取第一长工作表
   * @parma int col 列号 int row 行号
   * @return String
   */
  public String getCellType(int col, int row) {
    String typeStr = "";
    try {
      //读取第一张工作表
      Sheet rs = rwb.getSheet(0);
      //获得第一个单元格对象
      Cell c00 = rs.getCell(col, row);
      //判断单元格的类型, 做出相应的转化
      if (c00.getType() == CellType.LABEL) {
        typeStr = "String";
      }
      if (c00.getType() == CellType.DATE) {
        typeStr = "Date";
      }
      if (c00.getType() == CellType.NUMBER) {
        typeStr = "Number";
      }
    }
    catch (Exception e) {
      typeStr = "";
    }
    finally {
      try {

      }
      catch (Exception e) {
        typeStr = "";
      }
    }
    return typeStr;
  }

  /**
   * 取得某个单元格字符内容。没传入参数sheetIndex,默认取第一长工作表
   * @parma int col 列号 int row 行号
   * @return String
   */
  public String getCellTex(int col, int row) {
    String cellContent = "";
    try {
      //默认打开第一张工作薄。
      Sheet rs = rwb.getSheet(0);
      Cell c00 = rs.getCell(col, row);
      LabelCell labelc00 = (LabelCell) c00;
      cellContent = labelc00.getString();
    }
    catch (Exception e) {
      cellContent = "";
    }
    finally {
      try {

      }
      catch (Exception e) {
        cellContent = "";
      }
    }
    return cellContent;
  }



  /**
   * 取得某个单元格数字内容。没传入参数sheetIndex,默认取第一长工作表
   * @parma int col 列号 int row 行号
   * @return String
   */
  public double getCellNum(int col, int row) {
    double cellContent = 0;
    try {
      //默认打开第一张工作薄。
      Sheet rs = rwb.getSheet(0);
      Cell c10 = rs.getCell(col, row);
      NumberCell numc10 = (NumberCell) c10;
      cellContent = numc10.getValue();
    }
    catch (Exception e) {
      cellContent = 0;
    }
    finally {
      try {

      }
      catch (Exception e) {
        cellContent = 0;
      }
    }
    return cellContent;
  }


  /**
   * 取得某个单元格日期内容。没传入参数sheetIndex,默认取第一长工作表
   * @parma int col 列号 int row 行号
   * @return Date
   */
  public Date getCellDate(int col, int row) {
    Date cellContent = null;
    try {
      //默认打开第一张工作薄。
      Sheet rs = rwb.getSheet(0);
      Cell c00 = rs.getCell(col, row);
      DateCell labeldate00 = (DateCell) c00;
      cellContent = labeldate00.getDate();
    }
    catch (Exception e) {
      cellContent = null;
    }
    finally {
      try {

      }
      catch (Exception e) {
        cellContent = null;
      }
    }
    return cellContent;
  }




  /**
   * 读取指定路径下的Excel文件的数据。没传入参数sheetIndex,默认取第一张工作表
   * @parma
   * @return String[][] 返回二维数组
   */
  public String[][] readExcel() {
    String[][] strTemp = null;
    try {
      Sheet rs = rwb.getSheet(0);
      int rows = rs.getRows();
      int cols = rs.getColumns();
      strTemp = new String[rows][cols];
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          Cell ctemp = rs.getCell(j, i);
          strTemp[i][j] = ctemp.getContents();
        }
      }

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    finally {
      try {

      }
      catch (Exception e) {}
    }
    return strTemp;
  }

  public static String toCN(String strvalue) {
    try {
      if (strvalue == null)
        return null;
      else {
        strvalue = new String(strvalue.getBytes("gb2312"), "GBK");
        return strvalue;
      }
    }
    catch (Exception e) {
      return null;
    }
  }

  public static String DBtoCN(String strvalue) {
    try {
      if (strvalue == null)
        return null;
      else {
        strvalue = new String(strvalue.getBytes("ISO-8859-1"), "gb2312");
        return strvalue;
      }
    }
    catch (Exception e) {
      return null;
    }
  }

}
