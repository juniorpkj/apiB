package developersbank2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import developersbank2.domain.BoardVo;
import developersbank2.domain.CommunityVo;
import developersbank2.domain.MemberVo;
import developersbank2.domain.PageMaker;
import developersbank2.domain.SearchCriteria;
import developersbank2.service.BoardDao;
import developersbank2.service.CommunityDao;
import developersbank2.service.MemberDao;


@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);
		
		String pj = request.getContextPath();    //getpath : 프로젝트이름
		String command = uri.substring(pj.length());
		System.out.println("command:"+command);
		
		
		
		if(command.equals("/member/memberJoinAction.do")) {
			String memberId = request.getParameter("memberId");
		    String memberPwd = request.getParameter("memberPwd");
		    String memberName = request.getParameter("memberName");
		    String memberphone = request.getParameter("memberphone");
		    String memberEmail = request.getParameter("memberEmail");
		    String memberJumin = request.getParameter("memberJumin");
		    String memberGender = request.getParameter("memberGender");
		    String memberAddr = request.getParameter("memberAddr");
		    String[] memberHobby = request.getParameterValues("memberHobby");
		    String membernickname = request.getParameter("memberNickName");
		    
		  //getParameterValues : 중복값을 담을 수 있는 툴
		    String hobby = "";
		    for(int i=0; i<memberHobby.length;i++){
		    	hobby = hobby +","+ memberHobby[i];
		    //	out.println(memberHobby[i]+"<br>");;
		    
		    }
		    hobby = hobby.substring(1);
		    
//		    out.println(memberId+"<br>");
//		    out.println(memberPwd+"<br>");
		//   out.println(memberName+"<br>");
//		    out.println(memberphone+"<br>");
//		    out.println(memberEmail+"<br>");
//		    out.println(memberJumin+"<br>");
//		    out.println(memberGender+"<br>");
//		    out.println(memberAddr+"<br>");

		    String ip = InetAddress.getLocalHost().getHostAddress();   //회원 ip뽑아오기
		    MemberDao md = new MemberDao();
		    int value = md.insertMember(memberId,memberPwd,memberName,memberphone,memberEmail,memberJumin,memberGender,memberAddr,hobby,ip,membernickname);
		    System.out.println("value"+value);
		 // out.println(value);
		    // html은 보내는 기능말고 받는 기능이 없으므로 위에서 작업을 통해 받는 기능을 만든다. 
		    // getparameter는 받은 자료를 출력하는 메소드이다.   
		    // html은 String타입만 넘길수 있다.
		        //입력이 되었으면			
		    
		    if (value ==1){
		    	response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
		    	 //	out.println("<script> alert('회원가입성공');location.href='"+request.getContextPath()+"/' </script>");
	    		//index로 가는 이유는 서버에 기본적으로 index가 welcome페이지에 등록 되어 있어 따로 지정 하지 않아도 연결된다. 만약 다른 페이지로 이동하고 싶다면 / 뒤에 페이지를 쓰거나 서버 웰컴페이지에 그 페이지를 등록하여야 한다.
	        //자바에서 서버 이동 방식은 forward이고 update delete같은 처리해야하는 방식이 끝났을 때는 sendRedirect방식으로 새로 이동한다. (보안상 이유)
		    }else{
		    	response.sendRedirect(request.getContextPath()+"/member/memberJoin.do");
		    	 //	out.println("<script> alert('회원가입실패');location.href='"+request.getContextPath()+"/' </script>");
		    }
		 
		}else if (command.equals("/member/memberJoin.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberJoin.jsp");
			rd.forward(request, response);
		
			
			
		}else if (command.equals("/member/idcheck.do")) {
			String userId = request.getParameter("userId");
			System.out.println("userId"+userId);
			//memberJoin.jsp에서 받아온 key값이 userId
		    //value값은 유저가 실제로 적은 값, String userId에는 value값이 들어간다.
			
			PrintWriter out = response.getWriter();
			MemberDao md = new MemberDao();
			int idCheck = md.checkId(userId);
			
			//성공여부 확인 : 개발자용
			if(idCheck ==0) {
				System.out.println("이미 존재하는 아이디입니다.");
				
			}else if(idCheck==1){
				System.out.println("사용 가능한 아이디입니다.");
			}
			 out.write(idCheck+""); 	//ajax결과값인 result가 됨,  +는 연결연산자
			                            //String으로 값을 내보낼수 있도록 +""를 해준다.
			
		
			
			
		}else if (command.equals("/member/memberLoginAction.do")) {
			System.out.println("로그인처리화면에 들어옴");
			//1. 넘어온값
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			
			//2. 처리
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberLogin(memberId, memberPwd);
			System.out.println("mv"+mv);
			HttpSession session = request.getSession();
			
			
			//3. 이동
	        if(mv!=null) {
	        	session.setAttribute("midx", mv.getMidx());
	        	session.setAttribute("memberId", mv.getMemberid());
	        	session.setAttribute("membernickname", mv.getMembernickname());
	        	
	        	
	        	if(session.getAttribute("saveUrl") != null){
	        	   response.sendRedirect((String)session.getAttribute("saveUrl"));
	        	}else {
	        		response.sendRedirect(request.getContextPath()+"/");
	        	}
	        	
	        }else {
	        response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");	
	        }
			
		}else if (command.equals("/member/memberLogin.do")) {
			System.out.println("로그인화면에 들어옴");
			
		

			RequestDispatcher rd = request.getRequestDispatcher("/member/memberLogin.jsp");
			rd.forward(request, response);
			
			
		}else if (command.equals("/member/memberLogOut.do")) {
			HttpSession session = request.getSession();
			session.invalidate();  //세션 초기화
			response.sendRedirect(request.getContextPath()+"/");
			// welcome 페이지로 서버에 기본 등록 되어있기 때문에 생략되어도 index로 간다
		
	
		}else if(command.equals("/member/memberMyPage.do")) {
			
			HttpSession session = request.getSession();
		//    
		//	1. 페이지에 내 개인정보 확인 및 수정 부분(boardcontent부분 참고)
			 
			 if (session.getAttribute("midx") == null) {
				 session.setAttribute("saveUrl", uri);
				 response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
			  }else {
				 
				 int midx = (int)session.getAttribute("midx");		 
				 MemberDao md = new MemberDao()	;
				 MemberVo mv = md.memberSelectOne(midx);
				 System.out.println("mv"+mv);
				 request.setAttribute("mv", mv); //내부에 같은 위치에서 자원을 공유한다   "객체명", 객체
	  
				RequestDispatcher rd = request.getRequestDispatcher("/member/memberMyPage.jsp");
				rd.forward(request, response);
			  }
			
		}else if(command.equals("/member/memberMyPage2.do")) {
			
			HttpSession session = request.getSession();
		//    
		//	1. 페이지에 내 개인정보 확인 및 수정 부분(boardcontent부분 참고)
			 
			 if (session.getAttribute("midx") == null) {
				 session.setAttribute("saveUrl", uri);
				 response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
			  }else {
				 
				 int midx = (int)session.getAttribute("midx");
		//   1. Study		 
				 String page = request.getParameter("page");
					if(page ==null) page ="1";
					int pagex = Integer.parseInt(page);
					
					String keyword = request.getParameter("keyword");
					if(keyword ==null) keyword ="";
					
					String searchType = request.getParameter("searchType");
					if (searchType ==null) searchType = "subject";
					
					SearchCriteria scri = new SearchCriteria();
					 scri.setPage(pagex);
					 scri.setSearchType(searchType);
					 scri.setKeyword(keyword);
					 PageMaker pm = new PageMaker();
					
					 BoardDao bd = new BoardDao();	
					 int cnt = bd.boardTotal(scri);
					 
					 pm.setScri(scri);
					 pm.setTotalCount(cnt);
					 
					 ArrayList<BoardVo> alist = bd.MyboardSelectAll(scri,midx);
					
					 request.setAttribute("alist", alist);
					request.setAttribute("pm", pm);
	
				RequestDispatcher rd = request.getRequestDispatcher("/member/memberMyPage2.jsp");
				rd.forward(request, response);
			  }
			
		}else if(command.equals("/member/memberMyPage3.do")) {
			
			HttpSession session = request.getSession();
		//    
		//	1. 페이지에 내 개인정보 확인 및 수정 부분(boardcontent부분 참고)
			 
			 if (session.getAttribute("midx") == null) {
				 session.setAttribute("saveUrl", uri);
				 response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
			  }else {
				 
				 int midx = (int)session.getAttribute("midx");		 
				 String page = request.getParameter("page");
					if(page ==null) page ="1";
					int pagex = Integer.parseInt(page);
					
					String keyword = request.getParameter("keyword");
					if(keyword ==null) keyword ="";
					
					String searchType = request.getParameter("searchType");
					if (searchType ==null) searchType = "subject";
					
					SearchCriteria scri = new SearchCriteria();
					 scri.setPage(pagex);
					 scri.setSearchType(searchType);
					 scri.setKeyword(keyword);
					 PageMaker pm = new PageMaker();
	 	
					CommunityDao cd = new CommunityDao();	
					 int cnt = cd.communityTotal(scri);
					 
					 pm.setScri(scri);
					 pm.setTotalCount(cnt);
					 
					 ArrayList<CommunityVo> alist = cd.MycommunitySelectAll(scri,midx);
					request.setAttribute("alist", alist);
					request.setAttribute("pm", pm);
				
			
				RequestDispatcher rd = request.getRequestDispatcher("/member/memberMyPage3.jsp");
				rd.forward(request, response);
			  }
			
		}else if(command.equals("/member/memberModifyAction.do")) {
			System.out.println("회원정보수정 처리 화면으로 들어왔음");
			
			String memberpwd = request.getParameter("memberPwd");
			String memberemail = request.getParameter("memberEmail");
			String memberaddr = request.getParameter("memberAddr");
			System.out.println(memberpwd);
			int memberpwd_ = Integer.parseInt(memberpwd);			
			System.out.println(memberpwd_+";"+memberemail+";"+memberaddr);
		
		    
		    HttpSession session = request.getSession();
		    int midx = (int)session.getAttribute("midx");
		    
		
		      
		    MemberDao md = new MemberDao();
		    int value = md.MemberModify(memberpwd_, memberemail, memberaddr, midx);

		    		System.out.println("value"+value);	
	      	if (value ==1){
	      		
	      	
				session.invalidate();  //세션 초기화
				response.sendRedirect(request.getContextPath()+"/");
				    	
				   
	        }else{
	        	response.sendRedirect(request.getContextPath()+"/member/memberMyPage.do");
	        }
				  
				    
		  
		 
		}else if(command.equals("/member/memberDeleteAction.do")) {
			System.out.println("회원탈퇴 처리 화면으로 들어왔음");
			
			HttpSession session = request.getSession();
		    int midx = (int)session.getAttribute("midx");
		    	    
		
		    String ip = InetAddress.getLocalHost().getHostAddress();   
		    MemberDao md = new MemberDao();
		    int value = md.MemberDelete(midx);

		    		System.out.println("value"+value);	
	      	if (value ==1){
	      		        session.invalidate();
				    	response.sendRedirect(request.getContextPath()+"/home.jsp");
				   
	        }else{
	        	response.sendRedirect(request.getContextPath()+"/member/memberMyPage.do");
	        }
				  
				    
		  
		 
		}
			
		
}
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
