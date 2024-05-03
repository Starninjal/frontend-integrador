package com.wmw.frontend.integrador.util;

import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

import totalcross.db.sqlite.SQLiteUtil;
import totalcross.sql.Connection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;
import totalcross.sys.Settings;

public class DatabaseManager {
	private SQLiteUtil sqlLite;

	private static DatabaseManager database;

	private Connection conn;

	public Connection getConnection() {
		try {
			return sqlLite.con();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Ocorreu ao conectar com banco!");
		}

	}

	public DatabaseManager() {
		try {
			sqlLite = new SQLiteUtil(Settings.appPath, "app.db");
			Statement st = sqlLite.con().createStatement();
			st.execute(
					"CREATE TABLE if not exists cliente(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT NOT NULL, TIPOPESSOA TEXT NOT NULL, DOCUMENTO TEXT NOT NULL UNIQUE, TELEFONE TEXT NOT NULL, EMAIL TEXT)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DatabaseManager getInstance() {
		if (database == null) {
			database = new DatabaseManager();
		}

		return database;
	}

	public void fechar(PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fechar(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setarAutoCommitDa(Connection conn, boolean isAtivado) {
		try {
			if(conn != null) {
				conn.setAutoCommit(isAtivado);
			}
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao setar o autoCommit da conexão");
		}
		
	}

	
}
