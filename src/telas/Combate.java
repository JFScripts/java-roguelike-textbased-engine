package telas;

import java.util.Random;
import java.util.Scanner;

import entidades.Monstro;
import entidades.Personagem;
import entidades.Item;
import utils.Console;
import utils.Dado;
import utils.GeradorDeArma;
import utils.GeradorDeMonstros;


import static utils.Console.*;

public class Combate implements Tela{

    private Monstro alvo = GeradorDeMonstros.gerarMonstro();
    private Dado d20 = new Dado(20);

    @Override
    public Tela executar(Personagem jogador, Scanner input) {
        Random randon = new Random();
        limpar();
        narracao(alvo.getNome() + " Aparece!");
        while (alvo.estaVivo() && jogador.estaVivo()) {
            esperar(1);
            limpar();
            titulo("Combate");
            narracao(jogador.getNome() + " VS " + alvo.getNome());
            print("Vida atual: "+jogador.getVida());
            print("Vida do "+alvo.getNome()+ ": "+ alvo.getVida());
            opcao(opcoes());

            char escolha = Console.getCharInput("AFIG", input);
            input.nextLine();
            switch (escolha) {
                case 'A':
                    limpar();
                    jogador.atacar(alvo,d20);
                    if(alvo.estaVivo()){
                        alvo.atacar(jogador, d20);
                    }
                    print("\nPressione ENTER para continuar...");
                    input.nextLine();
                    break;
                case 'F':
                    print(jogador.getNome()+" fugiu!");
                    esperar(1);
                    return new MenuJogo();
                case 'I':
                    return new JogadorStatus(this);
                case 'G':
                    return new TelaMagia(this, alvo);
                default:
                    print("Opção INVALIDA tente novamente");

            }
       
        }
        if(jogador.estaVivo()){
            limpar();
            narracao("O "+ alvo.getNome() + " Cai morto aos seus pés.");
            print("Parabéns! Você venceu o combate!");
            esperar(2);
            if(randon.nextInt(100) < 30){
                Item loot = GeradorDeArma.gerarArmaAleatoria(jogador);
                String[] aceitarLoot = {"[S] - Sim", "[N] - Não"};
                limpar();
                Console.print(alvo.getNome() + " deixou cair " + loot.toString() + ", pegar?");
                Console.opcao(aceitarLoot);
                char escolha = Console.getCharInput("SN", input);
                if(escolha == 'S'){
                    jogador.receberItem(loot);
                    Console.pressioneENTER(input);
                } else {
                    Console.print("O item desaparece"); //Futuramente salvar no inventário da sala
                    Console.esperar(1);
                }
            }
            return new LevelUP(new MenuJogo());
            
        } else {
            limpar();
            print("XXX Você morreu XXX");
            esperar(3);
            return null;
            }
    }

    private String[] opcoes(){
        String[] escolhas = {
            "[A] - Atacar",
            "[F] - Fugir",
            "[I] - Inventário",
            "[G] - Grimório",
        };

        return escolhas;
    }
}
