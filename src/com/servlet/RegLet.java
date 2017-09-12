package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.DBService;

/**
 * Servlet implementation class RegLet
 */
@WebServlet("/RegLet")
public class RegLet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 接收客户端信息
        String username = request.getParameter("username");
        username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        
        String password = request.getParameter("password");
        password = new String(password.getBytes("ISO-8859-1"),"UTF-8");
        
        String icon = request.getParameter("icon");
        
        System.out.println("username:---->"+username);
        System.out.println("password:---->"+password);
        System.out.println("icon:---->"+icon);

        // 新建服务对象
        DBService serv = new DBService();
        
     // 返回信息到客户端
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // 验证处理
        boolean loged = serv.register(username,password,icon);
        if (loged) {
            System.out.print("注册成功");
            out.print(username+"♣");
            out.print(password+"♣");
            out.print(icon);
        } else {
            System.out.print("注册失败");
            out.print("x");
        }
        
        out.flush();
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
