package telas;

import java.util.Scanner;

import entidades.Magia;
import entidades.Personagem;
import enuns.Atributos;
import utils.Console;
import utils.GeradorDeMagia;

public class LevelUP implements Tela{

    private Tela ultimaTela;
    
    public LevelUP(Tela ultimaTela){
        this.ultimaTela = ultimaTela;
    }

    public Tela executar(Personagem jogador, Scanner input) {
        Console.limpar();
        Console.titulo("Level Up");
        Console.print("Parabéns! Você passou de nivel, escolha um atributo para melhorar");

        Atributos[] atributo = Atributos.values();
        for(int i = 0; i < atributo.length; i ++){
            Atributos curAtributos = atributo[i];
            int valorAtual = jogador.getAtributos(curAtributos);
            Console.opcao((i + 1) + " - " + curAtributos +" : " + valorAtual);
        }
        while (true) {
            Console.print("Digite o Atributo desejado");
            int escolha = Console.getIntINPUT(input);
            if(escolha <= 0 || (escolha - 1) > atributo.length){
                Console.print("Opção Inválida, tente novamente");
            } else {
                Atributos atributoEscolhido = atributo[escolha - 1];
                jogador.passarDeNivel(atributoEscolhido);
                if(atributoEscolhido == Atributos.INTELIGENCIA){
                    Magia novaMagia = GeradorDeMagia.gerarMagia(jogador);
                    jogador.aprenderMagia(novaMagia);
                    Console.limpar();
                    Console.narracao("Você sente um novo poder fluir...");
                    Console.print("Você aprendeu: " + novaMagia.getNome());
                    Console.pressioneENTER(input);
                }
                return ultimaTela;
            }
        
        }
    }
    
}
