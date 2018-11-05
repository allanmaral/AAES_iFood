/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Servlet;

/**
 *
 * @author allan
 */
public class ActionFactory {
    public static Action create(String action) {
        Action actionObject = null;
        Class classe = null;
        Object objeto = null;
        String nomeClasse = "br.ufjf.dcc078.Action." + action + "Action";
        
        try {
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        } catch (ClassNotFoundException | 
                 IllegalAccessException | 
                 InstantiationException ex) {
            return null;
        }
        
        if(!(objeto instanceof Action)) return null;
        actionObject = (Action)objeto;
        return actionObject;
    }
}
