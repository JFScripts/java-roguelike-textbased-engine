package entidades;

import java.util.ArrayList;
import java.util.List;

public class Mochila {

    private List<Item> itens;
    private int ouro;

    /**Inicializador da Mochila, os parametros são fixos
     * 
     */
    public Mochila() {
        this.itens = new ArrayList<>();
        this.ouro = 0;
    }


    /**Esse método adiciona um item ao inventário
     * @param item
     */
    public void adicionarItem(Item item){
        itens.add(item);
    }

    //TODO: Quando fazer a sala, colocar o item no inventário da sala
    /**Esse método remove um item ao inventário
     * @param item
     */
    public void removerItem(Item item){
        itens.remove(item);
    }


    /**Método para verificar se um item existe
     * @param item
     * @return Retorna true se existe e false se não
     */
    public boolean contem(Item item){
        return this.itens.contains(item);
    }

    
    /** Retorna a quantidade de ouro
     * @return
     */
    public int getOuro(){
        return ouro;
    }


    /**Adiciona uma quantidade de ouro mas NÃO da o retorno
     * @param qntOuro
     */
    public void adicionarOuro(int qntOuro){
        ouro += qntOuro;
    }


    /**Remove uma quantidade de ouro inteira e não mostra um feedback
     * @param qntOuro
     * @return Retorna false se não for possivel tirar o ouro e true se for e já diminui o ouro
     */
    public boolean removerOuro(int qntOuro){
        if(qntOuro > ouro){
            return false;
        } else {
            ouro -= qntOuro;
            return true;
        }
    }

    public List<Item> getItens() {
        return itens;
    }
    
}
