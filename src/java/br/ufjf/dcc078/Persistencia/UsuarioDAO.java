/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Persistencia;

/**
 *
 * @author douglas
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.ufjf.dcc078.Dominio.Usuario;

public class UsuarioDAO {

    private static UsuarioDAO instance = new UsuarioDAO();

    private UsuarioDAO() {
    }

    ;
    
    public static UsuarioDAO getInstance() {
        return instance;
    }

    public void save(Usuario usuario) throws
            SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;

        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("insert into usuario(nomeCompleto, nomeUsuario, email, senha) "
                    + "values('"
                    + usuario.getNomeCompleto() + "','"
                    + usuario.getNomeUsuario() + "', '"
                    + usuario.getEmail() + "', '"
                    + usuario.getSenha() + "')"
            );
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public String read(Usuario usuario) throws
            SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        String email = null;

        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            stmt = conn.createStatement();

            String query = "select email from usuario where nome_completo like '%"
                    + usuario.getNomeCompleto()
                    + "%'";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                email = rs.getString("email");
            }

            return email;
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, stmt);
        }
    }

    public void closeResources(Connection conn, Statement st) {
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
