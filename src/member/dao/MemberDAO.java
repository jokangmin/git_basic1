package member.dao; // data access object 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.bean.MemberDTO;



public class MemberDAO { //db 처리 
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "1234";
	private PreparedStatement pstmt;
	private Connection con;
	private ResultSet rs = null;
	private static MemberDAO instance = new MemberDAO();  //static 에서는 생성을 한번만 하므로 
	private String get_name;
	private String g_name;
	private String g_pwd;
	private int su = 0;
	
	public MemberDAO() {
		try {
			Class.forName(driver); 	
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}
	}
	
	public static MemberDAO getInstance(){
		/*
		if(instance == null) { 
			synchronized (MemberDAO.class) { //웹에서 사람이 많아 스레드를 많이 사용한다면 이렇게 
				instance = new MemberDAO();
			}
		}
		*/
		return instance;
	}
	
	public String gName() {
		return g_name;
	}
	
	public int getSu() {
		return su;
	}
	
	public String gPwd() {
		return g_pwd;
	}
	
	public int write(MemberDTO memberDTO) {
		int su = 0;
		getConnection();
		String sql = "insert into member values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPwd());
			pstmt.setString(4, memberDTO.getPhone());
			su = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//실행한 절차를 거꾸로 close 해줘야함 
			try {
				if(pstmt != null) pstmt.close(); //PreparedStatement //생성했을때만 close 해준다.
				if(con != null) con.close(); //Connection
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return su;
	}
	
	public String login( String id, String pwd) {
		String name = null;
		getConnection();
		String sql = "select * from member"; 
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String get_id = rs.getString("id");
				String get_pwd = rs.getString("pwd");
				if(id.equals(get_id) && pwd.equals(get_pwd)) {
					name = rs.getString("name");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//실행한 절차를 거꾸로 close 해줘야함 
			try {
				if(rs != null) rs.close(); 
				if(pstmt != null) pstmt.close(); //PreparedStatement //생성했을때만 close 해준다.
				if(con != null) con.close(); //Connection
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return name;
	}
	
	public int isExistId(MemberDTO memberDTO, String id) {// 중복체크 
		int state = 0;
		getConnection();
		String sql = "select * from member"; //select * from member where id = ? 
		try {
			pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, id);
			rs = pstmt.executeQuery();		
			while(rs.next()) { //if(rs.next()){
				//exist = true;
				String get_id = rs.getString("id");
				if(id.equals(get_id)) {
					state = 1;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close(); 
				if(pstmt != null) pstmt.close(); //PreparedStatement //생성했을때만 close 해준다.
				if(con != null) con.close(); //Connection
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return state;
	}
	
	public void Update(String name, String pwd, String id) {
		getConnection();
		String sql = "update member set name = ?, pwd = ? where id = ?";
		try {												// MemberDTO memberDTO = null;
			pstmt = con.prepareStatement(sql);    			//if(rs.next){
			pstmt.setString(1,name);						//	memberDTO = new MemberDTO();
			pstmt.setString(2,pwd);							//	memberDTO.setName(rs.getString("name")); //하나씩 다 가지고오기
			pstmt.setString(3,id);							// .	.	.
			int su = pstmt.executeUpdate();					//
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close(); 
				if(pstmt != null) pstmt.close(); //PreparedStatement //생성했을때만 close 해준다.
				if(con != null) con.close(); //Connection
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public boolean shId(String id) {
		getConnection();
		boolean exist = false;
		String sql = "select * from member where id = ?"; 
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				g_name = rs.getString("name");
				g_pwd = rs.getString("pwd");
				exist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close(); 
				if(pstmt != null) pstmt.close(); 
				if(con != null) con.close(); 
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exist;
	}
	
	
	public boolean delete( String id, String pwd) {
		boolean state = false;
		getConnection();
		String sql = "delete member where id = ? and pwd = ?"; 
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			su = pstmt.executeUpdate();
			if(su != 0) {
				state = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//실행한 절차를 거꾸로 close 해줘야함 
			try {
				if(rs != null) rs.close(); 
				if(pstmt != null) pstmt.close(); //PreparedStatement //생성했을때만 close 해준다.
				if(con != null) con.close(); //Connection
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return state;
	}
	
	
	public void getConnection() { 
		 try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
