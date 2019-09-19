package br.ufjf.dcc078.Strategy;

public abstract class AplicarPromocao {

    protected Promocao promocao;
    protected String codigo;

    public double desconto() {
        return promocao.obterDesconto();
    }

    public String getNome() {
        return codigo;
    }

    public String getPromocao() {
        return promocao.obterCodigo();
    }

}
