package telas;
import static utils.Console.*;

import java.util.Scanner;

import entidades.Personagem;
import mundo.Sala;
import utils.Console;
import utils.GeradorDeSala;


public class MenuJogo implements Tela {

    public Tela executar(Personagem jogador, Scanner input) {
        limpar();
        Sala cSala = GeradorDeSala.gerarSalaAleatoria(jogador, 100, 100);
        cSala.desenharMapa();
        titulo("Menu");
        print("Escolha uma Opção:");
        opcao(opcoes());

        char escolha = Console.getCharInput("SCIGE", input);

        switch (escolha) {
            case 'S':
                print("Salvando");
                return null;
            case 'C':
                print("Iniciando Jogo");
                esperar(0.5);
                return new Combate();
  
            case 'I':
                return new JogadorStatus(this);
            case 'G':
                return new TelaMagia(this);
            case 'E':
                return new TelaExploracao(this, jogador, cSala);
            default:
                return this;

        }
        
    }

    private String[] opcoes(){
        String[] escolhas = {
            "[S] - Sair",
            "[C] - Entrar em Combate",
            "[I] - Inventário",
            "[G] - Grimório",
            "[E] - Exploração"
        };

        return escolhas;
    }
    
}
