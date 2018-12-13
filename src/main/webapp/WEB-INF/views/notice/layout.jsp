<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<tiles:insertAttribute name="common"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.12/handlebars.js"></script>
<script src="/resources/js/file.js"></script>
<script src="//cdn.ckeditor.com/4.11.1/standard/ckeditor.js"></script>
<title>Kworld</title>
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