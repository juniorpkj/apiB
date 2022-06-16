<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<HTML>
 <HEAD>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE> New Document </TITLE> 
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
			
			}
			
		</style>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){ 
	
	$("#input_id").focusout(function(){ 
		
		let id = $("#input_id").val(); 		 			
		$.ajax({ //ajax통신
			type : 'post', //데이터를 보내는 방식
			url : "<%=request.getContextPath()%>/member/idcheck.do",  //서버 요청 페이지
			data : {"userId": id}, //서버로 보낼 데이터(JSON 형식)
			dataType : 'json',
			success : function(result){ 
				
				if(result==0){ 
				    $("#checkId").html('사용할 수 없는 아이디입니다.');
				    $("#checkId").attr("color","red");
				    $("#input_id").val("").trigger("focus"); 
				} else{					
				    $("#checkId").html('사용할 수 있는 아이디입니다.');
				    $("#checkId").attr("color","green");
				    

				}

			},
			error : function(error){ //에러 발생시
				alert(error);
				
			}

		}); 
    });
});	

</script>
  <script>
  function check(){
     //alert("테스트입니다");
    var fm= document.frm;
  
    if (fm.memberId.value == ""){
       alert("아이디를 입력하세요");
        fm.memerId.focus();
        return;
    }else if(fm.memberPwd.value==""){
       alert("비밀번호를 입력하세요")
        fm.memberPwd.focus();
        return;
       
    }else if(fm.memberPwd2.value==""){
       alert("비밀번호 확인을 입력하세요")
        fm.memberPwd2.focus()
        return;
    }else if(fm.memberPwd.value!=fm.memberPwd2.value){
        alert("비밀번호가 일치하지 않습니다")
        fm.memberPwd2.value=""
        fm.memberPwd.focus();
        return;
    
    }else if(fm.memberName.value==""){
        alert("이름을 입력하세요")
        fm.memberName.focus()
        return;
        
    }else if(fm.memberNickName.value==""){
        alert("별명을 입력하세요")
        fm.memberNickName.focus()
        return;
        
    }else if (fm.memberEmail.value==""){
        alert("이메일을 입력하세요")
        fm.memberEmail.focus()
        return;
    }else if (fm.memberphone.value==""){
        alert("연락처를 입력하세요")
        fm.memberphone.focus()
        return;
    }else if (fm.memberJumin.value==""){
        alert("주민번호를 입력하세요")
        fm.memberJumin.focus()
        return;
    
    }else {var flag = false;  //기본값 체크가 안된상태를 false로 지정
        for(var i=0;i<fm.memberHobby.length;i++ ){  //배열을 반복해서
        if(fm.memberHobby[i].checked==true){       //각 배열방에 값이 하나라도 있다면
        flag = true;
        break;
        }
        }
    
     if(flag == false){
        alert("취미를 한개 이상 선택해주세요");
        return;
     }
    
    
    }
      
    
    alert("전송합니다");
    //fm.action="./memberJoinOk.jsp";
    fm.action = "<%=request.getContextPath()%>/member/memberJoinAction.do"      //가상경로 활용으로 해킹 방지
    fm.method="post"; 
    fm.submit();
     
     
     
     
     return;  
  
  
  }
  
  
  </script>
 </HEAD>

 <BODY>
<table border="0" width="1000px" align="center">
			<tr style ="text-align:center; font-size:35px;">
				<td><b>|&nbsp; 회원가입</b></td>
			</tr>
			<tr><td style="border-bottom:1px solid; height:1px;" colspan="3"></td></tr>
            <tr>
            <td>
<form name="frm" > 
 <table class ="table" width="100%" style="border-collapse: collapse;">
<tr>
<td>
<label><b>ID : </b><input type="text" name="memberId" size="30" placeholder="아이디" id="input_id"><font id="checkId" size="2"></font></label><br>
</td>
</tr>
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
<label><b>이름 : </b><input type="text" name="memberName" size="30" placeholder="이름"></label><br>
</td>
</tr>
<tr>
<td>
<label><b>닉네임 : </b><input type="text" name="memberNickName" size="30" placeholder="활동할 닉네임"></label><br>
</td>
</tr>
<tr>
<td>
<label><b>이메일 : </b><input type="email" name="memberEmail" size="30" placeholder="이메일"></label><br>
</td>
</tr>
<tr>
<td>
<label><b>성별 : </b><input type="radio" name ="memberGender" value="M" checked>남자
<input type="radio" name ="memberGender" value="F">여자</label><br>
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
<label><b>전화번호 : </b><input type="text" name="memberphone" size="30" placeholder="전화번호"></label><br>
</td>
</tr>
<tr>
<td>
<label><b>주민등록번호 : </b><input type="number" name="memberJumin" size="30" placeholder="주민등록번호"></label><br>
</td>
</tr>
<tr>
<td>
<label><b>취미 : </b><input type="checkbox" name ="memberHobby" value="야구" checked>야구
<input type="checkbox" name ="memberHobby" value="축구">축구
<input type="checkbox" name ="memberHobby" value="농구">농구<br></label>
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
 </table>
 </form>
 </BODY>
</HTML>
