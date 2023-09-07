package com.multi.chatapp.dao;

import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.multi.chatapp.dto.UserDTO;
import com.multi.chatapp.utils.Encrypt;
public class UserDAO {
	public  boolean islogin(UserDTO userDTO) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select userid from user where userid=? and password =?";
		try {
		connection = CommonDAO.createConnection();
        pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, userDTO.getUserid());
        pstmt.setString(2, Encrypt.passwordEncrypt(new  String(userDTO.getPasword())));
//        System.out.println(userDTO.getUserid()+" "+Encrypt.passwordEncrypt(new  String(userDTO.getPasword())));
        rs = pstmt.executeQuery();
        return rs.next();
		}
		finally {
			if(rs != null)
			  rs.close();
			if(pstmt != null)
			  pstmt.close();
			if(connection != null)
			  connection.close(); 	
		}	
	}
	
	
    public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException
    {
    	System.out.println("RECEIVED == userid" + " " +userDTO.getUserid() + "\npassword" + userDTO.getPasword());
    	Connection connection = null;
    	Statement stmt = null;
    	try {
    	connection = CommonDAO.createConnection();//create connection with db;
    	//2nd query execution
    	stmt = connection.createStatement();
    	// record==0||1;
    	int record = stmt.executeUpdate("insert into user (userid, password)  values('"+ userDTO.getUserid()+ "','"+Encrypt.passwordEncrypt(new String(userDTO.getPasword()))+"')");
    	return record;
    	}
    	
    	finally {
    	if(stmt != null)	
    	stmt.close();
    	if(connection != null)
    	connection.close();
  
    	}
    }
           
}
