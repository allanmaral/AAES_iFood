package br.ufjf.dcc078.Dominio;

import br.ufjf.dcc078.Memento.MementoPedido;
import br.ufjf.dcc078.State.EstadoPedido;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Pedido extends Observable {
            
    EstadoPedido estado;
    private List<Produto> lista = new ArrayList<>();
    private Usuario usuario;
    private String titulo;
    private String descricao;
    private String status;
    private String promocao;

    public Pedido() {
    }

    
    public Pedido(Usuario usuario, String titulo, String descricao, String status, String promocao) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.promocao = promocao;
    }

    
    public void addLista(Produto p) {
        this.lista.add(p);
    }

    public List<Produto> getLista() {
        return lista;
    }

    public void setLista(List<Produto> lista) {
        this.lista = lista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPromocao() {
        return promocao;
    }

    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }
    
    public Double getTotal() {
        Double total = 0.0;

        for (int i = 0; i < lista.size(); i++) {
            total += lista.get(i).getQuantidade() * lista.get(i).getPreco();
        }
        return total;
    }
    
    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
        setChanged();
        notifyObservers();
    }
    
    public void aguardarConfirmacao(){
        this.getEstado().aguardarConfirmacao(this);
    }
    
    public void colocarEmProducao(){
        this.getEstado().colocarEmProducao(this);
    }
    
    public void encaminhar(){
        this.getEstado().encaminhar(this);
    }
    
    public void entregar(){
        this.getEstado().entregar(this);
    }
    
    public void cancelar(){
        this.getEstado().cancelar(this);
    }
    
    public MementoPedido save(){
         return new MementoPedido(this.estado);
    }
    
    public void restore(MementoPedido m){
        this.estado = m.getEstadoPedido();
    }
}
