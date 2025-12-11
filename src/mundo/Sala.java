package mundo;

import java.util.Random;

import entidades.Monstro;
import entidades.Personagem;
import utils.Console;
import utils.Posicao;

public class Sala {
    
    private int width;
    private int height;
    private Personagem jogador;
    private Monstro monstro;

    /**Método para criar um sala
     * @param width
     * @param height
     * @param jogador
     * @param monstro Caso não exista um monstro, ele poder ser null
     */
    public Sala(int width, int height, Personagem jogador, Monstro monstro) {
        this.width = width;
        this.height = height;
        this.jogador = jogador;
        this.monstro = monstro;
        jogador.setPosicao(new Posicao(1, height/2));
        if (monstro != null) {
            monstro.setPosicao(new Posicao(width - 2, height - 2));
        }
    }

    /**Método para desenhar o mapa na tela
     * 
     */
    public void desenharMapa(){
        Console.titulo("Mapa");
        for(int y = 0; y < this.height; y++){
            for(int x = 0; x < this.width; x++){
                Posicao temp = new Posicao(x, y);

                if(jogador.getPosicao().colisao(temp)){
                    System.out.print(jogador.getSimbolo() + " ");
                } else if(monstro != null && monstro.getPosicao().colisao(temp)){
                    System.out.print(monstro.getSimbolo() + " ");                    
                } else if(x == 0 || y == 0 || x == this.width - 1  || y == this.height - 1){
                    System.out.print("# ");
                } else {
                    System.out.print(". ");
                }

            }
            System.out.println();
        }
    }
    
    /**Método para verificar a colisão com paredes e monstros
     * @param p A posição do jogador
     * @return
     */
    public boolean posicaoEhValida(Posicao p) {
    
        if (p.getX() <= 0 || p.getX() >= this.width - 1 || 
            p.getY() <= 0 || p.getY() >= this.height - 1) {
            return false; 
        }
        if (monstro != null && p.colisao(monstro.getPosicao())) {
            return false; 
        }
        
        return true; 
    }
    

    public int getwidth() {
        return width;
    }
    public void setwidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public Personagem getJogador() {
        return jogador;
    }
    public void setJogador(Personagem jogador) {
        this.jogador = jogador;
    }
    public Monstro getMonstro() {
        return monstro;
    }
    public void setMonstro(Monstro monstro) {
        this.monstro = monstro;
    }

    
}
