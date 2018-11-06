/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Modelo;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author douglas
 */
public class Usuario implements Observer{
    private Integer id;
    private String nomeCompleto;
    private String nomeUsuario;
    private String email;
    private String senha;

    public Usuario(Integer id, String nome_completo, String nome_usuario, String email, String senha) {
        this.id = id;
        this.nomeCompleto = nome_completo;
        this.nomeUsuario = nome_usuario;
        this.email = email;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Pedido){
            Pedido pedido = (Pedido) o;
            System.out.println("Status: " + pedido.getEstado().getNome());
        }
    } 
}
