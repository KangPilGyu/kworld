<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script id="templateAttach" type="text/x-handlebars-template">
<li data-src='{{fullName}}' style="display:inline-block;">
	<img src="{{imgsrc}}" ><br>
	<div class='file-info'>
	<a id='file' href="{{getLink}}" >{{fileName}}</a><br>
	</div>
</li>
</script>

<script>
function fn_deleteSubmit(){
	var con = confirm("정말로 삭제하시겠습니까???");
	if(con){
		
		var arr =[];
		$("#file-attach li").each(function(index){
			arr.push($(this).attr("data-src"));
		});
		if(arr.length>0){
			$.post("/deleteAllFiles",{files:arr},function(){});
		}
		
		$("form").attr("action","/notice/delete/${notice.n_no}")
		.submit();
	}
}

 function getAllReply(n_no){
	 var str="";
	 $.getJSON("/noticereply/reply/all/"+n_no, function(data){
		 $(data).each(
				 function(){
					 str+='<div class="media mb-4"><img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt=""> <div class="media-body">'+
					 	'<h5 class="mt-0"> 작성자 :'+this.r_writer+' 등록 일시 : ' +new Date(this.n_regdate)+'</h5>글 : '+this.r_content+' </div></div>';
					if(this.m_id=='${notice.m_id}')
				          str+='<a href="javascript:deleteReply(' + this.r_no + ')"><button class="btn btn-danger">삭제</button></a>';
		
				 });
		$(".comment").html(str);		 
	 });
 }
 function deleteReply(r_no){
	 $.ajax({
			type:"delete",
			url:"/noticereply/reply/delete/"+r_no,
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Overrid":"DELETE"
			},
			dataType:"text",
			success:function(result){
				getAllReply('${notice.n_no}');
			}
	 });
 }
	 
$(document).ready(function(){
	var n_no =${notice.n_no};
	var template = Handlebars.compile($("#templateAttach").html());
	
	$.getJSON("/notice/getAttach/"+n_no,function(list){
		$(list).each(function(){
			var fileInfo = getFileInfo(this);
			var html = template(fileInfo);
			$("#file-attach").append(html);
		});
	});
	
	$("#file-attach").on("click",".file-info a", function(event){
		var fileLink=$(this).attr("href");
		
		if(checkImageType(fileLink)){
			event.preventDefault();
			
			var imgTag = $("#popup_img");
			imgTag.attr("src",fileLink);
			
			$(".popup").show('slow');
			imgTag.addClass("show");
		}
	});
	$("#popup_img").on("click",function(){
		$(".popup").hide('slow');
	});
	
	getAllReply('${notice.n_no}');
	
	
	$("#replyBtn").click(function(){
		$.ajax({
			type:"post",
			url:"/noticereply/reply",
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Overrid":"POST"
			},
			dataType:"text",
			data:JSON.stringify({
				n_no : "${notice.n_no}",
				r_writer : "${notice.n_writer}",
				m_id : $("input[name='m_id']").val(),
				r_content : $("input[name='r_content']").val()
			}),
			success:function(result){
				
				getAllReply('${notice.n_no}');
			}
		});
	});
});
</script>

<style>
.popup{position: absolute;}
.back{background-color: gray; opacity: 0.5; width: 100%; height: 300%;
overflow: hidden; z-index: 1101;}
.front{z-index: 1110; opacity: 1; border: 1px; margin: auto;}
.show{position: relative; max-width: 1200px; min-width: 800px; overflow: auto;}
</style>

<div class="row" style="height: 50px"></div>
		<div class="popup back" style="display:none"></div>
			<div id="popup_front" class="popup front" style="display:none">
				<img id="popup_img">
			</div>
<div class="container">

      <div class="row">
        <!-- Post Content Column -->
        <div class="col-lg-8">
          <!-- Title -->
          <h1 class="mt-4">${notice.n_title}
      <sec:authorize access="isAuthenticated()">
          	<c:set var="id"><sec:authentication property="principal.m_id" /></c:set>
          <c:if test="${notice.m_id eq id}">
          	<a href="/notice/update/${notice.n_no}"><button class="btn btn-primary">수정</button></a>
          	<button class="btn btn-danger" onclick="fn_deleteSubmit()">삭제</button>
        </c:if>
        </sec:authorize>
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
          <div id="fileDrop" class="jumbotron">
        		<ul id="file-attach" >
        		</ul>
        	</div>
        	
          <hr>
			
          <!-- Post Content -->
          <div class="postContent" >
          ${notice.n_content}
          </div>
          <hr>
          
          <!-- Comments Form -->
          
          <div class="card my-4">
            <h5 class="card-header">Leave a Comment:</h5>
            <div class="card-body">
              <form id="replyform">
                <div class="form-group">
                	<input type="hidden" name="n_no" value="${notice.n_no}">
                	<input type="hidden" name=" r_writer" value="${notice.n_writer}">
                 	<input type="hidden" name="m_id" value="${notice.m_id}"> 
                  <input type="text" name="r_content" class="form-control" style="height:100px"></input>
                </div>
                <button id="replyBtn" type="button" class="btn btn-primary">Submit</button>
              </form>
            </div>
          </div>
          <hr>
         
		<div class="comment">
		</div>
<!--           Comment with nested comments
          <div class="media mb-4">
            <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
            <div class="media-body">
              <h5 class="mt-0">Commenter Name</h5>
              Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.

              <div class="media mt-4">
                <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
                <div class="media-body">
                  <h5 class="mt-0">Commenter Name</h5>
                  Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                </div>
              </div>

              <div class="media mt-4">
                <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
                <div class="media-body">
                  <h5 class="mt-0">Commenter Name</h5>
                  Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                </div>
              </div> -->

            </div>
          </div>

        </div>

        <!-- Sidebar Widgets Column -->
       <!--  <div class="col-md-4"> -->

          <!-- Search Widget -->
<!--           <div class="card my-4">
            <h5 class="card-header">Search</h5>
            <div class="card-body">
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                  <button class="btn btn-secondary" type="button">Go!</button>
                </span>
              </div>
            </div>
          </div> -->

          <!-- Categories Widget -->
       <!--    <div class="card my-4">
            <h5 class="card-header">Categories</h5>
            <div class="card-body">
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="#">Web Design</a>
                    </li>
                    <li>
                      <a href="#">HTML</a>
                    </li>
                    <li>
                      <a href="#">Freebies</a>
                    </li>
                  </ul>
                </div>
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="#">JavaScript</a>
                    </li>
                    <li>
                      <a href="#">CSS</a>
                    </li>
                    <li>
                      <a href="#">Tutorials</a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div> -->

          <!-- Side Widget -->
<!--           <div class="card my-4">
            <h5 class="card-header">Side Widget</h5>
            <div class="card-body">
              You can put anything you want inside of these side widgets. They are easy to use, and feature the new Bootstrap 4 card containers!
            </div>
          </div> -->

  <!--       </div> -->

      </div>
      <!-- /.row -->

    </div>