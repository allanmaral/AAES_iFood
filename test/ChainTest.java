
import br.ufjf.dcc078.Modelo.Pedido;
import br.ufjf.dcc078.Modelo.Usuario;
import br.ufjf.dcc078.Pagamento.Pagamento;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Gabriel Maia
 */
public class ChainTest {
    
    @Test
    public void TestChain(){
        Usuario u = new Usuario();
        Pagamento pagamento = u.getPreferenciasPagamento();
        Pedido pedido = new Pedido();
        assertTrue(pagamento.processarPagamento(pedido));
    }
    
}