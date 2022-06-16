<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "developersbank2.domain.BoardVo"%> 
<%
    BoardVo bv = (BoardVo)request.getAttribute("bv");

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
<form name="frm" action="<%=request.getContextPath()%>/board/boardList.do" method="post">
<table border="0" width="1000px" align="center">
<tr>
<td><b>|&nbsp;<a href="<%=request.getContextPath() %>/home.jsp">DB</a></b>
 &nbsp;&nbsp;Study 
</td>		
</tr>
<tr><td style="border-bottom:2px solid; height:1px;" colspan="3"></td></tr>
<tr>
<td>
<br><br><br>
<table class = "table" border=1 style ="width:100%;height:90%;border-collapse:collapse;margin-left:auto;margin-right:auto">
<tr>
<td><h3><%=bv.getSubject() %></h3> 태그:<%=bv.getTag() %></td>
</tr>
<tr><td style="border-bottom:2px solid;><%=bv.getContent() %>">
    <img src="<%=request.getContextPath()%>/images/<%=bv.getFilename()%>">
    <a href="<%=request.getContextPath()%>/board/fileDownload.do?filename=<%=bv.getFilename() %>"><%=bv.getFilename() %></a>
</td></tr>
</table>
<table border=0 style ="margin-left:auto;margin-right:0px;">
<tr>
<td> 	<br>
<input type="button"  name="modify" value="수정" onclick="location.href='<%=request.getContextPath() %>/board/boardModify.do?bidx=<%=bv.getBidx() %>'">
<input type="button"  name="delete" value="삭제" onclick="location.href='<%=request.getContextPath() %>/board/boardDelete.do?bidx=<%=bv.getBidx() %>'">
<input type="button"  name="reply" value="답변" onclick="location.href='<%=request.getContextPath() %>/board/boardReply.do?bidx=<%=bv.getBidx() %>&originbidx=<%=bv.getOriginbidx() %>&depth=<%=bv.getDepth()%>&level_=<%=bv.getLevel_()%>&subject=<%=bv.getSubject()%>&content=<%=bv.getContent()%>&tag=<%=bv.getTag()%>'">
<input type="button"  name="list" value="목록" onclick="location.href='<%=request.getContextPath() %>/board/boardList.do'"> 
</td></tr>
</table>
</td>
</tr>
</table>
</form>
</body>
</html>