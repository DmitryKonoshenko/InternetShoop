<%@ page language="java" contentType="text/html; UTF-8"
         pageEncoding="UTF-8"%>


<h1 class="my-4">Электроник</h1>
<div class="list-group">
    <c:forEach items="${categories1}" var="category">
        <a href="#" class="list-group-item">${category.name}</a>
    </c:forEach>
</div>