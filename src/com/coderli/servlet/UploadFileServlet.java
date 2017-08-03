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
		// ������������ʽ
		req.setCharacterEncoding("utf-8");
		// ������Ӧ�����ʽ
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// ����FileItemFactory����
		FileItemFactory factory = new DiskFileItemFactory();
		// ����ServletFileUpload����
		ServletFileUpload upload = new ServletFileUpload(factory);
		// ����һ��������������User�����ֵ
		User user = null;
		// ʹ��servletFileUpload����request����
		List<FileItem> list = null;
		try {
			// �����ļ���С100���ֽ�
			upload.setFileSizeMax(1024 * 100);
			list = upload.parseRequest(req);
		} catch (FileUploadException e) {
			resp.getWriter().write("�ļ��������ֻ���ϴ�100kb");
		}
		user = new User();
		for (FileItem fileItem : list) {
			// ��ȡ�ϴ�����
			if (!fileItem.isFormField()) {
				// ��ȡ�ļ�������
				String realName = fileItem.getName();
				user.setRealName(realName);
				// ��ȡ�ļ���׺��
				String afterName = realName
						.substring(realName.lastIndexOf(".") + 1);
				if(!("jpg".equals(afterName.toLowerCase())||"png".equals(afterName.toLowerCase())||"gif".equals(afterName.toLowerCase()))){
					resp.getWriter().write("�ļ����Ͳ�֧�֣���ʱֻ֧��png,jpg,gif");
					return;
				}
				// ��������ļ���׺��
				System.out.println(afterName);
				// ��ȡ�ļ�������
				String type = fileItem.getContentType();
				user.setType(type);
				// ��ȡ�ļ�����·��
				String savePath = this.getServletContext().getRealPath(
						"/upload");
				File save = new File(savePath);
				// �������
				// System.out.println(savePath);
				// �ж��ļ����Ƿ����
				if (!save.exists()) {
					// ���������ڵ�Ŀ¼
					save.mkdirs();
				}
				// ��ȡΨһ��uuid�������ļ���
				String uuid = UUID.randomUUID().toString();
				// ���ɱ����ڷ������ϵ��ļ���
				String saveName = uuid + "." + afterName;
				// ����������ɵ��ļ���
				// System.out.println(saveName);
				// �������ɵ��ļ���
				user.setPhotoName(saveName);
				// �����ļ�
				File file = new File(savePath + "\\" + saveName);
				// �������
				// System.out.println(file.getAbsolutePath());
				try {
					fileItem.write(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// System.out.println("�ļ�д���ɹ���");
			} else {
				if ("uname".equals(fileItem.getFieldName())) {
					user.setUname(fileItem.getString("utf-8"));
				} else if ("pwd".equals(fileItem.getFieldName())) {
					user.setPwd(fileItem.getString());
				} else {
					System.out.println("�ֶβ�����");
				}
			}
		}
		System.out.println(user);
		//����service���������
		UserService service = new UserServiceImp();
		//����û���Ϣ
		int count = service.addUserInfoService(user);
		if(count>0){
			resp.getWriter().write("�û���Ϣ����ɹ�");
		}else{
			resp.getWriter().write("�û���Ϣ����ʧ��");
		}
	}
}
