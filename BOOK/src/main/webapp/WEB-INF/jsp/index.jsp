<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书管理系统</title>
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
    </script>
</head>
<body>
<div id="wrap" class="lr-layout">
    <div id="header" class="header clearfix">
        <div class="header-inner">
            <div class="logo"></div>
            <div class="header-right">
                <ul class="header-right-list">
                    <li class="nowtime">
                        <span id="nowTime">
                          <label class="data"></label>
                          <label class="week"></label>
                          <label class="time"></label>
		                </span>
                    </li>
                    <li>
                        <i class="header-icon i-user"></i>欢迎您！${sessionScope.userName}</a>
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
                            <li><a _href="sea" data-name="图书检索" href="javascript:;">图书检索</a></li>
                            </li>
                        </ul>
                    </dd>
                </dl>
                <dl>
                    <dt>
                        <a href="javascript:;">
                            <i class="icon">&#xe6c2;</i><span>我的</span>
                            <i class="icon arrow">&#xe658;</i>
                            <i class="icon arrow hide">&#xe659;</i>
                        </a>
                    </dt>
                    <dd>
                        <ul>
                            <li><a _href="" data-name="我的个人" data-js="prettify">我的个人</a></li>
                            <li><a _href="${pageContext.request.contextPath}/modifyinformation/modifyindex" data-name="修改资料" data-js="prettify" href="javascript:;">修改资料</a>
                            </li>
                            <li><a _href="${pageContext.request.contextPath}/modifypassword/tomodifypassword" data-name="修改密码" data-js="prettify" href="javascript:;">修改密码</a>
                            </li>
                            <li><a _href="" data-name="借阅历史" data-js="prettify" href="javascript:;">借阅历史</a>
                            </li>
                            <li><a _href="${pageContext.request.contextPath}/overtimepayment/toovertimepayment" data-name="超时归还" href="javascript:;">超时归还</a></li>

                        </ul>
                    </dd>
                </dl>

                <dl>
                    <dt>
                        <a href="javascript:;">
                            <i class="icon">&#xe657;</i><span>电子图书库</span>
                            <i class="icon arrow">&#xe658;</i>
                            <i class="icon arrow hide">&#xe659;</i>
                        </a>
                    </dt>
                    <dd>
                        <ul>
                            <li><a _href="${pageContext.request.contextPath}/eBookFile/toUpload" data-name="电子书入库"
                                   data-js="prettify" href="javascript:;">图书上传</a></li>
                            <li><a _href="${pageContext.request.contextPath}/eBookFile/ebookManger" data-name="电子书下载"
                                   data-js="prettify" href="javascript:;">图书浏览和下载</a></li>
                        </ul>
                    </dd>
                </dl>

            </div>
        </div>
        <div id="main">
            <div id="main-tab">
                <div class="main-tab">
                    <ul>
                        <li class="active"><a _href="Home" href="javascript:;">主页</a></li>
                    </ul>
                </div>
            </div>
            <div id="main-content"></div>
        </div>
    </div>
</div>
</body>
</html>