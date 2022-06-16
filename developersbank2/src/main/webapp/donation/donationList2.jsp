<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "developersbank2.*"%>    
<%@ page import = "developersbank2.domain.*"%>
<%@ page import = "java.util.*" %>
    
<%
ArrayList<DonationVo> alist = (ArrayList<DonationVo>)request.getAttribute("alist");
%>
<%
if(session.getAttribute("midx") ==null){
	session.setAttribute("saveUrl", request.getRequestURI());   // 글쓰기 누르면 로그인으로 유도 되는데 로그인 후 글쓰기 페이지로 다시 불러 오기위해 url을 세션에 저장
	out.println("<script>alert('로그인해주세요');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
}

%>

    
<!DOCTYPE HTML>
<HTML>
 <HEAD>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE> Welcome to DB! </TITLE> 
  <style>
			
			.table  tr
			{
			    height:50px;
			    border-bottom: 0px solid black;
			    text-align:center;
			    border-right : 1px solid black;
			    border-left : 1px solid black;
			}
			.table  td
			{
			    height:50px;
			    border: 1px solid black;
			    text-align:center;
			    
			}
			a
			{
				text-decoration:none;
				color:black;
				font-weight:bold;
			}
			a:hover:not(.current)
		   { 
		   text-decoration:none;
	       color:cornflowerblue;
		   }
			
	</style>

 </HEAD>

 <BODY>
 <form  name="frm" > 
 <input type="button" style="float : right;"  value="Logout" onclick="location.href='<%=request.getContextPath() %>/member/memberLogOut.do'">
<table border="0" width="1000px" align="center">
			<tr style ="text-align:left; font-size:20px;">
				<td>|&nbsp;<a href="<%=request.getContextPath() %>/home.jsp">DB</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Donation
				<a href ="<%=request.getContextPath() %>/donation/donationList.do"><span style="font-weight:normal">[1]</span></a>
	            <a href ="<%=request.getContextPath() %>/donation/donationList2.do">[2]</a>
				</td>				
			</tr>
			<tr><td style="border-bottom:3px solid; height:1px;" colspan="3"></td></tr>
            <tr>
            <td>

 <table class ="table" width="100%" style="border-collapse: collapse;">
 <tr><td style="border-bottom:1px solid;border-top:1px solid; height:1px;background-color: gray;" colspan="3"><b>&#60;포인트 Donation!&#62;</b></td></tr>
<tr>
<td>
<table width="100%" style="border-collapse: collapse;">
	<tr style="background-color: gray; border-bottom: 1px solid black; border-top: 1px solid black;">
	    <th style="width:70px; text-align:center;">No</th>
		<th style="width:70px; text-align:center;">닉네임</th>
		<th style="text-align:center;">포인트</th>
		<th style="width:120px; text-align:center;">날짜</th>	
		<%  for (DonationVo dv : alist){ %>	
	    <tr style="height:50px; border-bottom: 1px solid black;">
        <td style="text-align:center;"><%=dv.getDidx() %></td>
        <td style="text-align:center;"><%=dv.getD_writer() %></td>
        <td style="text-align:center;"><%=dv.getMemberpoint() %></td>
        <td style="text-align:center;"><%=dv.getD_writeday() %></td>
        </tr>
        <%} %>              
</table>
</td>
</tr>
 </table>
 </td>
 </tr>
 </table>
 </form>
 </BODY>
</HTML>
