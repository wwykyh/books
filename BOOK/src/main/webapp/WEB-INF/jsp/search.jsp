<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书检索</title>
<link rel="stylesheet" type="text/css"
	href="dvpt/libs/My97DatePicker/skin/WdatePicker.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body style="overflow: hidden;">
	<div class="panel">
		<div class="panel-header">
			<h2>图书检索</h2>
			<a href="javascript:;" class="arrow up"></a>
		</div>

		<div class="main-cont">
			<div class="tab tab-default">
				<div class="tab-contbox">
					<div class="search-box">
						<table class="search-table">
							<tr>
								<th></th>
								<td><select id="s_tsdl" name="s_tsdl" class="select">
										<option value="">------------------请选择图书大类------------------</option>
										<option value="纸质">纸质</option>
										<option value="电子书">电子书</option>
										<option value="CSDN">CSDN</option>
								</select></td>
								<td><select id="s_type" name="s_type" class="select">
										<option value="">------------------请选择图书类型------------------</option>
										<c:forEach items="${typeList }" var="t">
											<option value="${t.typeId }">${t.lxmc }</option>
										</c:forEach>
								</select></td>
								<td><input id="dim" type="text" placeholder="书名、作者"
									class="input-text" /></td>
								<td colspan="2"><a href="javascript:;" class="btn"><span
										id="btnLoad"><i class="icon icon-search"></i>查询</span></a></td>
							</tr>
						</table>
					</div>

					<div id="booksInfo"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var dim = null;
		var s_tsdl = null;
		var s_type = null;
		requirejs(
				[ 'jquery', 'ligerGrid', 'dg.datePicker', 'artdialog' ],

				function($, datepicker) {
					$("#d100").on('focus', function() {
						$(this).datePicker();
					});
					$("#d101").on('focus', function() {
						$(this).datePicker();
					});
					$(function() {
						select();
					});
					function select() {
						$("#booksInfo")
								.ligerGrid(
										{
											columns : [
													{
														display : '所属类型',
														//name : 'tsdl',
														width : 120,
														frozen : true,
														render : function(row) {
															if (row.id != undefined
																	&& row.id != null
																	&& row.id != "") {
																if (row.tsdl == '纸质') {
																	return row.tsdl;
																} else {
																	return row.tszl;
																}
															}
														}
													},{
														display : '图书编号',
														name : 'id',
														width : 150,
														frozen : true,
														
													},
													{
														display : 'ISBN',
														//name : 'isbn',
														width : 150,
														frozen : true,
														render : function(row) {
															if (row.id != undefined
																	&& row.id != null
																	&& row.id != "") {
															
																	
																
																	if (row.tsdl == '纸质') {
																		return  row.isbn;
																	}else{
																		return "无相关信息";
																	}
																
															}
														}
													},

													{
														display : '书名',
														width : 200,
														frozen : true,
														render : function(row) {
															if (row.id != undefined
																	&& row.id != null
																	&& row.id != "") {
																if (row.tsdl == '纸质') {
																	return row.sm;
																} else {
																	return row.eBookXm;
																}
															}
														}
													},
													{
														display : '出版社名称',
														//name : 'tPublish.pubName',
														width : 120,
														frozen : true,
														render : function(row) {
															if (row.id != undefined
																	&& row.id != null
																	&& row.id != "") {
																if (row.tPublish != undefined
																		&& row.tPublish != null
																		&& row.tPublish != "") {
																	return row.tPublish.pubName;
																} else {
																	return "无相关信息";
																}
															}
														}
													},
													{
														display : '作者',
														//name : 'zz',
														width : 120,
														frozen : true,
														render : function(row) {
															if (row.id != undefined
																	&& row.id != null
																	&& row.id != "") {
																if (row.zz != undefined
																		&& row.zz != null
																		&& row.zz != "") {
																	return row.zz;
																} else {
																	return "无相关信息";
																}
															}
														}
													},
													{
														display : '类型',
														name : 'lxmc',
														width : 120,
														frozen : true
													},
													{
														display : '图书状态',
													//	name : 'status',
														width : 120,
														frozen : true,
														render : function(row) {
															if (row.id != undefined
																	&& row.id != null
																	&& row.id != "") {
																var st = null;
																if (row.status == 0) {
																	var st = "出库";
																}
																if (row.status == 1) {
																	var st = "在库";
																}
																if (!(row.tsdl == '纸质')) {
																	var st = "在库";
																}

																return st;
															}
														}
													},
													
													{
														display : '操作',
														isAllowHide : false,
														render : function(row) {
															if (row.id != undefined
																	&& row.id != null
																	&& row.id != "") {
																var html = '<a href="javascript:void(0);" onclick="onBookInfo('
																		+ row.id
																		+ ')">查看详情</a>&nbsp;&nbsp;';
																if (row.tsdl == "纸质"
																		&& row.status == 1) {
																	html = html
																			+ '<a href="javascript:void(0);" onclick="borrow('
																			+ row.id
																			+ ')">借阅</a>';
																} else if (row.tszl == "电子书"
																		|| row.tszl == "CSDN") {
																	html = html
																			+ '<a href="javascript:void(0);" onclick="borrow('
																			+ row.id
																			+ ')">下载</a>';
																}

																return html;
															} else
																return "";
														}
													} ],
											url : '/search',
											method : 'get',
											dataType : 'server',
											dataAction : 'server',
											pageSize : 10,
											width : '100%',
											checkbox : false,
											rownumbers : false,
											parms : [ {
												name : "dim",
												value : dim
											}, {
												name : "s_type",
												value : s_type
											}, {
												name : "s_tsdl",
												value : s_tsdl
											} ],
											fixedCellHeight : false,
											iShowScroll : false,
											allowAdjustColWidth : true
										});
					}
					;

					$("#btnLoad").click(function() {
						dim = $("#dim").val();
						s_tsdl = $("#s_tsdl").val();
						s_type = $("#s_type").val();
						select();
					});

				});
		function borrow(id) {
			//alert("详情" + id) ;
			art.dialog.open('borrow?id=' + id, {
				title : '图书借阅',
				width : 1200,
				height : 675,
				//ok: true,
				// okVal: "打印",
				cancel : true,
				cancelVal : "关闭"
			});
		}

		function onBookEdit(id) {
			alert("编辑" + id);
		}
		function onBookInfo(id) {
			//alert("详情" + id) ;
			art.dialog.open('book_info?id=' + id, {
				title : '图书详情',
				width : 1200,
				height : 675,
				//ok: true,
				// okVal: "打印",
				cancel : true,
				cancelVal : "关闭"
			});
		}
	</script>
</body>
</html>