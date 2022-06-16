package developersbank2.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import developersbank2.domain.BoardVo;
import developersbank2.domain.DonationVo;
import developersbank2.domain.MemberVo;
import developersbank2.domain.PageMaker;
import developersbank2.domain.SearchCriteria;
import developersbank2.service.BoardDao;
import developersbank2.service.DonationDao;
import developersbank2.service.MemberDao;

@WebServlet("/DonationController")
public class DonationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		//가상경로로 온 request가 있으면 처리
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);
		
		String pj = request.getContextPath();    
		String command = uri.substring(pj.length());
		System.out.println("command:"+command);
		
		
	   if(command.equals("/donation/donationList.do")) {
		
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
  
			RequestDispatcher rd = request.getRequestDispatcher("/donation/donationList.jsp");
			rd.forward(request, response);
		  }
	
	   }else if(command.equals("/donation/donationAction.do")) {
		   HttpSession session = request.getSession();
		   if (session.getAttribute("midx") == null) {
				 session.setAttribute("saveUrl", uri);
				 response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
			  }else {
				 String ip = InetAddress.getLocalHost().getHostAddress();
				 int midx = (int)session.getAttribute("midx");	
				 String memberpoint = request.getParameter("memberpoint");
				 int memberpoint_=Integer.parseInt(memberpoint);
				 System.out.println("memberpoiont_"+memberpoint_);
				 String membernickname = (String) session.getAttribute("membernickname");
				 System.out.println("membernickname"+membernickname);
				 DonationDao dd = new DonationDao();
				 int value = dd.donationpoint(midx,memberpoint_);
				 System.out.println("value"+value);
	             int value2 = dd.insertdonation(midx,memberpoint_,ip,membernickname);
	             System.out.println("value2"+value2);
	             if (value ==1&&value2==1){
	 		    	response.sendRedirect(request.getContextPath()+"/home.jsp");
	 		    
	 		    }else{
	 		    	response.sendRedirect(request.getContextPath()+"/donation/donationList.do");
	 		  
	 	       }
			  }
	         }else if(command.equals("/donation/donationList2.do")) {
	      		
	      		HttpSession session = request.getSession();
	      	//    
	      	//	1. 페이지에 내 개인정보 확인 및 수정 부분(boardcontent부분 참고)
	      		 
	      		 if (session.getAttribute("midx") == null) {
	      			 session.setAttribute("saveUrl", uri);
	      			 response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
	      		  }else {
	      			 int midx = (int)session.getAttribute("midx");		 
	      			 DonationDao dd = new DonationDao();

	   			   ArrayList<DonationVo> alist = dd.donationSelectOne(midx);
	   			   request.setAttribute("alist", alist);
				    //내부에 같은 위치에서 자원을 공유한다   "객체명", 객체
	        
	      			RequestDispatcher rd = request.getRequestDispatcher("/donation/donationList2.jsp");
	      			rd.forward(request, response);
	      		  }
	      	
	      	   }    
		  }
	   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
