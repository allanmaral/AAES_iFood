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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void addComponent(int idPedido, Componente componente) {
        Connection conn = null;
        Statement st = null;
        int quantidadeExistente = 0;

        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM componente_pedido WHERE (" 
                    +  "id_componente = " + componente.getId() + " AND "
                    +  "id_pedido = " + idPedido + ")");
            
            if(rs.next()) {
                quantidadeExistente = rs.getInt("quantidade");
                System.out.print("Já existe "+ quantidadeExistente +" desse componente, adicionando de novo");
                st = conn.createStatement();
                st.execute("DELETE FROM componente_pedido WHERE (" 
                    +  "id_componente = " + componente.getId() + " AND "
                    +  "id_pedido = " + idPedido + ")");
            }
            
            st = conn.createStatement();
            st.execute("insert into componente_pedido(id_componente, id_pedido, quantidade) "
                    + "values("
                    + componente.getId() + ", "
                    + idPedido + ", "
                    + (componente.getQuantidade() + quantidadeExistente) + ")"
            );
            
            if(componente.temSubProduto()) {
                Produto p = (Produto) componente;
                for(Iterator<Componente> it = p.getComponentes().iterator(); it.hasNext(); ) {
                    addComponent(idPedido, it.next());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResources(conn, st);
        }
    }
    
    private void read(Componente componente, Pedido pedido) {
        Connection conn = null;
        Statement st = null;
        
        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM componente_pedido WHERE (id_pedido = " + pedido.getId() + " AND " + 
                                                                "id_componente = " + componente.getId() + " )";
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()) {
                componente.setQuantidade(rs.getInt("quantidade"));
                
                if(componente.temSubProduto() && componente.getQuantidade() > 0) {
                    Produto prod = (Produto) componente;
                    for(Iterator<Componente> it = prod.getComponentes().iterator(); it.hasNext(); ) {
                        read(it.next(), pedido);
                    }
                }
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResources(conn, st);
        }
    }
    
    ArrayList<Componente> readListByOrder(Pedido pedido) {
        ArrayList<Componente> lista = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
                
        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM componente_pedido WHERE (id_pedido = " + pedido.getId() + " AND " + 
                                                                "id_componente in ( SELECT id_componente FROM componente WHERE (id_pai IS NULL) ))";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                int id_componente = rs.getInt("id_componente");
                Componente componente = ComponenteDAO.getInstance()
                                                     .readById(id_componente)
                                                     .setQuantidade(rs.getInt("quantidade"));
                if(componente.getQuantidade() > 0) {
                    read(componente, pedido);                
                    lista.add(componente);
                }
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
