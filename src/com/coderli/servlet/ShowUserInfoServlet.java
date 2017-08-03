package com.coderli.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coderli.entity.User;
import com.coderli.service.UserService;
import com.coderli.service.imp.UserServiceImp;

public class ShowUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		//获取请求信息
		//处理请求信息
		UserService service = new UserServiceImp();
		List<User> list = service.getAllUserInfoService();
		//响应处理结果
		//将查询结果放到request作用域中
		req.setAttribute("list", list);
		//请求转发
		req.getRequestDispatcher("showInfo.jsp").forward(req, resp);
	}
}
