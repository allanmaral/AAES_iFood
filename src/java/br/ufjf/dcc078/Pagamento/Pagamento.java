package br.ufjf.dcc078.Pagamento;

import br.ufjf.dcc078.Modelo.Pedido;

public abstract class Pagamento {

    protected Pagamento sucessor;
    
    public Pagamento() {
        this.sucessor = null;
    }
    
    public Pagamento(Pagamento sucessor){
        this.sucessor = sucessor;
    }

    public Pagamento getSucessor() {
        return sucessor;
    }

    public void setSucessor(Pagamento sucessor) {
        this.sucessor = sucessor;
    }
    
    public Boolean pagar(Pedido pedido) {
        Boolean resultado = false;
                
        if(autenticarPagamento()){
            resultado = true;
            gerarNotaFiscal(pedido);
        }
        
        return resultado;        
    }
    
    public Boolean processarPagamento(Pedido pedido) {
        if(!pagar(pedido)) {
            if(sucessor != null) {
                return sucessor.processarPagamento(pedido);
            }
            else {
                return false;
            }
        }
        else
        {
            return true;
        }
    }
    
    abstract Boolean autenticarPagamento();

    private void gerarNotaFiscal(Pedido pedido) {
        String titulo = "Nota Fiscal AAES";
        String mensagem = "Email referente a nota fiscal do pedido #" + pedido.getId();
        pedido.getUsuario().enviarEmail(titulo, mensagem);
    }
   
}
