package ppNaver;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class ConnectionManager {

    public Connection getConnection(){
        Connection con = null;
        try {
            InitialContext initCtx = new InitialContext();
            Context ctx = (Context)initCtx.lookup("java:comp/env/");
            DataSource ds = (DataSource)ctx.lookup("jdbc/oracle");
            con = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    //    public Connection getConnection(){//CM
//        Connection con = null;
//        String url = "jdbc:oracle:thin:@localhost:1521:xe";
//        String driver = "oracle.jdbc.OracleDriver";
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url, "hr","1234");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return con;
//    }
    public void connectionClose(ResultSet rs, Statement stmt, Connection con){
        try {
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(con != null){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
