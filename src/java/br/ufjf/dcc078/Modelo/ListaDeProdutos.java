package br.ufjf.dcc078.Modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaDeProdutos {

    private static List<Produto> produtos;

    public static List<Produto> getInstance() {

        if (produtos == null) {
            produtos = new ArrayList<>();

            produtos.add(new Produto("Refrigerante", 2, 4.0));
            produtos.add(new Produto("Cerveja", 5, 5.0));

            produtos.add(new Produto("Dose de Wisk", 3, 10.0));
            produtos.add(new Produto("Porção Carne e Bacon", 1, 30.0));
            produtos.add(new Produto("Porção Batata Frita", 2, 15.0));

            produtos.add(new Produto("Suco de Limão", 2, 4.5));
            produtos.add(new Produto("Agua Mineral", 3, 2.0));
            produtos.add(new Produto("Chiclete", 5, 0.25));
            produtos.add(new Produto("Chocolate", 1, 2.0));

        }
        
        return produtos;
    }

}
