/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.State;

import br.ufjf.dcc078.Servlet.*;

/**
 *
 * @author allan
 */
public class StateFactory {
    public static EstadoPedido create(String action) {
        EstadoPedido estadoObject = null;
        Class classe = null;
        Object objeto = null;
        String nomeClasse = "br.ufjf.dcc078.State.Estado" + action.replaceAll("\\s+","");
        
        try {
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        } catch (ClassNotFoundException | 
                 IllegalAccessException | 
                 InstantiationException ex) {
            return null;
        }
        
        if(!(objeto instanceof Action)) return null;
        estadoObject = (EstadoPedido)objeto;
        return estadoObject;
    }
}
