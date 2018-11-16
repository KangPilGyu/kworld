<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script>
function fn_updateSubmit(){
	$("form").submit();
}
$(document).ready(function(){
	$('input[type="text"]').keydown(function() {
	    if (event.keyCode === 13) {
	        event.preventDefault();
	    }
	});
});

</script>
<div class="row" style="height: 50px"></div>
<div class="container">
 <form method="post">
      <div class="row">
        <!-- Post Content Column -->
        <div class="col-lg-8">
          <!-- Title -->
          <h1 class="mt-4">
			<input type="text" name="n_title" value="${notice.n_title}" required>
          </h1>
          <!-- Author -->
          <p class="lead">
            by
            <a href="#">${notice.n_writer}</a>
          </p>
          <hr>
          <!-- Date/Time -->
          <p>Posted on <fmt:formatDate value="${notice.n_regdate}" pattern="YYYY-MM-dd E HH:mm:ss"/>
          <span style="float: right;">Click : ${notice.n_cnt}        Recommend: ${notice.n_fav}</span>
          </p>
           <c:if test="${notice.n_updatedate != null}">
          <p>Last Update <fmt:formatDate value="${notice.n_updatedate}" pattern="YYYY-MM-dd E HH:mm:ss"/>
          </p>
          </c:if>
          <hr>

          <!-- Preview Image -->
          <img class="img-fluid rounded" src="http://placehold.it/900x300" alt="이미지 없음">

          <hr>

          <!-- Post Content -->
          <div class="postContent" >
           <input type="text" name="n_content" value="${notice.n_content}" style="height: 300px; width:900px" />
          </div>
          <hr>
          

      </div>
      <!-- /.row -->
    	</div>
    	<div class="row">
       <div class="col-md-1">
      	<button class="btn btn-primary btn-block btn-sm" onclick="fn_updateSubmit()">수정완료</button>
      	<button class="btn btn-danger btn-block btn-sm">취소</button>
      </div>
      </div>
 </form>
    </div>