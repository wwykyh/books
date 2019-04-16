<%--
  Created by IntelliJ IDEA.
  User: liulei
  Date: 2019/2/12
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书详情</title>
    <link rel="stylesheet" type="text/css" href="css/common/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="css/common/layout.css" />
    <link rel="stylesheet" type="text/css" href="dvpt/css/libs.css" />
    <link rel="stylesheet" type="text/css" href="css/demo/style.css" />
    <link rel="stylesheet" type="text/css" href="css/theme/blue.css" id="style" />
    <script type="text/javascript" src="dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="js/extend.js"></script>
    <!-- 共有的控件 -->
    <script data-main="main" src="dvpt/require.min.2.1.11.js"></script>
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h2>图书信息</h2>
        <a href="javascript:" class="arrow up"></a>
    </div>
    <div class="panel-body panel-noborder">
        <div class="write-box">
                <table class="form-table" width="100%">
                    <tr>
                        <th width="17%"><span class="ft-need"></span>图书ISBN：</th>
                        <td width="33%">${history.book.isbn}</td>
                        <th width="17%"><span class="ft-need"></span>图书名称：</th>
                        <td width="33%"> ${history.book.sm}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>出版社名称：</th>
                        <td width="33%">${history.book.cbsmc}</td>
                        <th width="17%"><span class="ft-need"></span>作者：</th>
                        <td width="33%">${history.book.zz}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>图书类别：</th>
                        <td width="33%">${history.book.tsdl}</td>
                        <th width="17%"><span class="ft-need"></span>图书类型：</th>
                        <td width="33%">${history.lxmc}</td>
                    </tr>
                </table>
        </div>
    </div>

    <div class="panel-header">
        <h2>用户信息</h2>
        <a href="javascript:" class="arrow up"></a>
    </div>
    <div class="panel-body panel-noborder">
        <div class="write-box">
            <table class="form-table" width="100%">
                <tr>
                    <th width="17%"><span class="ft-need"></span>用户名称：</th>
                    <td width="33%">${history.user.xm}</td>
                    <th width="17%"><span class="ft-need"></span>部门：</th>
                    <td width="33%"> ${history.user.bm}</td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need"></span>联系方式：</th>
                   <c:choose>
                       <c:when test="${history.user.lxfs == null || history.user.lxfs=='' || history.user.lxfs=='undefine'}">
                           <td width="33%">暂无联系方式</td>
                       </c:when>
                       <c:otherwise>
                           <td width="33%">${history.user.lxfs}</td>
                       </c:otherwise>
                   </c:choose>
                    <th width="17%"><span class="ft-need"></span>地址：</th>
                    <td width="33%"> ${history.user.dz}</td>
                </tr>
            </table>
        </div>
    </div>

    <div class="panel-header">
        <h2>借阅信息</h2>
        <a href="javascript:" class="arrow up"></a>
    </div>
    <div class="panel-body panel-noborder">
        <div class="write-box">
            <table class="form-table" width="100%">
                <tr>
                    <th width="17%"><span class="ft-need"></span>借阅日期：</th>
                    <td width="33%">${history.jyrq}</td>
                    <th width="17%"><span class="ft-need"></span>归还日期：</th>
                    <c:choose>
                        <c:when test="${history.jyzt == 0|| history.jyzt==1 }">
                            <td width="33%">暂未归还</td>
                        </c:when>
                        <c:otherwise>
                            <td width="33%"> ${history.ghrq}</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need"></span>确认状态：</th>
                    <td width="33%">${status}</td>
                    <th width="17%"><span class="ft-need"></span>借阅状态：</th>
                    <td width="33%"> ${jyzts}</td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need"></span>计划归还时间：</th>
                    <td width="33%">${history.jhghrq}</td>
                    <th width="17%"><span class="ft-need"></span>续借日期：</th>
                    <c:choose>
                        <c:when test="${history.xjrq == null || history.xjrq=='' || history.xjrq=='undefine'}">
                            <td width="33%">暂无续借信息</td>
                        </c:when>
                        <c:otherwise>
                            <td width="33%"> ${history.xjrq}</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">

</script>
</body>
</html>
