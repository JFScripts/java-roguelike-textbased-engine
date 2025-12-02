package entidades;

import java.util.List;

import enuns.TipoEfeito;

public class Magia {

    private String nome;
    private List<TipoEfeito> efeito;
    private int poder;
    private int custoMana;
    private Combatente alvo;
    
    public Magia(String nome, List<TipoEfeito> efeito, int poder, int custoMana) {
        this.nome = nome;
        this.efeito = efeito;
        this.poder = poder;
        this.custoMana = custoMana;
    }

    @Override
    public String toString() {
        return "Magia:" + nome + "\nEfeitos: " + efeito + "\nPoder:" + poder + "\nCusto de Mana: " + custoMana;
    }

    //Getter and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getCustoMana() {
        return custoMana;
    }

    public void setCustoMana(int custoMana) {
        this.custoMana = custoMana;
    }

    public Combatente getAlvo() {
        return alvo;
    }

    public void setAlvo(Combatente alvo) {
        this.alvo = alvo;
    }

    public List<TipoEfeito> getEfeito() {
        return efeito;
    }

    public void setEfeito(List<TipoEfeito> efeito) {
        this.efeito = efeito;
    }


}
