package br.ufjf.dcc078.Strategy;

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
