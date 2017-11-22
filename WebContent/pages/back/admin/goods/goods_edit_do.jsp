<%@page import="cn.mldn.util.factory.Factory"%>
<%@page import="cn.mldn.service.back.IGoodsServiceBack"%>
<%@page import="cn.mldn.util.DateTypeConverter"%>
<%@page import="cn.mldn.vo.Goods"%>
<%@page import="cn.mldn.util.ParameterUtil"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String msg = "修改失败";
		String url = "/pages/back/admin/goods/goods_list.jsp";
		ParameterUtil parameterUtil = new ParameterUtil(request, "/tmp");
		Goods goods = new Goods();
		goods.setGid(Long.parseLong(parameterUtil.getParameter("gid")));
		goods.setName(parameterUtil.getParameter("name"));
		goods.setPrice(Double.parseDouble(parameterUtil.getParameter("price")));
		goods.setIid(Long.parseLong(parameterUtil.getParameter("item")));
		Set<Long> set = DateTypeConverter.ConverterStringToLong(parameterUtil.getParameterValues("tag"));
		 
		String oldphoto = parameterUtil.getParameter("oldphoto");
		goods.setPhoto(oldphoto);
		if (parameterUtil.isUpload("photo")) {
			if ("nophoto.jpg".equals("oldphoto")) {
				goods.setPhoto(parameterUtil.createUploadFileName("photo").get(0));
			}
		} 

		IGoodsServiceBack back = Factory.getServiceInstance("goods.service.back");
		if (back.edit(goods, set)) {//保存成功
			if (parameterUtil.isUpload("photo")) {
				String savePath = this.getServletContext().getRealPath("/upload/goods/") + goods.getPhoto();
				parameterUtil.saveUploadFile("photo", savePath);
			}
			msg = "修改成功";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		pageContext.forward("/pages/plugins/forward.jsp");
		
	%>


</body>
</html>