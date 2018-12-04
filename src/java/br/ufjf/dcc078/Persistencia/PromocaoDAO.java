/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Persistencia;

import br.ufjf.dcc078.Strategy.Promocao;
import br.ufjf.dcc078.Strategy.PromocaoFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author douglas
 */
public class PromocaoDAO {

    private static PromocaoDAO instance = new PromocaoDAO();

    private PromocaoDAO() {
    }

    public static PromocaoDAO getInstance() {
        return instance;
    }

    public String read(Promocao promocao) throws
            SQLException, ClassNotFoundException {
        String desconto = null;
        String comando = "select desconto from promocao where codigo= '%"
                + promocao.obterCodigo()
                + "%'";

        ResultSet rs = DatabaseLocator.executarQuery(comando);

        try {
            while (rs.next()) {
                desconto = rs.getString("desconto");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromocaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return desconto;

    }

    public Promocao read(String codigo) {
        Promocao promocao = null;
        String comando = "SELECT * FROM promocao "
                + "WHERE codigo = '" + codigo + "'";

        ResultSet rs = DatabaseLocator.executarQuery(comando);

        try {
            if (rs.next()) {
                promocao = PromocaoFactory.create(rs.getString("nome"))
                        .setId(rs.getInt("id_promocao"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromocaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return promocao;
    }

    public Promocao read(int id) {
        Promocao promocao = null;
        String comando = "SELECT * FROM promocao "
                + "WHERE id_promocao =  " + id;

        ResultSet rs = DatabaseLocator.executarQuery(comando);

        try {
            if (rs.next()) {
                promocao = PromocaoFactory.create(rs.getString("nome"))
                        .setId(rs.getInt("id_promocao"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromocaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return promocao;
    }

}
