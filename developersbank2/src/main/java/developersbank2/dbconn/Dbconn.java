package developersbank2.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;


public class Dbconn {

	
	private String coninfo="jdbc:mysql://localhost/mysql?serverTimezone=UTC";
	private String idinfo="root";
	private String pwdinfo="1234";

    public Connection getConnection() {
    	
    	Connection conn = null;
    	
	
			
	
	try {
		//등록한 드라이버 중에 사용가능한 클래스를 찾아서 생성 (오라클과 자바를 연결하는 과정)
		Class.forName("com.mysql.cj.jdbc.Driver");  //여기 값은 Web App Libraries에서 driver를 찾아 한번 누르면 왼쪽 하단에 주소가 뜬다.
		//연결정보를 통해서 연결객체를 참조변수 conn에 담는다
		conn =  DriverManager.getConnection(coninfo, idinfo, pwdinfo);
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	
	
	return conn;
    }
}
