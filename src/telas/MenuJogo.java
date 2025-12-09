package telas;
import static utils.Console.*;

import java.util.Scanner;

import entidades.Arma;
import entidades.Personagem;
import mundo.Sala;
import utils.Console;
import utils.GeradorDeArma;


public class MenuJogo implements Tela {

    public Tela executar(Personagem jogador, Scanner input) {
        limpar();
        titulo("Menu");
        print("Escolha uma Opção:");
        opcao("00 - Sair");
        opcao("01 - Entrar em Combate");
        opcao("02 - Ver Inventario");
        opcao("03 - Acessar Grimório");
        int escolha = input.nextInt();
        input.nextLine();

        switch (escolha) {
            case 0:
                print("Salvando");
                return null;
            case 1:
                print("Iniciando Jogo");
                esperar(0.5);
                return new Combate();
  
            case 2:
                return new JogadorStatus(this);
            case 3:
                return new TelaMagia(this);
            case 4:
                return new TelaExploracao(this, jogador);

            default:
                print("Opção Inválida, tente novamente");
                Console.esperar(0.5);
                return this;
        }
        
    }
    
}
