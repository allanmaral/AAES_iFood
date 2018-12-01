package br.ufjf.dcc078.Strategy;

public class TesteStrategy {

    public static void main(String[] args) {
        AplicarPromocao produto1 = new PedidoCompraDobro();
        AplicarPromocao produto2 = new PedidoDezPorCento();

        System.out.println(produto1.getNome() + " na promoção " + produto1.getPromocao() + " com desconto de " + produto1.desconto() + "%");
        System.out.println(produto2.getNome() + " na promoção " + produto2.getPromocao() + " com desconto de " + produto2.desconto() + "%");

    }

}
