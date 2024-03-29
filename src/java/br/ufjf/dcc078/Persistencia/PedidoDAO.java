package br.ufjf.dcc078.Persistencia;

import br.ufjf.dcc078.Modelo.Pedido;
import br.ufjf.dcc078.Modelo.Usuario;
import br.ufjf.dcc078.State.StateFactory;
import br.ufjf.dcc078.Strategy.Promocao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

            st.execute("insert into pedido(titulo) "
                    + "values('"
                    + pedido.getTitulo() + "') '"
            );
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void update(Pedido pedido) {
        Connection conn = null;
        Statement st = null;

        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "UPDATE pedido "
                       + "SET estado = '" + pedido.getEstado().toString() + "' ";
            if(pedido.getPromocao() != null) {
                sql += ", id_promocao = " + pedido.getPromocao().getId()+ " "; 
            }
            sql += "WHERE id_pedido = " + pedido.getId();
            
            st.execute(sql);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql = "SELECT * FROM pedido WHERE (id_usuario = " + usuario.getId() + " AND estado <> 'Carrinho')";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Promocao promocao = getPromocao(rs);
                String estado = rs.getString("estado");
                
                Pedido pedido = new Pedido();
                
                pedido.setUsuario(usuario)
                      .setId(rs.getInt("id_pedido"))
                      .loadEstado(StateFactory.create(estado))
                      .setPromocao(promocao);
                
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
    
    public ArrayList<Pedido> readAll() {
        ArrayList<Pedido> lista = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        
        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM pedido WHERE (estado <> 'Carrinho')";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Promocao promocao = getPromocao(rs);
                Usuario usuario = UsuarioDAO.getInstance().read(rs.getInt("id_usuario"));
                
                Pedido pedido = (new Pedido())
                                .setUsuario(usuario)
                                .setId(rs.getInt("id_pedido"))
                                .loadEstado(StateFactory.create(rs.getString("estado")))
                                .setPromocao(promocao);
                
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
    
    public Pedido readById(int id) {
        Pedido pedido = null;
        Connection conn = null;
        Statement st = null;
        
        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM pedido WHERE (id_pedido = " + id + ")";
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()) {
                Promocao promocao = getPromocao(rs);
                Usuario usuario = UsuarioDAO.getInstance().read(rs.getInt("id_usuario"));
                
                String estado = rs.getString("estado");
                
                pedido = (new Pedido())
                         .setUsuario(usuario)
                         .setId(rs.getInt("id_pedido"))
                         .loadEstado(StateFactory.create(estado))
                         .setPromocao(promocao);
                
                pedido.setLista(ComponentePedidoDAO.getInstance().readListByOrder(pedido));
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResources(conn, st);
        }
        
        return pedido;
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

    public Pedido readCart(Usuario usuario) {
        Pedido pedido = null;
        Connection conn = null;
        Statement st = null;
        
        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM pedido "
                       + "WHERE (id_usuario = " + usuario.getId() + 
                                " AND estado = 'Carrinho' )";
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()) {
                Promocao promocao = getPromocao(rs);
                String estado = rs.getString("estado");
                
                pedido = (new Pedido())
                          .setUsuario(usuario)
                          .setId(rs.getInt("id_pedido"))
                          .loadEstado(StateFactory.create(estado))
                          .setPromocao(promocao);
                
                pedido.setLista(ComponentePedidoDAO.getInstance().readListByOrder(pedido));
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResources(conn, st);
        }
        
        return pedido;
    }

    public void saveCart(Usuario usuario) {
        Connection conn = null;
        Statement st = null;

        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("INSERT INTO pedido(id_usuario, estado) "
                    + "VALUES("
                    + usuario.getId() + ", "
                    + "'Carrinho')"
            );
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void updateState(Pedido pedido) {
        Connection conn = null;
        Statement st = null;
        
        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "UPDATE pedido " 
                       + "SET estado = '" + pedido.getEstado().toString() + "' "
                       + "WHERE id_pedido = " + pedido.getId();
            st.execute(sql);
                        
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResources(conn, st);
        }
    }
    
    private Promocao getPromocao(ResultSet rs) throws SQLException {
        Promocao promocao = null;
        int idPromo = 0;
        idPromo = rs.getInt("id_promocao");
        if(!rs.wasNull()){
            promocao = PromocaoDAO.getInstance().read(idPromo);
        }
        return promocao;
    }
}
