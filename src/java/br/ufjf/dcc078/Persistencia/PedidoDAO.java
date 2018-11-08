/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Persistencia;

import br.ufjf.dcc078.Modelo.Componente;
import br.ufjf.dcc078.Modelo.Pedido;
import br.ufjf.dcc078.Modelo.Usuario;
import br.ufjf.dcc078.State.StateFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author douglas
 */
public class PedidoDAO {

    private static PedidoDAO instance = new PedidoDAO();

    private PedidoDAO() {
    }

    ;
    
    public static PedidoDAO getInstance() {
        return instance;
    }

    public void save(Pedido pedido) throws
            SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;

        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("insert into pedido(id_usuario, titulo, descricao, status, promocao) "
                    + "values('"
                    + pedido.getUsuario().getId() + "','"
                    + pedido.getTitulo() + "', '"
                    + pedido.getStatus() + "', '"
                    + pedido.getPromocao() + "')"
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
        String id_pedido = null;

        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            stmt = conn.createStatement();

            String query = "select id_pedido from pedido where id_usuario = '%"
                    + usuario.getId()
                    + "%'";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                id_pedido = rs.getString("id_pedido");
            }

            return id_pedido;
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, stmt);
        }
    }
    
    public ArrayList<Pedido> readList(Usuario usuario) {
        ArrayList<Pedido> lista = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        
        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM pedido WHERE (id_usuario = " + usuario.getId() + ")";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                int idPromocao = rs.getInt("id_promocao");
                String estado = rs.getString("estado");
                
                Pedido pedido = new Pedido();
                
                pedido.setUsuario(usuario)
                      .setId(rs.getInt("id_pedido"))
                      .setTitulo(rs.getString("titulo"))
                      .setEstado(StateFactory.create(estado));
                
                System.out.println("Testando");
                pedido.setLista(ComponentePedidoDAO.getInstance().readListByOrder(pedido));
                
                lista.add(pedido);
                
                
                
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResources(conn, st);
        }
        
        return lista;
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
