<%--
  Created by IntelliJ IDEA.
  User: liulei
  Date: 2019/1/23
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书管理</title>
</head>
<body>
<div class="panel">
    <div class="panel-header">
        <h2>图书管理</h2>
        <a href="javascript:;" class="arrow up"></a>
    </div>

    <div class="main-cont">
        <div class="tab tab-default">
            <div class="tab-contbox">
                <div class="search-box">
                    <table class="search-table">
                        <tr>
                            <th></th>
                            <td>
                                <input type="text" placeholder="书名、作者" class="input-text" />
                            </td>
                            <td colspan="2"><a href="javascript:;" class="btn"><span><i class="icon icon-search"></i>查询</span></a></td>
                        </tr>
                    </table>
                </div>
                <div class="table-outer">
                    <table class="simple-table">
                        <thead>
                        <tr>
                            <th width="30">图书编号</th>
                            <th width="30">书名</th>
                            <th>图书类型</th>
                            <th>作者</th>
                            <th>出版社名称</th>
                            <th>出版日期</th>
                            <th>入库时间</th>
                            <th>状态</th>
                            <th>库存</th>
                            <th>损毁</th>
                            <th>详情</th>
                            <th>库存管理</th>
                            <th>出库</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>123</td>
                            <td>spring入门教程</td>
                            <td>IT类</td>
                            <td>杨开振</td>
                            <td>电子工业出版社</td>
                            <td>2016.9</td>
                            <td>2019.1</td>
                            <td>在库</td>
                            <td>3</td>
                            <td>无损毁</td>
                            <td><a href="javascript:;" title="详情"  class="icon icon-info">图书详情</a></td>
                            <td><a href="javascript:;" title="详情"  class="icon icon-info">库存管理</a></td>
                            <td><a href="javascript:;" title="详情"  class="icon icon-info">出库</a></td>
                        </tr>
                        <tr>
                            <td>123</td>
                            <td>spring入门教程</td>
                            <td>IT类</td>
                            <td>杨开振</td>
                            <td>电子工业出版社</td>
                            <td>2016.9</td>
                            <td>2019.1</td>
                            <td>在库</td>
                            <td>3</td>
                            <td>无损毁</td>
                            <td><a href="javascript:;" title="详情" class="icon icon-info">图书详情</a></td>
                            <td><a href="javascript:;" title="详情" class="icon icon-info">库存管理</a></td>
                            <td><a href="javascript:;" title="详情" class="icon icon-info">出库</a></td>
                        </tr>
                        <tr>
                            <td>123</td>
                            <td>spring入门教程</td>
                            <td>IT类</td>
                            <td>杨开振</td>
                            <td>电子工业出版社</td>
                            <td>2016.9</td>
                            <td>2019.1</td>
                            <td>在库</td>
                            <td>3</td>
                            <td>无损毁</td>
                            <td><a href="javascript:;" title="详情" class="icon icon-info">图书详情</a></td>
                            <td><a href="javascript:;" title="详情" class="icon icon-info">库存管理</a></td>
                            <td><a href="javascript:;" title="详情" class="icon icon-info">出库</a></td>
                        </tr>
                        <tr>
                            <td>123</td>
                            <td>spring入门教程</td>
                            <td>IT类</td>
                            <td>杨开振</td>
                            <td>电子工业出版社</td>
                            <td>2016.9</td>
                            <td>2019.1</td>
                            <td>在库</td>
                            <td>3</td>
                            <td>无损毁</td>
                            <td><a href="javascript:;" title="图书详情" class="icon icon-info">图书详情</a></td>
                            <td><a href="javascript:;" title="库存管理" class="icon icon-info">库存管理</a></td>
                            <td><a href="javascript:;" title="出库" class="icon icon-info">出库</a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="page">
                    <div class="page-total left">每页
                        <input type="text" class="input-text" value="10" /> 条 共21条</div>
                    <ul class="page-link right">
                        <li><a href="javascript:;">首页</a></li>
                        <li><a href="javascript:;">上一页</a></li>
                        <li>1/3</li>
                        <li><a href="javascript:;">下一页</a></li>
                        <li><a href="javascript:;">末页</a></li>
                        <li>转到
                            <input type="text" class="input-text" /> 页
                        </li>
                        <li><a href="javascript:;" class="btn btn-secondary a-go">跳转</a></li>
                    </ul>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    requirejs(['jquery', 'dg.datePicker', 'artdialog'], function($) {
        $(function() {
            $("#btn_info").click(function() {
                art.dialog.open('简要列表-详情.html', {
                    title: '详情',
                    width: 900,
                    height: 475,
                    ok: true,
                    okVal: "打印",
                    cancel: true,
                    cancelVal: "关闭"
                });
            });

        });
    });
</script>

</body>
</html>
