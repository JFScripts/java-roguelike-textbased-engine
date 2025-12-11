package telas;

import java.util.Scanner;

import entidades.Monstro;
import entidades.Personagem;
import enuns.Cor;
import enuns.Direcao;
import mundo.Sala;
import utils.Console;
import utils.Posicao;

public class TelaExploracao implements Tela {

    private Tela ultimaTela;
    private Sala salaAtual;

    // Construtor: Prepara o ambiente de teste
    public TelaExploracao(Tela ultimaTela, Personagem jogador, Sala curSala) {
        this.ultimaTela = ultimaTela;
        
        this.salaAtual = curSala;
    }

    @Override
    public Tela executar(Personagem jogador, Scanner input) {
        
        // O GAME LOOP DE EXPLORAÇÃO
        while (true) {
            Console.limpar();
            
            // 1. Renderiza o Mundo
            salaAtual.desenharMapa();
            
            // 2. Mostra Controles
            Console.titulo("Exploração");
            System.out.println("Use [W, A, S, D] para mover.");
            System.out.println("[0] Voltar para o Menu.");
            
            // 3. Lê o Input (Lê como String para pegar letras)
            System.out.print("> ");
            String comando = input.next().toUpperCase(); // Pega o texto e joga pra Maiúsculo
            char tecla = comando.charAt(0); // Pega só a primeira letra

            // 4. Lógica de Saída
            if (tecla == '0') {
                return ultimaTela;
            }

            // 5. Lógica de Movimento (Simulação Física)
            // Criamos uma cópia da posição atual para testar o futuro
            Posicao destino = new Posicao(jogador.getPosicao().getX(), jogador.getPosicao().getY());
            
            boolean tentouMover = false;

            switch (tecla) {
                case 'W': 
                    destino.mover(1, Direcao.CIMA); 
                    tentouMover = true;
                    break;
                case 'S': 
                    destino.mover(1, Direcao.BAIXO); 
                    tentouMover = true;
                    break;
                case 'A': 
                    destino.mover(1, Direcao.ESQUERDA); 
                    tentouMover = true;
                    break;
                case 'D': 
                    destino.mover(1, Direcao.DIREITA); 
                    tentouMover = true;
                    break;
                default:
                    // Se digitou outra coisa, não faz nada
                    break;
            }

            // 6. Validação e Execução
            if (tentouMover) {
                // Pergunta pra sala: "Posso pisar aqui?"
                if (salaAtual.posicaoEhValida(destino)) {
                    // Se sim, atualiza o jogador de verdade
                    jogador.setPosicao(destino);
                } else {
                    // Se não, feedback visual
                    System.out.println(); // Pula linha
                    Console.printColorido("Bloqueado! (Parede ou Monstro)", Cor.VERMELHO);
                    Console.esperar(0.5); // Pausa pra ler o erro
                }
            }
        }
    }
}