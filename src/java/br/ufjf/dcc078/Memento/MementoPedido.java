/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Memento;

import br.ufjf.dcc078.State.EstadoPedido;
import java.sql.Timestamp;

/**
 *
 * @author Gabriel Maia
 */
public class MementoPedido {
    
    private int id;
    private EstadoPedido estadoPedido;
    private long dataAlteracao;
    
    public MementoPedido(){
        this.dataAlteracao = System.currentTimeMillis();
    }
    
    public MementoPedido(EstadoPedido estadoPedido){
        this.estadoPedido = estadoPedido;
        this.dataAlteracao = System.currentTimeMillis();
    }
    
    public MementoPedido setEstado(EstadoPedido estadoPedido){
        this.estadoPedido = estadoPedido;
        return this;
    }
    
    public EstadoPedido getEstado(){
        return estadoPedido;
    }

    public int getId() {
        return id;
    }

    public MementoPedido setId(int id) {
        this.id = id;
        return this;
    }

    public long getDataAlteracao() {
        return dataAlteracao;
    }
    
    public Timestamp getTimestamp() {
        return new Timestamp(dataAlteracao);
    }

    public MementoPedido setDataAlteracao(long dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
        return this;
    }
    
    
}
