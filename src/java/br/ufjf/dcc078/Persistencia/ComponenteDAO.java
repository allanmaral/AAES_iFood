package br.ufjf.dcc078.Persistencia;

import br.ufjf.dcc078.Modelo.Adicional;
import br.ufjf.dcc078.Modelo.Componente;
import br.ufjf.dcc078.Modelo.Produto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allans
 */
public class ComponenteDAO {

    private static ComponenteDAO instance = new ComponenteDAO();

    private ComponenteDAO() {
    };
    
    public static ComponenteDAO getInstance() {
        return instance;
    }

    public void save(Componente componente) throws
            SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement st = null;

        try {
            conn = (Connection) DatabaseMananger.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("INSERT INTO componente (e_produto, preco, titulo, descricao, id_pai)"
                    + "VALUES("
                    + componente.temSubProduto() + ", "
                    + componente.getPreco() + ", '"
                    + componente.getNome() + "', '"
                    + componente.getDescricao() + "', "
                    + "NULL" + ")" // PRECISA SER CORRIGIDO
            );
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    private Componente lerComponente(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_componente");
        boolean temSubComponente = rs.getBoolean("e_produto");
        double preco = rs.getDouble("preco");
        String nome = rs.getString("titulo");
        String descricao = rs.getString("descricao");
                
        Componente comp = null;
        if(temSubComponente) {
            comp = new Produto();
            ((Produto)comp).setComponentes(readListByFatherId(id));
        } else {
            comp = new Adicional();
        }

        return comp.setId(id)
                   .setNome(nome)
                   .setDescricao(descricao)
                   .setPreco(preco);
    }
    
    public ArrayList<Componente> readList() {
        ArrayList<Componente> lista = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        
        try {
            conn = (Connection) DatabaseMananger.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM componente WHERE (id_pai IS NULL)";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {               
                lista.add(lerComponente(rs));
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResources(conn, st);
        }
        
        return lista;
    }
    
    public ArrayList<Componente> readListByFatherId(int id) {
        ArrayList<Componente> lista = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        
        try {
            conn = (Connection) DatabaseMananger.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM componente WHERE (id_pai = " + id + ")";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {               
                lista.add(lerComponente(rs));
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResources(conn, st);
        }
        
        return lista;
    }
    
    public Componente readById(int id) {
        Connection conn = null;
        Statement st = null;
        Componente comp = null;
        
        try {
            conn = (Connection) DatabaseMananger.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM componente WHERE (id_componente = " + id + ")";
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()) {               
                comp = lerComponente(rs);
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResources(conn, st);
        }
        
        return comp;
    }
    
    public Componente readByFatherId(int id) {
        Connection conn = null;
        Statement st = null;
        Componente comp = null;
        
        try {
            conn = (Connection) DatabaseMananger.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM componente WHERE (id_pai = " + id + ")";
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()) {               
                comp = lerComponente(rs);
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResources(conn, st);
        }
        
        return comp;
    }

    public String read(Componente componente) throws
            SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        String id_pedido = null;

        try {
            conn = (Connection) DatabaseMananger.getInstance().getConnection();
            stmt = conn.createStatement();

            String query = "select id_pedido from pedido where id_usuario = '%"
                    //+ usuario.getId()
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
