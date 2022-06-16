<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
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
	
	nav
   	{  width : 210px;
	   height : 100%;
	
	}
	
	nav > table
	{ border-top : 1px solid black;
	  width:300px;
	  height:100%;
	  text-align : center;
	  font-size : 20px;
	  float : left;
	   
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
	  height:800px;
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
<caption><b>이용안내</b></caption>
<tr><td>
<ul>
  <li><a href = "<%=request.getContextPath() %>/guidance/siteObject.jsp">사이트 목적</a></li>
  <li><a href = "<%=request.getContextPath() %>/guidance/terms.jsp">약관</a></li>
  <li><a href = "<%=request.getContextPath() %>/guidance/donationInfo.jsp">Donation이용안내</a></li>
</ul>
</td></tr>
</table>
</nav>
<main>
<table>
<caption style="text-align:left;"><b>약관</b></caption>
<tr><td style="vertical-align : top">
<pre>
제1장 총칙

 제1조(목적) 이 약관은 회사가 온라인으로 제공하는 디지털콘텐츠(이하 "콘텐츠"라고 한다) 및 제반서비스의 이용과 관련하여 회사와
           이용자와의 권리, 의무 및 책임사항 등을 규정함을 목적으로 합니다.
            
 제2조(정의) 이 약관에서 사용하는 용어의 정의는 다음과 같습니다.   
1. "회사"라 함은 "콘텐츠" 산업과 관련된 경제활동을 영위하는 자로서 콘텐츠 및 제반서비스를 제공하는 자를 말합니다. 

2. "이용자"라 함은 "회사"의 사이트에 접속하여 이 약관에 따라 "회사"가 제공하는 "콘텐츠" 및 제반서비스를 이용하는 회원 및 비회원을 말합니다. 

</pre>
</td></tr>  
</table>
</main>


</body>
</html>