
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

public class DatabaseMananger {
    private static DatabaseMananger instance = new DatabaseMananger();
    private static Connection connection = null;
    private static Statement statement = null;
    
    private DatabaseMananger() {};
    
    public static DatabaseMananger getInstance(){
        return instance;
    }
    
    public Connection getConnection() throws SQLException, ClassNotFoundException
    {
        //Class.forName("com.mysql.jdbc.Driver");
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ifood", "root", "admin");
        return conn;
    }
    
    public ResultSet executarQuery(String comando) {
        ResultSet rs = null;
        try {
            connection = (Connection) DatabaseMananger.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(comando);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseMananger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void executarStatement(String comando) {
        System.out.println(comando);
        try {
            connection = (Connection) DatabaseMananger.getInstance().getConnection();
            statement = connection.createStatement();
            statement.execute(comando);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseMananger.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
    }
    
    public void closeResources() {
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (statement != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}