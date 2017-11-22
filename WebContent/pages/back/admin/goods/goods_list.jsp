<%@page import="java.text.NumberFormat"%>
<%@page import="cn.mldn.util.factory.Factory"%>
<%@page import="cn.mldn.service.back.IGoodsServiceBack"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="cn.mldn.vo.*"%>
<html>
<head> 
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";

	String list_url = basePath + "pages/back/admin/goods/goods_list.jsp";
	String edit_url = basePath + "pages/back/admin/goods/goods_edit.jsp";
%>
<%!public static final String GOODS_ADD_URL = "pages/back/admin/goods/goods_add.jsp";
	public static final String GOODS_DELETE_URL = "pages/back/admin/goods/goods_delete_do.jsp";
	public static final String GOODS_EDIT_URL = "pages/back/admin/goods/goods_edit.jsp";
	%>
<base href="<%=basePath%>" />
<title>商品管理</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<script type="text/javascript" src="js/common/mldn_util.js"></script>
<script type="text/javascript" src="js/back/admin/goods/goods_list.js"></script>
<script type="text/javascript" src="jquery/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">jsDeleteUrl = "<%=basePath + GOODS_DELETE_URL%>"
</script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<body>


	<%
		long currentPage = 1L; // 当前所在页
		int lineSize = 2; // 每页显示的数据行
		String columnData = "商品名称:name"; // 定义搜索列的名称

		String column = request.getParameter("col"); // 查询列
		String keyWord = request.getParameter("kw"); // 关键字
		try { // 这行代码出错只有不传递或传递非法参数的时候出现
			currentPage = Long.parseLong(request.getParameter("cp"));
		} catch (Exception e) {
		}
		try { // 如果出错就使用默认值
			lineSize = Integer.parseInt(request.getParameter("ls"));
		} catch (Exception e) {
		}
	%>

	<%
		IGoodsServiceBack back = Factory.getServiceInstance("goods.service.back");
		Map<String, Object> map = back.list(column, keyWord, currentPage, lineSize);
		
		//Map<Long,String> mapitem=(Map<Long,String>)map.get("allitem");
		
		System.out.println(map);
		List<Goods> all = (List<Goods>) map.get("allsplits");
		System.out.println(all);
		Iterator<Goods> iter = all.iterator();
	%>
<%
		// 考虑到以后的结合开发，本处使用request属性范围传递
		request.setAttribute("columnData", columnData); // 属性名称
		request.setAttribute("handleUrl", list_url);
		request.setAttribute("keyWord", keyWord);
		request.setAttribute("column", column);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lineSize", lineSize);
		request.setAttribute("allRecorders", (Long) map.get("counts"));
	%>


	<div class="container">
		<div class="row">
			<div class="h1">
				<strong><span class="glyphicon glyphicon-th-list"></span>&nbsp;商品信息列表</strong>
			</div>
		</div>
		<div class="row">
			<jsp:include page="/pages/plugins/split_page_search_plugin.jsp" />
			<table class="table table-striped table-bordered table-hover">
				<tr>
					<td style="width: 5%"><input type="checkbox" id="selectall" /></td>
					<td>商品名称</td>
					<td>商品单价</td>
					<td>商品分类</td>
					<td>操作</td>
				</tr>
				<%
				while(iter.hasNext()){
					Goods goods=iter.next();
				%>
				
				<tr>
					<td><input type="checkbox" id="gid" value="<%=goods.getGid() %>" /></td>
					<td><%=goods.getName() %></td>
					<td><%=NumberFormat.getInstance().format(goods.getPrice()) %></td>
					<td>图书/音响</td>
					<td><a href="<%=edit_url%>?gid=<%=goods.getGid() %>"
						class="btn btn-warning btn-xs"> <span
							class="glyphicon glyphicon-pencil"></span>&nbsp;编辑
					</a></td>
				</tr>
				
				<%} %>
			</table>
			<button id="deleteBtn" class="btn btn-danger btn-lg">
				<span class="glyphicon glyphicon-trash"></span>&nbsp;删除选中部信息
			</button>
		</div>
		<jsp:include page="/pages/plugins/split_page_bar_plugin.jsp" />
	</div>
</body>
</html>