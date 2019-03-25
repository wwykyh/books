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
    <title>赔偿详情</title>
    <link rel="stylesheet" type="text/css" href="../css/common/iconfont/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="../css/common/layout.css" />
    <link rel="stylesheet" type="text/css" href="../dvpt/css/libs.css" />
    <link rel="stylesheet" type="text/css" href="../css/demo/style.css" />
    <link rel="stylesheet" type="text/css" href="../css/theme/blue.css" id="style" />
    <script type="text/javascript" src="../dvpt/config.js"></script>
    <!-- 改造的脚本 -->
    <script type="text/javascript" src="../js/extend.js"></script>
    <%--弹窗引用Jquery--%>
    <script type="text/javascript" src="../dvpt/jquery.min.js"></script>
    <!-- 共有的控件 -->
    <%--<script data-main="/main" src="http://127.0.0.1:9090/dvpt/require.min.2.1.11.js"></script>--%>
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
                        <td width="33%">${pc.book.isbn}</td>
                        <th width="17%"><span class="ft-need"></span>图书名称：</th>
                        <td width="33%"> ${pc.book.sm}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>出版社名称：</th>
                        <td width="33%">${pc.book.cbsmc}</td>
                        <th width="17%"><span class="ft-need"></span>作者：</th>
                        <td width="33%">${pc.book.zz}</td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need"></span>图书类型：</th>
                        <td width="33%">${pc.book.tsdl}</td>
                        <th width="17%"><span class="ft-need"></span> </th>
                        <td width="33%"> </td>
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
                    <td width="33%">${pc.user.xm}</td>
                    <th width="17%"><span class="ft-need"></span>部门：</th>
                    <td width="33%"> ${pc.user.bm}</td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need"></span>联系方式：</th>
                    <td width="33%">${pc.user.lxfs}</td>
                    <th width="17%"><span class="ft-need"></span>地址：</th>
                    <td width="33%"> ${pc.user.dz}</td>
                </tr>
            </table>
        </div>
    </div>

    <div class="panel-header">
        <h2>赔偿信息</h2>
        <a href="javascript:" class="arrow up"></a>
    </div>
    <div class="panel-body panel-noborder">
        <div class="write-box">
            <table class="form-table" width="100%">
                <tr>
                    <th width="17%"><span class="ft-need"></span>损毁程度：</th>
                    <td width="33%"><div id="sh" style="font-size: 16px">中度</div></td>
                    <th width="17%"><span class="ft-need"></span>生成日期：</th>
                    <td width="33%"> ${pc.pcdate}</td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need"></span>是否已赔偿：</th>
                    <td width="33%"><div id="isph" style="font-size: 16px">未赔偿</div></td>
                    <th width="17%"><span class="ft-need"></span>是否已被黑名单计数：</th>
                    <td width="33%"> <div id="iscount" style="font-size: 16px">未计数</div></td>
                </tr>
                <tr>
                    <th width="17%"><span class="ft-need"></span>备注：</th>
                    <td width="33%">${pc.pc}</td>
                    <th width="17%"><span class="ft-need"></span></th>
                    <td width="33%"> </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function (){
        var iscount = ${pc.iscount};
        var ispc  = ${pc.ispc};
        var shcd= ${pc.sh};

        if (shcd==1){
            $("#sh").text("轻度");
        }else if (shcd==3){
            $("#sh").text("重度");
        }

        if (ispc==1){
            $("#isph").text("已赔偿");
        }else {
            $("#isph").text("未赔偿");
        }

        if (iscount==1){
            $("#iscount").text("已计数");
        }else {
            $("#iscount").text("未计数");
        }
    });
</script>
</body>
</html>
