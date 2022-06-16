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

import developersbank2.domain.CommunityVo;
import developersbank2.domain.MemberVo;
import developersbank2.domain.PageMaker;
import developersbank2.domain.SearchCriteria;
import developersbank2.service.CommunityDao;
import developersbank2.service.MemberDao;

@WebServlet("/CommunityController")
public class CommunityController extends HttpServlet {
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
	    
		int sizeLimit = 1024*1024*15;  //15mb
		String uploadPath = "D:\\workspace_jquery\\developersbank2\\src\\main\\webapp\\"; 
		String saveFolder = "images";
		String saveFullPath = uploadPath+saveFolder;
		
		if(command.equals("/community/communityWriteAction.do")) {
			System.out.println("글쓰기 처리 화면으로 들어왔음");
			
			MultipartRequest multipartRequest =null;
			multipartRequest = new MultipartRequest(request,saveFullPath,sizeLimit,"utf-8",new DefaultFileRenamePolicy());
			
			//DefaultFileRenamePolicy : 이름이 겹쳤을때 기본값을 쓰겠다는의미
			
			
			String c_subject = multipartRequest.getParameter("c_subject");
		    String c_content = multipartRequest.getParameter("c_content");
		    String c_writer = multipartRequest.getParameter("c_writer");
		    String category_ = multipartRequest.getParameter("category_");
		    
		    //열거자인 저장될 파일을 담는 객체생성
		    Enumeration files = multipartRequest.getFileNames();
		    //담긴 객체의 파일 이름을 얻는다
		    String file = (String)files.nextElement();
		    //넘어오는 객체 중에 해당되는 파일 이름으로 되어있는 파일이름을 추출한다(저장되는 파일 이름)
		    String fileName = multipartRequest.getFilesystemName(file);
		    //원본의 파일이름
		    String originFileName = multipartRequest.getOriginalFileName(fileName);
		    
		    
		    //int midx = 2;
            HttpSession session = request.getSession();
		    int midx = (int)session.getAttribute("midx");
		    //System.out.println(c_subject+";"+c_content+";"+c_writer);
		
		    String ip = InetAddress.getLocalHost().getHostAddress();   
		    CommunityDao cd = new CommunityDao();
		    int value = cd.insertcommunity(c_subject,c_content,c_writer,ip,midx,fileName,category_);
            int value2 = cd.communitypoint(midx);
		    int value3 = cd.autolevel(midx);			
		    if (value ==1&&value2==1&&value3==1){
		    	response.sendRedirect(request.getContextPath()+"/home.jsp");
		    
		    }else{
		    	response.sendRedirect(request.getContextPath()+"/community/communityWrite.do");
		  
		    }
		 
		}else if (command.equals("/community/communityWrite.do")) {
			System.out.println("글쓰기 화면에 들어왔음");
			RequestDispatcher rd = request.getRequestDispatcher("/community/communityWrite.jsp");
			rd.forward(request, response);
		
		
		}else if(command.equals("/community/communityList.do")) {
			
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
			request.setAttribute("alist", alist);
			request.setAttribute("pm", pm);
			
			
			//setattribute를 통해 controller가 객체 생성 및 데이터를 담는 과정을 모두 담당
			
			RequestDispatcher rd = request.getRequestDispatcher("/community/communityList.jsp");
			rd.forward(request, response);
			//요청에 대한 응답을 누가할지 선택할 수 있습니다. 요청을 완전히 다른 URL로 방향을 바꿀 수 있으며(이를 리다이렉트(redirect)라고 함), 아니면 웹 애플리케이션에 있는 다른 컴포넌트(보통 JSP)에게 처리를 위임할 수도(이를 디스패치(dispatch)라고 함) 있습니다.
		    // redirect는 내부와 외부의 연결이고 forward는 서버 내에서의 연결로 forward는 클라이언트 입장에서 연결이 되었는지 알수가 없다.
			
			
		}else if(command.equals("/community/communitycontent.do")) {
			 //넘어온 값을 받는다
			 String cidx = request.getParameter("cidx");
			 int cidx_ = Integer.parseInt(cidx);
			 System.out.println("cidx_"+cidx_);
			 
			 //처리한다
			 CommunityDao cd = new CommunityDao();
			 CommunityVo cv = cd.communitySelectOne(cidx_);
			 System.out.println("cv"+cv);
			 request.setAttribute("cv", cv); //내부에 같은 위치에서 자원을 공유한다   "객체명", 객체
			 //이동한다
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/community/communitycontent.jsp");
			rd.forward(request, response);
			
			
		}else if(command.equals("/community/communityModifyAction.do")) {
			System.out.println("글수정 처리 화면으로 들어왔음");
			MultipartRequest multipartRequest =null;
			multipartRequest = new MultipartRequest(request,saveFullPath,sizeLimit,"utf-8",new DefaultFileRenamePolicy());
			
			
			
			
			String c_subject = multipartRequest.getParameter("c_subject");
		    String c_content = multipartRequest.getParameter("c_content");
		    String c_writer = multipartRequest.getParameter("c_writer");
		    String tag = multipartRequest.getParameter("tag");
		    String cidx = multipartRequest.getParameter("cidx");
		
		    Enumeration files = multipartRequest.getFileNames();
		   
		    String file = (String)files.nextElement();
		    
		    String fileName = multipartRequest.getFilesystemName(file);
		    
		    String originFileName = multipartRequest.getOriginalFileName(fileName);
		    System.out.println("cidx"+cidx);
			 int cidx_ = Integer.parseInt(cidx);
		    //int midx = 2;
		    HttpSession session = request.getSession();
		    int midx = (int)session.getAttribute("midx");
		    
		    System.out.println(c_subject+";"+c_content+";"+c_writer+";"+cidx_);
		
		    String ip = InetAddress.getLocalHost().getHostAddress();   
		    CommunityDao cd = new CommunityDao();
		    int value = cd.communityModify(c_subject, c_content, c_writer, ip, midx, cidx_,fileName);

		    		System.out.println("value"+value);	
	      	if (value ==1){
				    	response.sendRedirect(request.getContextPath()+"/home.jsp");
				   
	        }else{
	        	response.sendRedirect(request.getContextPath()+"/community/communityModify.do");
	        }
				  
				    
		  
		 
		}else if(command.equals("/community/communityModify.do")) {
		    System.out.println("글수정 화면에 들어왔음");
		    String cidx = request.getParameter("cidx");
		    System.out.println("cidx"+cidx);
			 int cidx_ = Integer.parseInt(cidx);
			 
			 
			 
			 CommunityDao cd = new CommunityDao();
			 CommunityVo cv = cd.communitySelectOne(cidx_);
			 request.setAttribute("cv", cv);
		
		
			
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/community/communityModify.jsp");
			rd.forward(request, response);
		
		
		}else if(command.equals("/community/communityDeleteAction.do")) {
			System.out.println("글삭제 처리 화면으로 들어왔음");
			
		    String cidx = request.getParameter("cidx");
		    System.out.println("cidx"+cidx);
			 int cidx_ = Integer.parseInt(cidx);
		    

		    
		    System.out.println(cidx_);
		
		    String ip = InetAddress.getLocalHost().getHostAddress();   
		    CommunityDao cd = new CommunityDao();
		    int value = cd.communityDelete(cidx_);

		    		System.out.println("value"+value);	
	      	if (value ==1){
				    	response.sendRedirect(request.getContextPath()+"/home.jsp");
				   
	        }else{
	        	response.sendRedirect(request.getContextPath()+"/community/communityDelete.do");
	        }
				  
				    
		  
		 
		}else if(command.equals("/community/communityDelete.do")) {
			System.out.println("글삭제 화면으로 들어왔음");
			String cidx = request.getParameter("cidx");
		    System.out.println("cidx"+cidx);
			 int cidx_ = Integer.parseInt(cidx);
			
			 CommunityDao cd = new CommunityDao();
			 CommunityVo cv = cd.communitySelectOne(cidx_);
			 request.setAttribute("cv", cv);
			
			
			//setattribute를 통해 controller가 객체 생성 및 데이터를 담는 과정을 모두 담당
			
			RequestDispatcher rd = request.getRequestDispatcher("/community/communityDelete.jsp");
			rd.forward(request, response);
		
		
		
	}if(command.equals("/community/communityReplyAction.do")) {
		System.out.println("답변 처리 화면으로 들어왔음");
		String c_subject = request.getParameter("c_subject");
	    String c_content = request.getParameter("c_content");
	    String c_writer = request.getParameter("c_writer");
	    String cidx = request.getParameter("cidx");
	    String origincidx = request.getParameter("origincidx");
	    String c_depth = request.getParameter("c_depth");
	    String c_level_= request.getParameter("c_level_");
	    String category_ = request.getParameter("category_");
	    
	    System.out.println(c_subject+";"+c_content+";"+c_writer+";"+origincidx+";"+c_depth+";"+c_level_+";"+category_);
	
	    String ip = InetAddress.getLocalHost().getHostAddress();  
	    //int midx = 2;
	    HttpSession session = request.getSession();
	    int midx = (int)session.getAttribute("midx");
	    CommunityDao cd = new CommunityDao();
	    CommunityVo cv = new CommunityVo();
	    cv.setC_subject(c_subject);
	    cv.setC_content(c_content);
	    cv.setC_writer(c_writer);
	    cv.setIp(ip);
	    cv.setCidx(Integer.parseInt(cidx));
		cv.setOrigincidx(Integer.parseInt(origincidx));
		cv.setC_depth(Integer.parseInt(c_depth));
		cv.setC_level_(Integer.parseInt(c_level_));
	    cv.setMidx(midx);
	    cv.setCategory_(category_);
	    int value = cd.communityReply(cv);
        int value2 = cd.communityreplypoint(midx);	
        int value3 = cd.autolevel(midx);
        if (value ==1&&value2==1&&value3==1){
	    	response.sendRedirect(request.getContextPath()+"/community/communityList.do");
	   
	    }else{
	    	response.sendRedirect(request.getContextPath()+"/community/communitycontent.do?cidx="+cidx);
	  
	    }
	 
	}else if (command.equals("/community/communityReply.do")) {
		System.out.println("글답변 화면에 들어왔음");
		
		String cidx = request.getParameter("cidx");
		String origincidx = request.getParameter("origincidx");
		String c_depth = request.getParameter("c_depth");
		String c_level_ = request.getParameter("c_level_");
	    String c_subject = request.getParameter("c_subject");
		String c_content = request.getParameter("c_content");
		String category_ = request.getParameter("category_");
		CommunityVo cv = new CommunityVo();
		cv.setCidx(Integer.parseInt(cidx));
		cv.setOrigincidx(Integer.parseInt(origincidx));
		cv.setC_depth(Integer.parseInt(c_depth));
		cv.setC_level_(Integer.parseInt(c_level_));
		cv.setC_subject(c_subject);
		cv.setC_content(c_content);
		cv.setCategory_(category_);
		
		
		 
		request.setAttribute("cv", cv);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/community/communityReply.jsp");
		rd.forward(request, response);
	
	
	}else if(command.equals("/community/fileDownload.do")) {
	  
	  String filename = request.getParameter("filename");
	  //파일의 전체경로
	  String filePath = saveFullPath+File.separator+filename;	
	  //separator(구분자) : 파일 경로와 파일명을 구분하는 것, 운영체제에 따라 구분자가 달라 file.separator로 표현
	  Path source = Paths.get(filePath);	
	  //파일을 실제로 찾아서 확인
	  String mimeType = Files.probeContentType(source);
	  //post방식과 같이 헤더에 mimeType(파일형식)을 담아서 넘김
	  response.setContentType(mimeType);
	  
	  //파일 이름이 글자가 깨지지 않게 인코딩
	  String encodingFileName = new String(filename.getBytes("utf-8"));
	  //첨부해서 다운로드 되는 파일을 헤더정보에 담는다 c_content-disposition이라는 이름으로 첨부된 파일명을 보낸다.
	  response.setHeader("c_content-Disposition", "attachment;fileName="+encodingFileName);	
	  
	  //파일을 읽어들이고 출력하는 스트림, 해당 위치에 잇는 파일을 읽어들인다.
	  FileInputStream fileInputStream = new FileInputStream(filePath);
	  //파일쓰기위한 스트림(브라우저 출력)
	  ServletOutputStream servletOutStream = response.getOutputStream(); 
	  
	  //4096 / 1024 = 4kb
	  byte[] b = new byte[4096];
	  
	  int read = 0;
	  //4킬로 바이트의 처리 속도로 읽어들여서 반복문을 통해 다시 작성한다. 숫자 0은 배열 0부터를 의미한다.
	  while((read = fileInputStream.read(b, 0, b.length))!=-1) {
		  
		  servletOutStream.write(b, 0, read);
	  }
	  //출력
	  servletOutStream.flush();  //stream에 남아 있을 수 있는 데이터를 모두 처리 한다
	  servletOutStream.close();
	  fileInputStream.close();
	  
	}
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
