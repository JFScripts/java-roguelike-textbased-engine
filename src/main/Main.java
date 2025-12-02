package main;
import java.util.Scanner;

import entidades.Personagem;
import telas.MainMenu;
import telas.MenuJogo;
import telas.Tela;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        Personagem jogador = MainMenu.iniciar(input);
        
        if(jogador == null){
            System.out.println("Saindo...");
            input.close();
            return;
        }
        Tela telaAtual = new MenuJogo();
        while(telaAtual != null){
            telaAtual = telaAtual.executar(jogador, input);
        }

        input.close();
        System.out.println("Jogo Encerrado");
    }
}
