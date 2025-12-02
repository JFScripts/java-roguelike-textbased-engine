package entidades;

import enuns.Raridade;

public class Item {
    private String nome;
    private int preco;
    private Raridade raridade;
    
    public Item(String nome, int preco, Raridade raridade) {
        this.nome = nome;
        this.preco = preco;
        this.raridade = raridade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public Raridade getRaridade() {
        return raridade;
    }

    public void setRaridade(Raridade raridade) {
        this.raridade = raridade;
    }

    
}
