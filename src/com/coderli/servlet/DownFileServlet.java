package com.coderli.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.coderli.entity.User;
import com.coderli.service.UserService;
import com.coderli.service.imp.UserServiceImp;

public class DownFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		// 设置响应编码格式
		resp.setCharacterEncoding("utf-8");
		// 获取请求信息
		String uid = req.getParameter("uid");
		// 调用service处理
		UserService service = new UserServiceImp();
		User user = service.getUserInfoByUidService(uid);
		//设置页面展示的文件格式
		resp.setContentType(user.getType());
		// 处理请求信息
		// 测试输出
		// System.out.println(user);
		//获取文件的路径
		String filePath= this.getServletContext().getRealPath("/upload")+"/"+user.getPhotoName();
		File file = new File(filePath);
		resp.setContentLength((int) file.length());
		resp.setHeader("Content-Disposition", "attachment;filename="+user.getRealName());
		// 响应处理结果
		InputStream is = new FileInputStream(file);
		OutputStream os = resp.getOutputStream();
		IOUtils.copy(is, os);
		//关闭流资源
		is.close();
		os.close();
	}
}
