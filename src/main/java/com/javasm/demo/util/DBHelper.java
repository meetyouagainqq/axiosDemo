package com.javasm.demo.util;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @description:
 */
public class DBHelper {

    private static String username;
    private static String userpass;
    private static String drivername;
    private static String url;

    private static Properties prop = new Properties();

    static{
        try {

            /*
            *
            * username=root
                password=root
                url=jdbc:mysql://192.168.17.240:3306/demo
                driverClassName=com.mysql.cj.jdbc.Driver
            * */

            prop.load(DBHelper.class.getResourceAsStream("/db.properties"));
            username = prop.getProperty("username");
            userpass = prop.getProperty("password");
            drivername = prop.getProperty("driverClassName");
            url = prop.getProperty("url");

            Class.forName(drivername);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,username,userpass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }



    public static void closeConn(Connection conn, PreparedStatement psta,ResultSet rs){
        try {
                if(rs!=null){rs.close();}

                if(psta!=null){psta.close();}
                if(conn!=null){conn.close();}
            } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
    }


}
