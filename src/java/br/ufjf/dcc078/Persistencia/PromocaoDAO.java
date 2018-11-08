/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Persistencia;

import br.ufjf.dcc078.Strategy.Promocao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        Connection conn = null;
        Statement stmt = null;
        String desconto = null;

        try {
            conn = (Connection) DatabaseLocator.getInstance().getConnection();
            stmt = conn.createStatement();

            String query = "select desconto from promocao where codigo= '%"
                    + promocao.obterCodigo()
                    + "%'";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                desconto = rs.getString("desconto");
            }

            return desconto;
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
