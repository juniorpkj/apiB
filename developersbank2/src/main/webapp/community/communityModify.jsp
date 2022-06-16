<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "developersbank2.domain.CommunityVo"%> 
<%
    CommunityVo cv = (CommunityVo)request.getAttribute("cv");

%>
<%

if(session.getAttribute("midx") ==null){
	//session.setAttribute("saveUrl", request.getRequestURI());   // 글쓰기 누르면 로그인으로 유도 되는데 로그인 후 글쓰기 페이지로 다시 불러 오기위해 url을 세션에 저장
	out.println("<script>alert('로그인해주세요');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
}else{
	int midx_ = (Integer)session.getAttribute("midx");

	if(midx_ != 1 && midx_ != cv.getMidx()){

	out.println("<script>alert('게시글 수정은 작성자와 관리자만 가능합니다.');"+
	"location.href='"+request.getContextPath()+"/community/communitycontent.do?cidx="+cv.getCidx()+"'</script>");
	}
}
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>글 수정</title>
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
     
    var fm= document.frm;
  
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
         fm.c_writer.focus()
         return;
     }
   
    alert("전송합니다");
   
    fm.action = "<%=request.getContextPath()%>/community/communityModifyAction.do"      //가상경로 활용으로 해킹 방지
    fm.enctype = "multipart/form-data";
    fm.method="post"; 
    fm.submit();
     
     
     
     
     return;  
  
  
  }
  
  
  </script>
</head>
<body>
<form name="frm">
<input type="hidden" name="cidx" value="<%=cv.getCidx()%>">
<table border="0" width="1000px" align="center">
<tr>
<td><b>|&nbsp;<a href="<%=request.getContextPath() %>/home.jsp">DB</a></b>
 &nbsp;&nbsp;&nbsp;커뮤니티 
<span>게시글 수정</span>&nbsp;
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
<td><input type ="text" name ="tag" size = "100" placeholder ="카테고리를 입력해주세요" ></td>
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