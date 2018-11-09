<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(document).ready(function(){

		$.ajax({
			method:"POST",
			datatype: "html",
			url:"/login/modal?modalSwitch=${param.modalSwitch}",
			success: function(data, textStatus, jqXHR ){
				$("#loginModal").html(data);
				if("${param.modalSwitch}"=="1")
					$("#loginbtn").click(); 
			}
		});
		
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
		
		$("#loginModal").on('hidden.bs.modal',function(){
			$("#needLogin").remove();
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
        <li><a id="loginbtn" href="#"  data-toggle="modal" data-target="#loginModal"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </div>
</nav>


<!-- Login ----------  Modal -->
  <div class="modal fade" id="loginModal" role="dialog" class="modal-dialog modal-sm">
   
        
   
      </div>
        
        

