使用commons-fileupload&commons-io实现简单的文件上传和下载功能

上传功能（核心代码）
```java
// 创建FileItemFactory对象
FileItemFactory factory = new DiskFileItemFactory();
// 创建ServletFileUpload对象
ServletFileUpload upload = new ServletFileUpload(factory);
//使用ServletFileUpload解析request，返回的是数据
List<FileItem> list=upload.parseRequest(req);
//根据api方法判断上传表单项和普通表单项
for (FileItem fileItem : list) {
	// 获取上传表单项
	if (!fileItem.isFormField()) {
		// 获取文件的名字
		String realName = fileItem.getName();
		user.setRealName(realName);
		// 获取文件后缀名
		String afterName = realName.substring(realName.lastIndexOf(".") + 1);
		//过滤文件类型
		if(!("jpg".equals(afterName.toLowerCase())||"png".equals(afterName.toLowerCase())||"gif".equals(afterName.toLowerCase()))){
			resp.getWriter().write("文件类型不支持，暂时只支持png,jpg,gif");
			return;
		}
		// 测试输出文件后缀名
		//System.out.println(afterName);
		// 获取文件的类型
		String type = fileItem.getContentType();
		user.setType(type);
		// 获取文件保存路径
		String savePath = this.getServletContext().getRealPath("/upload");
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
```
下载功能实现（核心代码）
```java
	//User是是一个实体类，和数据库中一个表对应，存有图片的一些信息
	//设置页面展示的文件格式
	resp.setContentType(user.getType());
	// 处理请求信息
	// 测试输出
	// System.out.println(user);
	//获取文件的路径，upload位于webRoot或者WebContent
	String filePath= this.getServletContext().getRealPath("/upload")+"/"+user.getPhotoName();
	//获取文件的大小
	File file = new File(filePath);
	//
	resp.setContentLength((int) file.length());
	//获取文件的原名
	String realName =user.getRealName();
	//获取浏览器信息
	String userAgent = req.getHeader("User-Agent").toLowerCase();
	//判断浏览器，针对不同浏览器设置不同的解决方案
	if(userAgent.indexOf("msie")>=0){ 
		//ie的编码解决方式
		realName = URLEncoder.encode(realName, "utf-8");
	}else{
		//其他浏览器的中文解决方式
		byte [] bytes = realName.getBytes("utf-8");
		realName = new String(bytes,"iso-8859-1");
	}	
	resp.setHeader("Content-Disposition", "attachment;filename="+realName);
	// 响应处理结果
	InputStream is = new FileInputStream(file);
	OutputStream os = resp.getOutputStream();
	IOUtils.copy(is, os);
	//关闭流资源
	is.close();
	os.close();
```

