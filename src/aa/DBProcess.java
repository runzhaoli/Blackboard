package aa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DBProcess {
	
	public static String DB = "registerDB";
	
	public static void main(String[] args) {
//		String table = "post";
//		String[] info = new String[4];
//		info[0] = "ccc";
//		info[1] = "6";
//		info[2] = "student";
//		info[3] = "gentleman and mellow";
//		String str = arrayTOstr(reading(table));
//		System.out.println(str);
//		writing(table, info);
//		String id = "bbb";
//		String name = "1";
//		delete(table, "ccc", "6");
//		update(table, info);
		
//		String[] info = query(table, "aaa", "2");
//		for (int i = 0; i < info.length; i++) {
//			System.out.println(info[i]);
//		}
		
	}
	
	public static Connection connectDB(String database) {
		Connection con;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/" + database;
		String user = "root";
		String password = "1234";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			if(!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			
			return con;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("can not connect");
		}
		
		return null;
	}
	
	public static void update(String table, String[] info) {
		
		String id = info[0];
		String idORname = info[2];
		delete(table, id, idORname);
		write(table, info);
	}
	
	public static void delete(String table, String id, String idORname) {
		
		Connection con = connectDB(DB);
		if(con == null) {
			return;
		}
		
		try {
			Statement statement = con.createStatement();
			String sql = "select * from " + table;
			ResultSet rs = statement.executeQuery(sql);
//			int col = rs.getMetaData().getColumnCount();
			ResultSetMetaData data = rs.getMetaData();
			
			String str01 = data.getColumnName(1);
			String str02 = data.getColumnName(2);
			
			sql = "delete from " + table + " where " + str01 + " = '" + id + 
					"' and " + str02 + " = '" + idORname + "'";
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			
			closeDB(con);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static String[] metaData(String table) {
		
		Connection con = connectDB(DB);
		if(con == null) {
			return null;
		}
		
		try {
			Statement statement = con.createStatement();
			String sql = "select * from " + table;
			ResultSet rs = statement.executeQuery(sql);
			
			ResultSetMetaData data = rs.getMetaData();
			int col = data.getColumnCount();
			
			String[] str = new String[col];
			
			for (int i = 0; i < col; i++) {
				String name = data.getColumnName(i + 1);
				str[i] = name;
			}
			
			return str;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public static void write(String table, String[] info) {
		
		Connection con = connectDB(DB);
		if(con == null) {
			return;
		}
		
		try {
			Statement statement = con.createStatement();
			String sql = "select * from " + table;
			ResultSet rs = statement.executeQuery(sql);
			
			int col = rs.getMetaData().getColumnCount();
			ResultSetMetaData data = rs.getMetaData();
			
			String str = "(";
			for (int i = 0; i < col; i++) {
				if(i == col - 1) {
					str = str + "?)";
				}else {
					str = str + "?,";
				}
			}
			sql = "insert into " + table + " values" + str;
			
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
			
			for (int i = 0; i < col; i++) {
				
				String type = data.getColumnTypeName(i+1).trim();
				if(type.equals("INT")) {
					pstmt.setInt(i + 1, Integer.parseInt(info[i]));
				}else {
					pstmt.setString(i + 1, info[i]);
				}
			}
			pstmt.executeUpdate();
			pstmt.close();
			closeDB(con);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static String[] query(String table, String id, String idORname) {
		
		List<List<String>> list = read(table);
		for (int i = 0; i < list.size(); i++) {
			List<String> str = list.get(i);
			
			String str01 = str.get(0).trim();
			String str02 = str.get(1).trim();
			if(id.equals(str01) && idORname.equals(str02)) {
				
				int num = str.size();
				String[] info = new String[num];
				for (int j = 0; j < num; j++) {
					info[j] = str.get(j) + "";
				}
				
				return info;
			}
		}
		
		return null;
	}
	
	public static List<List<String>> read(String table){
		
		return read(DB, table);
	}
	
	public static List<List<String>> read(String database, String table) {
		List<List<String>> list = new LinkedList<>();
		
		Connection con = connectDB(database);
		if(con == null) {
			return null;
		}
		
		try {
			Statement statement = con.createStatement();
			String sql = "select * from " + table;
			ResultSet rs = statement.executeQuery(sql);
			
//			int row = rs.getRow();
			int col = rs.getMetaData().getColumnCount();
//			ResultSetMetaData data = rs.getMetaData();
			
			while(rs.next()) {
				List<String> str = new LinkedList<>();
				for (int i = 0; i < col; i++) {
					str.add(rs.getString(i + 1));
				}
				list.add(str);
			}
			
			rs.close();
			closeDB(con);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	
	public static void closeDB(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String arrayTOstr(List<List<String>> list) {
		String string = "";
		for (int i = 0; i < list.size(); i++) {
			List<String> str = list.get(i);
			for (int j = 0; j < str.size(); j++) {
				string = string + (String)str.get(j) + "\t";
			}
			string = string + "\n";
		}
		
		return string;		
	}
	
}
