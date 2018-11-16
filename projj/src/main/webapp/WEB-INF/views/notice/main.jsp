<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
$(document).ready(function(){
	if('${param.msg}'=='upsuccess')
		alert("업데이트 완료 했습니다.");
	else if('${param.msg}'=='delsuccess')
		alert("삭제 완료 했습니다.");
	
	$(".dropdown-menu li").click(function(){
		$("#dropdownMenu1").html($(this).text()+"<span class='caret'></span>");
	});
	
});
</script>
  <div class="container">
 <div class="row" style="height: 50px"></div>
<div class="row">
          <h2 class="sub-header">공지 사항</h2>
         
         <div class="search" style="float:right; display: inline-block;">
          
          <div class="dropdown">
  				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
    			검색조건
    			<span class="caret"></span>
 				</button>
 					 <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
    						<li role="presentation"><a role="menuitem" tabindex="-1" href="#">제목</a></li>
   							 <li role="presentation"><a role="menuitem" tabindex="-1" href="#">작성자</a></li>
    						<li role="presentation"><a role="menuitem" tabindex="-1" href="#">내용</a></li>
    						<li role="presentation"><a role="menuitem" tabindex="-1" href="#">제목+내용</a></li>
    						<li role="presentation"><a role="menuitem" tabindex="-1" href="#">제목+내용+작성자</a></li>
 				 </ul>
 				 <input id="condition" type="text" value=""/>
 				 <button type="submit" class="btn btn-primary btn-sm">검색</button>
			</div>
			
			
		
</div>
<div class="row">
		<br>
		<br>
          <div class="table-responsive" style="float:left; width: 100%">
            <table class="table table-striped">
              <thead >
                <tr >
                  <th width="10%" style="text-align: center">글번호</th>
                  <th width="50%" style="text-align: center">제목</th>
                  <th width="10%" style="text-align: center">글쓴이</th>
                   <th width="10%" style="text-align: center">등록일</th>
                  <th width="10%" style="text-align: center">조회수</th>
                  <th width="10%" style="text-align: center">좋아욤</th>
                </tr>
              </thead>
              <tbody style="text-align: center">
              <c:forEach items="${list}" var="notice">
                <tr>
                  <td>${notice.n_no}</td>
                  <td><a href="/notice/detail/${notice.n_no}">${notice.n_title}</a></td>
                  <td>${notice.n_writer}</td>                 
                  <td><fmt:formatDate value="${notice.n_regdate}" pattern="YYYY-MM-dd"/></td>
                  <td>${notice.n_cnt}</td>
                  <td>${notice.n_fav}</td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
        
        <div class="NavpageNum" style="text-align: center;">
          <ul class="pagination">
          		<!-- 앞으로 가기  -->
    			<li class="page-item <c:if test="${!pageMaker.prev}">disabled</c:if>">
    			<a class="page-link" href="/notice/main/${pageMaker.makeSearch(pageMaker.startPage-1)}">&laquo;</a></li>
    			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    			<li class="page-item <c:if test="${idx eq pageMaker.cri.page}">disabled</c:if>">
    			<a class="page-link" href="/notice/main/${pageMaker.makeSearch(idx)}">
    			${idx}</a>
    			</li>
 
   				 </c:forEach>
   				 <!-- 뒤로가기 -->
    			<li class="page-item <c:if test="${!pageMaker.next}">disabled</c:if>">
    			<a class="page-link" href="/notice/main/${pageMaker.makeSearch(pageMaker.endPage+1)}">&raquo;</a></li>
  		</ul>
  		</div>
        
        <button class="btn btn-lg btn-primary btn-block" onclick="javascript:self.location.href='/notice/register'">등록</button>
        
      </div>
      </div>