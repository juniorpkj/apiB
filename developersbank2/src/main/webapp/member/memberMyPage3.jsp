<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "developersbank2.*"%>    
<%@ page import = "developersbank2.domain.*"%>
<%@ page import = "java.util.*" %>
    
<%
   PageMaker pm = (PageMaker)request.getAttribute("pm");
    ArrayList<CommunityVo> alist = (ArrayList<CommunityVo>)request.getAttribute("alist");
    
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
			div
			{
				width:400px;
				height:50px;
				float:right;
				border: 1px solid black;
				display:inline-block;
				box-sizing:border-box;
				padding:12px 0px;
				border-radius: 15px;
				text-align: center;
			}
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
				font-weight : bold;
			}
			a:hover:not(.current)
		 { 
		   text-decoration:none;
	       color:cornflowerblue;
		 }
			
	</style>
  <script>
  function check(){
	  var fm= document.frm;
	  fm.action = "<%=request.getContextPath()%>/member/memberMyPage3.do"
	  fm.method = "post";
	  fm.submit();
  }
  
  </script>
 </HEAD>

 <BODY>
 <form  name="frm" > 
 <input type="button" style="float : right;"  value="Logout" onclick="location.href='<%=request.getContextPath() %>/member/memberLogOut.do'">
<table border="0" width="800px" align="center">
 <tr style ="text-align:left; font-size:20px;">
 <td>|&nbsp;<a href="<%=request.getContextPath() %>/home.jsp">DB</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;마이페이지
     <a href ="<%=request.getContextPath() %>/member/memberMyPage.do"><span style="font-weight:normal">[1]</span></a>
	 <a href ="<%=request.getContextPath() %>/member/memberMyPage2.do">[2]</a>
 </td>				
 </tr>
 <tr><td style="border-bottom:3px solid; height:1px;" colspan="3"></td></tr>
 <tr><td style="border:1px solid; height:1px;text-align:center;background-color: gray;" colspan="3"><b>&#60;내 글 관리&#62;</b></td></tr>
 <tr><td style="border:1px solid; height:1px;text-align:center;" colspan="3">
 <input type="button" name="study" value="study" onclick="location.href='<%=request.getContextPath() %>/member/memberMyPage2.do'">
 <input type="button" name="community" value="커뮤니티" onclick="location.href='<%=request.getContextPath() %>/member/memberMyPage3.do'">
 </td></tr>
 <tr><td style="border:1px solid; height:1px;text-align:center;" colspan="3"><b>&#60;커뮤니티&#62;</b> </td></tr>
 <tr>
 <td>
 <tr><td style="border:0px solid;">
 <div>
	<span><b>검색</b></span>
		&nbsp;
	<span>
	<select name="searchType">
	<option value="c_subject">제목</option>
    <option value="c_writer">작성자</option>
	</select>
	</span>
	&nbsp;
	<span><input type="text" name ="keyword" size="10"></span>
	&nbsp;
	<span><input type="submit" name="submit" value="검색" ></span>
	</div>
 </td></tr>
 <tr>
	  <td>
	  <table width="100%" style="border-collapse: collapse;">
	  <tr style="background-color: gray; border-bottom: 1px solid black; border-top: 1px solid black;">
	  <th style="width:70px; text-align:center;">No</th>
	  <th style="width:70px; text-align:center;">카테고리</th>
      <th style="text-align:center;">제목</th>
      <th style="width:120px; text-align:center;">작성자</th>
      <th style="width:150px; text-align:center;">작성일</th>
    </tr>
   	<%  for (CommunityVo cv : alist){ %>
      <tr style="height:50px; border-bottom: 1px solid black;">
      <td style="text-align:center;"><%=cv.getCidx() %></td>
      <td style="text-align:center;"><%=cv.getCategory_() %></td>
      <td>
    <%
       for(int i =1; i<=cv.getC_level_();i++){
	   out.println("&nbsp;&nbsp;");
	   if(i == cv.getC_level_()){
	   out.println("ㄴ");	
       }
        }%>
        <a href = "<%= request.getContextPath()%>/community/communitycontent.do?cidx=<%=cv.getCidx()%>"><%=cv.getC_subject() %></a></td>
        <td style="text-align:center;"><%=cv.getC_writer() %></td>
        <td style="text-align:center;"><%=cv.getC_writeday() %></td>
        </tr>
        <% } %>
	    </table> 
	 	</td>
    	</tr>
<tr><td>
<table width="100%" style="border-collapse: collapse;text-align: center;">		
<tr>
<td style ="text-align:right;">
<% 
String keyword = pm.getScri().getKeyword();

String searchType = pm.getScri().getSearchType();


 if(pm.isPrev() == true){
	 out.println("<a href ='"+request.getContextPath()+"/member/memberMyPage3.do?page="+(pm.getStartPage()-1)+"&keyword="+keyword+"&searchType="+searchType+"'>◀</a>");
 }
%>
</td>
<td><% 
for(int i = pm.getStartPage(); i<=pm.getEndPage(); i++){
  out.println("<a href ='"+request.getContextPath()+"/member/memberMyPage3.do?page="+i+"&keyword="+keyword+"&searchType="+searchType+"'>"+i+"</a>");

}
%></td>
<td style ="text-align:left">
<%
if (pm.isNext()&&pm.getEndPage() >0){
   out.println("<a href ='"+request.getContextPath()+"/member/memberMyPage3.do?page="+(pm.getEndPage()+1)+"&keyword="+keyword+"&searchType="+searchType+"'>▶</a>");
}
%></td>
</tr>
 </table>
 </form>
 </BODY>
</HTML>
