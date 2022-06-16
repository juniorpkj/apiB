<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "developersbank2.*"%> <!-- MemberDao생성을 위함 -->
<%@ page import = "java.util.*" %> <!-- ArrayList사용을 위함 -->
<%@ page import = "developersbank2.domain.*" %>
<%
     PageMaker pm = (PageMaker)request.getAttribute("pm");
     ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist");
     
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome to DB!</title>
<style>
	div
	{
	  width:200px;
	  height:50px;
	  border: 0px solid black;
	  display:inline-block;
	  box-sizing:border-box;
	  border-radius: 15px;
	  text-align: center;
	  font-size : 30px;
	  margin-bottom : 20px;
	  background-color : white;
			}	
	a
	{
		text-decoration:none;
		color:black;
	}
	
	a:hover:not(.current)
	{ color : cornflowerblue;
	}
	
	nav
   	{  width : 210px;
	   height : 100%;
	
	}
	
	nav > table
	{ border-top : 1px solid black;
	  width:20%;
	  height:100%;
	  text-align : center;
	  font-size : 20px;
	  float : left;
	  background-color : cornflowerblue;
	   
	}
	
	ul
	{ background-color : cornflowerblue;
	  width : 300px;
	  list-style-type : none;
	  margin : 0;
	  padding : 0;
	
	}
	li a 
	{ display : block;
	  color : black;
	  padding : 8px;
	  text-align : center;
	  text-decoration : none;
	  font-size : bold;
	}
	
	li a.current 
	{ background-color : blue;
	  color : white
	}
	
	li a:hover:not(.current)
	{ background-color : orange;
	  color : white
	}
	
	main > table
	{ border : 1px solid black;
	  width:80%;
	  height:100%;
	  text-align : left;
	  font-size : 20px;
	  float : left;
	  padding : 0;
	  border-radius: 0px 15px 15px 0px;
	  background-color : white;
	}
	
		</style>
</head>
<body style = "background-color : cornflowerblue;">
<header>
<table>
<tr>
<td> <div><b>|&nbsp;<a href="<%=request.getContextPath() %>/home.jsp">DB</a></b></div>
</td>
</tr>
</table>
</header>
<nav>
<table>
<caption><b>메뉴</b></caption>
<tr><td>
<ul>
  <li><a href = "<%=request.getContextPath() %>/master/masterModemember.do">회원관리</a></li>
  <li><a href = "<%=request.getContextPath() %>/master/masterModeboard.do">게시판관리</a></li>
  <li><a href = "<%=request.getContextPath() %>/master/masterModecommunity.do">커뮤니티관리</a></li>
  <li><a href = "<%=request.getContextPath() %>/master/masterModedonation.do">Donation관리</a></li>
</ul>
</td></tr>
</table>
</nav>
<main>
<table>
<caption style="text-align:left;"><b>회원관리</b></caption>
<tr><td>
<form name="frm" action="<%=request.getContextPath()%>/master/masterModemember.do" method="post">
<tr>
<td>
<span> ㅣ&nbsp;검색 &nbsp;&nbsp;
<select name="searchType">
<option value="midx">회원번호</option>
<option value="memberid">아이디</option>
</select>
</span>
<span><input type="text" name ="keyword" size="10"></span>
<span><input type="submit" name="submit" value="검색"></span>
</td>
</tr>
<tr>
<td>
<table width="100%" style="border-collapse: collapse;">
	<tr style="background-color: gray; border-bottom: 1px solid black; border-top: 1px solid black;">
	<th style="width:50px; text-align:center;">회원번호</th>
	<th style="width:80px; text-align:center;">ID</th>
	<th style="width:80px;text-align:center;">이름</th>
	<th style="width:100px; text-align:center;">포인트/누적포인트</th>
	<th style="width:130px; text-align:center;">전화번호</th>
	<th style="width:150px; text-align:center;">이메일</th>
	</tr>
	<%  for (MemberVo mv : alist){ %>
<tr>
<td style="width:50px; text-align:center;"><%=mv.getMidx() %></td>
<td style="width:80px; text-align:center;"><a href ="<%=request.getContextPath()%>/master/masterModememberdelete.do?midx=<%=mv.getMidx() %>"><%=mv.getMemberid() %></a></td>
<td style="width:80px; text-align:center;"><%=mv.getMembername() %></td>
<td style="width:100px; text-align:center;"><%=mv.getMemberpoint()%>점&nbsp;/&nbsp;<%=mv.getAcpoint() %></td>
<td style="width:130px; text-align:center;"><%=mv.getMemberphone() %></td>
<td style="width:150px; text-align:center;"><%=mv.getMemberemail() %></td>
</tr>
<% } %>
</table>

</td></tr>
<tr><td>
<table width="100%" style="border-collapse: collapse;text-align: center;">
<tr>
<td style ="width : 200px;text-align:right">
<% 
String keyword = pm.getScri().getKeyword();

String searchType = pm.getScri().getSearchType();


 if(pm.isPrev() == true){
	 out.println("<a href ='"+request.getContextPath()+"/master/masterModemember.do?page="+(pm.getStartPage()-1)+"&keyword="+keyword+"&searchType="+searchType+"'>◀</a>");
 }
%>
</td>
<td><% 
for(int i = pm.getStartPage(); i<=pm.getEndPage(); i++){
  out.println("<a href ='"+request.getContextPath()+"/master/masterModemember.do?page="+i+"&keyword="+keyword+"&searchType="+searchType+"'>"+i+"</a>");

}
%></td>
<td style ="width : 200px;text-align:left">
<%
if (pm.isNext()&&pm.getEndPage() >0){
   out.println("<a href ='"+request.getContextPath()+"/master/masterModemember?page="+(pm.getEndPage()+1)+"&keyword="+keyword+"&searchType="+searchType+"'>▶</a>");
}
%>
</td>
</tr>
</table>
</td></tr>
</form>
</td></tr>  
</table>
</main>


</body>
</html>