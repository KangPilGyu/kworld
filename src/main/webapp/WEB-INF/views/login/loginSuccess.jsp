<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$("#snsemail",opener.document).val("${member.m_email}");
	$("#snspwd",opener.document).val("1q2w3e4r!");
	window.close();
	opener.parent.snsSubmit();

</script>