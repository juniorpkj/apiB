<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "developersbank2.domain.CommunityVo"%> 
<%
    CommunityVo cv = (CommunityVo)request.getAttribute("cv");

%>
<%
if(session.getAttribute("midx") ==null){
	out.println("<script>alert('로그인해주세요');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>답변달기</title>
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
 border-right: 2px solid black;
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
	}else if(fm.c_writer.value==""){
	       alert("작성자를 입력하세요")
	        fm.c_writer.focus();
	        return;
	
	}
	alert("전송합니다");
fm.action = "<%=request.getContextPath()%>/community/communityReplyAction.do"     
    fm.method="post"; 
    fm.submit();
      return;
     
}
</script>
</head>
<body>
<form name = "frm">
<input type="hidden" name="cidx" value="<%=cv.getCidx()%>">
<input type="hidden" name="origincidx" value="<%=cv.getOrigincidx()%>">
<input type="hidden" name="c_depth" value="<%=cv.getC_depth()%>">
<input type="hidden" name="c_level_" value="<%=cv.getC_level_()%>">
<input type="hidden" name="category_" value="<%=cv.getCategory_()%>"> 
<table border="0" width="1000px" align="center">
<tr>
<td><b>|&nbsp;<a href="<%=request.getContextPath() %>/home.jsp">DB</a></b>
 &nbsp;&nbsp;&nbsp;커뮤니티 
<span>답변 달기</span>&nbsp;
</td>		
</tr>
<tr><td style="border-bottom:2px solid; height:1px;" colspan="3"></td></tr>
<tr>
<td>
<br><br><br>
<table class="table" width="100%" style="border-collapse: collapse;">
<tr>
<td style="width : 150px; border-right: 1px solid black;text-align:center;background-color: gray;"><b>제목</b></td>
<td><input type ="text" name ="c_subject" size = "100"></td>
</tr>
<tr>
<td style="width : 150px; border-right: 1px solid black;text-align:center;background-color: gray;"><b>카테고리</b></td>
<td>
<%=cv.getCategory_() %>
</td>
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
<td colspan=2 style= "text-align : right;border-bottom:2px solid;"> <input type="button"  value="확인" onclick="check();"> 
<input type="reset" value="다시작성"></td>
</tr>
</table>
</td>
</tr>
</table>



</form>



</body>
</html>