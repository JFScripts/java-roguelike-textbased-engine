package telas;
import java.util.Scanner;

import entidades.Personagem;

public interface Tela {
    Tela executar(Personagem jogador, Scanner input);
    
}