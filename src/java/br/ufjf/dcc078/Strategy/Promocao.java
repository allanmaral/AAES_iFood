/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Strategy;

/**
 *
 * @author ice
 */
public abstract class Promocao {
    
    private int id;
    
    public int getId() {
        return this.id;
    }
    
    public Promocao setId(int id) {
        this.id = id;
        return this;
    }
    
    public abstract double obterDesconto();
    public abstract String obterCodigo();
     
    
}
