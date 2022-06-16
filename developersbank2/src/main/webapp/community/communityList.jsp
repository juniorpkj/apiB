<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "developersbank2.*"%> <!-- communityDao생성을 위함 -->
<%@ page import = "java.util.*" %> <!-- ArrayList사용을 위함 -->
<%@ page import = "developersbank2.domain.*" %>
<%
 

    PageMaker pm = (PageMaker)request.getAttribute("pm");
   ArrayList<CommunityVo> alist = (ArrayList<CommunityVo>)request.getAttribute("alist");
    //controller에서 받아온 alist정보를 jsp화면에 구현
     
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome to DB!</title>
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
			a
			{
				text-decoration:none;
				color:black;
			}
		</style>
	</head>
	<body>
		<form name="frm" action="<%=request.getContextPath()%>/community/communityList.do" method="post">
		<table border="0" width="1000px" align="center">
			<tr>
				<td><b>|&nbsp;<a href="<%=request.getContextPath() %>/home.jsp">DB</a></b>&nbsp;&nbsp;&nbsp;커뮤니티</td>
			</tr>
			<tr><td style="border-bottom:1px solid; height:1px;" colspan="3"></td></tr>
			<tr>
				<td style="height:70px;">
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
						<span><input type="submit" name="submit" value="검색"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<span style="float: right;"><input type="button"  name="write" value="글쓰기" onclick="location.href='<%=request.getContextPath() %>/community/communityWrite.do'" style="width:100px;"></span>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" style="border-collapse: collapse;">
						<tr style="background-color: gray; border-bottom: 1px solid black; border-top: 1px solid black;">
							<th style="width:70px; text-align:center;">No</th>
							<th style="width:70px; text-align:center;">카테고리</th>
							<th style="text-align:center;">제목</th>
							<th style="width:120px; text-align:center;">작성자</th>
							<th style="width:150px; text-align:center;">작성일</th>
					
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
		</table>
		<table width="100%" style="border-collapse: collapse;text-align: center;">
<tr>
<td style ="width : 200px;text-align:right">
<% 
String keyword = pm.getScri().getKeyword();

String searchType = pm.getScri().getSearchType();


 if(pm.isPrev() == true){
	 out.println("<a href ='"+request.getContextPath()+"/community/communityList.do?page="+(pm.getStartPage()-1)+"&keyword="+keyword+"&searchType="+searchType+"'>◀</a>");
 }
%>
</td>
<td><% 
for(int i = pm.getStartPage(); i<=pm.getEndPage(); i++){
  out.println("<a href ='"+request.getContextPath()+"/community/communityList.do?page="+i+"&keyword="+keyword+"&searchType="+searchType+"'>"+i+"</a>");

}
%></td>
<td style ="width : 200px;text-align:left">
<%
if (pm.isNext()&&pm.getEndPage() >0){
   out.println("<a href ='"+request.getContextPath()+"/community/communityList.do?page="+(pm.getEndPage()+1)+"&keyword="+keyword+"&searchType="+searchType+"'>▶</a>");
}
%></td>
</tr>
</table>
		</form>
	</body>
</html>