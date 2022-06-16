package developersbank2.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import developersbank2.dbconn.Dbconn;
import developersbank2.domain.CommunityVo;
import developersbank2.domain.Criteria;
import developersbank2.domain.MemberVo;
import developersbank2.domain.SearchCriteria;


public class CommunityDao {
	private Connection conn;  
	private PreparedStatement pstmt;
	public CommunityDao() {
		
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
		
	}
	
	
	
		public int insertcommunity (String c_subject, String c_content, String c_writer, String ip, int Midx,String fileName,String category_){
			int value = 0;
			
		    //level은 예약어로 되어 있어 만들때 c_level_으로 만들었다
		    String sql = "insert into db_community(c_subject,c_content,c_writer,ip,midx,origincidx,c_depth,c_level_,filename,category_)" 
		    		+"select ?,?,?,?,?,max(cidx)+1,0,0,?,? from db_community";
		    
		    try{
		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
		    pstmt.setString(1, c_subject);
		    pstmt.setString(2, c_content);
		    pstmt.setString(3, c_writer);
		    pstmt.setString(4, ip);
		    pstmt.setInt(5, Midx);
		    pstmt.setString(6, fileName);
		    pstmt.setString(7, category_);

		    
		    value = pstmt.executeUpdate();
		    	
		   

		    }catch(Exception e){
		    	e.printStackTrace();}
		   
			return value;
		}

		public ArrayList<CommunityVo> communitySelectAll(SearchCriteria scri){
			
			ArrayList<CommunityVo> alist = new ArrayList<CommunityVo>();
			
			ResultSet rs = null;
			
			String str="";
			if(scri.getSearchType().equals("c_subject")) {
				str = "and c_subject like ?";	
			}else {
				str = "and c_writer like ?";
				
			}
			
			
			
	//		String sql = "select * from b_community where delyn = 'n' order by origincidx desc, c_depth asc";
		    String sql = "SELECT * FROM db_community WHERE delyn='n' "+str+" ORDER BY ORIGINcidx DESC, c_depth asc limit ?,15";	
			try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			pstmt.setInt(2, (scri.getPage()-1)*15);

			
			rs =  pstmt.executeQuery();
			
			
			
			
			while(rs.next()){
				
				CommunityVo bv = new CommunityVo();
				bv.setCidx(rs.getInt("cidx"));
				bv.setC_subject(rs.getString("c_subject"));
				bv.setC_content(rs.getString("c_content"));
				bv.setC_writer(rs.getString("c_writer"));
				bv.setC_writeday(rs.getString("c_writeday"));
				bv.setC_level_(rs.getInt("c_level_"));
				bv.setCategory_(rs.getString("category_"));
				
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
		
		public CommunityVo communitySelectOne(int cidx) {
			 CommunityVo bv = null;
			 ResultSet rs = null;
			 String sql = "select * from db_community where cidx =?";
			
			 try {
				pstmt =  conn.prepareStatement(sql);   //쿼리화 시킴
				pstmt.setInt(1, cidx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {       //다음값이 존재하면 true 커서는 다음행으로 이동
					bv = new CommunityVo();
					bv.setCidx(rs.getInt("cidx"));   //rs에 담겨져있는 데이터를 bv에 담는다
					bv.setOrigincidx(rs.getInt("origincidx"));
					bv.setC_depth(rs.getInt("c_depth"));
					bv.setC_level_(rs.getInt("c_level_"));
					bv.setC_subject(rs.getString("c_subject"));
					bv.setC_content(rs.getString("c_content"));
					bv.setC_writer(rs.getString("c_writer"));
					bv.setC_writeday(rs.getString("c_writeday"));
					bv.setFilename(rs.getString("filename"));
					bv.setCategory_(rs.getString("category_"));
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
		
		public int communityModify (String c_subject, String c_content, String c_writer, String ip, int Midx,int cidx_,String fileName){
			int value = 0;
			
		    
		    String sql = "update db_community set c_subject=?,c_content=?,c_writer=?,ip=?,filename=?,c_writeday=now() where cidx =?"; 
		    		
		    try{
		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
		    pstmt.setString(1, c_subject);
		    pstmt.setString(2, c_content);
		    pstmt.setString(3, c_writer);
		    pstmt.setString(4, ip);
		    pstmt.setString(5, fileName);
		    pstmt.setInt(6, cidx_);

		    
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
		
		public int communityDelete (int Cidx_){
			int value = 0;
			
		    
		    String sql = "update db_community set delyn='y',c_writeday=now() where cidx=?"; 
		    		
		    try{
		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
		    pstmt.setInt(1, Cidx_);
		    
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
		
		
		

		public int communityReply (CommunityVo bv){
			int value = 0;
			
		    //level은 예약어로 되어 있어 만들때 c_level_으로 만들었다
		    String sql1 ="update db_community set c_depth = c_depth+1 where origincidx = ? and c_depth > ?";
		    
		    String sql2 ="insert into db_community(c_subject,c_content,c_writer,ip,midx,origincidx,c_depth,c_level_,category_)" 
		    		      +"values(?,?,?,?,?,?,?,?,?)";
		    try{
		    conn.setAutoCommit(false);    //오라클 설정이 기본적으로 오토커밋 되어 있는데 이것을 종료시켜 수동으로 바꿈
		    pstmt = conn.prepareStatement(sql1);	
		    pstmt.setInt(1, bv.getOrigincidx());
		    pstmt.setInt(2, bv.getC_depth());
		    pstmt.executeUpdate();
		    
		    
		    pstmt = conn.prepareStatement(sql2);    //문자열된 쿼리를 연결시켜 구문화함
		    
		    pstmt.setString(1, bv.getC_subject());
		    pstmt.setString(2, bv.getC_content());
		    pstmt.setString(3, bv.getC_writer());
		    pstmt.setString(4, bv.getIp());
		    pstmt.setInt(5, bv.getMidx());
		    pstmt.setInt(6, bv.getOrigincidx());
		    pstmt.setInt(7, bv.getC_depth()+1);
		    pstmt.setInt(8, bv.getC_level_()+1);
		    pstmt.setString(9, bv.getCategory_());
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
		public int communityTotal(SearchCriteria scri) {
			
			int cnt = 0;
			ResultSet rs = null;
			String str="";
			if(scri.getSearchType().equals("c_subject")) {
				str = "and c_subject like ?";	
			}else {
				str = "and c_writer like ?";
				
			}
			
			
			String sql = "select count(*) as cnt from db_community where delyn ='n'"+str+"";
			
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
		
		public int communityreplypoint (int midx){
			int value = 0;
			
		    
		    String sql = "UPDATE dB_member SET memberpoint = memberpoint+10, acpoint = acpoint+10 where midx = ?"; 
		    
		    try{
		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
		    pstmt.setInt(1, midx);
		    
		    value = pstmt.executeUpdate();
		     	
		   

		    }catch(Exception e){
		    	e.printStackTrace();}
		   
			return value;
		}
		public int communitypoint (int midx){
			int value = 0;
			
		    
		    String sql = "UPDATE dB_member SET memberpoint = memberpoint+20, acpoint = acpoint+20 where midx = ?"; 
		    
		    try{
		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
		    pstmt.setInt(1, midx);
		    
		    value = pstmt.executeUpdate();
		     	
		   

		    }catch(Exception e){
		    	e.printStackTrace();
		  
				}
			return value;
		}
    public ArrayList<CommunityVo> MycommunitySelectAll(SearchCriteria scri,int midx){
			
			ArrayList<CommunityVo> blist = new ArrayList<CommunityVo>();
			
			ResultSet rs = null;
			
			String str="";
			if(scri.getSearchType().equals("c_subject")) {
				str = "and c_subject like ?";	
			}else {
				str = "and c_writer like ?";
				
			}
			
			
			
	//		String sql = "select * from b_community where delyn = 'n' order by origincidx desc, c_depth asc";
		    String sql = "SELECT * FROM db_community WHERE delyn='n'AND midx =? "+str+" ORDER BY ORIGINcidx DESC, c_depth asc limit ?,15";	
			try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, midx);
			pstmt.setString(2, "%"+scri.getKeyword()+"%");
			pstmt.setInt(3, (scri.getPage()-1)*15);

			
			rs =  pstmt.executeQuery();
			
			
			
			
			while(rs.next()){
				
				CommunityVo bv = new CommunityVo();
				bv.setCidx(rs.getInt("cidx"));
				bv.setC_subject(rs.getString("c_subject"));
				bv.setC_content(rs.getString("c_content"));
				bv.setC_writer(rs.getString("c_writer"));
				bv.setC_writeday(rs.getString("c_writeday"));
				bv.setC_level_(rs.getInt("c_level_"));
				bv.setCategory_(rs.getString("category_"));
				
				blist.add(bv);
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
			return blist;
			
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
		
		
	
	
	

