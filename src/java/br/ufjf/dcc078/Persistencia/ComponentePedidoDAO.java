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
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static ComponentePedidoDAO getInstance() {
        return instance;
    }

    public void save(Pedido pedido) {
        String comando = "insert into pedido(id_usuario, titulo, descricao, status, promocao) "
                + "values('"
                + pedido.getUsuario().getId() + "','"
                + pedido.getTitulo() + "', '"
                + pedido.getStatus() + "', '"
                + pedido.getPromocao() + "')";
        DatabaseLocator.executarStatement(comando);
    }

    public String read(Usuario usuario) {
        String id_pedido = null;

        String comando = "select id_pedido from pedido where id_usuario = '%"
                + usuario.getId()
                + "%'";

        ResultSet rs = DatabaseLocator.executarQuery(comando);

        try {
            while (rs.next()) {
                id_pedido = rs.getString("id_pedido");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComponentePedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id_pedido;
    }

    public void addComponent(int idPedido, Componente componente) {
        int quantidadeExistente = 0;

        DatabaseLocator.executarStatement(
                  "INSERT INTO componente_pedido (id_componente, id_pedido, quantidade) "
                + "VALUES("
                + componente.getId() + ", "
                + idPedido + ", "
                + (componente.getQuantidade() + quantidadeExistente) + ") "
                + "ON DUPLICATE KEY UPDATE quantidade="
                + (componente.getQuantidade() + quantidadeExistente));

         
        if (componente.temSubProduto()) {
            Produto p = (Produto) componente;
            for (Iterator<Componente> it = p.getComponentes().iterator(); it.hasNext();) {
                addComponent(idPedido, it.next());
            }
        }
    }

    private void read(Componente componente, Pedido pedido) {
        
        ResultSet rs = DatabaseLocator.executarQuery(
                  "SELECT * FROM componente_pedido "
                + "WHERE (id_pedido = " + pedido.getId()
                + " AND "
                + "id_componente = " + componente.getId() + " )");

        try {
            if (rs.next()) {
                componente.setQuantidade(rs.getInt("quantidade"));

                if (componente.temSubProduto() && componente.getQuantidade() > 0) {
                    Produto prod = (Produto) componente;
                    for (Iterator<Componente> it = prod.getComponentes().iterator(); it.hasNext();) {
                        read(it.next(), pedido);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    ArrayList<Componente> readListByOrder(Pedido pedido) {
        ArrayList<Componente> lista = new ArrayList<>();
        
        ResultSet rs = DatabaseLocator.executarQuery(
                  "SELECT * FROM componente_pedido "
                + "WHERE (id_pedido = " + pedido.getId() + " "
                + "AND id_componente in ( "
                    + "SELECT id_componente FROM componente WHERE (id_pai IS NULL) ))");

        try {
            while (rs.next()) {
                int id_componente = rs.getInt("id_componente");
                Componente componente = ComponenteDAO.getInstance()
                        .readById(id_componente)
                        .setQuantidade(rs.getInt("quantidade"));
                if (componente.getQuantidade() > 0) {
                    read(componente, pedido);
                    lista.add(componente);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ComponenteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
}
