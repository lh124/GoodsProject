<%@page import="cn.mldn.util.factory.Factory"%>
<%@page import="cn.mldn.service.back.IGoodsServiceBack"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.mldn.vo.*"%>
<html>
<head>  
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=basePath%>" />
<title>商品管理</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<script type="text/javascript" src="jquery/jquery.min.js"></script>
<script type="text/javascript" src="js/back/admin/goods/goods_add.js"></script>
<script type="text/javascript" src="js/common/mldn_util.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<%!public static final String GOODS_URL_URL = "pages/back/admin/goods/goods_list.jsp";
public static final String GOODS_add_do = "pages/back/admin/goods/goods_add_do.jsp";
%>
<body>

	<%
		IGoodsServiceBack service = Factory.getServiceInstance("goods.service.back");
		Map<String, Object> map = service.preAdd();
		List<Item> item = (List<Item>) map.get("item");
		List<Tag> tag = (List<Tag>) map.get("tag");
		Iterator<Item> iter = item.iterator();
		Iterator<Tag> itag = tag.iterator();
	%>

	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<form action="<%=GOODS_add_do %>" method="post" class="form-horizontal" id="goodsForm" enctype="multipart/form-data">
					<fieldset>
						<legend>
							<label>增加商品</label>
						</legend>
						<div class="form-group" id="nameDiv">
							<label class="col-md-2 control-label" for="name">商品名称：</label>
							<div class="col-md-5">
								<input type="text" id="name" name="name" class="form-control"
									placeholder="请填写商品名称">
							</div>
							<span class="col-md-5" id="nameSpan">*</span>
						</div>
						<div class="form-group" id="priceDiv">
							<label class="col-md-2 control-label" for="price">商品价格：</label>
							<div class="col-md-5">
								<input type="text" id="price" name="price" class="form-control"
									placeholder="请填写商品单价">
							</div>
							<span class="col-md-5" id="priceSpan">*</span>
						</div>
						<div class="form-group" id="itemDiv">
							<label class="col-md-2 control-label" for="item">商品分类：</label>
							<div class="col-md-5">

								<select id="item"  name="item" class="form-control">
									<option value="">========= 请选择商品所属分类 =========</option>
									<%
										while (iter.hasNext()) {
											Item items = iter.next();
									%>
									<option value="1"><%=items.getTitle()%></option>
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
										<label><input type="checkbox" id="tag" name="tag" value="<%=tags.getTid() %>"><%=tags.getTitle() %></label>
									</div>
								</div>

								<%} %>

							</div>
							<span class="col-md-5" id="tagSpan">*</span>
						</div>




						<div class="form-group" id="photoDiv">
							<label class="col-md-2 control-label" for="photo" >商品图片：</label>
							<div class="col-md-5">
								<input type="file" id="photo" name="photo" class="form-control"
									placeholder="请选择商品宣传图">
							</div>
							<span class="col-md-5" id="photoSpan">*</span>
						</div>
						<div class="form-group">
							<div class="col-md-3 col-md-offset-3">
								<input type="submit" value="提交" class="btn btn-primary">
								<input type="reset" value="重置" class="btn btn-warning">
								<a href="<%=GOODS_URL_URL%>" class="btn btn-link">商品列表</a>
							</div>
						</div> 
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>