package com.coderli.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

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
		// ������������ʽ
		req.setCharacterEncoding("utf-8");
		// ������Ӧ�����ʽ
		resp.setCharacterEncoding("utf-8");
		// ��ȡ������Ϣ
		String uid = req.getParameter("uid");
		// ����service����
		UserService service = new UserServiceImp();
		User user = service.getUserInfoByUidService(uid);
		//����ҳ��չʾ���ļ���ʽ
		resp.setContentType(user.getType());
		// ����������Ϣ
		// �������
		// System.out.println(user);
		//��ȡ�ļ���·��
		String filePath= this.getServletContext().getRealPath("/upload")+"/"+user.getPhotoName();
		File file = new File(filePath);
		resp.setContentLength((int) file.length());
		//��ȡ�ļ���ԭ��
		String realName =user.getRealName();
		//��ȡ�������Ϣ
		String userAgent = req.getHeader("User-Agent").toLowerCase();
		//�ж����������Բ�ͬ��������ò�ͬ�Ľ������
		if(userAgent.indexOf("msie")>=0){ 
			//ie�ı�������ʽ
			realName = URLEncoder.encode(realName, "utf-8");
		}else{
			//��������������Ľ����ʽ
			byte [] bytes = realName.getBytes("utf-8");
			realName = new String(bytes,"iso-8859-1");
		}	

		resp.setHeader("Content-Disposition", "attachment;filename="+realName);
		// ��Ӧ������
		InputStream is = new FileInputStream(file);
		OutputStream os = resp.getOutputStream();
		IOUtils.copy(is, os);
		//�ر�����Դ
		is.close();
		os.close();
	}
}
