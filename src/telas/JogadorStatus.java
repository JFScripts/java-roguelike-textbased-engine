package telas;

import utils.EfeitoTemporario;
import utils.Console;

import java.util.Scanner;

import entidades.Personagem;
import enuns.Atributos;
import enuns.Cor;

public class JogadorStatus implements Tela{

    private Tela ultimaTela;

    public JogadorStatus(Tela ultimaTela) {
        this.ultimaTela = ultimaTela;
    }

    public Tela executar(Personagem jogador, Scanner input) {
        Console.limpar();
        Console.titulo("Inventário de " + jogador.getNome());
        Console.printarBarra("Vida", jogador.getVida(), jogador.getVidaMaxima(), Cor.VERDE);
        Console.printarBarra("Mana", jogador.getMana(), jogador.getManaMaxima(), Cor.AZUL);
        Console.printColorido("Ouro: " + jogador.getOuro(), Cor.AMARELO);
        
        Console.titulo("Atributos");
        for(Atributos cAtributos : Atributos.values()){
            int valorTotal = jogador.getAtributos(cAtributos);
            int valorBuff = 0;
            String mensagem = "";
            EfeitoTemporario buffEncontrado = jogador.getBuffsAtivos().get(cAtributos);
            if(buffEncontrado != null){
                valorBuff = buffEncontrado.getPotencia();
                valorTotal -= valorBuff;
            }
            
            mensagem = cAtributos + ": " + valorTotal;
            System.out.print(mensagem);
            if(valorBuff > 0){
                Console.printColorido(" (+" + valorBuff + ")", Cor.VERDE);
            }
            System.out.println();
        
        }
        Console.titulo("Itens Equipados");
        String maoDireita = (jogador.getmDireita() != null) ? jogador.getmDireita().getNome() : "Vazio";
        String maoEsquerda = (jogador.getmEsquerda() != null) ? jogador.getmEsquerda().getNome() : "Vazio";
        
        Console.print("Mão Direita: " + maoDireita);
        Console.print("Mão Esquerda: " + maoEsquerda);
        
        Console.titulo("===");
        
        Console.opcao("00 - Voltar");
        Console.opcao("01 - Abrir Mochila");
        int escolha = Console.getIntINPUT(input);
        if(escolha == 0){
            return ultimaTela;
        } else if(escolha == 1){
            return new TelaMochila(this);
        } else {
            Console.printColorido("Opção Invalida", Cor.VERMELHO);
            Console.pressioneENTER(input);
            return this;
        }
                
    }
    
}
