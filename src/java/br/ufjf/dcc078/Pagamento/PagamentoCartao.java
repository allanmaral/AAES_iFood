package br.ufjf.dcc078.Pagamento;

public class PagamentoCartao extends Pagamento {

    public PagamentoCartao() {
    }

    public PagamentoCartao(Pagamento p) {
        super(p);
    }
    
    @Override
    Boolean autenticarPagamento() {
        return true;
    }
    
}
