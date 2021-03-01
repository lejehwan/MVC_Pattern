package board;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDataBean {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	static DataSource ds = null;
	static {
		try{
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	public List<BoardDBBean> listBoard() throws SQLException {
		try {
			String sql = "select * from board order by num desc";
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<BoardDBBean> list = makeList(rs);
			return list;
		}finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
	public List<BoardDBBean> makeList(ResultSet rs) throws SQLException {
		List<BoardDBBean> list = new ArrayList<>();
		while(rs.next()) {
			BoardDBBean dto = new BoardDBBean();
			dto.setNum(rs.getInt("num"));
			dto.setWriter(rs.getString("writer"));
			dto.setEmail(rs.getString("email"));
			dto.setSubject(rs.getString("subject"));
			dto.setReg_date(rs.getString("reg_date"));
			dto.setReadcount(rs.getInt("readcount"));
			dto.setContent(rs.getString("content"));
			dto.setIp(rs.getString("ip"));
			list.add(dto);
		}
		return list;
	}
	
	public int insertBoard(BoardDBBean dto) throws SQLException {
		try {
			String sql = "insert into board values"
					+ "(mvc_board_seq.nextval, ?,?,?,?,sysdate,0,?,?)";
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getWriter());
			ps.setString(2, dto.getEmail());
			ps.setString(3, dto.getSubject());
			ps.setString(4, dto.getPasswd());
			ps.setString(5, dto.getContent());
			ps.setString(6, dto.getIp());
			int res = ps.executeUpdate();
			return res;
		}finally {
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
	
	public BoardDBBean getBoard(int num) throws SQLException {
		try {
			String sql = "select * from board where num = ?";
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			List<BoardDBBean> list = makeList(rs);
			return list.get(0);
		}finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
	public boolean isPassword(int num, String passwd) throws SQLException{
		try {
			String sql = "select passwd from board where num = ?";
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1,num);
			rs = ps.executeQuery();
			if (rs.next()) {
				String dbPass = rs.getString(1);
				if (dbPass.trim().equals(passwd)) {
					return true;
				}
			}
			return false;
		}finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
	public int deleteBoard(int num, String passwd) throws SQLException {
		if (!isPassword(num, passwd)) {
			return -1;
		}
		try {
			String sql ="delete from board where num =?";
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			int res = ps.executeUpdate();
			return res;
		}finally {
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
	
	public int updateBoard(BoardDBBean dto) throws SQLException {
		if (!isPassword(dto.getNum(), dto.getPasswd())) {
			return -1;
		}
		try {
			String sql = 
					"update board set writer=?, email=?, subject=?, content=? where num = ?";
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getWriter());
			ps.setString(2, dto.getEmail());
			ps.setString(3, dto.getSubject());
			ps.setString(4, dto.getContent());
			ps.setInt(5, dto.getNum());
			int res = ps.executeUpdate();
			return res;
		}finally {
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
	
	public int plusReadCount(int num) throws SQLException{
		try {
			String sql = "update board set readcount = readcount + 1 where num =?";
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			int res = ps.executeUpdate();
			return res;
		} finally {
			if (ps != null) ps.close();
			if (con != null) con.close();
		}
	}
}













