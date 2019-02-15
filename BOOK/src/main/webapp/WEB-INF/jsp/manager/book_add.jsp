<%--
  Created by IntelliJ IDEA.
  User: liulei
  Date: 2019/1/23
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书添加</title>
</head>
<body>
    <div class="panel">
        <div class="panel-header">
            <h2>图书添加</h2>
            <a href="javascript:;" class="arrow up"></a></div>
        <div class="panel-body panel-noborder">
            <div class="write-box">
                <table class="form-table" width="100%">
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>图书编号：</th>
                        <td width="33%"><input type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th width="17%"><span class="ft-need">*</span>图书名称：</th>
                        <td><input type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>图书类型：</th>
                        <td>
                            <select class="select">
                                <option>==请选择==</option>
                                <option selected="selected">IT类</option>
                                <option>社科类</option>
                                <option>杂志类</option>
                                <option>电子图书</option>
                                <option>CSDN</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>作者：</th>
                        <td><input type="text" class="input-text "></td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>出版社名称：</th>
                        <td><select class="select">
                            <option>==请选择==</option>
                            <option>清华大学出版社</option>
                            <option>电子工业出版社</option>
                            <option>北京大学出版社</option>
                        </select></td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>出版日期：</th>
                        <td><input type="text"  class="input-text"></td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>是否有损毁：</th>
                        <td><input type="text"  class="input-text"></td>
                    </tr>
                    <tr>
                        <th><span class="ft-need">*</span>图书提供者：</th>
                        <td><input type="text"  class="input-text"></td>
                    </tr>
                    <tr>
                        <th><input type="reset" class="btn" value="重置"></th>
                        <td><input type="submit" class="btn" value="提交"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

</body>
</html>
