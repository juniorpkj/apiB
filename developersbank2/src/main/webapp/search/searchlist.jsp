<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "developersbank2.*"%>
<%@ page import = "java.util.*" %>
<%@ page import = "developersbank2.domain.*" %>    
<%
     PageMaker pm = (PageMaker)request.getAttribute("pm");
     ArrayList<SearchVo> alist = (ArrayList<SearchVo>)request.getAttribute("alist");
     
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome to DB!</title>
</head>
	<style>
		 div
         {
		   display:inline-block;
		   width:150px;
		   height:50px;
		   border: 1px solid black;
		   display:inline-block;
		   box-sizing:border-box;
		   padding:12px 0px;
		   border-radius: 15px;
		   text-align: center;
		   margin-top:30px;
		 
		 }
		 div:hover:not(.current)
	     { background-color : cornflowerblue;
	       color : white
	     }
		 
		 
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
		 
	</style>
<body>
<form name="frm" action="<%=request.getContextPath()%>/search/searchlistAction.do" method="post">
<table border=0 style ="width:1500px;">
<tr>
<td>
<b>|&nbsp;<a href="<%=request.getContextPath() %>/home.jsp">DB</a></b>
</td>
<td>
   <input type="text" name ="keyword" size="100">
   <input type="submit" name="submit" value="검색">
</td>
</tr>
</table>
</form>
<div><a href ="<%= request.getContextPath()%>/search/searchlist.do">검색결과</a></div>
<div><a href ="<%= request.getContextPath()%>/board/boardList.do">Study</a></div>
<div><a href ="<%= request.getContextPath()%>/community/communityList.do">커뮤니티</a></div>
<div><a href ="<%= request.getContextPath()%>/donation/donationList.do">Donation</a></div><hr> 

<table border=0 style ="width:50%;height:100%;">
<%  for (SearchVo sv : alist){ %>
   
   <tr>
   <td style="text-align:left;"><br><br>
   <%if (sv.getBidx() != 0) {%>   
   <a href = "<%= request.getContextPath()%>/board/boardContent.do?bidx=<%=sv.getBidx()%>">
   <%= request.getContextPath()%>/board/boardContent.do?bidx=<%=sv.getBidx()%>
   </a>
   <%}else if (sv.getCidx() != 0){ %>
    <a href = "<%= request.getContextPath()%>/community/communitycontent.do?cidx=<%=sv.getCidx()%>">
   <%= request.getContextPath()%>/community/communitycontent.do?cidx=<%=sv.getCidx()%>
   </a>   
   <%} %>
   </td>
   </tr>
   
   <tr>
   <td style="text-align:left;">
   <%if (sv.getBidx() != 0) {%>
   <%  for(int i =1; i<=sv.getLevel_();i++){
	   out.println("&nbsp;&nbsp;");
	   if(i == sv.getLevel_()){
       out.println("ㄴ");	
         }
   }%>
   
    <a href = "<%= request.getContextPath()%>/board/boardContent.do?bidx=<%=sv.getBidx()%>">
    <span style="font-size:25px;"><%=sv.getSubject() %></span>
    </a>
   <%}else if (sv.getCidx() != 0){ %> 
   <%  for(int i =1; i<=sv.getC_level_();i++){
	   out.println("&nbsp;&nbsp;");
	   if(i == sv.getC_level_()){
       out.println("ㄴ");	
         }
   }%>
   
    <a href = "<%= request.getContextPath()%>/community/communitycontent.do?cidx=<%=sv.getCidx()%>">
    <span style="font-size:25px;"><%=sv.getC_subject() %></span>
    </a>
   
   <%} %>
    </td>
    </tr>
    
    <tr>
    <td style="text-align:left;">
    <%if (sv.getBidx() != 0) {%>
    <%=sv.getWriteday() %>
    <%}else if (sv.getCidx() != 0){ %> 
    <%=sv.getC_writeday() %>
    <%} %>
    </td>
    </tr>
<%} %>
   
   
    
</table>

</body>
</html>