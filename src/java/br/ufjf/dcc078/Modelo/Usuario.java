/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc078.Modelo;

import br.ufjf.dcc078.Pagamento.Pagamento;
import br.ufjf.dcc078.Pagamento.PagamentoCartao;
import br.ufjf.dcc078.Pagamento.PagamentoDinheiro;
import br.ufjf.dcc078.Pagamento.PagamentoRefeicao;
import br.ufjf.dcc078.Services.MailService;
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
    private Pagamento preferenciasPagamento;
    
    public Usuario(Integer id, String nome_completo, String nome_usuario, String email, String senha) {
        this.id = id;
        this.nomeCompleto = nome_completo;
        this.nomeUsuario = nome_usuario;
        this.email = email;
        this.senha = senha;
  
        //teste chain of responsability  
        this.setPreferenciasPagamento(new PagamentoCartao(new PagamentoRefeicao(new PagamentoDinheiro())));
    }

    public Usuario() {
        //teste chain of responsability  
        this.setPreferenciasPagamento(new PagamentoCartao(new PagamentoRefeicao(new PagamentoDinheiro())));
    }

    public Integer getId() {
        return id;
    }

    public Usuario setId(Integer id) {
        this.id = id;
        return this;
    }
    
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public Usuario setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
        return this;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public Usuario setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public Pagamento getPreferenciasPagamento() {
        return preferenciasPagamento;
    }

    public Usuario setPreferenciasPagamento(Pagamento preferenciasPagamento) {
        this.preferenciasPagamento = preferenciasPagamento;
        return this;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Pedido){
            Pedido pedido = (Pedido) o;
            MailService.getInstance().send(this.getEmail(), "Pedido no AAES Food", "Status: " + pedido.getEstado().getNome());
        }
    }    
}
