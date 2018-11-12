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
    private ArrayList<Componente> componentes = new ArrayList<>();
    private Usuario usuario;
    private String titulo;
    private String status;
    private Promocao promocao;
    private int id;

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

    public int getId() {
        return id;
    }

    public Pedido setId(int id) {
        this.id = id;
        return this;
    }

    

    public void addLista(Produto p) {
        this.componentes.add(p);
    }

    public ArrayList<Componente> getLista() {
        return componentes;
    }

    public Pedido setLista(ArrayList<Componente> lista) {
        this.componentes = lista;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Pedido setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Pedido setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Pedido setStatus(String status) {
        this.status = status;
        return this;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public Pedido setPromocao(Promocao promocao) {
        this.promocao = promocao;
        return this;
    }

    public double getValorPedido() {
        double total = 0.0;

        for (Iterator<Componente> it = componentes.iterator(); it.hasNext();) {
            Componente cp = it.next();
            total += cp.getPrecoTotal();
        }
        
        return total;
    }
    
    public double getValorDesconto() {
        double desconto = 0.0;

        if (getPromocao() != null) {
            desconto = (1.0 - getPromocao().obterDesconto());
        }

        return getValorPedido() * desconto;
    }
    
    public double getTotal() {
        double total = getValorPedido();

        if (getPromocao() != null) {
            total *= getPromocao().obterDesconto();
        }

        return total;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public Pedido setEstado(EstadoPedido estado) {
        this.estado = estado;
        setChanged();
        notifyObservers();
        return this;
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
