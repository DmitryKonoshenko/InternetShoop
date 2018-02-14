<%--
  Created by IntelliJ IDEA.
  User: Divanxan
  Date: 14.02.2018
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="contextRoot" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Internet Shop</title>
</head>
<body>
    ${contextRoot}says -${greeting}
</body>
</html>
