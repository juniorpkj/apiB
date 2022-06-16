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
import developersbank2.domain.SearchVo;


public class SearchDao {
	private Connection conn;  
	private PreparedStatement pstmt;
	public SearchDao() {
		
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
		
	}
	

		public ArrayList<SearchVo> searchSelectAll(SearchCriteria scri){
			
			ArrayList<SearchVo> alist = new ArrayList<SearchVo>();
			
			ResultSet rs = null;
			ResultSet rs1 = null;
	        
	
		    String sql1 = "SELECT * FROM db_board  WHERE delyn='n' and subject like ?";
		    String sql2 = "SELECT * FROM DB_COMMUNITY WHERE delyn='n' and c_subject like ?";
		    
    
			try{
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			rs1 =  pstmt.executeQuery();
			System.out.println("rs1"+rs1);
            while(rs1.next()){
				
				SearchVo sv = new SearchVo();
				
                sv.setBidx(rs1.getInt("bidx"));
                sv.setSubject(rs1.getString("subject"));
                sv.setWriteday(rs1.getString("writeday"));
                sv.setDepth(rs1.getInt("depth"));
                sv.setLevel_(rs1.getInt("level_"));
               
                
                
                
				alist.add(sv);
			}
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			rs =  pstmt.executeQuery();
            System.out.println("rs"+rs);
			
			while(rs.next()){
				
				SearchVo sv = new SearchVo();
				
                sv.setCidx(rs.getInt("cidx"));
                sv.setC_subject(rs.getString("c_subject"));
                sv.setC_writeday(rs.getString("c_writeday"));
                sv.setC_depth(rs.getInt("c_depth"));
                sv.setC_level_(rs.getInt("c_level_"));
                
                
                
                
				alist.add(sv);
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
		
               public int searchTotal(SearchCriteria scri) {
			
			int cnt = 0;
			ResultSet rs = null;
	
			String sql = "select count(*) as cnt from db_board where delyn ='n' and subject like ?";
			
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
		
		
	
	
	

