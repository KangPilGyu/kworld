<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
if('${msg}'=='success')
	alert("회원 인증 되었습니다. 로그인 후 사용해 주세요~~~");
else 
	alert("회원 인증에 실패하였습니다... 다시 시도해 주세요");
	
self.location.href="/";
</script>