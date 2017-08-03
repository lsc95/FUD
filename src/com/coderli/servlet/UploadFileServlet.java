package com.coderli.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.coderli.entity.User;
import com.coderli.service.UserService;
import com.coderli.service.imp.UserServiceImp;

public class UploadFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		// 设置响应编码格式
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// 创建FileItemFactory对象
		FileItemFactory factory = new DiskFileItemFactory();
		// 创建ServletFileUpload对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 创建一个对象用来保存User对象的值
		User user = null;
		// 使用servletFileUpload解析request对象
		List<FileItem> list = null;
		try {
			// 设置文件大小100个字节
			upload.setFileSizeMax(1024 * 100);
			list = upload.parseRequest(req);
		} catch (FileUploadException e) {
			resp.getWriter().write("文件过大，最大只能上传100kb");
		}
		user = new User();
		for (FileItem fileItem : list) {
			// 获取上传表单项
			if (!fileItem.isFormField()) {
				// 获取文件的名字
				String realName = fileItem.getName();
				user.setRealName(realName);
				// 获取文件后缀名
				String afterName = realName
						.substring(realName.lastIndexOf(".") + 1);
				if(!("jpg".equals(afterName.toLowerCase())||"png".equals(afterName.toLowerCase())||"gif".equals(afterName.toLowerCase()))){
					resp.getWriter().write("文件类型不支持，暂时只支持png,jpg,gif");
					return;
				}
				// 测试输出文件后缀名
				System.out.println(afterName);
				// 获取文件的类型
				String type = fileItem.getContentType();
				user.setType(type);
				// 获取文件保存路径
				String savePath = this.getServletContext().getRealPath(
						"/upload");
				File save = new File(savePath);
				// 测试输出
				// System.out.println(savePath);
				// 判断文件夹是否存在
				if (!save.exists()) {
					// 创建不存在的目录
					save.mkdirs();
				}
				// 获取唯一的uuid，用于文件名
				String uuid = UUID.randomUUID().toString();
				// 生成保存在服务器上的文件名
				String saveName = uuid + "." + afterName;
				// 测试输出生成的文件名
				// System.out.println(saveName);
				// 保存生成的文件名
				user.setPhotoName(saveName);
				// 保存文件
				File file = new File(savePath + "\\" + saveName);
				// 测试输出
				// System.out.println(file.getAbsolutePath());
				try {
					fileItem.write(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// System.out.println("文件写出成功！");
			} else {
				if ("uname".equals(fileItem.getFieldName())) {
					user.setUname(fileItem.getString("utf-8"));
				} else if ("pwd".equals(fileItem.getFieldName())) {
					user.setPwd(fileItem.getString());
				} else {
					System.out.println("字段不存在");
				}
			}
		}
		System.out.println(user);
		//调用service层插入数据
		UserService service = new UserServiceImp();
		//添加用户信息
		int count = service.addUserInfoService(user);
		if(count>0){
			resp.getWriter().write("用户信息插入成功");
		}else{
			resp.getWriter().write("用户信息插入失败");
		}
	}
}
