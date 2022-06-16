<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "developersbank2.*"%>    
<%@ page import = "developersbank2.domain.*"%>
<%@ page import = "java.util.*" %>
    
<%
    MemberVo mv = (MemberVo)request.getAttribute("mv");
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
  <script>
  
  function check(){
     //alert("테스트입니다");
    var fm= document.frm;
  
    if(fm.memberpoint.value==""){
       alert("기부할 포인트를 입력하세요");
        fm.memberpoint.focus();
        return;
       
    }else if(fm.memberpoint.value > <%=mv.getMemberpoint() %>){
       alert("입력하신 포인트가 보유량을 초과합니다")
        fm.memberpoint.focus();
        return;
    }
    alert("전송합니다");
    fm.action = "<%=request.getContextPath()%>/donation/donationAction.do"      //가상경로 활용으로 해킹 방지
    fm.method="post"; 
    fm.submit();
     
     
     
     
     return;  
  
  
  }
  </script>
 </HEAD>

 <BODY>
 <form  name="frm" > 
 <input type="button" style="float : right;"  value="Logout" onclick="location.href='<%=request.getContextPath() %>/member/memberLogOut.do'">
<table border="0" width="800px" align="center">
			<tr style ="text-align:left; font-size:20px;">
				<td>|&nbsp;<a href="<%=request.getContextPath() %>/home.jsp">DB</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Donation
				<a href ="<%=request.getContextPath() %>/donation/donationList.do">[1]</a>
	            <a href ="<%=request.getContextPath() %>/donation/donationList2.do"><span style="font-weight:normal">[2]</span></a>
				</td>				
			</tr>
			<tr><td style="border-bottom:3px solid; height:1px;" colspan="3"></td></tr>
            <tr>
            <td>

 <table class ="table" width="100%" style="border-collapse: collapse;">
 <tr><td style="border-bottom:1px solid;border-top:1px solid; height:1px;background-color: gray;" colspan="3"><b>&#60;포인트 Donation!&#62;</b></td></tr>
<tr>
<td style="text-align:left;">
<label><b>포인트기부 : </b><input type="text" name="memberpoint" size="30" placeholder="포인트"></label><br><br>
<span style="color :red; ">&#60;포인트 기부방식 안내&#62;</span><br>
회원님이 보유하신 Point는 DB사이트를 통해 기부가 됩니다.<br>
기부 방식은 포인트를 입력하시면 100Point 당 10원으로 계산되며 [2]번 메뉴를 통해 기부 목록을 확인하실 수 있습니다.<br>
최종 기부내역은 커뮤니티 공지사항을 통해 관리자가 매월 1일 기부 내역을 공지하여 회원님의 Point 사용 내역을 확인해드립니다.
</td>
</tr>

<tr>
<td>
<input type="reset" value="다시작성">
<input type="button"  value="확인" onclick="check();">
</td>
</tr>
 </table>
 </td>
 </tr>
 <tr><td style="height:1px;" colspan="3"><br></td></tr>
 <tr><td style="border:1px solid; height:1px;text-align:center;background-color: gray;" colspan="3"><b>&#60;계정정보&#62;</b></td></tr>
 <tr><td style="border:1px solid;">다음은 회원님의 계정 정보입니다.<br>
 <label><b>내가 가진 Point : </b><%=mv.getMemberpoint() %>점입니다.</label><br>
 <label><b>총 누적 Point : </b><%=mv.getAcpoint() %>점입니다. </label><br>
 <label><b>나의 등급 : </b><%=mv.getMembergrade() %></label><br><br>
  다음은 누적 포인트별 등급입니다.<br><br>
  <table border="1" width="100%" style="border-collapse: collapse;">
  <caption><b>누적포인트별 등급표</b></caption>
  <tr><th>등급</th><th>Bronze</th><th>Silver</th><th>Gold</th><th>Platinum</th><th>Diamond</th><th>Master</th>
  <tr style="text-align : center;"><td>점수</td><td>0</td><td>1000</td><td>15000</td><td>200000</td><td>500000</td><td>관리자</td>
  </table>
 </td></tr>
 </table>
 </form>
 </BODY>
</HTML>
