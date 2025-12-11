package telas;

import static utils.Console.print;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Combatente;
import entidades.Magia;
import entidades.Personagem;
import utils.Console;
import utils.Dado;

public class TelaMagia implements Tela{
    
    private Tela ultimaTela;
    private Combatente alvoInimigo;

    public TelaMagia(Tela ultimaTela){
        this.ultimaTela = ultimaTela;
        this.alvoInimigo = null;

    }
    public TelaMagia(Tela ultimaTela, Combatente alvoInimigo){
        this.ultimaTela = ultimaTela;
        this.alvoInimigo = alvoInimigo;

    }

    @Override
    public Tela executar(Personagem jogador, Scanner input) {
        while (true) {
            List<String> stringMagiaLista = new ArrayList<>();

            Console.limpar();
            Console.titulo("Grimório");
            int tamanhoGrimorio = jogador.getGrimorio().getMagias().size();

            if(tamanhoGrimorio == 0){
                Console.print(jogador.getNome() + " não conhece nenhuma magia.");
                Console.pressioneENTER(input);
                return ultimaTela;
            }

            Console.print("Magias Conhecidas:");


            for(int i = 0; i < tamanhoGrimorio; i ++){
                String nomeCurMagia = jogador.getGrimorio().getMagiaIndex(i).getNome();
                int custoCurMagia = jogador.getGrimorio().getMagiaIndex(i).getCustoMana();
                stringMagiaLista.add((i+1) + " - " + nomeCurMagia + " | MP: " +custoCurMagia + " | Efeitos: " + jogador.getGrimorio().getMagiaIndex(i).getEfeito());
            }
            Console.opcao(stringMagiaLista);
            Console.print("Digite a magia que deseja conjurar ou pressione 0 para voltar");
            int escolha = input.nextInt();
            input.nextLine();
            if(escolha == 0){
                return ultimaTela;
            } else if(escolha > 0 && escolha <= tamanhoGrimorio){
                Magia magiaSelecionada = jogador.getGrimorio().getMagiaIndex(escolha - 1);
                List<Combatente> alvos = new ArrayList<>();
                alvos.add(jogador);
                Combatente alvoFinal;
                if(alvoInimigo != null){
                    alvos.add(alvoInimigo);
                }
                if(alvos.size() == 1){
                    alvoFinal = alvos.get(0);
                } else {
                    Console.print("Selecione o alvo ou aperte 0 para voltar:");
                    for(int i = 0; i < alvos.size(); i++){
                        Console.opcaoInt((i+1) + " - " + alvos.get(i).getNome());
                    }
                    escolha = input.nextInt();
                    input.nextLine();
                    if(escolha == 0){
                        return this;
                    } else if(escolha > 0 && escolha <= alvos.size()){
                        alvoFinal = alvos.get(escolha-1);
                    } else {
                        Console.print("Alvo Inválido");
                        continue;
                    }
                }
                String[] selecionarMagia = {"[C] - Cancelar", "[S] - Sim", "[N] - Não"};
                print("Conjurar " + magiaSelecionada.getNome() + " em " + alvoFinal.getNome() + "?");
                Console.opcao(selecionarMagia);

                char decisao = Console.getCharInput("CSN", input);
                input.nextLine();
                switch (decisao) {
                    case 'C':
                        return this;
                     case 'S':
                        jogador.conjurarMagia(alvoFinal, magiaSelecionada);
                        if(alvoInimigo != null && alvoInimigo.estaVivo()){
                            jogador.passarTurno();
                            Console.print("O turno passa e o Inimigo Reage!");
                            Console.esperar(0.5);
                            alvoInimigo.atacar(jogador, new Dado(20));
                        }
                        Console.pressioneENTER(input);
                        return ultimaTela;
                    case 'N':
                        Console.print("Voltando...");
                        Console.esperar(0.5);
                        return ultimaTela;
                    default:
                        Console.print("Comando Inválido");
                        break;
                }
            input.nextLine();
            } else {
                Console.print("Opção Inválida, tente novamente");
                Console.pressioneENTER(input);
            }
        }
    }
    
}
