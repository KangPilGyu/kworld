<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr,jibunAddr,zipNo){
	$("#addr1").val(roadAddrPart1+" "+roadAddrPart2.replace(", ",",")); // 도로명 주소
	$("#addr2").val(addrDetail); // 고객입력 상세주소
	$("#addr3").val(zipNo); // 우편번호 앞자리 5자리 
}
function openDoroAdr(name1, name2, name3, name4)
{
	var height_ = 650;
	var width_ = 600;
	var left_ = screen.width;
	var top_ = screen.height;

	left_ = left_/2-(width_/2);
	top_ = top_/2-(height_/2);
	
	rstr = window.open('', 'openDoroAdr', 'width='+width_+', height='+height_+', top='+top_+', left='+left_+', scrollbars=yes');
	rstr.location.href = "/login/doro";
	rstr.window.focus();
}


$(document).ready(function(){
	$("#cancel").click(function(){
		self.location.href="/";
	});
	
	$("#formjoin").submit(function(event){
		$("#formjoin").attr("action","/login/join");
		
		var pwd1 = $("#pwd1").val();
		
		var chk_num = pwd1.search(/[0-9]/g);
		var chk_eng = pwd1.search(/[a-z]/ig);
		var strSpecial = pwd1.search(/[`~!@#$%^&*()\-_=+\\\|\[\]{};:\'\",.<>\/?]/gi);


		if (pwd1.length < 8 || pwd1.length > 20){
			alert('홈페이지 비밀번호는 8자리 이상 입력 가능합니다.');
			$("#pwd1").focus().select();
			event.preventDefault();
			return;
		}

		if (chk_num < 0 || chk_eng < 0 || strSpecial < 0){
			alert('홈페이지 비밀번호는 문자, 숫자, 특수문자가 조합 되어야 합니다.');
			$("#pwd1").focus().select();
			event.preventDefault();
			return;
		}

		if (pwd1 != $("#pwd2").val()){
			alert('홈페이지 비밀번호와 비밀번호확인 값이 일치하지 않습니다. 비밀번호를 올바르게 입력하여 주십시오.');
			$("#pwd2").focus().select();
			event.preventDefault();
			return;
		}
		
		
		var emailExist;
		var v_email=$("#inputEmail2").val();
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
		if(emailExist=="exist"){
			alert("이메일이 존재합니다~~");
			event.preventDefault();	
			return;
		}
	});
	
});
</script>

<div class="row" style="height:50px"></div>
      <article class="container">
        <div class="page-header" style="text-align: center">
          <h1>회원 가입 </h1>
        </div>
        
        <div class="col-md-6 col-md-offset-3">
          <form role="form" method="post" id="formjoin">
              <div class="form-group">
              <label for="inputEmail">이메일 주소</label>
              <input type="email" class="form-control" id="inputEmail2" name="m_email" placeholder="Email address" required value="">
            </div>
            <div class="form-group">
             <label for="inputPassword1">비밀번호</label>
        	<input  name="m_pwd" type="password" id="pwd1" class="form-control" placeholder="Password" required>
            </div>
            <div class="form-group">
              <label for="inputPassword2">비밀번호 확인</label>
              <input name="pwd2" type="password" class="form-control" id="pwd2" placeholder="Password Confirm" required>
              <p class="help-block">비밀번호 확인을 위해 다시한번 입력 해 주세요</p>
            </div>
             <div class="form-group">
              <label for="username">이름</label>
              <input name="m_name" type="text" class="form-control" id="username" placeholder="Your Name" required>
            </div>
            
            <div class="form-group">
              <label for="addr" style="display:block; width:100%">주소</label>
              <input name="m_wuaddr" style="width: 70%; display:inline-block" type="text" class="form-control" id="addr3" placeholder="우편번호" disabled="disabled" >
              <span class="input-group-btn" style="display: inline-block; width: 30%; float:right">
              <button  type="button" class="btn btn-primary btn-block"
               onclick="javascript:openDoroAdr('txtDoroZip1','','txtDoroAddrVil','hidBdMgNo')">도로명 검색</button></span>
              <input name="m_mainaddr" type="text" class="form-control" id="addr1" placeholder="도로명주소" disabled="disabled">
              <input name="m_detailaddr" type="text" class="form-control" id="addr2" placeholder="상세주소" disabled="disabled">
              
            </div>
            
    
            <div class="form-group">
                <label>약관 동의</label>
              <div data-toggle="buttons">
              <label class="btn btn-primary">
                  <span class="fa fa-check"></span>
                  <input id="agree" type="checkbox" autocomplete="off" checked>
              </label>
              <a href="#">이용약관</a>에 동의합니다.
              </div>
            </div>
            
            <div class="form-group text-center">
              <button type="submit" class="btn btn-info">회원가입<i class="fa fa-check spaceLeft"></i></button>
              <button id="cancel" type="button" class="btn btn-warning">가입취소<i class="fa fa-times spaceLeft"></i></button>
            </div>
          </form>
        </div>

      </article>
