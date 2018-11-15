<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script>
function fn_logout(){
	var con = confirm("로그아웃 하시겠습니까??");
	if(con)
		self.location.href="/login/logout";
}
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
		})
				
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
      <a class="navbar-brand" href="/">Kworld</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul id="navv"  class="nav navbar-nav">
        <li><a href="/">Home</a></li>
        <li><a href="/blog/main">Blog</a></li>
        <li><a href="/notice/main">Notice</a></li>
        <li><a href="${pageContext.request.contextPath}/board/">Board</a></li>
        <li><a href="/news/main">News</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <sec:authorize access="isAuthenticated()">
		<li><a><sec:authentication property="principal.m_name"/>님 환영합니다!</a></li>
      	<li><a id="logoutbtn" href="#" onclick="fn_logout()"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </sec:authorize>
      <sec:authorize access="isAnonymous()">
        <li><a id="loginbtn" href="/login/login" ><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
     </sec:authorize>
     
      </ul>
    </div>
  </div>
</nav>


