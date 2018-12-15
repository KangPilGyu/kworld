<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
 <script>
 // 클라이언트 서버 스크립트 언어......
 // 
 function openWindows(url){
	 var win = window.open(url,'login',"width=500px, height=500px, top=200px, left=200px");
 }
 
 function setCookie(name,value,expiredays){
	 var d = new Date();
	 d.setTime(d.getTime()+(expiredays*24*60*60*1000));
	 var expires = "expires="+d.toGMTString();
	 document.cookie = name+"="+value+";"+expires+"; Path=/";
 }
 
 function getCookie(cname) {
	    var name = cname + "=";
	    var decodedCookie = decodeURIComponent(document.cookie);
	    var ca = decodedCookie.split(';');
	    for(var i = 0; i <ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0) == ' ') {
	            c = c.substring(1);
	        }
	        if (c.indexOf(name) == 0) {
	            return c.substring(name.length, c.length);
	        }
	    }
	    return "";
	}
 function snsSubmit(){
	// alert($("#snsemail").val() +" " + $("#snspwd").val() );
	 $("#snsform").attr("action","/j_spring_security_check");
	 $("#snsform").attr("method","post");
	 $("#snsform").submit();
 }
 
 $(document).ready(function(){
	 if(getCookie("id")!=""){
		 $("#inputEmail").val(getCookie("id"));
		 $("#inputPassword").val(getCookie("pwd"));
		 $("#remember").attr("checked","checked");
	 }
	 
	 
	 $("#formlogin").submit(function(){
				
		var emailExist;
		var v_email=$("#inputEmail").val();
			// 동기 방식으로 이메일 검사 코드
			$.ajax({
				method:"POST",
				datatype: "text",
				data:{"m_email" : v_email},
				url:"/login/hasmail",
				async:false,
				success: function(data, textStatus, jqXHR ){
					emailExist=data;	
				}		
			});
			
			if(emailExist=="none"){
				alert("이메일이 존재하지 않습니다~~");
				event.preventDefault();	
				return;
			}
			
			var msg;
			$.ajax({
				method:"POST",
				datatype: "text",
				data:{"m_email" : v_email,"m_pwd":$("#inputPassword").val()},
				url:"/login/loginCk",
				async:false,
				success: function(data, textStatus, jqXHR ){
					msg=data;	
				}		
			});
			
			if(msg=="success"){
				alert("로그인 성공하셨습니다.");
				 $("form").attr("action","/j_spring_security_check");
				 $("form").attr("method","post");
				 
				 if($("#remember").is(":checked")){
					 setCookie("id",$("#inputEmail").val(),30); 
					 setCookie("pwd",$("#inputPassword").val(),30);
				 }
			}
			else if(msg=="needAuth"){
				alert("이메일 인증이 필요합니다.");
				event.preventDefault();	
				return;		
			}
			else if(msg=="pwdMismatch"){
				alert("비밀번호가 일치하지 않습니다.");
				event.preventDefault();	
				return;		
			}
				 
	 });
	 
 }); 
 </script>   
 
 <form id="snsform" action="/j_spring_security_check" method="post">
 	<input type="hidden" name="m_email" id="snsemail" value="">
 	<input type="hidden" name="m_pwd" id="snspwd" value="">
 </form>
 
<div class="row" style="height:50px"></div>

<div class="modal-dialog modal-sm" > 
      <!-- Modal content-->
 <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">로그인</h4>
          <c:if test="${param.modalSwitch eq '1'}">
          <span id="needLogin" style='color:red'>로그인이 필요합니다....</span>
          </c:if>
        </div>
        
        <div class="modal-body">
          <form class="form-signin" id="formlogin">  
        <h2 class="form-signin-heading"></h2>
        <label for="inputEmail" class="sr-only">이메일 주소</label>
        <input name="m_email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
        <label for="inputPassword" class="sr-only">비밀번호</label>
        <input  name="m_pwd" type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
        <div class="checkbox">
          <label>
            <input id="remember" type="checkbox" value="remember-me"> Remember me
          </label> 
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
       	 <br>
       	 <div style="text-align: center"><label>아직 회원이 아니십니까? <a href="${pageContext.request.contextPath}/login/join">회원 가입</a></label></div>
     	  <br>
	      <button id="btnJoinGoogle" class="btn btn-lg btn-danger btn-block" 
	      						onclick="openWindows('${google_url}')" type="button">
                                <i style="float: left"class="fab fa-google" aria-hidden="true"></i>구글 로그인
           </button>
           <%-- 	<button id="btnJoinFacebook" class="btn btn-lg btn-primary btn-block"
	      				 onclick="openWindows('${facebook_url}')" type="button"> 
                                <i style="float: left"class="fab fa-facebook" aria-hidden="true"></i>페이스북 로그인
           </button> --%>
             	<button id="btnJoinNaver" class="btn btn-lg btn-success btn-block"
	      				 onclick="openWindows('${naver_url}')" type="button"> 
                                <i style="float: left"class="fab fa-neos" aria-hidden="true"></i>네이버 로그인
           </button>
	      </form>
        </div>
        
        
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>  
    </div>
