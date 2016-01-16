package com.icss.hit.servlet.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hit.bean.FileBean;
import com.icss.hit.hibernate.vo.Files;

public class DownLoad extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DownLoad() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 用户ID
		long uid = 1;
		
		if( request.getSession().getAttribute("UserId") != null ){
			uid = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
		long fId = 1;
		try{
			fId = Long.parseLong(request.getParameter("id"));
		}catch(Exception e){
			response.sendRedirect("MyFile.do");
		}
		
		
		
		// 根据传过来的ID来得到文件信息
		Files files = new FileBean().getFile(fId);
		if( files == null ){
			System.out.println("文件不存在");
			request.getRequestDispatcher("MyFile.do").forward(request, response);
		}
		// 该文件所属用户的ID
		long fuid = files.getFileFolder().getSysUser().getSuId();
		
		
		// 文件所属文件夹是否共享
		String share = files.getFileFolder().getFfShare();
		// 如果该文件不属于该登陆用户
		if( uid != fuid && share.equals("0")){
			System.out.println(uid);
			System.out.println(fuid);
			System.out.println(share);
			request.getRequestDispatcher("MyFile.do").forward(request, response);
		}else{
			// 文件路径
			String filepath=request.getRealPath("/file/") + "\\" + fuid + "\\";
		filepath +=  files.getFileFolder().getFfName() + "\\"; 
		String filename = files.getFName();
		java.io.File file = new java.io.File(filepath + filename);
		if (!file.exists()) {
			response.sendRedirect("MyFile.do");
		}
		// 读取文件流
		java.io.FileInputStream fileInputStream = new java.io.FileInputStream(file);
		javax.servlet.ServletOutputStream out = response.getOutputStream();
		// 下载文件
		// 设置响应头和下载保存的文件名
		if (filename != null && filename.length() > 0) {
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" 
					+ new String(filename.getBytes("gb2312"),"iso8859-1") + "");
			if (fileInputStream != null) {
				int filelen = fileInputStream.available();
				//文件太大时内存不能一次读出,要循环
				byte a[] = new byte[filelen];
				fileInputStream.read(a);
				out.write(a);
			}
			fileInputStream.close();
			out.close();
		}
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
