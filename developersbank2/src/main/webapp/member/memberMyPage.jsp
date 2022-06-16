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
  
    if(fm.memberPwd.value==""){
       alert("비밀번호를 입력하세요");
        fm.memberPwd.focus();
        return;
       
    }else if(fm.memberPwd2.value==""){
       alert("비밀번호 확인을 입력하세요");
        fm.memberPwd2.focus();
        return;
    }else if(fm.memberPwd.value!=fm.memberPwd2.value){
        alert("비밀번호가 일치하지 않습니다");
        fm.memberPwd2.value="";
        fm.memberPwd.focus();
        return;
        
    }else if (fm.memberEmail.value==""){
        alert("이메일을 입력하세요");
        fm.memberEmail.focus();
        return;
    }else if (fm.memberphone.value==""){
        alert("연락처를 입력하세요");
        fm.memberphone.focus();
        return;
    
    
       }
     
    
    
    alert("전송합니다");
    //fm.action="./memberJoinOk.jsp";
    fm.action = "<%=request.getContextPath()%>/member/memberModifyAction.do"      //가상경로 활용으로 해킹 방지
    fm.method="post"; 
    fm.submit();
     
     
     
     
     return;  
  
  
  }
  function check2(){
	     //alert("테스트입니다");
	    var fm= document.frm;
	    if(fm.memberPwd3.value==""){
	        alert("비밀번호를 입력하세요")
	         fm.memberPwd3.focus();
	         return;
	    }else if(fm.memberPwd4.value==""){
	        alert("비밀번호 확인을 입력하세요");
	        fm.memberPwd2.focus();
	        return;
	    }else if(fm.memberPwd3.value!=fm.memberPwd4.value){
	        alert("비밀번호가 일치하지 않습니다");
	        fm.memberPwd4.value="";
	        fm.memberPwd3.focus();
	        return;
	        
	    }
	   
	         
	    fm.action = "<%=request.getContextPath()%>/member/memberDeleteAction.do"      //가상경로 활용으로 해킹 방지
	    fm.method="post"; 
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
				    <a href ="<%=request.getContextPath() %>/member/memberMyPage.do">[1]</a>
	                 <a href ="<%=request.getContextPath() %>/member/memberMyPage2.do"><span style="font-weight:normal">[2]</span></a>
				</td>				
			</tr>
			<tr><td style="border-bottom:3px solid; height:1px;" colspan="3"></td></tr>
            <tr>
            <td>

 <table class ="table" width="100%" style="border-collapse: collapse;">
 <tr><td style="border-bottom:1px solid;border-top:1px solid; height:1px;background-color: gray;" colspan="3"><b>&#60;개인정보 확인 및 수정&#62;</b></td></tr>
<tr>
<td>
<label><b>PWD : </b><input type="password" name="memberPwd" size="30" placeholder="비밀번호"></label><br>
</td>
</tr>
<tr>
<td>
<label><b>PWD확인 : </b><input type="password" name="memberPwd2" size="30" placeholder="비밀번호 확인"></label><br>
</td>
</tr>
<tr>
<td>
<label><b>닉네임 : </b><input type="text" name="memberNickName" size="30" value="<%=mv.getMembernickname()%>" readonly="readonly"></label><br>
</td> 
</tr>
<tr>
<td>
<label><b>이메일 : </b><input type="email" name="memberEmail" size="30" placeholder="<%=mv.getMemberemail()%>"></label><br>
</td>
</tr>
<tr>
<td>
<label><b>거주지 : </b><select name="memberAddr" style="width:100px;height:25px">
	<option value="서울">서울</option>
	<option value="대전">대전</option>
	<option value="전주">전주</option>
	</select></label><br>
	</td>
</tr>
<tr>
<td>
<label><b>전화번호 : </b><input type="text" name="memberphone" size="30" placeholder="<%=mv.getMemberphone()%>"></label><br>
</td>
</tr>
<tr>
<td>
<input type="reset" value="다시작성">
<input type="button"  value="수정" onclick="check();">
</td>
</tr>
 </table>
 </td>
 </tr>
 <tr><td style="height:1px;" colspan="3"><br></td></tr>
 <tr><td style="border:1px solid; height:1px;text-align:center;background-color: gray;" colspan="3"><b>&#60;회원탈퇴&#62;</b></td></tr>
 <tr><td style="border:1px solid;"><span style="color : red;">비밀번호를 입력하시면 회원탈퇴가 진행됩니다.<br>
 삭제된 아이디는 복구가 불가능합니다.</span><br><br>
 <label><b>PWD : </b><input type="password" name="memberPwd3" size="30" placeholder="비밀번호"></label><br>
 <label><b>PWD : </b><input type="password" name="memberPwd4" size="30" placeholder="비밀번호 확인"></label>
 <input type="button"  value="회원탈퇴" onclick="check2();">
 </td></tr>
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
