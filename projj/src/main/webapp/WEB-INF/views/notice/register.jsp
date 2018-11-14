<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
          <img class="img-fluid rounded" src="http://placehold.it/900x300" alt="">

          <hr>

          <!-- Post Content -->
         <div class="postContent" >
         <input type="text" name="n_content" placeholder="내용을 입력해주세요" style="height: 300px; width:900px" />
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