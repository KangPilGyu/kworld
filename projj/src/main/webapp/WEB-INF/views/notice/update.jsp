<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script id="template" type="text/x-handlebars-template">
<li style="display:inline-block;" data-src='{{fullName}}' >
	<img src="{{imgsrc}}" ><br>
	<a href="{{getLink}}" >{{fileName}}</a><br>
	<a id='file'><i class="fas fa-trash-alt"></i><a/>
</li>
</script>

<script>
function fn_updateSubmit(){
		
		var that = $("form");
		
		var str = "";
		
		$("#file-attach li").each(function(index){
			str +="<input type='hidden' name='files["+index+"]' value='"+$(this).attr("data-src")+"'>";
		});
	
		that.append(str);
		
		that.get(0).submit();
}
$(document).ready(function(){
	$("#file-attach").on("click","li #file",function(event){
		var that = $(this);
		
/* 		$.ajax({
			url:"/deleteNoticeFile",
			data : {fileName:$(this).parent().attr("data-src")},
			async : false,
			success:function(){
				alert("성공디비 삭제");
				
			}
		}); */
		$.ajax({
			url:"/deleteFile",
			type:"post",
			data:{fileName:$(this).parent().attr("data-src")},
			dataType:"text",
			async : false,
			success:function(result){
				if(result=='deleted'){
					that.parent().remove();	
				}
			}
		});
	});
	//초기화
	var n_no =${notice.n_no};
	var template = Handlebars.compile($("#template").html());
	
	$.getJSON("/notice/getAttach/"+n_no,function(list){
		$(list).each(function(){
			var fileInfo = getFileInfo(this);
			var html = template(fileInfo);
			$("#file-attach").append(html);
		});
	});
	
	
var template = Handlebars.compile($("#template").html());
			
	$("#fileDrop").on("dragenter dragover",function(event){event.preventDefault();});
	$("#fileDrop").on("drop",function(event){
		event.preventDefault();
		var files = event.originalEvent.dataTransfer.files;
		var file =files[0];
		
		var formData = new FormData();
		formData.append("file",file);
		
		$.ajax({
			url: '/uploadAjax' ,
			data: formData ,
			dataType:'text' ,
			processData : false ,
			contentType : false ,
			type : 'POST' ,
			success : function(data){
				var fileInfo = getFileInfo(data);
				var html = template(fileInfo);
				$("#file-attach").append(html);
			}
		});
		
		});
		
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
          <div id="fileDrop" class="jumbotron">
        		<ul id="file-attach" >
        		</ul>
        	</div>
        	
          <hr>

          <!-- Post Content -->
          <div class="postContent" >
          <textarea name="n_content" id="editor1" rows="10" cols="80">
               ${notice.n_content}
            </textarea>
            <script>
                // Replace the <textarea id="editor1"> with a CKEditor
                // instance, using default configuration.
                CKEDITOR.replace( 'editor1' );
               	
            </script>
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