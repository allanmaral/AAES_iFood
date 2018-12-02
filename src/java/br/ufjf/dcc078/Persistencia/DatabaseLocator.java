/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author douglas
 */

public class DatabaseLocator {
    private static DatabaseLocator instance = new DatabaseLocator();
    
    private DatabaseLocator() {};
    
    public static DatabaseLocator getInstance(){
        return instance;
    }
    
    public Connection getConnection() throws SQLException, ClassNotFoundException
    {
        //Class.forName("com.mysql.jdbc.Driver");
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ifood", "root", "admin");
        return conn;
    }
    
    public static ResultSet executarQuery(String comando) 
            {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(comando);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseLocator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, stmt);
        }
        return rs;
    }
    
    public static void excutarStatement(String comando) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            stmt = conn.createStatement();
            stmt.execute(comando);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseLocator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, stmt);
        }
    }
    
    public static void closeResources(Connection conn, Statement st) {
        try {
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}