package developersbank2.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import developersbank2.dbconn.Dbconn;
import developersbank2.domain.BoardVo;
import developersbank2.domain.Criteria;
import developersbank2.domain.DonationVo;
import developersbank2.domain.MemberVo;
import developersbank2.domain.SearchCriteria;


public class DonationDao {
	private Connection conn;  
	private PreparedStatement pstmt;
	public DonationDao() {
		
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
		
	}
	public int donationpoint (int midx,int memberpoint_){
		int value = 0;
		
	    
	    String sql = "UPDATE dB_member SET memberpoint = memberpoint-? where midx = ?"; 
	    
	    try{
	    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
	    pstmt.setInt(1, memberpoint_);
	    pstmt.setInt(2, midx);
	    
	    
	    value = pstmt.executeUpdate();
	     	
	   

	    }catch(Exception e){
	    	e.printStackTrace();
	 }
	    
		return value;
	}
	    
	    public int insertdonation (int midx,int memberpoint_,String ip,String membernickname){
	    int value2 = 0;
	    String sql = "insert into db_donation(d_writer,ip,midx,memberpoint)" 
	    		+"values(?,?,?,?)";
	    try{
	    pstmt = conn.prepareStatement(sql);    //문자열된 쿼리를 연결시켜 구문화함
	    pstmt.setString(1, membernickname);
        pstmt.setString(2, ip);
        pstmt.setInt(3, midx);
	    pstmt.setInt(4, memberpoint_);
	    value2 = pstmt.executeUpdate();
	    	
	   

	    }catch(Exception e){
	    	e.printStackTrace();}
	   
		return value2;

	
	
	 }
		
	      public ArrayList<DonationVo> donationSelectOne(int midx){
	  			ArrayList<DonationVo> alist = new ArrayList<DonationVo>();
	  			
	  			ResultSet rs = null;
	  			
	  		    String sql = "select * from db_donation where midx=?";	
	  			try{
	  			pstmt = conn.prepareStatement(sql);
     			pstmt.setInt(1, midx);

	  			
	  			rs =  pstmt.executeQuery();
	  	
	  			
	  			while(rs.next()){

	  				DonationVo dv = new DonationVo();
	  				dv.setDidx(rs.getInt("didx"));   //rs에 담겨져있는 데이터를 bv에 담는다
					dv.setIp(rs.getString("ip"));
					dv.setMemberpoint(rs.getInt("memberpoint"));
					dv.setD_writeday(rs.getString("d_writeday"));
					dv.setD_writer(rs.getString("d_writer"));
	  				alist.add(dv);
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

          public ArrayList<DonationVo> masterSelectAll(SearchCriteria scri){
  			ArrayList<DonationVo> alist = new ArrayList<DonationVo>();
  			
  			ResultSet rs = null;
  			
  			String str="";
  			if(scri.getSearchType().equals("midx")) {
  				str = "midx like ?";	
  			}else {
  				str = "d_writer like ?";
  				
  			}
  			

  		    String sql = "SELECT * FROM db_donation WHERE "+str+" ORDER BY midx desc limit ?,15";	
  			try{
  			pstmt = conn.prepareStatement(sql);
  			pstmt.setString(1, "%"+scri.getKeyword()+"%");
  			pstmt.setInt(2, (scri.getPage()-1)*15);

  			
  			rs =  pstmt.executeQuery();
  	
  			
  			while(rs.next()){

  				DonationVo dv = new DonationVo();
  				dv.setMidx(rs.getInt("midx"));
  				dv.setD_writer(rs.getString("d_writer"));
  				dv.setD_writeday(rs.getString("d_writeday"));
  				dv.setMemberpoint(rs.getInt("memberpoint"));
  				dv.setDidx(rs.getInt("didx"));
  				alist.add(dv);
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
           public int masterTotal(SearchCriteria scri) {
     			
     			int cnt = 0;
     			ResultSet rs = null;
     			String str="";
     			if(scri.getSearchType().equals("midx")) {
     				str = "and midx like ?";	
     			}else {
     				str = "and d_writer like ?";
     				
     			}
     			
     			
     			String sql = "select count(*) as cnt from db_donation where delyn ='n'"+str+"";
     			
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
               public int donationTotal(SearchCriteria scri) {
    			
    			int cnt = 0;
    			ResultSet rs = null;
    			String str="";
    			if(scri.getSearchType().equals("midx")) {
    				str = "and midx like ?";	
    			}else {
    				str = "and memberid like ?";
    				
    			}
    			
    			
    			String sql = "select count(*) as cnt from db_donation where delyn ='N'"+str+"";
    			
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
	    
	    
	    
	    
}
		
		
	
	
	

