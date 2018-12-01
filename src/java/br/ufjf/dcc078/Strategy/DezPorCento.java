package br.ufjf.dcc078.Strategy;

public class DezPorCento extends Promocao {

    @Override
    public double obterDesconto() {
        return 0.9;
    }

    @Override
    public String obterCodigo() {
        return "DEZPORCENTO";
    }

}
