package utils;

import entidades.Monstro;
import java.util.Random;

public class GeradorDeMonstros {
    private static Monstro[] catalogo = {
        new Monstro("Goblin", 10, 5, 1, 5),
        new Monstro("Zumbi", 7, 10, 2, 5)
    };

    public static Monstro gerarMonstro(){
        Random random = new Random();
        int indice = random.nextInt(catalogo.length);
        return catalogo[indice].clonar();
    }
}
