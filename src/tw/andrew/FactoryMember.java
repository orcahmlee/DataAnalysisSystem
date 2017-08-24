/*
 * MODEL
 */
package tw.andrew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class FactoryMember {
	private String id, account, password, firstname, lastname, isLogin, isApproval;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public FactoryMember() throws ClassNotFoundException, SQLException {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/andrew", prop);
	}
	
	public boolean isValidMember() throws SQLException {
		String sql = "select * from factoryMember where account = ? and password = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		pstmt.setString(2, password);
		
		ResultSet rs = pstmt.executeQuery();
		boolean ret = rs.next(); // return true if there has data; return false if there doesn't has data.
		
		if (ret) {
			id = rs.getString("id");
			firstname = rs.getString("firstname");
			lastname = rs.getString("lastname");
			isLogin = rs.getString("isLogin");
			isApproval = rs.getString("isApproval");
		}
		return ret; // The "FactoryMemberLogin" controller determine "Login Success" or "Login Failure" by this boolean. 
	}
	
	public boolean newInsert() throws SQLException {
		String sql = "insert into factoryMember (account, password, firstname, lastname) values (?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		pstmt.setString(2, password);
		pstmt.setString(3, firstname);
		pstmt.setString(4, lastname);
		int n = pstmt.executeUpdate();
		// if insert success, return "the row count"; if insert fail, return 0;
		return n > 0;
		// if n > 0 = "true", insert success; if n > 0 = "false", insert fail;
	}
	
	
	
	
	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLasttname(String lastname) {
		this.lastname = lastname;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public void setIsApproval(String isApproval) {
		this.isApproval = isApproval;
	}
	
	public String getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public String getIsApproval() {
		return isApproval;
	}

}
