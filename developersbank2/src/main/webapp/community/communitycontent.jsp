<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "developersbank2.domain.CommunityVo"%> 
<%
    CommunityVo cv = (CommunityVo)request.getAttribute("cv");

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome to DB!</title>
<style>
a
{
 text-decoration:none;
 color:black;
}
a:hover:not(.current)
		 { 
		   text-decoration:none;
	       color:cornflowerblue;
		 }
.table td{ border-right:2px solid;
      
}
</style>
</head>
<body>
<form name="frm" action="<%=request.getContextPath()%>/community/communityList.do" method="post">
<table border="0" width="1000px" align="center">
<tr>
<td><b>|&nbsp;<a href="<%=request.getContextPath() %>/home.jsp">DB</a></b>
 &nbsp;&nbsp;커뮤니티 
</td>		
</tr>
<tr><td style="border-bottom:2px solid; height:1px;" colspan="3"></td></tr>
<tr>
<td>
<br><br><br>
<table class = "table" border=1 style ="width:100%;height:90%;border-collapse:collapse;margin-left:auto;margin-right:auto">
<tr>
<td><h3><%=cv.getC_subject() %></h3> 카테고리:<%=cv.getCategory_() %></td>
</tr>
<tr><td style="border-bottom:2px solid;"><%=cv.getC_content() %>
    <img src="<%=request.getContextPath()%>/images/<%=cv.getFilename()%>">
    <a href="<%=request.getContextPath()%>/community/fileDownload.do?filename=<%=cv.getFilename() %>"><%=cv.getFilename() %></a>
</td></tr>
</table>
<table border=0 style ="margin-left:auto;margin-right:0px;">
<tr>
<td> 	<br>
<input type="button"  name="modify" value="수정" onclick="location.href='<%=request.getContextPath() %>/community/communityModify.do?cidx=<%=cv.getCidx() %>'">
<input type="button"  name="delete" value="삭제" onclick="location.href='<%=request.getContextPath() %>/community/communityDelete.do?cidx=<%=cv.getCidx() %>'">
<input type="button"  name="reply" value="답변" onclick="location.href='<%=request.getContextPath() %>/community/communityReply.do?cidx=<%=cv.getCidx() %>&origincidx=<%=cv.getOrigincidx() %>&c_depth=<%=cv.getC_depth()%>&c_level_=<%=cv.getC_level_()%>&c_subject=<%=cv.getC_subject()%>&c_content=<%=cv.getC_content()%>&category_=<%=cv.getCategory_()%>'">
<input type="button"  name="list" value="목록" onclick="location.href='<%=request.getContextPath() %>/community/communityList.do'">
</td></tr>
</table>
</td>
</tr>
</table>
</form>
</body>
</html>