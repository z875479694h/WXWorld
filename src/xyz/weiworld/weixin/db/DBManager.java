package xyz.weiworld.weixin.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;



/**
 * 用于数据库连接配置
 * 
 * @author 张冠诚
 *
 */
public class DBManager {

	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String USERNAME = "weiworld";
	public static final String PASSWORD = "Zhang123.";
	public static final String URL = "jdbc:mysql://localhost:3306/weiworld?useUnicode=true&characterEncoding=GBK&useSSL=false";
	public static DataSource dataSource = null;

	/**准备加载数据源 C3P0*/
	static {
		try {
			ComboPooledDataSource pool = new ComboPooledDataSource();
			pool.setDriverClass(DRIVER_NAME);
			pool.setUser(USERNAME);
			pool.setPassword(PASSWORD);
			pool.setJdbcUrl(URL);
			pool.setMaxPoolSize(300);
			pool.setMinPoolSize(100);
			dataSource = pool;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据连接池加载失败!");
		}
	}

	/**获得Connection连接对象*/
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
