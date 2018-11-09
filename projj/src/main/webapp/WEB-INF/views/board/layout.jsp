<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<tiles:insertAttribute name="common"/>
<link href="${pageContext.request.contextPath}/resources/css/board/dashboard.css" rel="stylesheet"/>
<title>Insert title here</title>
</head>
<body>
<!-- header  (nav bar)-->
<tiles:insertAttribute name="header"/>
<!-- content  (content)-->
<tiles:insertAttribute name="content"/>
<br>
<!-- footer  -->
<tiles:insertAttribute name="footer"/>

</body>
</html>