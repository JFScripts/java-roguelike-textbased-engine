package telas;

import java.util.List;
import java.util.Scanner;

import entidades.Arma;
import entidades.Item;
import entidades.Personagem;
import enuns.Cor;
import utils.Console;

public class TelaMochila implements Tela {

    private Tela ultimaTela;

    public TelaMochila(Tela ultimaTela) {
        this.ultimaTela = ultimaTela;
    }

    @Override
    public Tela executar(Personagem jogador, Scanner input) {
        Console.limpar();
        Console.titulo("Mochila de " + jogador.getNome());
        List<Item> mochilaLista = jogador.getMochila().getItens();
        
        if(mochilaLista.size() > 0){
            for(int i = 0; i < mochilaLista.size(); i++){
                Cor curCor = mochilaLista.get(i).getRaridade().getCorRaridade();
                String curItem = mochilaLista.get(i).toString();
                Console.printColorido((i+1) + " - " + curItem, curCor);
            }
        } else {
            Console.print("A mochila está vazia");
        }
        Console.titulo("===");
        Console.print("Selecione um item ou pressione 0 para voltar");
        int escolha = Console.getIntINPUT(input);
        if(escolha == 0){
            return ultimaTela;
        } else if(escolha > 0 && escolha <= mochilaLista.size()){
            Item itemEscolhido = mochilaLista.get(escolha - 1);
            Console.limpar();
            Console.printColorido(itemEscolhido.toString(), itemEscolhido.getRaridade().getCorRaridade());
            if(itemEscolhido instanceof Arma){
                lidarComArma(jogador, itemEscolhido, input);  
                return this;      
            } else {
                lidarComItemGenerico(jogador, itemEscolhido, input);
                return this;
            }  
        } else {
            Console.printColorido("Opção Invalida Tente Novamente", Cor.VERMELHO);
            return this;
        }
        
    }

    private void lidarComArma(Personagem jogador, Item arma, Scanner input){
        while (true) {
            String[] armaEscolha = {"[C] - Cancelar", "[E] - Empunhar", "[J] - Jogar Fora"};
            Console.titulo("===");
            Console.opcao(armaEscolha);

            char escolha = Console.getCharInput("CEJ",input);
            switch (escolha) {
                case 'C': 
                    return;
                case 'E':
                    boolean sucesso = escolherMao(jogador, arma, input);
                    if(sucesso) return;
                case 'J':
                    jogador.getMochila().removerItem(arma);
                    Console.printColorido("Você jogou " + arma.getNome() + " fora e ele desaparece", Cor.VERMELHO);
                    Console.pressioneENTER(input);
                    return;
            
                default:
                    break;
            }
        }
    }
    private boolean escolherMao(Personagem jogador, Item item, Scanner input){
        while (true) {
            String[] escolherMaoOpcao = {"[C] - Cancelar", "[D] - Mão Direita", "[E] - Mão Esquerda"}; 
            Console.limpar();
            Console.print("Escolha a Mão");
            Console.opcao(escolherMaoOpcao);
  
            char escolha = Console.getCharInput("CDE",input);
            switch (escolha) {
                case 'C':
                    return false; 
                case 'D':
                    jogador.empunharItens(item, true);
                    Console.pressioneENTER(input);
                    return true;
                case 'E':
                    jogador.empunharItens(item, false);
                    Console.pressioneENTER(input);
                    return true;
                default:
                    Console.printColorido("Escolha um opção valida", Cor.VERMELHO);
                    break;
            }
           
        }
    }

    private void lidarComItemGenerico(Personagem jogador, Item item, Scanner input){
        while (true) {
            String[] opcoesItemGenerico = {"[C] - Cancelar", "[J] - Jogar Fora"};
            Console.titulo("===");
            Console.opcao(opcoesItemGenerico);

            char escolha = Console.getCharInput("CJ",input);
            switch (escolha) {
                case 'C': 
                    return;
                case 'J':
                    jogador.getMochila().removerItem(item);
                    Console.printColorido("Você jogou " + item.getNome() + " fora e ele desaparece", Cor.VERMELHO);
                    Console.pressioneENTER(input);
                    return;
            
                default:
                    break;
            }
        }
    }
}
