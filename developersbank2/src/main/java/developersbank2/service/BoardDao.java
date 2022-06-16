package developersbank2.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import developersbank2.dbconn.Dbconn;
import developersbank2.domain.BoardVo;
import developersbank2.domain.Criteria;
import developersbank2.domain.MemberVo;
import developersbank2.domain.SearchCriteria;


public class BoardDao {
	private Connection conn;  
	private PreparedStatement pstmt;
	public BoardDao() {
		
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
		
	}
	
	
	
		public int insertBoard (String subject, String content, String writer, String ip, int Midx,String fileName,String tag){
			int value = 0;
			
		    //level은 예약어로 되어 있어 만들때 level_으로 만들었다
		    String sql = "insert into db_board(subject,content,writer,ip,midx,originbidx,depth,level_,filename,tag) "
		    		+ "select ?,?,?,?,?,max(bidx)+1,0,0,?,? from db_board";
		    try{
		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
		    pstmt.setString(1, subject);
		    pstmt.setString(2, content);
		    pstmt.setString(3, writer);
		    pstmt.setString(4, ip);
		    pstmt.setInt(5, Midx);
		    pstmt.setString(6, fileName);
		    pstmt.setString(7, tag);

		    //last_insert_id() 는 max(bidx)로 대체 가능, 만약 max(bidx)는 값이 삭제 될 경우 큰값이 정상값과 다를 수 있음.
		    value = pstmt.executeUpdate();
		    	
		   

		    }catch(Exception e){
		    	e.printStackTrace();}
		   
			return value;
		}

		public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri){
			
			ArrayList<BoardVo> alist = new ArrayList<BoardVo>();
			
			ResultSet rs = null;
			
			String str="";
			if(scri.getSearchType().equals("subject")) {
				str = "and subject like ?";	
			}else {
				str = "and writer like ?";
				
			}
			
			
			
	//		String sql = "select * from b_board where delyn = 'n' order by originbidx desc, depth asc";
		    String sql = "SELECT * FROM db_board WHERE delyn='n' "+str+" ORDER BY ORIGINBIDx DESC, DEPTH asc limit ?,15";
			try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			pstmt.setInt(2, (scri.getPage()-1)*15);
			
			
			rs =  pstmt.executeQuery();
			
			
			
			
			while(rs.next()){
				
				BoardVo bv = new BoardVo();
				bv.setBidx(rs.getInt("bidx"));
				bv.setSubject(rs.getString("subject"));
				bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setLevel_(rs.getInt("level_"));
				bv.setTag(rs.getString("tag"));
				
				alist.add(bv);
			}
			
			}catch(Exception e){
				e.printStackTrace();
			}finally {
			
				try {
					
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return alist;
			
		}   	
		
		public BoardVo boardSelectOne(int bidx) {
			 BoardVo bv = null;
			 ResultSet rs = null;
			 String sql = "select * from db_board where bidx =?";
			
			 try {
				pstmt =  conn.prepareStatement(sql);   //쿼리화 시킴
				pstmt.setInt(1, bidx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {       //다음값이 존재하면 true 커서는 다음행으로 이동
					bv = new BoardVo();
					bv.setBidx(rs.getInt("bidx"));   //rs에 담겨져있는 데이터를 bv에 담는다
					bv.setOriginbidx(rs.getInt("originbidx"));
					bv.setDepth(rs.getInt("depth"));
					bv.setLevel_(rs.getInt("level_"));
					bv.setSubject(rs.getString("subject"));
					bv.setContent(rs.getString("content"));
					bv.setWriter(rs.getString("writer"));
					bv.setWriteday(rs.getString("writeday"));
					bv.setFilename(rs.getString("filename"));
					bv.setTag(rs.getString("tag"));
					bv.setMidx(rs.getInt("midx"));
					
					
					
				}
				
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				
				
			}
			
			
			return bv;
		}
		
		public int BoardModify (String subject, String content, String writer, String ip, int Midx,int Bidx_,String fileName){
			int value = 0;
			
		    
		    String sql = "update db_board set subject=?,content=?,writer=?,ip=?,filename=?,writeday=now() where bidx =?"; 
		    		
		    try{
		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
		    pstmt.setString(1, subject);
		    pstmt.setString(2, content);
		    pstmt.setString(3, writer);
		    pstmt.setString(4, ip);
            pstmt.setString(5, fileName);
            pstmt.setInt(6, Bidx_);
		    
		    value = pstmt.executeUpdate();
		    	
		   

		    }catch(Exception e){
		    	e.printStackTrace();}
		    finally {
				
				try {
									
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
			return value;
		}
		
		
		//디버깅시 확인할 것 :  쿼리를 그대로 복사해서 db에서 실행해본다.
		
		public int BoardDelete (int Bidx_){
			int value = 0;
			
		    
		    String sql = "update db_board set delyn='y',writeday=now() where bidx=?"; 
		    		
		    try{
		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
		    pstmt.setInt(1, Bidx_);
		    
		    value = pstmt.executeUpdate();
		    	
		   

		    }catch(Exception e){
		    	e.printStackTrace();}
		    finally {
				
				try {
									
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
			return value;
		}
		
		
		

		public int BoardReply (BoardVo bv){
			int value = 0;
			
		    //level은 예약어로 되어 있어 만들때 level_으로 만들었다
		    String sql1 ="update db_board set depth = depth+1 where originbidx = ? and depth > ?";
		    
		    String sql2 ="insert into db_board(subject,content,writer,ip,midx,originbidx,depth,level_,tag)" 
		    		      +"values(?,?,?,?,?,?,?,?,?)";
		    try{
		    conn.setAutoCommit(false);    //오라클 설정이 기본적으로 오토커밋 되어 있는데 이것을 종료시켜 수동으로 바꿈
		    pstmt = conn.prepareStatement(sql1);	
		    pstmt.setInt(1, bv.getOriginbidx());
		    pstmt.setInt(2, bv.getDepth());
		    pstmt.executeUpdate();	    
		    pstmt = conn.prepareStatement(sql2);    //문자열된 쿼리를 연결시켜 구문화함		    
		    pstmt.setString(1, bv.getSubject());
		    pstmt.setString(2, bv.getContent());
		    pstmt.setString(3, bv.getWriter());
		    pstmt.setString(4, bv.getIp());
		    pstmt.setInt(5, bv.getMidx());
		    pstmt.setInt(6, bv.getOriginbidx());
		    pstmt.setInt(7, bv.getDepth()+1);
		    pstmt.setInt(8, bv.getLevel_()+1);
		    pstmt.setString(9, bv.getTag());
		    value = pstmt.executeUpdate();
		    	
		    conn.commit();

		    }catch(Exception e){
		    	try {
					conn.rollback();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
		    	e.printStackTrace();}
		    
			return  value;
			
		}
		public int boardTotal(SearchCriteria scri) {
			
			int cnt = 0;
			ResultSet rs = null;
			String str="";
			if(scri.getSearchType().equals("subject")) {
				str = "and subject like ?";	
			}else {
				str = "and writer like ?";
				
			}
			
			
			String sql = "select count(*) as cnt from db_board where delyn ='n'"+str+"";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+scri.getKeyword()+"%");
				rs = pstmt.executeQuery();
				
				
				
				if(rs.next()) {
					cnt = rs.getInt("cnt");
					
					
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			
			
			return cnt;
		}
		
		public int boardreplypoint (int midx){
			int value = 0;
			
		    
		    String sql = "UPDATE dB_member SET memberpoint = memberpoint+50, acpoint = acpoint+50 where midx = ?"; 
		    
		    try{
		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
		    pstmt.setInt(1, midx);
		    
		    value = pstmt.executeUpdate();
		     	
		   

		    }catch(Exception e){
		    	e.printStackTrace();
		    }
		    
			return value;
		}
		public int boardpoint (int midx){
			int value = 0;
			
		    
		    String sql = "UPDATE dB_member SET memberpoint = memberpoint+100, acpoint = acpoint+100 where midx = ?"; 
		    
		    try{
		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
		    pstmt.setInt(1, midx);
		    
		    value = pstmt.executeUpdate();
		     	
		   

		    }catch(Exception e){
		    	e.printStackTrace();
		    	}
		    
			return value;
		}
   public ArrayList<BoardVo> MyboardSelectAll(SearchCriteria scri,int midx){
			
			ArrayList<BoardVo> alist = new ArrayList<BoardVo>();
			
			ResultSet rs = null;
			
			String str="";
			if(scri.getSearchType().equals("subject")) {
				str = "and subject like ?";	
			}else {
				str = "and writer like ?";
				
			}
			
			
			
	//		String sql = "select * from b_board where delyn = 'n' order by originbidx desc, depth asc";
		    String sql = "SELECT * FROM db_board WHERE delyn='n'AND midx=? "+str+" ORDER BY ORIGINBIDx DESC, DEPTH asc limit ?,15";	
			try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setString(2, "%"+scri.getKeyword()+"%");
			pstmt.setInt(3, (scri.getPage()-1)*15);
			
			rs =  pstmt.executeQuery();
			
			
			
			
			while(rs.next()){
				
				BoardVo bv = new BoardVo();
				bv.setBidx(rs.getInt("bidx"));
				bv.setSubject(rs.getString("subject"));
				bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setLevel_(rs.getInt("level_"));
				bv.setTag(rs.getString("tag"));
				
				alist.add(bv);
			}
			
			}catch(Exception e){
				e.printStackTrace();
			}finally {
			
				try {
					
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return alist;
			
		}      
          public int autolevel (int midx){
		int value3 = 0;
		
	    
	    String sql = "update db_member"
	    		+ "   SET membergrade="
	    		+ "   CASE"
	    		+ "   WHEN AcPoint >=0 AND ACPOINT <1000 AND midx!=1 THEN 'Bronze'"
	    		+ "   WHEN AcPoint >=1000 and AcPoint < 15000 AND midx!=1 THEN 'Silver'"
	    		+ "   WHEN AcPoint >=15000 and AcPoint < 200000 AND midx!=1 THEN 'Gold'"
	    		+ "   WHEN AcPoint >=200000 and AcPoint < 500000 AND midx!=1 THEN 'Platinum'"
	    		+ "   WHEN AcPoint >=500000 AND midx!=1 THEN 'Diamond'"
	    		+ "   else 'Master'"
	    		+ "   END where midx=?"; 
	    
	    try{
	    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
	    pstmt.setInt(1, midx);
	    System.out.println("midx"+midx);
	    value3 = pstmt.executeUpdate();
	    
	    System.out.println("value3"+value3); 	
	   

	    }catch(Exception e){
	    	e.printStackTrace();}
	    finally {
			
			try {
								
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		return value3;
	}
   
   
   
   
		
		
		}
		
		
	
	
	

