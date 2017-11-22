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

String edit_do_url=basePath+"pages/back/admin/goods/goods_edit_do.jsp";
%>
<base href="<%=basePath%>" />
<title>商品管理</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<script type="text/javascript" src="jquery/jquery.min.js"></script>
<script type="text/javascript" src="js/back/admin/goods/goods_edit.js"></script>
<script type="text/javascript" src="js/common/mldn_util.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<%!public static final String GOODS_URL_URL = "pages/back/admin/goods/goods_list.jsp";%>
<body>

	<%
		IGoodsServiceBack service = Factory.getServiceInstance("goods.service.back");
		Map<String, Object> map = service.preEdit(Long.parseLong(request.getParameter("gid")));
		
		List<Item> item = (List<Item>) map.get("item");
		List<Tag> tag = (List<Tag>) map.get("tag");
		
		Set<Long> tid=(Set<Long>)map.get("tid");
		
		Iterator<Item> iters= item.iterator();
		Iterator<Tag> itag = tag.iterator();
		Goods goods=(Goods)map.get("goods");
		
	%>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<form action="<%=edit_do_url %>" method="post" class="form-horizontal" id="goodsForm" enctype="multipart/form-data">
					<fieldset>
						<legend>
							<label><span class="glyphicon glyphicon-pencil"></span>&nbsp;编辑商品</label>
						</legend>
						<div class="form-group" id="nameDiv">
							<label class="col-md-2 control-label" for="name">商品名称：</label>
							<div class="col-md-5">
								<input type="text" id="name" name="name" class="form-control"
									placeholder="请填写商品名称" value="<%=goods.getName() %>">
							</div>
							<span class="col-md-5" id="nameSpan">*</span>
						</div>
						<div class="form-group" id="priceDiv">
							<label class="col-md-2 control-label" for="price">商品价格：</label>
							<div class="col-md-5">
								<input type="text" id="price" name="price" class="form-control"
									placeholder="请填写商品单价" value="<%=goods.getPrice()%>">
							</div>
							<span class="col-md-5" id="priceSpan">*</span>
						</div>
						<div class="form-group" id="itemDiv">
							<label class="col-md-2 control-label" for="item">商品分类：</label>
							<div class="col-md-5">
								<select id="item" class="form-control" name="item">
									<%
										while (iters.hasNext()) {
											Item items = iters.next();
									%>
									<option  value="<%=items.getIid() %>"  <%=tid.contains(items.getIid()) ? "selected":"" %>><%=items.getTitle()%></option>

									<%
										}
									%>

								</select>
							</div>
							<span class="col-md-5" id="itemSpan">*</span>
						</div>
						<div class="form-group" id="tagDiv">
							<label class="col-md-2 control-label" for="tag">商品标签：</label>
							<div class="col-md-5">

								<%
						while(itag.hasNext()){
							Tag tags=itag.next();
						%>
								<div class="col-md-3">
									<div class="checkbox">
										<label><input type="checkbox" id="tag" name="tag" 
											value="<%=tags.getTid() %>" <%=tid.contains(tags.getTid()) ? "checked":"" %> ><%=tags.getTitle() %></label>
									</div>   
								</div>
								<%} %>
								


							</div>
							<span class="col-md-5" id="tagSpan">*</span>
						</div>
						<div class="form-group" id="photoDiv">
							<label class="col-md-2 control-label" for="photo">商品图片：</label>
							<div class="col-md-5">
								<img src="upload/goods/<%=goods.getPhoto() %>"><br> <input
									type="file" id=" " name="photo" class="form-control"
									placeholder="请选择商品宣传图">
							</div>
							<span class="col-md-5" id="photoSpan">如果不修改可以不选择</span>
						</div>
						<div class="form-group">
							<div class="col-md-3 col-md-offset-3">
								<input type="hidden" id="gid" name="gid" value="<%=goods.getGid() %>"> 
								<input type="hidden" id="oldphoto" name="oldphoto" value="<%=goods.getPhoto() %>"> 
								<input
									type="submit" value="提交" class="btn btn-primary"> <input
									type="reset" value="重置" class="btn btn-warning">
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>