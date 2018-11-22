<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function deleteSubmit() {
	var con = confirm("삭제 하시겠습니까?");
	if (con) {
		self.location.href="/news/delete/${news.n_no}";
	}
}

</script>
 
  <div class="row" style="height: 50px"></div>
  
<br/>
  
 <!-- Page Content -->
    <div class="container">

      <div class="row">

        <!-- Post Content Column -->
        <div class="col-lg-8">


	<a href="/news/main"><img alt="" src="/resources/images/left-arrow-key.png" style="width: 50px;height: 50px" ></a>


          <!-- Title -->
          <h1 class="mt-4">${news.n_title}</h1>

          <!-- Author -->
          <p class="lead">
            글쓴이 : 
            <a href="#">${news.n_writer }</a>
          </p>

          <hr>

          <!-- Date/Time -->
          <p>${news.n_regdate }</p>

          <hr>

          <!-- Preview Image -->
          <img class="img-fluid rounded" src="/resources/images/${news.n_file}.jpg" alt="" style="width:800px; height:300px">

          <hr>

          <!-- Post Content -->
          <p class="lead">${news.n_content2}</p>
<br/>

          <p>${news.n_content }</p>

<hr>

<a href="/news/reg"><button type="button" class="btn btn-primary">등록</button></a>
<a href="/news/rev/${news.n_no}"><button type="button" class="btn btn-warning">수정</button></a>
<a href="/news/delete/${news.n_no}"><button type="button" class="btn btn-danger " onclick="deleteSubmit()">삭제</button></a>






         

        </div>

      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->