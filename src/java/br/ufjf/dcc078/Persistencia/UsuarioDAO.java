package br.ufjf.dcc078.Persistencia;

/**
 *
 * @author douglas
 */
import java.sql.ResultSet;
import java.sql.SQLException;
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

        String comando = "insert into usuario(nomeCompleto, nomeUsuario, email, senha) "
                + "values('"
                + usuario.getNomeCompleto() + "','"
                + usuario.getNomeUsuario() + "', '"
                + usuario.getEmail() + "', '"
                + usuario.getSenha() + "')";
        DatabaseLocator.executarStatement(comando);

    }

    public String read(Usuario usuario) throws
            SQLException, ClassNotFoundException {
        String email = null;
        String comando = "select email from usuario where nome_completo like '%"
                + usuario.getNomeCompleto()
                + "%'";

        ResultSet rs = DatabaseLocator.executarQuery(comando);

        try {
            while (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }

    public Usuario read(int idUsuario) throws
            SQLException, ClassNotFoundException {
        Usuario usuario = null;
        String comando = "SELECT * FROM usuario "
                + "WHERE id_usuario = " + idUsuario;

        ResultSet rs = DatabaseLocator.executarQuery(comando);

        try {
            if (rs.next()) {
                usuario = (new Usuario())
                        .setNomeCompleto(rs.getString("nome_completo"))
                        .setNomeUsuario(rs.getString("nome_usuario"))
                        .setEmail(rs.getString("email"))
                        .setSenha(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }


}
