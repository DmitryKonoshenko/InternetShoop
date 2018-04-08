<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="list-group">
    <%--@elvariable id="categories" type="java.util.List"--%>
    <c:forEach items="${categories}" var="category">
        <a title="${category.description}" href="${contextRoot}/show/category/${category.id}/products" class="list-group-item" id="a_${category.name}">${category.name}</a>
    </c:forEach>
</div>
