<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<tiles:insertAttribute name="common"/>
<title>Insert title here</title>
</head>
<style>
.btn-danger {
    background-repeat: repeat-y;
    border-color: #b92c28;
    }
.btn-primary {
    background-repeat: repeat-y;
    border-color: #245580;
    }
    .btn btn-warning{
    background-repeat: repeat-y;
    
    }
    .panel {
    margin-bottom: 20px;
    background-color: #fff;
    border: 1px solid transparent;
    border-radius: 4px;
    }
</style>
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