package com.musictour.dbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DBManager {
	Connection conn;
	Statement stmt;
	String url;
	String user;
	String password;
	String sql;
	ResultSet rs;

	public DBManager() {
		conn = null;
		stmt = null;
		url = null;
		user = null;
		password = null;
		sql = null;
		rs = null;
	}

	public void getDirver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("load driver error");
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			url = "jdbc:mysql://localhost/MusicFans?useUnicode=true&characterEncoding=UTF-8";
			user = "root";
			password = "";
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("connect error");
			e.printStackTrace();
		}
	}

	public void shutdown() {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			System.out.println("close db error");
			// e.printStackTrace();
		}
	}

	public int checkPassWorld(String name, String password, int flag) {
		int num = 0;
		try {
			sql = null;
			if (flag == 0) {
				sql = "select count(*) as num from user where uname = ? and upassword = ?";
			} else {
				sql = "select count(*) as num from band where bname = ? and bpassword = ?";
			}
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				num = rs.getInt("num");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public boolean isExistEmail(String email, int flag) {
		int num = 0;
		try {
			sql = null;
			if (flag == 0) {
				sql = "select count(*) as num from user where uemail = ?";
			} else {
				sql = "select count(*) as num from band where bemail = ?";
			}
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, email);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				num = rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num > 0;
	}

	public boolean isExistUsername(String name, int flag) {
		int num = 0;
		try {
			sql = null;
			if (flag == 0) {
				sql = "select count(*) as num from user where uname = ?";
			} else {
				sql = "select count(*) as num from band where bname = ?";
			}
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				num = rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num > 0;
	}

	// insert for user and band
	// 0 sql err; 1 success; 2 exist email; 3 exist name
	public int insertUser(String uname, String upassword, int uscore,
			String ulastname, String ufirstname, String ubirthdate,
			String uemail, String ucity, String website, int flag) {

		if (flag == 0) {
			if (isExistUsername(uname, 0))
				return 3;
			if (isExistEmail(uemail, 0))
				return 2;
			try {
				PreparedStatement updateStatus = null;
				sql = "insert into user values(?,?,?,?,?,?,?,?)";
				try {
					updateStatus = conn.prepareStatement(sql);
					updateStatus.setString(1, uname);
					updateStatus.setString(2, upassword);
					updateStatus.setInt(3, uscore);
					updateStatus.setString(4, ulastname);
					updateStatus.setString(5, ufirstname);
					updateStatus.setString(6, ubirthdate);
					updateStatus.setString(7, uemail);
					updateStatus.setString(8, ucity);
					updateStatus.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					return 0;
				} finally {
					updateStatus.close();
				}
			} catch (SQLException e) {
				System.out.println("sql error");
				e.printStackTrace();
				return 0;
			}
			return 1;
		} else {
			if (isExistUsername(uname, 1))
				return 3;
			if (isExistEmail(uemail, 1))
				return 2;
			try {
				PreparedStatement updateStatus = null;
				sql = "insert into band values(?,?,?,?,?,?,?)";
				try {
					updateStatus = conn.prepareStatement(sql);
					updateStatus.setString(1, uname);
					updateStatus.setString(2, upassword);
					updateStatus.setString(3, ulastname);
					updateStatus.setString(4, ufirstname);
					updateStatus.setString(5, ubirthdate);
					updateStatus.setString(6, uemail);
					updateStatus.setString(7, website);
					updateStatus.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					return 0;
				} finally {
					updateStatus.close();
				}
			} catch (SQLException e) {
				System.out.println("sql error");
				e.printStackTrace();
				return 0;
			}
			return 1;
		}
	}

	public String getProfile(String name, int flag) {
		JSONObject out = new JSONObject();
		try {
			sql = null;
			if (flag == 0) {
				sql = "select * from user where uname = ?";
			} else {
				sql = "select * from band where bname = ?";
			}
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			rs = preparedStatement.executeQuery();
			if (flag == 0) {
				while (rs.next()) {
					// uname upassword uscore ulastname ufirstname ubirthdate
					// uemail ucity
					out.put("uname", rs.getString("uname"));
					out.put("upassword", rs.getString("upassword"));
					out.put("uscore", rs.getString("uscore"));
					out.put("ulastname", rs.getString("ulastname"));
					out.put("ufirstname", rs.getString("ufirstname"));
					out.put("ubirthdate", rs.getString("ubirthdate"));
					out.put("uemail", rs.getString("uemail"));
					out.put("ucity", rs.getString("ucity"));
				}
			} else {
				while (rs.next()) {
					// bname bpassword blastname bfirstname bbirthdate bemail
					// bwebsite
					out.put("bname", rs.getString("bname"));
					out.put("bpassword", rs.getString("bpassword"));
					out.put("blastname", rs.getString("blastname"));
					out.put("bfirstname", rs.getString("bfirstname"));
					out.put("bbirthdate", rs.getString("bbirthdate"));
					out.put("bemail", rs.getString("bemail"));
					out.put("bwebsite", rs.getString("bwebsite"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out.toJSONString();
	}

	public int updateBandProfile(String bname, String bpassword,
			String blastname, String bfirstname, String bbirthdate,
			String bemail, String bwebsite) {
		try {
			PreparedStatement updateStatus = null;
			sql = "update band set bpassword = ?, blastname = ?, bfirstname = ?, bbirthdate = ?, bemail = ?, bwebsite = ? WHERE bname = ?";
			try {
				updateStatus = conn.prepareStatement(sql);
				updateStatus.setString(1, bpassword);
				updateStatus.setString(2, blastname);
				updateStatus.setString(3, bfirstname);
				updateStatus.setString(4, bbirthdate);
				updateStatus.setString(5, bemail);
				updateStatus.setString(6, bwebsite);
				updateStatus.setString(7, bname);
				updateStatus.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				updateStatus.close();
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public int addConcert(String cname, String bname, String cdatetime,
			String cprice, String cwebsite, String vname, String uname,
			String confirmed) {

		int maxCid = 1;
		try {
			sql = "SELECT IFNULL(MAX(cid)+1,1) FROM concert";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				maxCid = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
			return 0;
		}

		try {
			PreparedStatement updateStatus = null;
			sql = "insert into concert values(?,?,?,?,?,?,?,?,?)";
			try {
				updateStatus = conn.prepareStatement(sql);
				updateStatus.setInt(1, maxCid);
				updateStatus.setString(2, cname);
				updateStatus.setString(3, bname);
				updateStatus.setString(4, cdatetime);
				updateStatus.setString(5, cprice);
				updateStatus.setString(6, cwebsite);
				updateStatus.setString(7, vname);
				updateStatus.setString(8, uname);
				updateStatus.setString(9, confirmed);
				updateStatus.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				updateStatus.close();
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public String getConcertByBandName(String bname) {
		JSONArray array = new JSONArray();
		try {
			sql = null;
			sql = "select * from concert where bname = ? and uname is null";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, bname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				// cid cname bname cdatetime cprice cwebsite vname uname
				// confirmed
				obj.put("cid", rs.getString("cid"));
				obj.put("cname", rs.getString("cname"));
				obj.put("bname", rs.getString("bname"));
				obj.put("cdatetime", rs.getString("cdatetime"));
				obj.put("cprice", rs.getString("cprice"));
				obj.put("cwebsite", rs.getString("cwebsite"));
				obj.put("vname", rs.getString("vname"));
				obj.put("uname", rs.getString("uname"));
				obj.put("confirmed", rs.getString("confirmed"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public String getConcertByUserName(String uname) {
		JSONArray array = new JSONArray();
		try {
			sql = null;
			sql = "select * from concert where uname = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, uname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				// cid cname bname cdatetime cprice cwebsite vname uname
				// confirmed
				obj.put("cid", rs.getString("cid"));
				obj.put("cname", rs.getString("cname"));
				obj.put("bname", rs.getString("bname"));
				obj.put("cdatetime", rs.getString("cdatetime"));
				obj.put("cprice", rs.getString("cprice"));
				obj.put("cwebsite", rs.getString("cwebsite"));
				obj.put("vname", rs.getString("vname"));
				obj.put("uname", rs.getString("uname"));
				obj.put("confirmed", rs.getString("confirmed"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public String getConcertByAttend(String uname) {
		JSONArray array = new JSONArray();
		try {
			sql = null;
			sql = "select c.cid, c.cname, c.bname, c.cdatetime, c.cprice, c.cwebsite, c.vname, c.uname, c.confirmed from attend a, concert c where a.cid = c.cid and a.uname = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, uname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				// cid cname bname cdatetime cprice cwebsite vname uname
				// confirmed
				obj.put("cid", rs.getString("cid"));
				obj.put("cname", rs.getString("cname"));
				obj.put("bname", rs.getString("bname"));
				obj.put("cdatetime", rs.getString("cdatetime"));
				obj.put("cprice", rs.getString("cprice"));
				obj.put("cwebsite", rs.getString("cwebsite"));
				obj.put("vname", rs.getString("vname"));
				obj.put("uname", rs.getString("uname"));
				obj.put("confirmed", rs.getString("confirmed"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}
	
	public int addAttend(String uname, String cid, String attended) {
		try {
			PreparedStatement updateStatus = null;
			sql = "insert into attend values(?, ?, ?)";
			try {
				updateStatus = conn.prepareStatement(sql);
				updateStatus.setString(1, uname);
				updateStatus.setString(2, cid);
				updateStatus.setString(3, attended);
				updateStatus.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				updateStatus.close();
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public int updateAttend(String attended, String uname, String cid) {
		try {
			PreparedStatement updateStatus = null;
			sql = "update attend set attend = ? where uname = ? and cid = ?";
			try {
				updateStatus = conn.prepareStatement(sql);
				updateStatus.setString(1, attended);
				updateStatus.setString(2, uname);
				updateStatus.setString(3, cid);
				updateStatus.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				updateStatus.close();
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public String getAllConcert() {
		JSONArray array = new JSONArray();
		try {
			sql = null;
			sql = "select * from concert";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			// preparedStatement.setString(1, uname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				// cid cname bname cdatetime cprice cwebsite vname uname
				// confirmed
				obj.put("cid", rs.getString("cid"));
				obj.put("cname", rs.getString("cname"));
				obj.put("bname", rs.getString("bname"));
				obj.put("cdatetime", rs.getString("cdatetime"));
				obj.put("cprice", rs.getString("cprice"));
				obj.put("cwebsite", rs.getString("cwebsite"));
				obj.put("vname", rs.getString("vname"));
				obj.put("uname", rs.getString("uname"));
				obj.put("confirmed", rs.getString("confirmed"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public String getAllBand() {
		JSONArray array = new JSONArray();
		try {
			sql = null;
			sql = "select * from band";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			// preparedStatement.setString(1, uname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				// cid cname bname cdatetime cprice cwebsite vname uname
				// confirmed
				obj.put("bname", rs.getString("bname"));
				obj.put("bpassword", rs.getString("bpassword"));
				obj.put("blastname", rs.getString("blastname"));
				obj.put("bfirstname", rs.getString("bfirstname"));
				obj.put("bbirthdate", rs.getString("bbirthdate"));
				obj.put("bemail", rs.getString("bemail"));
				obj.put("bwebsite", rs.getString("bwebsite"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public String getRatedConcertByUserName(String uname) {
		JSONArray array = new JSONArray();
		try {
			sql = null;
			sql = "select * from concert c, rate r where r.uname = ? and c.cid = r.cid";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, uname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				// cid cname bname cdatetime cprice cwebsite vname uname
				// confirmed
				obj.put("cid", rs.getString("cid"));
				obj.put("cname", rs.getString("cname"));
				obj.put("bname", rs.getString("bname"));
				obj.put("cdatetime", rs.getString("cdatetime"));
				obj.put("cprice", rs.getString("cprice"));
				obj.put("cwebsite", rs.getString("cwebsite"));
				obj.put("vname", rs.getString("vname"));
				obj.put("uname", rs.getString("uname"));
				obj.put("confirmed", rs.getString("confirmed"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public String getRatedByConcert(String cid) {
		JSONArray array = new JSONArray();
		try {
			sql = null;
			sql = "select * from rate where cid = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, cid);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("uname", rs.getString("uname"));
				obj.put("cid", rs.getString("cid"));
				obj.put("rate", rs.getString("rate"));
				obj.put("review", rs.getString("review"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public static String getNow() {
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}

	public int addRate(String uname, String cid, String rate, String review) {
		// uname cid rate review
		try {
			PreparedStatement updateStatus = null;
			sql = "insert into rate values(?,?,?,?)";
			try {
				updateStatus = conn.prepareStatement(sql);
				updateStatus.setString(1, uname);
				updateStatus.setString(2, cid);
				updateStatus.setString(3, rate);
				updateStatus.setString(4, review);
				updateStatus.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				updateStatus.close();
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public int updateUserScore(String uname, int uscore) {
		try {
			PreparedStatement updateStatus = null;
			sql = "update user set uscore = uscore + ? where uname = ?";
			try {
				updateStatus = conn.prepareStatement(sql);
				updateStatus.setInt(1, uscore);
				updateStatus.setString(2, uname);
				updateStatus.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				updateStatus.close();
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public String getBandByFan(String uname) {
		JSONArray array = new JSONArray();
		try {
			sql = "select bname from fan where uname = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, uname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("bname", rs.getString("bname"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public String getFollowerByFollow(String uname) {
		JSONArray array = new JSONArray();
		try {
			sql = "select follower from fan where uname = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, uname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("follower", rs.getString("follower"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public String getUserByFan(String bname) {
		JSONArray array = new JSONArray();
		try {
			sql = "select uname from fan where bname = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, bname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("uname", rs.getString("uname"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	// get uname's following
	public String getFollowingByFollow(String uname) {
		JSONArray array = new JSONArray();
		try {
			sql = "select uname from follow where follower = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, uname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("uname", rs.getString("uname"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public int addFan(String uname, String bname) {
		try {
			PreparedStatement updateStatus = null;
			sql = "insert into fan values(?, ?)";
			try {
				updateStatus = conn.prepareStatement(sql);
				updateStatus.setString(1, uname);
				updateStatus.setString(2, bname);
				updateStatus.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				updateStatus.close();
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public int addFollow(String uname, String follower) {
		try {
			PreparedStatement updateStatus = null;
			sql = "insert into follow values(?, ?)";
			try {
				updateStatus = conn.prepareStatement(sql);
				updateStatus.setString(1, uname);
				updateStatus.setString(2, follower);
				updateStatus.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				updateStatus.close();
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public int deleteFan(String uname, String bname) {
		try {
			PreparedStatement updateStatus = null;
			sql = "delete from fan where uname = ? and bname = ?";
			try {
				updateStatus = conn.prepareStatement(sql);
				updateStatus.setString(1, uname);
				updateStatus.setString(2, bname);
				updateStatus.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				updateStatus.close();
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public int deleteFollow(String uname, String follower) {
		try {
			PreparedStatement updateStatus = null;
			sql = "delete from fan where uname = ? and follower = ?";
			try {
				updateStatus = conn.prepareStatement(sql);
				updateStatus.setString(1, uname);
				updateStatus.setString(2, follower);
				updateStatus.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				updateStatus.close();
			}
		} catch (SQLException e) {
			System.out.println("sql error");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public String getConcertByFan(String uname) {
		JSONArray array = new JSONArray();
		try {
			sql = "select c.cid, c.cname, c.bname, c.cdatetime, c.cprice, "
					+ "c.cwebsite, c.vname, c.uname, c.confirmed from fan f, "
					+ "concert c where f.uname = ? and f.bname = c.bname";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, uname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("cid", rs.getString("cid"));
				obj.put("cname", rs.getString("cname"));
				obj.put("bname", rs.getString("bname"));
				obj.put("cdatetime", rs.getString("cdatetime"));
				obj.put("cprice", rs.getString("cprice"));
				obj.put("cwebsite", rs.getString("cwebsite"));
				obj.put("vname", rs.getString("vname"));
				obj.put("uname", rs.getString("uname"));
				obj.put("confirmed", rs.getString("confirmed"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public String getAllType() {
		JSONArray array = new JSONArray();
		try {
			sql = null;
			sql = "select * from type";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			// preparedStatement.setString(1, uname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("tname", rs.getString("tname"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public String getStypeByType(String tname) {
		JSONArray array = new JSONArray();
		try {
			sql = null;
			sql = "select * from subtype where tname = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, tname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("stname", rs.getString("stname"));
				obj.put("tname", rs.getString("tname"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public String recommendConcertByFan(String uname) {
		JSONArray array = new JSONArray();
		try {
			sql = null;
			sql = "select c.cid, c.cname, c.bname, c.cdatetime, c.cprice, c.cwebsite, c.vname, c.confirmed "
					+ "from fan f1, fan f2, fan f3, concert c  "
					+ "where f1.uname = 'zy123' "
					+ "and f1.uname <> f2.uname "
					+ "and f1.bname = f2.bname  "
					+ "and f2.uname = f3.uname  "
					+ "and f3.bname <> f1.bname "
					+ "and f3.bname = c.bname  "
					+ "group by c.cid having count(*) >= 2";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, uname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("cid", rs.getString("cid"));
				obj.put("cname", rs.getString("cname"));
				obj.put("bname", rs.getString("bname"));
				obj.put("cdatetime", rs.getString("cdatetime"));
				obj.put("cprice", rs.getString("cprice"));
				obj.put("cwebsite", rs.getString("cwebsite"));
				obj.put("vname", rs.getString("vname"));
				obj.put("confirmed", rs.getString("confirmed"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	// get similar type band
	public String recommendBandByType(String uname) {
		JSONArray array = new JSONArray();
		try {
			sql = null;
			sql = "select f2.bname "
					+ "from fan f1, usertype t1, fan f2, usertype t2 "
					+ "where f1.uname=? " + "and f1.uname = t1.uname "
					+ "and f2.uname <> f1.uname " + "and t1.tname = t2.tname "
					+ "and f1.bname <> f2.bname;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, uname);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("bname", rs.getString("bname"));
				array.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array.toJSONString();
	}

	public static void main(String args[]) {
		DBManager ma = new DBManager();
		ma.getDirver();
		ma.connect();
		// System.out.println(ma.checkPassWorld("justin123", "abc123", 0));
		// System.out.println(ma.checkPassWorld("MBand", "addd", 1));

		// java.util.Date dt = new java.util.Date();
		// java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
		// "yyyy-MM-dd HH:mm:ss");
		// String currentTime = sdf.format(dt);
		// int out = ma.insertUser("niu1236", "123", 0,
		// "", "", currentTime,
		// "123@gmail.com", "", "", 1);
		// System.out.print(out);
		// ma.addConcert("cname","bname","2014-12-06 22:00:25","5","url","vname",
		// null,"1");
		// System.out.print(ma.addRate("zy123", "1", "3", "good"));
		// System.out.print(ma.getRatedByConcert("1"));
		// ma.addConcert("concert1", "band1", getNow(), "4", "www.q", "8av",
		// "sdf", "1");
		System.out.println(ma.recommendConcertByFan("zy123"));
		// ma.deleteFan("zy123", "MBand");
		ma.shutdown();
	}
}
