package utils;

import enuns.Cor;
import enuns.Direcao;

public class Posicao {
    
    private int x,y;

    /**Método utilizado para iniciar um objeto com uma posição
     * @param x Direita = X > 0; Esquerda = X < 0
     * @param y Cima = Y < 0; Baixo = Y > 0
     */
    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Método utilizado para teleporta
     * @param X Direita = X > 0; Esquerda = X < 0
     * @param Y Cima = Y < 0; Baixo = Y > 0
     */
    public void setPosicao(int X, int Y){
        this.x = X;
        this.y = Y;
    }



    /**Método para fazer o objto se mover
     * @param vel Quantas unidades da GRID que o objeto se move
     * @param dir A direcao que o objeto vai se mover
     */
    public void mover(int vel, Direcao dir){
        switch (dir) {
            case CIMA:
                this.y -= vel;
                break;
            case BAIXO:
                this.y += vel;
                break;
            case ESQUERDA:
                this.x -= vel;
                break;
            case DIREITA:
                this.x += vel;
                break;
            default:
                Console.printColorido("Opção Invalida", Cor.VERMELHO);
                break;
        }
    }


    /** Método para verificar a colisão
     * @param outra O objeto posição do outro objeto
     * @return Se a posição for igual retorna true
     */
    public boolean colisao(Posicao outra){
        return (this.x == outra.x && this.y == outra.y);

    }

    /** Método para calcular a distancia entre duas 
     * @param p1 A posicao do alvo que deseja calcular a distancia
     * @return Retorna o valor da distancia utilizando o método manhatan
     */
    public int calcularDistancia(Posicao p1){
        int diferencaX = Math.abs(this.x - p1.x);
        int diferencaY = Math.abs(this.y - p1.y);
        
        return diferencaX + diferencaY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
