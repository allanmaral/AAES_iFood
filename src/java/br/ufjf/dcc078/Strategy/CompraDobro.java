package br.ufjf.dcc078.Strategy;

public class CompraDobro extends Promocao {

    @Override
    public double obterDesconto() {
        return 0.5;
    }

    @Override
    public String obterCodigo() {
        return "COMPRAEMDOBRO";
    }
}
