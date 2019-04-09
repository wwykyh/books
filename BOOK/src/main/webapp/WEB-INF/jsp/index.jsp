<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <meta name=”viewport” content=”width=device-width, initial-scale=1″ />
    <title>图书管理系统</title>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

       <link rel="stylesheet" type="text/css" href="css/common/iconfont/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="css/common/layout.css"/>
    <link rel="stylesheet" type="text/css" href="dvpt/css/libs.css"/>
    <link rel="stylesheet" type="text/css" href="css/demo/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/theme/blue.css" id="style"/>
    <script type="text/javascript" src="dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="dvpt/require.min.2.1.11.js"></script>
    <script type="text/javascript">
        requirejs(['main'], function (main) {
            requirejs(['jquery', 'jquery.extend', 'basic-global'], function (jquery, extend, basic) {
                $(function() {
                    // 默认加载页面
                    $.openWindow({ url:'Home?isadmin=${user.isadmin}'});
                    // 换肤操作
                    $.switchSkin('css');
                });
                // 计算高度
                window.setInterval(function () {
                    frameHeight();
                }, 200);

                $(function () {
                    // 加载导航事件
                    var sidebar = $("#sidebar").children(".sidebar-list");
                    sidebar.sidebarEffect();
                    $.menuLink(sidebar);

                    // 菜单扩展
                    $("#main-tab").menuLinkExtend({
                        contain: ".main-tab ul", // 盒容器
                        childItem: "li", // 列表容器
                        dataItem: "a", // 数据容器
                        closeItem: ".tab-close", // 关闭标签
                        menuClick: "#sidebar .sidebar-list dd a", // 菜单点击
                        rightMenu: ['closeThis', 'closeOthers', 'closeAll'], // 部署不同的功能及排序
                    });
                });
            });
        });

        /*var dim = null;
        var s_tsdl = null;
        var s_type = null;*/

       /* $("#btnLoad").click(function() {
            dim = $("#dim").val();
            s_type = $("#s_type").val();
            select();
        });
*/

        function page(num) {
            //alert(num);
            $("#page").attr("_href","page?pageNumber="+num).trigger("click");
            $("#page").attr("_href","sea")

        }

    </script>
</head>
<body>
<div id="wrap" class="lr-layout">
    <div id="header" class="header clearfix">
        <div class="header-inner">
           <%-- <div class="logo"></div>--%>
           <%-- <div style="float: left;height: 100%;width: auto;padding-left: 20%"><form action="">

                <select id="s_type" name="s_type" class="select">
                    <option value="">------------------请选择图书类型------------------</option>
                    <c:forEach items="${typeList }" var="t">
                        <option value="${t.typeId }">${t.lxmc }</option>
                    </c:forEach>
                </select>
                <input style="height: 40px" id="dim" type="text" placeholder="书名、作者"
                       class="input-text" />
                <a href="javascript:;" class="btn"><span style="height: 40px"
                            id="btnLoad"><i class="icon icon-search"></i>查询</span></a>


            </form></div>--%>
            <div style="float: right;height: auto;width: auto;padding-right: 10px">
                <ul class="header-right-list">
                    <li class="nowtime">
                        <span id="nowTime">
                          <label class="data"></label>
                          <label class="week"></label>
                          <label class="time"></label>
		                </span>
                    </li>
                    <li>
                        <a><i class="header-icon i-user"></i>欢迎您！${user.xm}</a>
                    </li>
                    <li class="skin-change"><a href="javascript:;" title="皮肤" class="skin-change-acitve"><span><i
                            class="header-icon i-skin"></i>换肤<i class="arrow"></i></span></a>
                        <div class="skin-changelist hide">
                            <a href="javascript:;" class="skin-dark" title="dark"><i></i></a>
                            <a href="javascript:;" class="skin-microStereo" title="microStereo"><i></i></a>
                            <a href="javascript:;" class="skin-blue" title="blue"><i></i></a>
                            <a href="javascript:;" class="skin-green" title="green"><i></i></a>
                            <a href="javascript:;" class="skin-red" title="red"><i></i></a>
                        </div>
                    </li>
                    <li><span><a href="logout" title="退出"><i class="header-icon i-logout"></i>退出</a></span></li>
                </ul>
            </div>
        </div>
    </div>
    <div id="container">
        <div class="toggle-collapse"></div>
        <div id="sidebar">
            <div class="sidebar-list">
                <dl>
                    <dt>
                        <a href="javascript:;">
                            <i class="icon">&#xe717;</i><span>检索</span>
                            <i class="icon arrow">&#xe659;</i>
                            <i class="icon arrow hide">&#xe658;</i>
                        </a>
                    </dt>
                    <dd>
                        <ul>
                            <li><a id="page" _href="sea" class="borrow" data-name="图书检索" href="javascript:;">图书检索</a></li>
                            </li>
                        </ul>
                    </dd>
                </dl>
                <dl>
                    <dt>
                        <a href="javascript:;">
                            <i class="icon">&#xe6c2;</i><span>个人中心</span>
                            <i class="icon arrow">&#xe658;</i>
                            <i class="icon arrow hide">&#xe659;</i>
                        </a>
                    </dt>
                    <dd>
                        <ul>
                            <li><a id="pager" _href="${pageContext.request.contextPath}/personal/toPersonalIndex?userId=${user.userId}" data-name="个人中心" data-js="prettify" href="javascript:;">个人中心</a></li>
                            <li><a _href="${pageContext.request.contextPath}/modifyInformation/modifyIndex" data-name="修改资料" data-js="prettify" href="javascript:;">修改资料</a>
                            </li>
                            <li><a _href="${pageContext.request.contextPath}/modifyPassword/toModifyPassword" data-name="修改密码" data-js="prettify" href="javascript:;">修改密码</a>
                            </li>
                            </li>
                            <li><a _href="${pageContext.request.contextPath}/news/toNews?userId=${user.userId}" data-name="消息通知" data-js="prettify" href="javascript:;">消息通知</a>
                            <li><a _href="${pageContext.request.contextPath}/userBorrowHistory" data-name="借阅历史" data-js="prettify" href="javascript:;">借阅历史</a>
                            </li>
                            <li><a _href="${pageContext.request.contextPath}/overtimePayment/toOvertimePayment?userId=${user.userId}" data-name="超时归还" href="javascript:;">超时归还</a></li>

                        </ul>
                    </dd>
                </dl>

                <dl>
                    <dt>
                        <a href="javascript:;">
                            <i class="icon">&#xe657;</i><span>电子书库</span>
                            <i class="icon arrow">&#xe658;</i>
                            <i class="icon arrow hide">&#xe659;</i>
                        </a>
                    </dt>
                    <dd>
                        <ul>
                            <li><a _href="${pageContext.request.contextPath}/eBookFile/page/upload" data-name="电子书上传"
                                   data-js="prettify" href="javascript:;">电子书上传</a></li>
                            <li><a _href="${pageContext.request.contextPath}/eBookFile/page/manage" data-name="电子书下载"
                                   data-js="prettify" href="javascript:;">电子书下载</a></li>
                        </ul>
                    </dd>
                </dl>

            </div>
        </div>
        <div id="main">
            <div id="main-tab">
                <div class="main-tab">
                    <ul>
                        <li class="active"><a _href="Home?isadmin=${user.isadmin}" href="javascript:;" id="myHome">主页</a></li>
                    </ul>
                </div>
            </div>
            <div id="main-content"></div>
        </div>
    </div>
</div>
</body>
</html>