package merch.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ImgReader extends HttpServlet{
	Connection con;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("img/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			Statement stmt = con.createStatement();
			String merchID = req.getParameter("merchID");
			ResultSet rs = stmt.executeQuery("SELECT merchImg FROM merch WHERE merchID = "+merchID);
			
			if(rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("merchImg"));
				byte[] buf = new byte[4*1024];
				int len;
				while((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			}else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	
	
	}
	
	public void init()throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/g4?serverTimezone=Asia/Taipei", "root", "system1695");
		} catch (ClassNotFoundException e) {
			throw new UnavailableException("Couldn't load JdbcOdbcDriver");
		} catch (SQLException e) {
			throw new UnavailableException("Couldn't get db connection");
		}
	}
	
	public void destroy() {
		try {
			if(con != null) {
				con.close();
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
}
