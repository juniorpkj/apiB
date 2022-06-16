<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "developersbank2.domain.MemberVo"%> 
<%
    MemberVo mv = (MemberVo)request.getAttribute("mv");

%>  
<%

if(session.getAttribute("midx") ==null){
	//session.setAttribute("saveUrl", request.getRequestURI());   // 글쓰기 누르면 로그인으로 유도 되는데 로그인 후 글쓰기 페이지로 다시 불러 오기위해 url을 세션에 저장
	out.println("<script>alert('로그인해주세요');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
}
%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원삭제하기</title>
<script>
  function check(){
     
    var fm= document.frm;
  
   
   
    alert("전송합니다");
    fm.action = "<%=request.getContextPath()%>/master/memberDeleteAction.do"      //가상경로 활용으로 해킹 방지
    fm.method="post"; 
    fm.submit();
     
     
     
     
     return;  
  
  
  }
</script>  
</head>
<body>
<form name="frm">
<input type="hidden" name="midx" value="<%=mv.getMidx()%>">
<table border="0" width="1000px" align="center">
<tr>
<td><b><span style="color : red;">|&nbsp;정말 삭제하시겠습까</span></b></td>		
</tr>
<tr><td style="border-bottom:2px solid; height:1px;" colspan="3"></td></tr>
<tr>
<td>
<table border = 1 width=100% style="border-collapse : collapse; text-align : center">
<tr>
<td><span style="color : red;">회원번호가 맞는지 다시 확인해주세요. 회원번호 : <%=mv.getMidx() %></span><br>
<input type="button"   value="삭제" onclick="check();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button"   value="취소" onclick="location.href='<%=request.getContextPath()%>/board/boardContent.do?bidx=<%=mv.getMidx()%>'">
</td>
</tr>
</table>
</td>
</tr>
</table>
</form>

</body>
</html>