<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-11-4
  Time: 下午4:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
    <!-- 
    <script>
   		window.location.href = "http://127.0.0.1:8080/InformationSystemClient/index.html"; 
    </script>
     -->
</head>
<body>
    <%
	   // 重定向到新地址
	   String site = new String("http://127.0.0.1:8080/InformationSystemClient/index.html");
	   response.setStatus(response.SC_MOVED_TEMPORARILY);
	   response.setHeader("Location", site); 
	%>
</body>
</html>
