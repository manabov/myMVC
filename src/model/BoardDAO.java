package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;

	public BoardDAO() {
		try {
			/// 주석 단다 또 달아 또 달아 또
			// 한번 더 달아볼까?? 3번째로..!!

			//경민오빠 하이요
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/oracleDB");
			con = ds.getConnection();
			System.out.println(con);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getRnum(int id) {
		int rnum = 1;
		try {
			sql = "select * from "
					+ "(select rownum rnum, tt.* from "
					+ "(select * from mvcBoard order by gid desc, seq) tt) "
					+ "where id = ?";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();
			if(rs.next()) {
				rnum = rs.getInt("RNUM");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return rnum;
	}

	public int totalCnt() {

		try {
			sql = "select count(*) from mvcBoard";
			ptmt = con.prepareStatement(sql);
			rs=ptmt.executeQuery();
			rs.next();
			return rs.getInt(1);

		} catch(Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public ArrayList<BoardVO> list(int start, int end) {

		ArrayList<BoardVO> arr = new ArrayList<>();
		try {
			sql = "select * from "
					+ "(select rownum rnum, tt.* from "
					+ "(select * from mvcBoard order by gid desc, seq) tt) "
					+ "where rnum >= ? and rnum <= ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, start);
			ptmt.setInt(2, end);
			rs = ptmt.executeQuery();

			while(rs.next()) {
				BoardVO vo = new BoardVO();

//				vo.setGid(rs.getInt("GID"));
//				vo.setSeq(rs.getInt("SEQ"));

				vo.setId(rs.getInt("ID"));
				vo.setLev(rs.getInt("LEV"));
				vo.setCnt(rs.getInt("CNT"));
				vo.setReg_date(rs.getTimestamp("REG_DATE"));

				vo.setPname(rs.getString("PNAME"));
//				vo.setPw(rs.getString("PW"));
				vo.setContent(rs.getString("CONTENT"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setUpfile(rs.getString("UPFILE"));

				arr.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return arr;
	}

	public int insert(BoardVO vo) {
		int nextId = 0;
		try {

			sql = "select max(id)+1 from mvcBoard";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			nextId = rs.getInt(1);

			sql = "insert into mvcBoard(id, gid, seq, lev, cnt, reg_date, pname, pw, title, content, upfile) "
					+ "values(?, ?, 0, 0, -1, sysdate, ?, ?, ?, ?, ?)";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, nextId);
			ptmt.setInt(2, nextId);

			ptmt.setString(3, vo.getPname());
			ptmt.setString(4, vo.getPw());
			ptmt.setString(5, vo.getTitle());
			ptmt.setString(6, vo.getContent());
			ptmt.setString(7, vo.getUpfile());

			ptmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		}
		return nextId;
	}

	public void addCnt(int id) {
		try {
			sql = "update mvcBoard set cnt=cnt+1 where id=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public BoardVO detail(int id) {
		try {
			sql = "select * from mvcBoard where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1,id);
			rs = ptmt.executeQuery();

			if(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setId(rs.getInt("ID"));
				vo.setGid(rs.getInt("GID"));
				vo.setSeq(rs.getInt("SEQ"));
				vo.setLev(rs.getInt("LEV"));
				vo.setCnt(rs.getInt("CNT"));
				vo.setReg_date(rs.getTimestamp("REG_DATE"));

				vo.setPname(rs.getString("PNAME"));
				vo.setPw(rs.getString("PW"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setContent(rs.getString("CONTENT"));
				vo.setUpfile(rs.getString("UPFILE"));
				return vo;
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BoardVO search(BoardVO vo) {

		try {
			sql = "select * from mvcBoard where id = ? and pw = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, vo.getId());
			ptmt.setString(2, vo.getPw());
			rs = ptmt.executeQuery();
			if(rs.next()) {
				BoardVO res = new BoardVO();
				res.setUpfile(rs.getString("UPFILE"));
				return res;
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void delete(int id) {
		try {
			sql = "delete from mvcBoard where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void fileDelete(int id) {

		try {
			sql = "update mvcBoard set upfile=null where id=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void modify(BoardVO vo) {

		try {
			sql = "update mvcBoard set pname=?, title=?, content=?, upfile=? where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, vo.getPname());
			ptmt.setString(2, vo.getTitle());
			ptmt.setString(3, vo.getContent());
			ptmt.setString(4, vo.getUpfile());
			ptmt.setInt(5, vo.getId());

			ptmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int reply(BoardVO vo) {
		int nextId=0;
		try {

			sql = "select max(id)+1 from mvcBoard";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			nextId = rs.getInt(1);

			BoardVO orig = detail(vo.getId());
			sql = "update mvcBoard set seq=seq+1 where gid=? and seq>?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, orig.getGid());
			ptmt.setInt(2, orig.getSeq());
			ptmt.executeUpdate();

			sql = "insert into mvcBoard(id, gid, seq, lev, cnt, reg_date, pname, pw, title, content) "
					+ "values(?, ?, ?, ?, -1, sysdate, ?, ?, ?, ?)";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, nextId);
			ptmt.setInt(2, orig.getGid());
			ptmt.setInt(3, orig.getSeq()+1);
			ptmt.setInt(4, orig.getLev()+1);
			ptmt.setString(5, vo.getPname());
			ptmt.setString(6, vo.getPw());
			ptmt.setString(7, vo.getTitle());
			ptmt.setString(8, vo.getContent());

			ptmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}

		return nextId;
	}

	public void close() {
		if(rs!=null) try {rs.close();} catch(Exception e) {e.printStackTrace();}
		if(ptmt!=null) try {ptmt.close();} catch(Exception e) {e.printStackTrace();}
		if(con!=null) try {con.close();} catch(Exception e) {e.printStackTrace();}
	}
}
