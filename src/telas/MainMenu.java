package telas;

import java.util.Scanner;

import entidades.Personagem;
import utils.Console;
import utils.GeradorDeArma;

public class MainMenu {
    
    public static Personagem iniciar(Scanner input){
        while (true) {
            String[] opcoes = {"[N] - Novo Jogo", "[C] - Carregar jogo", "[S] - Sair"};
            Console.limpar();
            Console.titulo("Menu Principal");
            Console.print("Bem vindo a Masmorra Infinita, o lugar onde lendas são feitas.");
            Console.opcao(opcoes);
 
            char escolha = Console.getCharInput("NCS", input);

            switch (escolha) {
                case 'N':
                    return criarNovoPersonagem(input);
                case 'C':
                    Console.print("Carregar jogo não implementado");
                    Console.esperar(1);
                    break;
                case 'S':
                    return null;
                default:
                    Console.print("Opção Inválida");
                    break;
            }
        }
    }

    private static Personagem criarNovoPersonagem(Scanner input){
        Console.limpar();
        Console.titulo("Criação de Personagem");
        Console.print("Digite o Nome do seu herói: ");
        String nome = input.nextLine();

        return new Personagem(nome, 5, 5, 5, 5, 5, null, GeradorDeArma.gerarEspadaMadeira());
    }


}
