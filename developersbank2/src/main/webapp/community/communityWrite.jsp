<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
if(session.getAttribute("midx") ==null){
	session.setAttribute("saveUrl", request.getRequestURI());   // 글쓰기 누르면 로그인으로 유도 되는데 로그인 후 글쓰기 페이지로 다시 불러 오기위해 url을 세션에 저장
	out.println("<script>alert('로그인해주세요');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
}

%>
<%
int midx =0;
if (session.getAttribute("midx") != null){
	midx = (int)session.getAttribute("midx");
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
	if (fm.c_subject.value == ""){
	       alert("제목을 입력하세요");
	        fm.c_subject.focus();
	        return;
	}else if(fm.c_content.value==""){
	       alert("내용을 입력하세요")
	        fm.c_content.focus();
	        return;
	}else if(fm.category_.value==""){
	       alert("카테고리를 입력하세요")
	        fm.tag.focus();
	        return;
	
	}
	alert("전송합니다");
fm.action = "<%=request.getContextPath()%>/community/communityWriteAction.do"
    fm.enctype = "multipart/form-data";   //문자만이 아닌 파일 형식으로 넘길 수 있게 설정
    fm.method="post"; 
    fm.submit();
      return;

}
</script>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script>
		$(function(){	
			 let btn = $("#category_option"); 	
			 if(<%= midx%>!=1){
				   btn.css("display", "none");
		   } 
	    });
		

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
<td><input type ="text" name ="c_subject" size = "100"></td>
</tr>
<tr>
<td style="width : 150px; border-right: 1px solid black;text-align:center;background-color: gray;"><b>카테고리</b></td>
<td><select name="category_">
            <option id="category_option" value="공지사항">공지사항</option>
            <option value="일반" selected>일반게시판</option>
            <option value="구직자">구직자</option>
            <option value="취업자">취업자</option>
</select></td>
</tr>
<tr>
<td style="width : 150px; border-right: 1px solid black;text-align:center;background-color: gray;"><b>내용</b></td>
<td><textarea placeholder = "내용을 입력해주세요" cols="100" rows="10" name = "c_content"></textarea></td>
</tr>
<tr>
<td style="width : 150px; border-right: 1px solid black;text-align:center;background-color: gray;"><b>작성자</b></td>
<td><input type ="text" name ="c_writer" size = "100" value ="<%=session.getAttribute("membernickname") %>" readonly="readonly" ></td>
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