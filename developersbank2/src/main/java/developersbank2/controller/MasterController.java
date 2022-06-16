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
import developersbank2.domain.DonationVo;
import developersbank2.domain.MemberVo;
import developersbank2.domain.PageMaker;
import developersbank2.domain.SearchCriteria;
import developersbank2.service.BoardDao;
import developersbank2.service.CommunityDao;
import developersbank2.service.DonationDao;
import developersbank2.service.MemberDao;


@WebServlet("/MasterController")
public class MasterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);
		
		String pj = request.getContextPath();    //getpath : 프로젝트이름
		String command = uri.substring(pj.length());
		System.out.println("command:"+command);
		
		
		
		if(command.equals("/master/masterModemember.do")) {
			
			String page = request.getParameter("page");
			if(page ==null) page ="1";
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			System.out.println("keyword"+keyword);
			if(keyword ==null) keyword ="";
			
			String searchType = request.getParameter("searchType");
			if (searchType ==null) searchType = "midx";
			
			SearchCriteria scri = new SearchCriteria();
			 scri.setPage(pagex);
			 scri.setSearchType(searchType);
			 scri.setKeyword(keyword);
			 PageMaker pm = new PageMaker();			
			 MemberDao md = new MemberDao();
			 
			 int cnt = md.memberTotal(scri);
			 pm.setScri(scri);
			 pm.setTotalCount(cnt);
			 System.out.println("pm"+pm);	
			 ArrayList<MemberVo> alist = md.memberSelectAll(scri);
			request.setAttribute("alist", alist);	
			request.setAttribute("pm", pm);
			
		RequestDispatcher rd = request.getRequestDispatcher("/master/mastermodemember.jsp");
		rd.forward(request, response);	
		}
            if(command.equals("/master/masterModeboard.do")) {
			
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
			 ArrayList<BoardVo> alist = bd.boardSelectAll(scri);
			 System.out.println("alist"+alist);
			request.setAttribute("alist", alist);	
			request.setAttribute("pm", pm);
			
		RequestDispatcher rd = request.getRequestDispatcher("/master/mastermodeboard.jsp");
		rd.forward(request, response);	
		}
            if(command.equals("/master/masterModecommunity.do")) {
    			
    			String page = request.getParameter("page");
    			if(page ==null) page ="1";
    			int pagex = Integer.parseInt(page);
    			
    			String keyword = request.getParameter("keyword");
    			if(keyword ==null) keyword ="";
    			
    			String searchType = request.getParameter("searchType");
    			if (searchType ==null) searchType = "c_subject";
    			
    			SearchCriteria scri = new SearchCriteria();
    			scri.setPage(pagex);
    			scri.setSearchType(searchType);
    			scri.setKeyword(keyword);
    			PageMaker pm = new PageMaker();			
    			CommunityDao cd = new CommunityDao();
    			 
    			int cnt = cd.communityTotal(scri);
    			pm.setScri(scri);
    			pm.setTotalCount(cnt);	
    			ArrayList<CommunityVo> alist = cd.communitySelectAll(scri);
    			System.out.println("alist"+alist);
    			request.setAttribute("alist", alist);	
    			request.setAttribute("pm", pm);
    			
    		RequestDispatcher rd = request.getRequestDispatcher("/master/mastermodecommunity.jsp");
    		rd.forward(request, response);	
    		}    
            if(command.equals("/master/masterModememberdelete.do")) {
            	System.out.println("회원삭제 화면으로 들어왔음");
    			System.out.println(request.getQueryString());
    			String midx = request.getParameter("midx");
    		    System.out.println("midx"+midx);
    			 int midx_ = Integer.parseInt(midx);
    			 
    			 HttpSession session = request.getSession();
    			 if(session.getAttribute("midx") ==null){
    					session.setAttribute("saveUrl", request.getRequestURI()+"?"+request.getQueryString());   // 글쓰기 누르면 로그인으로 유도 되는데 로그인 후 글쓰기 페이지로 다시 불러 오기위해 url을 세션에 저장
    			 }	
    			
    			 MemberDao md = new MemberDao();
    			 MemberVo mv = md.MemberDeleteOne(midx_);
    			 request.setAttribute("mv", mv);
    			
    			
    			//setattribute를 통해 controller가 객체 생성 및 데이터를 담는 과정을 모두 담당
    			
    			RequestDispatcher rd = request.getRequestDispatcher("/master/mastermodememberdelete.jsp");
    			rd.forward(request, response);
            	
            }    
            else if(command.equals("/master/memberDeleteAction.do")) {
    			System.out.println("회원삭제 처리 화면으로 들어왔음");
    			
    		    String midx = request.getParameter("midx");
    		    System.out.println("midx"+midx);
    			 int midx_ = Integer.parseInt(midx);
    	
    		    System.out.println(midx_);
    		
    		    String ip = InetAddress.getLocalHost().getHostAddress();   
    		    MemberDao md = new MemberDao();
    		    int value = md.MasterDelete(midx_);

    		    System.out.println("value"+value);	
    	      	if (value ==1){
    				    	response.sendRedirect(request.getContextPath()+"/master/masterModemember.do");
    				   
    	        }else{
    	        	response.sendRedirect(request.getContextPath()+"/master/masterModememberdelete.do");
    	        }
    				  
    				    
    		  
    		 
    		}
               if(command.equals("/master/masterModedonation.do")) {
    			
    			String page = request.getParameter("page");
    			if(page ==null) page ="1";
    			int pagex = Integer.parseInt(page);
    			
    			String keyword = request.getParameter("keyword");
    			if(keyword ==null) keyword ="";
    			
    			String searchType = request.getParameter("searchType");
    			if (searchType ==null) searchType = "memberid";
    			
    			SearchCriteria scri = new SearchCriteria();
    			 scri.setPage(pagex);
    			 scri.setSearchType(searchType);
    			 scri.setKeyword(keyword);
    			 PageMaker pm = new PageMaker();			
    			 DonationDao dd = new DonationDao();
    			 
    			 int cnt = dd.masterTotal(scri);
    			 pm.setScri(scri);
    			 pm.setTotalCount(cnt);	
    			 ArrayList<DonationVo> alist = dd.masterSelectAll(scri);
    			 System.out.println("alist"+alist);
    			request.setAttribute("alist", alist);	
    			request.setAttribute("pm", pm);
    			
    		RequestDispatcher rd = request.getRequestDispatcher("/master/mastermodedonation.jsp");
    		rd.forward(request, response);	
    		}
            
            
            
            
            
            
            
}
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
