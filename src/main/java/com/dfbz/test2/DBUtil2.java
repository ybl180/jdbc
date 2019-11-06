package com.dfbz.test2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil2 {
    private static String driver;
    private static String url;
    private static String userName;
    private static String password;

    static {
        Properties pro = new Properties();
        try {
            pro.load(DBUtil2.class.getResourceAsStream("/res/db.properties"));
            driver = pro.getProperty("driver");
            url = pro.getProperty("url");
            userName = pro.getProperty("userName");
            password = pro.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        new DBUtil2();
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        System.out.println(getConn());
    }
}
