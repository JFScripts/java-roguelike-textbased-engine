package entidades;

import java.util.HashMap;

import enuns.Atributos;
import utils.Console;

/**
 * Gerencia os atributos base (Natos) de uma criatura.
 * <p>
 * Esta classe armazena os valores permanentes de Força, Inteligência, etc.
 * Não deve ser usada para armazenar buffs temporários.
 */
public class FichaAtributos {
    private HashMap<Atributos, Integer> atributos = new HashMap<>();

    /**
     * Inicializa uma nova ficha com os valores base definidos.
     * @param inteligencia Capacidade mágica e regeneração de mana.
     * @param forca Dano físico e capacidade de carga.
     * @param constituicao Define a Vida Máxima (HP).
     * @param agilidade Define a Classe de Armadura (AC) e ordem de turno.
     */
    public FichaAtributos(int inteligencia, int forca, int constituicao, int agilidade){
        
        atributos.put(Atributos.INTELIGENCIA, inteligencia);
        atributos.put(Atributos.FORCA, forca);
        atributos.put(Atributos.CONSTITUICAO, constituicao);
        atributos.put(Atributos.AGILIDADE, agilidade);
    }

    /**Permite coletar o valor de um atributo SEM o buff apenas o valor base
     * @param atributoEscolhido
     * @return Devolve o valor do atributo escolhido SEM o buff e retorna 0 caso não seja encontrado
     */
    public int getValorBase(Atributos atributoEscolhido){
        return this.atributos.getOrDefault(atributoEscolhido, 0);
    }

    /**Aumenta o atributo escolhido em uma quantidade inteira
     * @param atributoEscolhido
     * @param quantidade
     * Printa no console o valor novo
     */
    public void aumentarAtributo(Atributos atributoEscolhido, int quantidade){
        int valorAtual = getValorBase(atributoEscolhido);
        int valorNovo = valorAtual + quantidade;
        atributos.put(atributoEscolhido, valorNovo);

        Console.print(atributoEscolhido + " Subiu para " + valorNovo);

    }
}
