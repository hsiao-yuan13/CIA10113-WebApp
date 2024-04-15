package merch.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImgUpload {


		public static final String URL = "jdbc:mysql://localhost:3306/g4?serverTimezone=Asia/Taipei";
		public static final String USER = "root";
		public static final String PASSWORD = "system1695";
		
		
		private static final String SQL = "INSERT INTO merch(merchImg) VALUES (?)";

		public static void main(String[] args) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(SQL);

				// 1. setBlob (JDBC 4.0 / JDK 6)
				InputStream is = getPictureStream("images/glove.jpg");
				pstmt.setBlob(1, is);
				pstmt.executeUpdate();
				is.close();

				// 2. setBytes
				byte[] pic = getPictureByteArray("images/sandworm.jpg");
				pstmt.setBytes(1, pic);
				pstmt.executeUpdate();

				// 3. setBinaryStream
				InputStream is2 = getPictureStream("cat.jpg");
				pstmt.setBinaryStream(1, is2);
				pstmt.executeUpdate();
				is2.close();

				System.out.println("新增成功");


			} catch (SQLException se) {
				System.out.println(se);
			} catch (IOException ie) {
				System.out.println(ie);
			} finally {
				// 依建立順序關閉資源 (越晚建立越早關閉)
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						System.out.println(se);
					}
				}

				if (con != null) {
					try {
						con.close();
					} catch (SQLException se) {
						System.out.println(se);
					}
				}
			}
		}

		// 使用InputStream資料流方式
		public static InputStream getPictureStream(String path) throws IOException {
			FileInputStream fis = new FileInputStream(path);
			return fis;
		}

		// 使用byte[]方式
		public static byte[] getPictureByteArray(String path) throws IOException {
			FileInputStream fis = new FileInputStream(path);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			return buffer;
		}
	
}
