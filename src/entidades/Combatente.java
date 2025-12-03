package entidades;

import enuns.Atributos;
import enuns.Estados;
import utils.Dado;

public interface Combatente {

    void atacar(Combatente alvo, Dado dado);
    void receberDano(int dano);
    boolean estaVivo();
    String getNome();
    int getAc();
    int getVidaMaxima();
    void curar(int valor);
    void receberBuff(Atributos atributo, int valor, int duracao);
    void receberEfeito(Estados estado, int valor, int turnos);
}
