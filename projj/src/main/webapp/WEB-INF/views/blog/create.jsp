<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div class="row" style="height:50px"></div>
      <article class="container">
        <div class="page-header" style="text-align: center">
          <h1>블로그 생성하기 </h1>
        </div>
        
        <div class="col-md-6 col-md-offset-3">
          <form role="form" method="post">
          
              <div class="form-group">
              <label for="blogName">블로그명</label>
              <input type="text" name="b_name" class="form-control"  placeholder="블로그명" required>
            </div>
            
            <div class="form-group">
             <label for="blogIntro">소개글</label>
        	<input  type="text" name="b_intro" class="form-control" placeholder="소개글을 적어주세요" required>
            </div>
            
            <div class="form-group">
             <label for="blogImg">프로필 이미지 등록</label>
        	<input  type="text" name="b_img" class="form-control" placeholder="나중에 만들께요 넘어가요">
            </div>
            
            <div class="form-group">
             	<label for="blogAddr">블로그 주소 등록</label>
             	<label for="blogAddr1">/blog/뒤에 사용 되는 아이디 입니다.</label>
        		<input  type="text" name="b_img" class="form-control" placeholder="나중에 만들께요 넘어가요">
        		<button class="btn btn-lg btn-primary btn-block">중복 확인</button>
            </div>
                        
            <div class="form-group text-center">
              <button type="submit" class="btn btn-info">회원가입<i class="fa fa-check spaceLeft"></i></button>
              <button id="cancel" type="button" class="btn btn-warning">가입취소<i class="fa fa-times spaceLeft"></i></button>
            </div>
            
          </form>
        </div>

      </article>
