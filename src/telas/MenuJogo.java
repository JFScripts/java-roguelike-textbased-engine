package telas;
import static utils.Console.*;

import java.util.Scanner;

import entidades.Arma;
import entidades.Personagem;
import utils.Console;
import utils.GeradorDeArma;


public class MenuJogo implements Tela {

    public Tela executar(Personagem jogador, Scanner input) {
        limpar();
        titulo("Menu");
        print("Escolha uma Opção:");
        opcao("00 - Sair");
        opcao("01 - Iniciar Jogo");
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
                int comum = 0, incomum = 0, raro= 0, epico= 0, lendario= 0;
                int madeira = 0, pedra = 0, osso = 0, cobre = 0, bronze = 0, ferro = 0, aco = 0, prata = 0, diamante = 0, adamantite = 0;
                for(int i = 0; i < 100; i++){
                    titulo(i);
                    Arma armaGerada = GeradorDeArma.gerarArmaAleatoria(jogador);
                    Console.print(armaGerada);
                    switch (armaGerada.getRaridade()) {
                        case COMUM:
                            comum ++;
                            break;
                        case INCOMUM:
                            incomum ++;
                            break;
                        case RARO:
                            raro ++;
                            break;
                        case EPICO:
                            epico ++;
                        case LENDARIO:
                            lendario ++;
                            break;
                    }

                    switch (armaGerada.getMaterial()) {
                        case MADEIRA:
                            madeira ++;
                            break;
                        case PEDRA:
                            pedra ++;
                            break;
                        case OSSO:
                            osso ++;
                            break;
                        case COBRE:
                            cobre ++;
                            break;
                        case BRONZE:
                            bronze ++;
                            break;
                        case FERRO:
                            ferro ++;
                            break;
                        case ACO:
                            aco ++;
                            break;
                        case PRATA:
                            prata ++;
                            break;
                        case DIAMANTE:
                            diamante ++;
                            break;
                        case ADAMANTITE:
                            adamantite ++;
                            break;
                    }
                }
                Console.titulo("");
                Console.print("Comum: "+ comum);
                Console.print("Inomum: "+ incomum);
                Console.print("Raro: "+ raro);
                Console.print("Epico: "+ epico);
                Console.print("Lendario: "+ lendario);
                Console.titulo("");
                Console.print("Madeira: "+ madeira);
                Console.print("Pedra: "+ pedra);
                Console.print("Osso: "+ osso);
                Console.print("Cobre: "+ cobre);
                Console.print("Bronze: "+ bronze);
                Console.print("Ferro: "+ ferro);
                Console.print("Aco: "+ aco);
                Console.print("Prata: "+ prata);
                Console.print("Diamante: "+ diamante);
                Console.print("Adamantite: "+ adamantite);
                Console.pressioneENTER(input);
                return this;

            default:
                print("Opção Inválida, tente novamente");
                Console.esperar(0.5);
                return this;
        }
        
    }
    
}
