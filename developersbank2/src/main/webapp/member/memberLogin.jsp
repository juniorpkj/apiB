<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<script>
  function check(){
     //alert("테스트입니다");
    var fm= document.frm;
  
    if (fm.memberId.value == ""){
       alert("아이디를 입력하세요");
        fm.memerId.focus();
        return;
    }else if(fm.memberPwd.value==""){
       alert("비밀번호를 입력하세요")
        fm.memberPwd.focus();
        return;
       
    }
      
    	
    alert("전송합니다");
    //fm.action="./memberJoinOk.jsp";
    fm.action = "<%=request.getContextPath()%>/member/memberLoginAction.do"      //가상경로 활용으로 해킹 방지
    fm.method="post"; 
    fm.submit();
     
     
     
     
     return;  
  
  
  }
  
  
  </script>
</head>
<body>
<form name="frm">
<table style="margin:auto;width:1000px;height:500px">
<tr>
<td style="text-align: center;">
<h2>로그인</h2><br>
<input type="text" name="memberId" size="30" placeholder="ID"> <br><br>
<input type="password" name="memberPwd" size="30" placeholder="Password"><br><br>
<a href="<%=request.getContextPath()%>/member/memberJoin.do">계정이 없으신가요?</a> <input type="button"  value="로그인" onclick="check();">
</td>
</tr>
</table>
</form>
</body>
</html>