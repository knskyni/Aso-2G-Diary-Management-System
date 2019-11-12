package diary.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Dao {
    protected Connection con = null;

    public void connect() {
        // If connected, do nothing
        if(con != null) {
            return;
        }

        // Database connect
        InitialContext context = null;
        try {
            String jndi = "java:comp/env/jdbc/MySQL";
            context = new InitialContext();
            DataSource dataSource = (DataSource)context.lookup(jndi);
            con = dataSource.getConnection();
        } catch(NamingException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if(con != null) {
            try {
                con.close();
                con = null;
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
