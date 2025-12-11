package utils;

import java.util.Random;

import entidades.Personagem;
import mundo.Sala;

public class GeradorDeSala {

    public static Sala gerarSalaAleatoria(Personagem jogador, int width, int height){
        Random random = new Random();
        int tamanhoMaximoX = 20;
        int tamanhoMaximoY = 30;
        int tamanhoMinimo = 3;
        if(width > tamanhoMaximoX){
            width = tamanhoMaximoX;
        }
        if(height > tamanhoMaximoY){
            height = tamanhoMaximoX;
        }
        int sizeX = random.nextInt(width - tamanhoMinimo + 1) + tamanhoMinimo;
        int sizeY = random.nextInt(height - tamanhoMinimo + 1) + tamanhoMinimo;
        Sala salaGerada = new Sala(sizeX, sizeY, jogador, null);
        return salaGerada;
    }
    
}
