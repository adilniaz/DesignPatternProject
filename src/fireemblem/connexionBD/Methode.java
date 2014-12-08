package fireemblem.connexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Methode {
    
    protected Connection connection;
    protected ResultSet resultSet;
    
    public Methode(Connection conn) {
        this.connection = conn;
    }
    
    public ResultSet getResultSet () {
        return this.resultSet;
    }
    
    protected PreparedStatement prepareStatement (String requete) {
        PreparedStatement prepare = null;
        try {
            prepare = this.connection.prepareStatement(requete);
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prepare;
    }
    
    protected void setParameterInt(PreparedStatement prepare, int parameter, int place) {
        try {
            prepare.setInt(place, parameter);
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void setParameterDouble(PreparedStatement prepare, double parameter, int place) {
        try {
            prepare.setDouble(place, parameter);
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void setParameterString(PreparedStatement prepare, String parameter, int place) {
        try {
            prepare.setString(place, parameter);
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void setParameterBoolean(PreparedStatement prepare, boolean parameter, int place) {
        try {
            prepare.setBoolean(place, parameter);
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected ResultSet executePreparedStatement (PreparedStatement prepare) {
        ResultSet result = null;
        try {
            result = prepare.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    protected int executeUpdatePreparedStatement (PreparedStatement prepare) {
        int result = 0;
        try {
            result = prepare.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    protected boolean aResult (ResultSet result) {
        boolean reussi;
        try {
            reussi = result.next();
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
            reussi = false;
        }
        return reussi;
    }
    
    protected int getIntResultByString (ResultSet result, String value) {
        int intVal;
        try {
            intVal = result.getInt(value);
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
            intVal = -1;
        }
        return intVal;
    }
    
    protected double getDoubleResultByString (ResultSet result, String value) {
        double doubleVal;
        try {
            doubleVal = result.getDouble(value);
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
            doubleVal = -1;
        }
        return doubleVal;
    }
    
    protected boolean getBooleanResultByString (ResultSet result, String value) {
        boolean val;
        try {
            val = result.getBoolean(value);
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
            val = false;
        }
        return val;
    }
    
    protected String getStringResultByString (ResultSet result, String value) {
        String stringVal;
        try {
            stringVal = result.getString(value);
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
            stringVal = "";
        }
        return stringVal;
    }
    
    protected ResultSet executeQuery (String requete) {
        ResultSet result = null;
        try {
            Statement statement = this.connection.createStatement() ;
            result = statement.executeQuery(requete);
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    protected void closeResultSet (ResultSet result) {
        try {
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected boolean exexuteUpdate (String requete) {
        boolean reussi = true;
        try {
            Statement statement = this.connection.createStatement() ;
            statement.executeUpdate(requete);
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Methode.class.getName()).log(Level.SEVERE, null, ex);
            reussi = false;
        }
        return reussi;
    }
    
    protected boolean estConnecter () {
    	return this.connection != null;
    }
}