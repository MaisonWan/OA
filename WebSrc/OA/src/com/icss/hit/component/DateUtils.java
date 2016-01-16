/*--

 Copyright (C) 2001-2003 Aetrion LLC.
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:

 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions, and the following disclaimer.

 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions, and the disclaimer that follows
    these conditions in the documentation and/or other materials
    provided with the distribution.

 3. The name "JPublish" must not be used to endorse or promote products
    derived from this software without prior written permission.  For
    written permission, please contact info@aetrion.com.

 4. Products derived from this software may not be called "JPublish", nor
    may "JPublish" appear in their name, without prior written permission
    from Aetrion LLC (info@aetrion.com).

 In addition, the authors of this software request (but do not require)
 that you include in the end-user documentation provided with the
 redistribution and/or in the software itself an acknowledgement equivalent
 to the following:
     "This product includes software developed by
      Aetrion LLC (http://www.aetrion.com/)."

 THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY DIRECT,
 INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 POSSIBILITY OF SUCH DAMAGE.

 For more information on JPublish, please see <http://www.jpublish.org/>.

 */

package com.icss.hit.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;

/** Utility class for parsing and formatting dates.  An instance of this
    class can be dropped in the context to allow parsing and formatting
    dates from within view templates.

    @author Anthony Eden
    @since 1.4
*/

public class DateUtils{

    private static final DateUtils dateUtils = new DateUtils();

    /** Get a shared instance of the DateUtilities class.

        @return A DateUtilities instance
    */

    public static DateUtils getInstance(){
        return dateUtils;
    }

    /** Format the specified date using the specified format String.  The
        format String follows the rules specified in the
        <code>java.text.SimpleDateFormat</code> class.

        @param date The date
        @param format The format String
        @return A formatted Date String
    */

    public String format(Date date, String format){
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(date);
    }

    /** Parse the specified date String using the specified format String.
        The format String follows the rules specified in the
        <code>java.text.SimpleDateFormat</code> class.

        @param date The date String
        @param format The format String
        @return A Date object
        @throws ParseException
    */

    public Date parse(String date, String format) throws ParseException{
        SimpleDateFormat fmt = new SimpleDateFormat(format);

        return fmt.parse(date);
    }

    public String getToday(){
      String result="";
      Date date=new Date();
      result=this.format(date,"yyyy-MM-dd");
      return result;
    }

    public String getTimeNoSeparate(){
      String result="";
      Date date=new Date();
      result=this.format(date,"yyyyMMddHHmmss");
      return result;
    }
       public String getTime(){
      String result="";
      Date date=new Date();
      result=this.format(date,"yyyy-MM-dd HH:mm:ss");
      return result;
    }

    public String getNow(){
      String result="";
      Date date=new Date();
      int year=Calendar.getInstance(Locale.getDefault()).YEAR;
      int month=Calendar.getInstance(Locale.getDefault()).MONTH;
      int day=Calendar.getInstance(Locale.getDefault()).DAY_OF_MONTH;
      int week=Calendar.getInstance(Locale.getDefault()).DAY_OF_WEEK;
      result=this.format(date,"yyyyMMdd");
      return result;
    }

    /**
     * 将某类字符串转化为日期格式
     * @param str
     * @return
     */
    public Date format(String str,String format){
      Date result=null;

      try
      {
	str+=" ";
        int endStr=str.indexOf(" ");
        String dateString=str.substring(0,endStr);
        result=this.parse(dateString,format);
      }
      catch (Exception ex)
      {

      }

      return result;
    }
  public String formatDate(String str){
      String result="";

      try
      {
        if(str.equals("")){
          return result;
        }
	str+=" ";
        int endStr=str.indexOf(" ");
        String dateString=str.substring(0,endStr);
        String[] st = dateString.split("/");
        result = st[2]+"-"+st[0]+"-"+st[1];
      }
      catch (Exception ex)
      {

      }

      return result;
    }

    public String formatYearMonth(String str){
        String result="";

        try
        {
          if(str.equals("")){
            return result;
          }
          str+=" ";
          int endStr=str.indexOf(" ");
          String dateString=str.substring(0,endStr);
          String[] st = dateString.split("/");
          result = st[2]+"."+st[0];
        }
        catch (Exception ex)
        {

        }

        return result;
      }

    public static void main(String[] args)
    {
      //String x=DateUtils.getInstance().getTime();
      try
      {
       Date xx=DateUtils.getInstance().parse("9/1/2002 0:0:0","dd/MM/yyyy");

       String yy=DateUtils.getInstance().format(xx,"yyyy-MM-dd");


       System.out.println(yy);
      }
      catch (Exception ex)
      {
        System.out.print(ex.getMessage());
      }




    }

}
