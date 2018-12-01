package br.ufjf.dcc078.Pagamento;

public class PagamentoRefeicao extends Pagamento {

    public PagamentoRefeicao() {
    }

    public PagamentoRefeicao(Pagamento p) {
        super(p);
    }

    @Override
    Boolean autenticarPagamento() {
        // VALIDA O PAGAMENTO COM O SERVIDOR EXTERNO
        return true;
    }
    
}
