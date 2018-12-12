<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<tiles:insertAttribute name="common"/>
<script src="${pageContext.request.contextPath}/resources/js/coverid.js"></script>

<title>Insert title here</title>
</head>
<body style="background:url('/resources/images/back.webp'); background-position:center; background-repeat: no-repeat;  background-size: cover;">

<!-- header  (nav bar)-->
<tiles:insertAttribute name="header"/>
<!-- content  (content)-->
<tiles:insertAttribute name="content"/>
<br>
<!-- footer  -->
<tiles:insertAttribute name="footer"/>


</body>
</html>