<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>     
<%
int midx =0;
if (session.getAttribute("midx") != null){
	midx = (int)session.getAttribute("midx");
}
%>
<!--로그인 전1111 2222333344445555-->
<%
if(midx == 0){%>
<input type="button" style="float : right;"  value="Login" onclick="location.href='<%=request.getContextPath() %>/member/memberLogin.do'">
<!--관리자 로그인 후 -->
<%}else if(midx ==1) {%>
<input type="button" style="float : right;"  value="관리자모드" onclick="location.href='<%=request.getContextPath() %>/master/masterModemember.do'">
<input type="button" style="float : right;"  value="logout" onclick="location.href='<%=request.getContextPath() %>/member/memberLogOut.do'">
<!--로그인 후 -->
<%}else if(midx >1) {%>
<input type="button" style="float : right;"  value="Logout" onclick="location.href='<%=request.getContextPath() %>/member/memberLogOut.do'">  
<%}; %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome to DB!</title>
<style>
a{
 text-decoration:none;
 color:black;
 }
</style>
<script>
function check(){
	var fm = document.frm
	
    fm.action = "<%=request.getContextPath()%>/search/searchlistAction.do"
    fm.method="post"; 
    fm.submit();
      return;

}
</script>
</head>
<body style = "background-color : cornflowerblue;">
<form name="frm">
<h1 style= "text-align: center;margin-bottom : 50px;margin-top:80px;">Developer's Bank git test asdasd1</h1>

<table border=0 style ="with:800px;text-align:right;margin:auto;">
<tr>
<td>
   <input style="width:500px;" type="text" name ="keyword" size="10">
</td>
<td>
   <input type="submit" name="submit" value="검색" onclick="check();">
</td>
</tr>
</table>
<table border=0 style ="with:800px;text-align:right;margin:auto;margin-top:40px;">
<tr>
<td><input type="button"   value="Study" onclick="location.href='<%=request.getContextPath() %>/board/boardList.do'"></td> 
<td><input type="button"   value="커뮤니티" onclick="location.href='<%=request.getContextPath() %>/community/communityList.do'"></td> 
<td><input type="button"   value="Donation" onclick="location.href='<%=request.getContextPath() %>/donation/donationList.do'"> </td>
<td><input type="button"   value="마이페이지" onclick="location.href='<%=request.getContextPath() %>/member/memberMyPage.do'"> </td>
<td><input type="button"   value="이용안내" onclick="location.href='<%=request.getContextPath() %>/guidance/siteObject.jsp'"> </td>
</tr>
</table>
</form>
</html>