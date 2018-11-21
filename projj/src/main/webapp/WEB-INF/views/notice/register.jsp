<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
$(document).ready(function(){
	var template = Handlebars.compile($("#template").html());
	
	$("#file-attach").on("click","li #file",function(event){
		var that = $(this);
		
		$.ajax({
			url:"/deleteFile",
			type:"post",
			data:{fileName:$(this).parent().attr("data-src")},
			dataType:"text",
			success:function(result){
				alert("성공");
				if(result=='deleted'){
					that.parent().remove();
				}
			}
		})
	});
	
	
	$("form").submit(function(event){
		event.preventDefault();
		
		var that = $(this);
		
		var str = "";
		
		$("#file-attach li").each(function(index){
			str +="<input type='hidden' name='files["+index+"]' value='"+$(this).attr("data-src")+"'>";
		});
		
		that.append(str);
		
		that.get(0).submit();
	});
		
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
});
</script>

<script id="template" type="text/x-handlebars-template">

<li style="display:inline-block;" data-src='{{fullName}}' >
	<img src="{{imgsrc}}" ><br>
	<a href="{{getLink}}" >{{fileName}}</a><br>
	<a id='file'><i class="fas fa-trash-alt"></i><a/>
</li>

</script>

<div class="row" style="height: 50px"></div>
<div class="container">
 <form method="post">
      <div class="row">
             <!-- Post Content Column -->
        <div class="col-lg-8">
          <!-- Title -->
          <h1 class="mt-4">
          <input type="text" name="n_title" placeholder="제목을 입력해주세요" required>
          </h1>

          <!-- Author -->
          <p class="lead">
            by
            <a href="#"><sec:authentication property="principal.m_name"/>
            <input type="hidden" name="n_writer" value="<sec:authentication property="principal.m_name"/>" >
			<input type="hidden" name="m_id" value="<sec:authentication property="principal.m_id"/>" >
			</a>
          </p>

          <hr>

          <!-- Date/Time -->
          <p>Posted on </p>

          <hr>

          <!-- Preview Image -->
        	<div id="fileDrop" class="jumbotron">
        	<h3>파일을 여기에 끌어다주세요~~</h3>
        		<ul id="file-attach" >
        		</ul>
        	</div>
        	
          <img id="imgBox" class="img-fluid rounded" style="height:300px; width: 900px" src="http://placehold.it/900x300" alt="">

          <hr>

          <!-- Post Content -->
         <div class="postContent" >
           <textarea name="n_content" id="editor1" rows="10" cols="80">
            </textarea>
            <script>
                // Replace the <textarea id="editor1"> with a CKEditor
                // instance, using default configuration.
                CKEDITOR.replace( 'editor1' );
            </script>
         <!-- <input type="text" name="n_content" placeholder="내용을 입력해주세요" style="height: 300px; width:900px" /> -->
		</div>
      </div>
      <!-- /.row -->
    
      </div>
        <hr>
      <div class="row">
       <div class="col-md-1">
      	<button type="submit" class="btn btn-primary btn-block btn-sm">등록</button>
      	<button class="btn btn-danger btn-block btn-sm">취소</button>
      </div>
      </div>
		</form>
    
  
    </div>