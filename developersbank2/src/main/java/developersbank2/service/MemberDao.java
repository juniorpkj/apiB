package developersbank2.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import developersbank2.dbconn.Dbconn;
import developersbank2.domain.BoardVo;
import developersbank2.domain.DonationVo;
import developersbank2.domain.MemberVo;
import developersbank2.domain.SearchCriteria;

public class MemberDao {
	private Connection conn;  
	private PreparedStatement pstmt;
	public MemberDao() {
		
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
		
	}
	
	
	
		public int insertMember (String memberId, String memberPwd,String memberName,String memberphone,String memberEmail,String memberJumin,String memberGender,String memberAddr,String hobby,String ip,String membernickname){
			int value = 0;
			
		    
		    String sql = "insert into db_member(MEMBERID,MEMBERPWD,MEMBERName,"
		    		+"MEMBERGender,MEMBERAddr,MEMBERJumin,MEMBERphone,memberIP,MEMBEREmail,memberHobby,membernickname)" 
		    		+"values(?,?,?,?,?,?,?,?,?,?,?)";
		    try{
		    pstmt = conn.prepareStatement(sql);    // statement는 해킹 위험이 있어서 preparedstatement를 사용한다.
		    pstmt.setString(1, memberId);	
		    pstmt.setString(2, memberPwd);
		    pstmt.setString(3, memberName);
		    pstmt.setString(4, memberGender);
		    pstmt.setString(5, memberAddr);
		    pstmt.setString(6, memberJumin);
		    pstmt.setString(7, memberphone);
		    pstmt.setString(8, ip);
		    pstmt.setString(9, memberEmail);
		    pstmt.setString(10, hobby);
		    pstmt.setString(11, membernickname);
		    value = pstmt.executeUpdate();
		    	
		    //구문을 만드는 객체 (createStatement), 연결객체를 통해서 Statement 구문객체를 생성해서 stmt에 담는다. 
		    //Statement stmt = conn.createStatement();
		    //구문을 실행하고 리턴값으로 실행되었으면 1아니면 0을 value 변수에 담는다.
		    //value = stmt.executeUpdate(sql);

		    }catch(Exception e){
		    	e.printStackTrace();
		    	}
		    
			return value;
		}

		public ArrayList<MemberVo> memberSelectAll(SearchCriteria scri){
			ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
			
			ResultSet rs = null;
			
			String str="";
			if(scri.getSearchType().equals("midx")) {
				str = "and midx like ?";	
			}else {
				str = "and memberid like ?";
				
			}
			

		    String sql = "SELECT * FROM db_member WHERE delyn='N' "+str+" ORDER BY midx desc limit ?,15";	
			try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			pstmt.setInt(2, (scri.getPage()-1)*15);

			
			rs =  pstmt.executeQuery();
	
			
			while(rs.next()){

				MemberVo mv = new MemberVo();
				mv.setMidx(rs.getInt("midx"));
				mv.setMembername(rs.getString("memberName"));
				mv.setMemberphone(rs.getString("memberphone"));
				mv.setWriteday(rs.getString("writeday"));
				mv.setMemberid(rs.getString("memberid"));
				mv.setMemberpoint(rs.getInt("memberpoint"));
				mv.setAcpoint(rs.getInt("acpoint"));
				mv.setMemberemail(rs.getString("memberemail"));
				alist.add(mv);
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
		public MemberVo memberLogin(String memberId, String memberPwd) {
			MemberVo mv = null;
			ResultSet rs = null;
			String sql ="select * from db_member where delyn ='N' and memberid = ? and memberpwd = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);
				pstmt.setString(2, memberPwd);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					mv = new MemberVo();
					mv.setMidx(rs.getInt("midx"));
					mv.setMemberid(rs.getString("memberid"));
					mv.setMembernickname(rs.getString("membernickname"));
					mv.setMemberpoint(rs.getInt("memberpoint"));
					
					
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
			
			
			return mv;
		}
		public MemberVo memberSelectOne(int midx) {
			 MemberVo mv = null;
			 ResultSet rs = null;
			 String sql = "select * from db_member where midx =?";
			
			 try {
				pstmt =  conn.prepareStatement(sql);   //쿼리화 시킴
				pstmt.setInt(1, midx);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {       //다음값이 존재하면 true 커서는 다음행으로 이동
					mv = new MemberVo();
					mv.setMidx(rs.getInt("midx"));   //rs에 담겨져있는 데이터를 bv에 담는다
					mv.setMemberpwd(rs.getString("memberpwd"));
					mv.setMemberid(rs.getString("memberid"));
					mv.setMembergender(rs.getString("membergender"));
					mv.setMemberaddr(rs.getString("memberaddr"));
					mv.setMemberphone(rs.getString("memberphone"));
					mv.setMemberhobby(rs.getString("memberhobby"));
					mv.setMemberemail(rs.getString("memberemail"));
					mv.setWriteday(rs.getString("writeday"));
					mv.setMembernickname(rs.getString("membernickname"));
					mv.setMemberpoint(rs.getInt("memberpoint"));
					mv.setMembergrade(rs.getString("membergrade"));
					mv.setAcpoint(rs.getInt("acpoint"));
					
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
			
			
			return mv;
		}public int MemberModify (int memberpwd_, String memberemail, String memberaddr, int Midx){
			int value = 0;
			
		    
		    String sql = "update db_member set memberpwd=?,memberemail=?,memberaddr=? where midx =?"; 
		    		
		    try{
		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
		    pstmt.setInt(1, memberpwd_);
		    pstmt.setString(2, memberemail);
		    pstmt.setString(3, memberaddr);
            pstmt.setInt(4, Midx);
            
		    
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
		}public int MemberDelete (int midx){
			int value = 0;
			
		    
		    String sql = "update db_member set delyn='y' where midx=?"; 
		    		
		    try{
		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
		    pstmt.setInt(1, midx);
		    
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
               public int memberTotal(SearchCriteria scri) {
			
			int cnt = 0;
			ResultSet rs = null;
			String str="";
			if(scri.getSearchType().equals("midx")) {
				str = "and midx like ?";	
			}else {
				str = "and membername like ?";
				
			}
			
			
			String sql = "select count(*) as cnt from db_member where delyn ='N'"+str+"";
			
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
               public MemberVo MemberDeleteOne(int midx_) {
      			 MemberVo mv = null;
      			 ResultSet rs = null;
      			 String sql = "select * from db_member where midx =?";
      			
      			 try {
      				pstmt =  conn.prepareStatement(sql);   //쿼리화 시킴
      				pstmt.setInt(1, midx_);
      				rs = pstmt.executeQuery();
      				
      				if(rs.next()) {       //다음값이 존재하면 true 커서는 다음행으로 이동
      					mv = new MemberVo();
      					mv.setMidx(rs.getInt("midx"));   //rs에 담겨져있는 데이터를 bv에 담는다
      					
      					
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
      			
      			
      			return mv;
      		}
               public int MasterDelete (int midx_){
       			int value = 0;
       			
       		    
       		    String sql = "update db_member set delyn='y' where midx=?"; 
       		    		
       		    try{
       		    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
       		    pstmt.setInt(1, midx_);
       		    
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
               public int checkId(String id) {
      	    	 ResultSet rs = null;
      	    	 String sql ="select * from db_member where memberid=?";
      	    	 int idCheck=0;
      	    	 
      	    	 try {
      				pstmt =conn.prepareStatement(sql);
      				pstmt.setString(1, id);
      				rs = pstmt.executeQuery();
      				
      				if(rs.next()||id.equals("")) {
      					idCheck = 0;  //이미 존재하는 경우 생성 불가능

      				}else {
      					idCheck = 1;  //존재하는 경우 생성 가능
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
      	    	 return idCheck;
      	     }  
               
               
	
		
		}
