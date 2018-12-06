package br.ufjf.dcc078.Persistencia;

import br.ufjf.dcc078.Memento.MementoPedido;
import br.ufjf.dcc078.Modelo.Pedido;
import br.ufjf.dcc078.State.EstadoPedido;
import br.ufjf.dcc078.State.StateFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rian Alves
 */
public class MementoPedidoDAO {
  
    private static MementoPedidoDAO instance = new MementoPedidoDAO();
    
    private MementoPedidoDAO() {}
    
    public static MementoPedidoDAO getInstance() {
        return instance;
    }

    public void save(MementoPedido memento) {
        Connection conn = null;        
        Statement st = null;
        try
        {
            conn= DatabaseMananger.getInstance().getConnection();
            st = conn.createStatement();
            
            Timestamp timestamp = new Timestamp(memento.getDataAlteracao());

            st.execute("INSERT INTO memento_pedido (id_memento, data_alteracao, estado)" +
                    " VALUES (" + memento.getId()    + ", '" + timestamp           + "'," +
                            "'" + memento.getEstado().toString() + "')");
        } catch(SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MementoPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, st);
        }       
    }
    
    public MementoPedido load(int idAluno, long dataAlt)throws SQLException, ClassNotFoundException{
        MementoPedido memento = null;
        Connection conn = null;        
        Statement st = null;
        try
        {
            conn= DatabaseMananger.getInstance().getConnection();
            st = conn.createStatement();

            Timestamp timestamp = new Timestamp(dataAlt);
            ResultSet rs = st.executeQuery("SELECT * FROM memento_pedido WHERE id_memento=" + idAluno + " AND data_alteracao='"+ timestamp +"'");
            
            if(rs.next())
            {
                int id = rs.getInt("id_memento");
                EstadoPedido estado = StateFactory.create(rs.getString("estado"));
                memento = (new MementoPedido())
                            .setId(id)
                            .setEstado(estado);
            }
            
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        } 
        return memento;
    }
    
    public ArrayList<MementoPedido> loadHistory(Pedido pedido) {
        ArrayList<MementoPedido> lista = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        
        try {
            conn = DatabaseMananger.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM memento_pedido WHERE id_memento=" + pedido.getId();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                int id = rs.getInt("id_memento");
                long ts = rs.getTimestamp("data_alteracao").getTime();
                EstadoPedido estado = StateFactory.create(rs.getString("estado"));
                MementoPedido memento  = (new MementoPedido())
                                          .setId(id)
                                          .setEstado(estado)
                                          .setDataAlteracao(ts);
                lista.add(memento);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MementoPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, st);
        }
        
        return lista;
    }
    
    public void deleteByID(int idAluno)throws SQLException, ClassNotFoundException{
        Connection conn = null;        
        Statement st = null;
        try
        {
            conn= DatabaseMananger.getInstance().getConnection();
            st = conn.createStatement();

            st.execute("DELETE FROM memento_pedido WHERE id_memento=" + idAluno);
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }       
    }
    
    public boolean hasNewer(MementoPedido memento)throws SQLException, ClassNotFoundException{
        Connection conn = null;        
        Statement st = null;
        boolean hasNewer = false;
        try
        {
            conn= DatabaseMananger.getInstance().getConnection();
            st = conn.createStatement();
            Timestamp timestamp = new Timestamp(memento.getDataAlteracao());
            ResultSet rs = st.executeQuery("SELECT * FROM memento_pedido WHERE id_memento=" + memento.getId() +
                       " AND data_alteracao >= '" + timestamp + "'");
            if(rs.next()) {
                hasNewer = true;
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        
        return hasNewer;
    }
    
    public void deleteNewer(MementoPedido memento)throws SQLException, ClassNotFoundException{
        Connection conn = null;        
        Statement st = null;
        try
        {
            conn= DatabaseMananger.getInstance().getConnection();
            st = conn.createStatement();
            Timestamp timestamp = new Timestamp(memento.getDataAlteracao());
            st.execute("DELETE FROM memento_pedido WHERE id_memento=" + memento.getId() +
                       " AND data_alteracao > '" + timestamp + "'");
        } catch(SQLException e) {
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
