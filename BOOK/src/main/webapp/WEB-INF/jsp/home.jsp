<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/home.css" />
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="left" style="width:49.5%;height:300px ">1</div>
	<div  class="right" style="width:49.5%;height:300px ">2</div>
	<div class="left" style="width:49.5%;height:300px ">
		<p style="text-align: center; font-size: 20px;">图书排行榜</p>
		<table class="simple-table">
			<thead>
				<tr>
					<th>排名</th>
					<th>图书编号</th>
					<th>图书名</th>
					<th>借阅次数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bookList }" var="bl" varStatus="status">
					<tr>
						<td>${requestScope.offset+status.index+1}</td>
						<td>${bl.isbn }</td>
						<td>${bl.sm }</td>
						<td>${bl.cs }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="right" style="width:49.5%;height:300px">
		<p style="text-align: center; font-size: 20px;">用户排行榜</p>
		<table class="simple-table">
			<thead>
				<tr>
					<th>排名</th>
					<th>用户名</th>
					<th>借阅次数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList }" var="ul" varStatus="status">
					<tr>
						<td>${requestScope.offset+status.index+1}</td>
						<td>${ul.user.xm}</td>
						<td>${ul.cs }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>