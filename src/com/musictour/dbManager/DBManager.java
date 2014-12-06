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
			url = "jdbc:mysql://localhost/MusicFans?user=root&password=root?useUnicode=true&characterEncoding=UTF-8";
			user = "root";
			password = "root";
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

	// public void update(StreamStatus streamStatus) {
	// long sId = streamStatus.sId;
	// String sName = streamStatus.sName;
	// String sText = streamStatus.sText;
	// double sLatitude = streamStatus.sLatitude;
	// double sLongitude = streamStatus.sLongitude;
	// java.sql.Time sTime = new java.sql.Time(streamStatus.sTime.getTime());
	// java.sql.Date sDate = new java.sql.Date(streamStatus.sTime.getTime());
	// try {
	// stmt = conn.createStatement();
	// PreparedStatement updateStatus = null;
	// sql = "insert into status values(?,?,?,?,?,?,?)";
	//
	// try {
	// updateStatus = conn.prepareStatement(sql);
	// updateStatus.setLong(1, sId);
	// updateStatus.setString(2, sName);
	// updateStatus.setDouble(3, sLatitude);
	// updateStatus.setDouble(4, sLongitude);
	// updateStatus.setString(5, sText);
	// updateStatus.setTime(6, sTime);
	// updateStatus.setDate(7, sDate);
	// // updateStatus.setdat
	//
	// updateStatus.executeUpdate();
	// } catch (SQLException e) {
	// // System.out.println(sId + "||" +sText);
	// } finally {
	// updateStatus.close();
	// }
	// } catch (SQLException e) {
	// System.out.println("sql error");
	// e.printStackTrace();
	// }
	// }
	//
	// public String queryNum() {
	// String out = null;
	// try {
	// stmt = conn.createStatement();
	// sql = "select count(*) as num from status ";
	// rs = stmt.executeQuery(sql);
	// while (rs.next()) {
	// out = rs.getString("num") + "";
	// // System.out.println(out);
	// }
	//
	// } catch (SQLException e) {
	// System.out.println("sql error");
	// e.printStackTrace();
	// }
	// return out;
	// }
	//

	//
	// /*
	// * insert text analyse into attitude table
	// */
	// public void insertAttitude(long sId, String polarity, String score) {
	// java.util.Date dt = new java.util.Date();
	// java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:ss");
	// String currentTime = sdf.format(dt);
	// try {
	// stmt = conn.createStatement();
	// PreparedStatement updateStatus = null;
	// sql = "insert into attitude values(?,?,?,?)";
	//
	// try {
	// updateStatus = conn.prepareStatement(sql);
	// updateStatus.setLong(1, sId);
	// updateStatus.setString(2, polarity);
	// updateStatus.setString(3, score);
	// updateStatus.setString(4, currentTime);
	//
	// updateStatus.executeUpdate();
	// } catch (SQLException e) {
	// // System.out.println(sId + "||" +sText);
	// } finally {
	// updateStatus.close();
	// }
	// } catch (SQLException e) {
	// System.out.println("sql error");
	// e.printStackTrace();
	// }
	// }
	//
	// /*
	// * get all from attitude table and output json
	// */
	// public String getJsonFromAttitudeByTime(String datetime) {
	// JSONObject obj = new JSONObject();
	// JSONArray jarray = new JSONArray();
	// try {
	// try {
	// stmt = conn.createStatement();
	// sql = "select * from attitude where insertDateTime like ?";
	// PreparedStatement preparedStatement = conn
	// .prepareStatement(sql);
	// preparedStatement.setString(1, "%" + datetime + "%");
	// System.out.println(preparedStatement);
	// rs = preparedStatement.executeQuery();
	// while (rs.next()) {
	// String sid = rs.getString("sid");
	// String polarity = rs.getString("polarity");
	// String score = rs.getString("score");
	// // String insertDateTime = rs.getString("insertDateTime");
	// // System.out.println(sid + polarity + score);
	// JSONObject ob = new JSONObject();
	// ob.put("sid", sid);
	// ob.put("polarity", polarity);
	// ob.put("score", score);
	// // ob.put("insertDateTime", insertDateTime);
	// jarray.put(ob);
	// }
	//
	// } catch (SQLException e) {
	// System.out.println("sql error");
	// e.printStackTrace();
	// }
	// obj.put("outcome", jarray);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return obj.toString();
	// }

	public int checkPassWorld(String name, String password, int flag) {
		int num = 0;
		try {
			stmt = conn.createStatement();
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
			stmt = conn.createStatement();
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
			stmt = conn.createStatement();
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
				stmt = conn.createStatement();
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
				stmt = conn.createStatement();
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
			stmt = conn.createStatement();
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
			stmt = conn.createStatement();
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

	public static String getNow() {
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
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

		System.out.print(ma.updateBandProfile("YBand", "123",
				"Y", "F", getNow(),
				"123@yho.com", "www.sd.com"));
		ma.shutdown();
	}
}
