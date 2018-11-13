<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script>
$(document).ready(function(){		
		//nav 상태 유지
		var path="${requestScope['javax.servlet.forward.request_uri']}";
		$("#navv li").each(function(){
			if(path=="/"){
				$( this ).addClass( "active" );
				return false;
			}
			if(path.indexOf($( this ).children("a").attr("href"))!=-1 && $( this ).children("a").attr("href")!="/"){	
				$( this ).addClass( "active" );
			}
		});
				
});
</script>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul id="navv"  class="nav navbar-nav">
        <li><a href="/">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Projects</a></li>
        <li><a href="${pageContext.request.contextPath}/board/">Board</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <sec:authorize access="isAuthenticated()">
      <li><a id="logoutbtn" href="" ><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </sec:authorize>
      <sec:authorize access="isAuthenticated()">
        <li><a id="loginbtn" href="/login/login" ><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
     </sec:authorize>
      </ul>
    </div>
  </div>
</nav>


