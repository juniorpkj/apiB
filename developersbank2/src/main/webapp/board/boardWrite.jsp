<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
if(session.getAttribute("midx") ==null){
	session.setAttribute("saveUrl", request.getRequestURI());   // 글쓰기 누르면 로그인으로 유도 되는데 로그인 후 글쓰기 페이지로 다시 불러 오기위해 url을 세션에 저장
	out.println("<script>alert('로그인해주세요');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
}

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 글쓰기</title>
<style>
a{
 text-decoration:none;
 color:black;
 }
 .table tr{
 height:50px; 
 border-bottom: 1px solid black;
 
 }
 .table td{
 width : 150px; 
 border-right: 1px solid black;
 text-align:center;
 
 }
 
</style>
<script>
function check(){
	var fm = document.frm
	if (fm.subject.value == ""){
	       alert("제목을 입력하세요");
	        fm.subject.focus();
	        return;
	}else if(fm.content.value==""){
	       alert("내용을 입력하세요")
	        fm.content.focus();
	        return;
	}else if(fm.tag.value==""){
	       alert("태그를 입력하세요")
	        fm.tag.focus();
	        return;
	
	}
	alert("전송합니다");
fm.action = "<%=request.getContextPath()%>/board/boardWriteAction.do"
    fm.enctype = "multipart/form-data";   //문자만이 아닌 파일 형식으로 넘길 수 있게 설정
    fm.method="post"; 
    fm.submit();
      return;

}
</script>
</head>
<body>

<form name = "frm">
<table border="0" width="1000px" align="center">
<tr>
<td><b>|&nbsp;<a href="<%=request.getContextPath() %>/home.jsp">DB</a></b>&nbsp;&nbsp;&nbsp;게시글 작성</td>
</tr>
<tr><td style="border-bottom:1px solid; height:1px;" colspan="3"></td></tr>
<tr>
<td>
<table class="table" width="100%" style="border-collapse: collapse;">
<tr>
<td style="width : 150px; border-right: 1px solid black;text-align:center;background-color: gray;"><b>제목</b></td>
<td><input type ="text" name ="subject" size = "100"></td>
</tr>
<tr>
<td style="width : 150px; border-right: 1px solid black;text-align:center;background-color: gray;"><b>태그</b></td>
<td><input type ="text" name ="tag" size = "100" placeholder ="태그를 입력해주세요" ></td>
</tr>
<tr>
<td style="width : 150px; border-right: 1px solid black;text-align:center;background-color: gray;"><b>내용</b></td>
<td><textarea placeholder = "내용을 입력해주세요" cols="100" rows="10" name = "content"></textarea></td>
</tr>
<tr>
<td style="width : 150px; border-right: 1px solid black;text-align:center;background-color: gray;"><b>작성자</b></td>
<td><input type ="text" name ="writer" size = "100" value ="<%=session.getAttribute("membernickname") %>" readonly="readonly" ></td>
</tr>
<tr>
<td style="width : 150px; border-right: 1px solid black;text-align:center;background-color: gray;"><b>파일업로드</b>
</td>
<td><input type ="file" name = "filename">
</td>
</tr>
<tr>
<td colspan=2 style= "text-align : right"> <input type="button"  value="확인" onclick="check();"> 
<input type="reset" value="다시작성"></td>
</tr>
</table>
</td>
</tr>
</table>

</form>



</body>
</html>