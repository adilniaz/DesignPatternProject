package implementations.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    
    private Connection connectionHSQL;
    
    public DBConnection () {
        this.connectionHSQL = null;
    }
    
    public Connection getConnexionHSQL (String location, String user, String mdp) {
        if (this.connectionHSQL == null) {
            try {
                try {
                    Class.forName("org.hsqldb.jdbcDriver").newInstance();
                    try {
                        this.connectionHSQL = DriverManager.getConnection("jdbc:hsqldb:file:"+location, user,  mdp);
                    } catch (SQLException ex) {
                        Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (InstantiationException ex) {
                    Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.connectionHSQL;
    }
    
    public void closeHSQLConnection () {
        if (this.connectionHSQL != null) {
            try {
                Statement statement = connectionHSQL.createStatement();
                statement.execute("SHUTDOWN");
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.connectionHSQL.close();
                this.connectionHSQL = null;
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
