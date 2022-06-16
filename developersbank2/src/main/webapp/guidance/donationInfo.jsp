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
<caption style="text-align:left;"><b>Donation이용안내</b></caption>
<tr><td style="vertical-align : top">
<pre>
Donation 이용시 주의사항

1. 제출 이후 취소는 불가능 하오니 주의하시기 바랍니다.

2. 보유 포인트를 확인하시고 입찰하세요. 

</pre>
</td></tr>  
</table>
</main>


</body>
</html>