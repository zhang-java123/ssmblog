<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/blog.css">
	<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
	<title>${title}</title>
	<style type="text/css">
		body{
			padding-top:10px;
			padding-bottom:40px;
			background-color: #F8F8FF;
			font-family: microsoft yahei;
		}
	</style>
</head>

<body>
<div class="container" style="width: 1330px">
	<div class="row">
		<div class="col-md-3">
			<div class="blog"><strong>博客</strong></div>
		</div>
		<div class="col-md-9">
			<iframe style="float:right" width="420" scrolling="no" height="60" frameborder="0"
					allowtransparency="true"
					src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=5"></iframe>
		</div>
	</div>

	<div class="row" style="padding-top: 10px">
		<div class="col-md-12">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active"><a class="navbar-brand" href="${pageContext.request.contextPath}/index.html">博客首页</a></li>
							<li><a class="navbar-brand" href="${pageContext.request.contextPath}/blogger/aboutMe.html">关于博主</a></li>
							<%--<li><a class="navbar-brand" href="#">我的相册</a></li>--%>
							<li><a class="navbar-brand" href="#">资源小站</a></li>
							<li><a class="navbar-brand" href="#">程序人生</a></li>
							<li><a class="navbar-brand" href="https://github.com/zhang-java123" target="_blank">github</a></li>
						</ul>
						<form class="navbar-form navbar-right" role="search">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="请输入要查询的关键字">
							</div>
							<button type="submit" class="btn btn-default">搜索</button>
						</form>
					</div><!-- /.navbar-collapse -->
				</div><!-- /.container-fluid -->
			</nav>
		</div>
	</div>

	<div class="row">
		<div class="col-md-3">
			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/user_icon.png"/>
					博主信息
				</div>
				<div class="user_image">
					<img src="${pageContext.request.contextPath}/static/userImages/${blogger.imageName}"/>
				</div>
				<div class="userSign">${blogger.sign }</div>
			</div>

			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/byType_icon.png"/>
					文章分类
				</div>
				<div class="datas">
					<ul>
						<c:forEach items="${blogTypeCountList }" var="blogTypeCount">
							<li><span><a href="${pageContext.request.contextPath}/index.html?type_id=${blogTypeCount.id}">${blogTypeCount.typeName}(${blogTypeCount.blogCount})</a></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/byDate_icon.png"/>
					日志日期
				</div>
				<div class="datas">
					<ul>
						<c:forEach items="${blogCountList }" var="blogCount">
							<li><span><a href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blogCount.releaseDateStr}">${blogCount.releaseDateStr}(${blogCount.blogCount})</a></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/link_icon.png"/>
					友情链接
				</div>
				<div class="datas">
					<ul>
						<c:forEach items="${linkList }" var="link">
							<li><span><a href="${link.linkurl}" target="_blank">${link.linkName}</a></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>


		</div>

		<div class="col-md-9">
			<jsp:include page="${mainPage}"></jsp:include>
		</div>

	</div>

	<div class="row">
		<div class="col-md-12" >
			<div class="footer" align="center" style="padding-top: 120px" >
				<font>Copyright ? 2020-2021 个人博客系统 版权所有</font>

			</div>
		</div>
	</div>
</div>
</body>
</html>
