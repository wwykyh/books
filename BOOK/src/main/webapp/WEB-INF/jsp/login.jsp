<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/common/login.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<title></title>
</head>
<body>
<div id="login">
  <div class="login-header login-wrap">
    <div id="login-logo"></div>
  </div>
  <div class="login-body clearfix">
    <div class="login-wrap">
      <div class="system-img"></div>
      <div class="login-con">
      <form action="../dologin" method="post">
        <table>
          <tr>
            <th>用户名：</th>
          </tr>
          <tr>
            <td><input type="text" class="input-text"  name="username"/></td>
          </tr>
          <tr>
            <th>密码：</th>
          </tr>
          <tr>
            <td><input type="password" class="input-text" name="pwd"/></td>
          </tr>
        </table>
        <div class="login-btnbar"><input type="submit" value="登录" class="btn btn-zsdl"> <a href="index.html" class="btn btn-zsdl">证书登录</a></div>
     </form>
      </div>
    </div>
  </div>
</div>
<div class="login-footer">
  <p>Copyright©2015  厦门市巨龙信息科技有限公司 版权所有</p>
</div>
</body>
</html>