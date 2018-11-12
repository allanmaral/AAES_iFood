/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Strategy;

import br.ufjf.dcc078.State.*;
import br.ufjf.dcc078.Servlet.*;

/**
 *
 * @author allan
 */
public class PromocaoFactory {
    public static Promocao create(String action) {
        Promocao promocaoObject = null;
        Class classe = null;
        Object objeto = null;
        String nomeClasse = "br.ufjf.dcc078.Strategy." + action;
        
        try {
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        } catch (ClassNotFoundException | 
                 IllegalAccessException | 
                 InstantiationException ex) {
            return null;
        }
        
        if(!(objeto instanceof Promocao)) return null;
        promocaoObject = (Promocao)objeto;
        return promocaoObject;
    }
}
