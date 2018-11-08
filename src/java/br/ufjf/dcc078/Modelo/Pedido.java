package br.ufjf.dcc078.Modelo;

import br.ufjf.dcc078.Memento.MementoPedido;
import br.ufjf.dcc078.State.EstadoPedido;
import br.ufjf.dcc078.State.PedidoAguardandoConfirmacao;
import br.ufjf.dcc078.Strategy.Promocao;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Pedido extends Observable {

    private EstadoPedido estado;
    private List<Produto> produtos = new ArrayList<>();
    private Usuario usuario;
    private String titulo;
    private String status;
    private Promocao promocao;

    public Pedido() {
        this.estado = new PedidoAguardandoConfirmacao();
    }

    public Pedido(Usuario usuario, String titulo, String status, Promocao promocao) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.status = status;
        this.promocao = promocao;
        this.estado = new PedidoAguardandoConfirmacao();
    }

    public Pedido(String titulo) {
        this.titulo = titulo;
    }

    public Pedido(String titulo, Promocao promocao) {
        this.titulo = titulo;
        this.promocao = promocao;
    }

    

    public void addLista(Produto p) {
        this.produtos.add(p);
    }

    public List<Produto> getLista() {
        return produtos;
    }

    public void setLista(List<Produto> lista) {
        this.produtos = lista;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public Double getTotal() {
        Double total = 0.0;

        for (Iterator i = produtos.iterator(); i.hasNext();) {
            Produto p = (Produto)i.next();
            total += p.getQuantidade() * p.getPreco();
        }

        if (getPromocao() != null) {
            total *= getPromocao().obterDesconto();
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

    public void aguardarConfirmacao() {
        this.getEstado().aguardarConfirmacao(this);
    }

    public void colocarEmProducao() {
        this.getEstado().colocarEmProducao(this);
    }

    public void encaminhar() {
        this.getEstado().encaminhar(this);
    }

    public void entregar() {
        this.getEstado().entregar(this);
    }

    public void cancelar() {
        this.getEstado().cancelar(this);
    }

    public MementoPedido save() {
        return new MementoPedido(this.estado);
    }

    public void restore(MementoPedido m) {
        this.estado = m.getEstadoPedido();
    }
}
