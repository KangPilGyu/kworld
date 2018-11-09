<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
 
 <script>
 function openWindows(url){
	 var win = window.open(url,'login',"width=500px, height=500px, top=200px, left=200px");
 }
 </script>   
    
<div class="modal-dialog"> 
      <!-- Modal content-->
 <div class="modal-content">
        <div class="modal-header">
          <button id="closeBtn" type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">로그인</h4>
          <c:if test="${param.modalSwitch eq '1'}">
          <span id="needLogin" style='color:red'>로그인이 필요합니다....</span>
          </c:if>
        </div>
        
        <div class="modal-body">
          <form class="form-signin">  
        <h2 class="form-signin-heading"></h2>
        <label for="inputEmail" class="sr-only">이메일 주소</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
        <label for="inputPassword" class="sr-only">비밀번호</label>
        <input  type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
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
           	<button id="btnJoinFacebook" class="btn btn-lg btn-primary btn-block"
	      				 onclick="openWindows('${facebook_url}')" type="button"> 
                                <i style="float: left"class="fab fa-facebook" aria-hidden="true"></i>페이스북 로그인
           </button>
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