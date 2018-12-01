package br.ufjf.dcc078.State;

public class StateFactory {
    public static EstadoPedido create(String action) {
        EstadoPedido estadoObject = null;
        Class classe = null;
        Object objeto = null;
        String nomeClasse = "br.ufjf.dcc078.State.Pedido" + (action.replaceAll("\\s+",""));
        
        try {
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        } catch (ClassNotFoundException | 
                 IllegalAccessException | 
                 InstantiationException ex) {
            return null;
        }
        
        if(!(objeto instanceof EstadoPedido)) return null;
        estadoObject = (EstadoPedido)objeto;
        return estadoObject;
    }
}
