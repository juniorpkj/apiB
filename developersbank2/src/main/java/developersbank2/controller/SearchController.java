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
import developersbank2.domain.CommunityVo;
import developersbank2.domain.MemberVo;
import developersbank2.domain.PageMaker;
import developersbank2.domain.SearchCriteria;
import developersbank2.domain.SearchVo;
import developersbank2.service.BoardDao;
import developersbank2.service.CommunityDao;
import developersbank2.service.MemberDao;
import developersbank2.service.SearchDao;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
	
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);
		
		String pj = request.getContextPath();    
		String command = uri.substring(pj.length());
		System.out.println("command:"+command);
	    
		
	       if(command.equals("/search/searchlistAction.do")) {

				String page = request.getParameter("page");
				if(page ==null) page ="1";
				int pagex = Integer.parseInt(page);
				
				String keyword = request.getParameter("keyword");
				if(keyword ==null) keyword ="";
				System.out.println("keyword"+keyword);
				SearchCriteria scri = new SearchCriteria();
				 scri.setPage(pagex);
				 scri.setKeyword(keyword);
				 
				 PageMaker pm = new PageMaker();
				 SearchDao sd = new SearchDao();
					
				 int cnt = sd.searchTotal(scri);
				 pm.setScri(scri);
				 pm.setTotalCount(cnt);
	    	   
	    	   	
			   ArrayList<SearchVo> alist = sd.searchSelectAll(scri);
               request.setAttribute("alist", alist);
			   request.setAttribute("pm", pm);

			RequestDispatcher rd = request.getRequestDispatcher("/search/searchlist.jsp");
			rd.forward(request, response);
			
			
			
		}if(command.equals("/search/searchlist.do")) {
	       
			String page = request.getParameter("page");
			if(page ==null) page ="1";
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if(keyword ==null) keyword ="";
			System.out.println("keyword"+keyword);
			SearchCriteria scri = new SearchCriteria();
			 scri.setPage(pagex);

			 scri.setKeyword(keyword);
			 PageMaker pm = new PageMaker();
			 SearchDao sd = new SearchDao();
				
			 int cnt = sd.searchTotal(scri);
			 pm.setScri(scri);
			 pm.setTotalCount(cnt);
    	   
    	   	
		   ArrayList<SearchVo> alist = sd.searchSelectAll(scri);
           request.setAttribute("alist", alist);
		   request.setAttribute("pm", pm);
	  
			
			RequestDispatcher rd = request.getRequestDispatcher("/search/searchlist.jsp");
			rd.forward(request, response);
			
		}
		    if(command.equals("/search/searchliststudy.do")) {
		       
			String page = request.getParameter("page");
			if(page ==null) page ="1";
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if(keyword ==null) keyword ="";
			System.out.println("keyword"+keyword);
			SearchCriteria scri = new SearchCriteria();
			 scri.setPage(pagex);

			 scri.setKeyword(keyword);
			 PageMaker pm = new PageMaker();
			 SearchDao sd = new SearchDao();
				
			 int cnt = sd.searchTotal(scri);
			 pm.setScri(scri);
			 pm.setTotalCount(cnt);
    	   
    	   	
		   ArrayList<SearchVo> alist = sd.searchSelectAll(scri);
           request.setAttribute("alist", alist);
		   request.setAttribute("pm", pm);
	  
			
			RequestDispatcher rd = request.getRequestDispatcher("/search/searchlist.jsp");
			rd.forward(request, response);
		
		    }
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
