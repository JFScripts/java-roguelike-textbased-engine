package utils;

public class Buff {
    private int potencia;
    private int duracao;
    
    public Buff(int potencia, int duracao) {
        this.potencia = potencia;
        this.duracao = duracao;
    }

    public void diminuirTurno(){
        this.duracao --;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    
}
