package app.mrquan.util;

import java.sql.*;

public class DBUtil {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://47.94.13.255:3306/system?useUnicode=true&characterEncoding=utf8";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "quan";
    public static Connection createConnection() {
        Connection con = null;
        try {
            Class.forName(DBDRIVER);
            con = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void close(Connection con, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (pst != null)
                pst.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
