package br.ufjf.dcc078.Dominio;

/**
 *
 * @author douglas
 */
public class Produto {

    String nome;
    Integer quantidade;
    Double preco;

    public Produto(String nome, Integer quantidade, Double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Produto(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "produtos{" + "nome=" + nome + '}';
    }

}
