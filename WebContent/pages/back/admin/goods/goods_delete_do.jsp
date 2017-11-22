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
	
	String msg="shanc失败";
	String url="/pages/back/admin/goods/goods_list.jsp";
		ParameterUtil parameterUtil = new ParameterUtil(request, "/tmp");
		Set<Long> setid = DateTypeConverter.converterStringSplit(request.getParameter("ids"));
		IGoodsServiceBack back=Factory.getServiceInstance("goods.service.back");
		if(back.delete(setid, 1)){//保存成功
			
			msg="删除成功";
		}
		
	request.setAttribute("msg", msg);
	request.setAttribute("url", url);
	pageContext.forward("/pages/plugins/forward.jsp");
	
	%>





</body>
</html>