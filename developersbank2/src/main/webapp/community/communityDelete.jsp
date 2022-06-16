<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "developersbank2.domain.CommunityVo"%> 
<%
    CommunityVo cv = (CommunityVo)request.getAttribute("cv");

%>    
<%

if(session.getAttribute("midx") ==null){
	//session.setAttribute("saveUrl", request.getRequestURI());   // 글쓰기 누르면 로그인으로 유도 되는데 로그인 후 글쓰기 페이지로 다시 불러 오기위해 url을 세션에 저장
	out.println("<script>alert('로그인해주세요');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
}else{
	int midx_ = (Integer)session.getAttribute("midx");

	if(midx_ != 1 && midx_ != cv.getMidx()){

	out.println("<script>alert('게시글 삭제는 작성자와 관리자만 가능합니다.');"+
	"location.href='"+request.getContextPath()+"/community/communitycontent.do?cidx="+cv.getCidx()+"'</script>");
	}
}
%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>글삭제하기</title>
<script>
  function check(){
     
    var fm= document.frm;
  
   
   
    alert("전송합니다");
   
    fm.action = "<%=request.getContextPath()%>/community/communityDeleteAction.do"      //가상경로 활용으로 해킹 방지
    fm.method="post"; 
    fm.submit();
     
     
     
     
     return;  
  
  
  }
</script>  
</head>
<body>
<form name="frm">
<input type="hidden" name="cidx" value="<%=cv.getCidx()%>">
<table border="0" width="1000px" align="center">
<tr>
<td><b>|&nbsp;정말 삭제하시겠습니까</b></td>		
</tr>
<tr><td style="border-bottom:2px solid; height:1px;" colspan="3"></td></tr>
<tr>
<td>
<table border = 1 width=100% style="border-collapse : collapse; text-align : center">
<tr>
<td><span style="color : red;">삭제된 글은 복구되지 않습니다.</span><br>
<input type="button"   value="삭제" onclick="check();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button"   value="취소" onclick="location.href='<%=request.getContextPath()%>/community/communityContent.do?cidx=<%=cv.getCidx()%>'">
</td>
</tr>
</table>
</td>
</tr>
</table>
</form>

</body>
</html>