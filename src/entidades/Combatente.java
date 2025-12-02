package entidades;

import enuns.Atributos;
import utils.Dado;

public interface Combatente {


    void atacar(Combatente alvo, Dado dado);
    void receberDano(int dano);
    boolean estaVivo();
    String getNome();
    int getAc();
    void curar(int valor);
    void receberBuff(Atributos atributo, int valor, int duracao);
}
