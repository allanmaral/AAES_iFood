package br.ufjf.dcc078.Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.ufjf.dcc078.Modelo.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public Usuario read(int idUsuario) {
        Connection conn = null;
        Statement stmt = null;
        Usuario usuario = null;

        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            stmt = conn.createStatement();

            String query = "SELECT * FROM usuario "
                         + "WHERE id_usuario = " + idUsuario;

            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                usuario = (new Usuario())
                          .setNomeCompleto(rs.getString("nome_completo"))
                          .setNomeUsuario(rs.getString("nome_usuario"))
                          .setEmail(rs.getString("email"))
                          .setSenha(rs.getString("email"));
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, stmt);
        }
        
        return usuario;
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
