package merch.model;

import java.util.*;
import java.sql.*;

public class MerchJDBCDAO implements MerchDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/g4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "system1695";

	private static final String INSERT_STMT = 
		"INSERT INTO merch (merchName, merchImg, merchInfo, merchPrice, merchStatus) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE merch set merchName=?, merchImg=?, merchInfo=?, merchPrice=?, merchStatus=? where merchID = ?";
	private static final String GET_ALL_STMT = 
		"SELECT merchID, merchName, merchImg, merchInfo, merchPrice, merchStatus FROM merch order by merchID";
	private static final String FIND_BY_PK = 
		"SELECT merchID, merchName, merchImg, merchInfo, merchPrice, merchStatus FROM merch where merchID = ?";
	private static final String FIND_BY_NAME = 
		"SELECT merchID, merchName, merchImg, merchInfo, merchPrice, merchStatus FROM merch where merchName = ?";

	//新增
	@Override
	public void insert(MerchVO merchVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, merchVO.getMerchName());
			pstmt.setBytes(2, merchVO.getMerchImg());
			pstmt.setString(3, merchVO.getMerchInfo());
			pstmt.setInt(4, merchVO.getMerchPrice());
			pstmt.setString(5, merchVO.getMerchStatus());

			pstmt.executeUpdate();

			System.out.println("資料新增成功");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	//修改
	@Override
	public void update(MerchVO merchVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, merchVO.getMerchName());
			pstmt.setBytes(2, merchVO.getMerchImg());
			pstmt.setString(3, merchVO.getMerchInfo());
			pstmt.setInt(4, merchVO.getMerchPrice());
			pstmt.setString(5, merchVO.getMerchStatus());
			
			pstmt.executeUpdate();

			System.out.println("資料更新成功");
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}


	//查詢編號
	@Override
	public MerchVO findByPrimaryKey(Integer merchID) {

		MerchVO merchVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, merchID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				merchVO = new MerchVO();
				
				merchVO.setMerchID(rs.getInt("merchID"));
				merchVO.setMerchName(rs.getString("merchName"));
				merchVO.setMerchImg(rs.getBytes("merchImg"));
				merchVO.setMerchInfo(rs.getString("merchInfo"));
				merchVO.setMerchPrice(rs.getInt("merchPrice"));
				merchVO.setMerchStatus(rs.getString("merchStatus"));
			}


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return merchVO;
	}
	
	//查詢名稱
		@Override
		public MerchVO findByName(String merchName) {

			MerchVO merchVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(FIND_BY_NAME);

				pstmt.setString(1, merchName);

				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					merchVO = new MerchVO();
					
					merchVO.setMerchID(rs.getInt("merchID"));
					merchVO.setMerchName(rs.getString("merchName"));
					merchVO.setMerchImg(rs.getBytes("merchImg"));
					merchVO.setMerchInfo(rs.getString("merchInfo"));
					merchVO.setMerchPrice(rs.getInt("merchPrice"));
					merchVO.setMerchStatus(rs.getString("merchStatus"));
				}

			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());

			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return merchVO;

		}
	

	//查詢全部
	@Override
	public List<MerchVO> getAll() {
		List<MerchVO> list = new ArrayList<MerchVO>();
		MerchVO merchVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				merchVO = new MerchVO();
				
				merchVO.setMerchID(rs.getInt("merchID"));
				merchVO.setMerchName(rs.getString("merchName"));
				merchVO.setMerchImg(rs.getBytes("merchImg"));
				merchVO.setMerchInfo(rs.getString("merchInfo"));
				merchVO.setMerchPrice(rs.getInt("merchPrice"));
				merchVO.setMerchStatus(rs.getString("merchStatus"));
				
				list.add(merchVO);
			}


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		MerchJDBCDAO dao = new MerchJDBCDAO();

		
		
	}
}

