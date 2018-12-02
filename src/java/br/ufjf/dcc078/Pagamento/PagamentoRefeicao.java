package br.ufjf.dcc078.Pagamento;

public class PagamentoRefeicao extends Pagamento {

    public PagamentoRefeicao() {
    }

    public PagamentoRefeicao(Pagamento p) {
        super(p);
    }

    @Override
    Boolean autenticarPagamento() {
        return true;
    }
    
}
