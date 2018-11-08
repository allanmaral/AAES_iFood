/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Persistencia;

import br.ufjf.dcc078.Modelo.Componente;
import br.ufjf.dcc078.Modelo.Pedido;
import br.ufjf.dcc078.Modelo.Produto;
import br.ufjf.dcc078.Modelo.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

/**
 *
 * @author douglas
 */
public class ComponentePedidoDAO {

    private static ComponentePedidoDAO instance = new ComponentePedidoDAO();

    private ComponentePedidoDAO() {
    }

    ;
    
    public static ComponentePedidoDAO getInstance() {
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
    
    public void addComponent(int idPedido, Componente componente) throws
            SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;

        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("insert into componente_pedido(id_componente, id_pedido, quantidade) "
                    + "values("
                    + componente.getId() + ", "
                    + idPedido + ", "
                    + componente.getQuantidade() + ")"
            );
            
            if(componente.temSubProduto()) {
                Produto p = (Produto) componente;
                for(Iterator<Componente> it = p.getComponentes().iterator(); it.hasNext(); ) {
                    addComponent(idPedido, it.next());
                }
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
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
