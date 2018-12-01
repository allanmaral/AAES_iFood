package br.ufjf.dcc078.Pagamento;

public class PagamentoDinheiro extends Pagamento {

    public PagamentoDinheiro(Pagamento p) {
        super(p);
    }

    public PagamentoDinheiro() {  }
        
    @Override
    Boolean autenticarPagamento() {
        // VALIDA O PAGAMENTO COM O SERVIDOR EXTERNO
        return true;
    }
    
}
