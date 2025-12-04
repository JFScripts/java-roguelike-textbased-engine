package utils;

import entidades.Combatente;
import enuns.Cor;
import enuns.Estados;

public class ProcessadorDeEfeitos {
    

    public static void aplicarTick(Combatente alvo, Estados estado, int potencia){
        
        switch (estado) {
            case QUEIMADO:
                queimar(alvo, potencia);
                break;
            case ENVENENADO:
                envenenar(alvo, potencia);
                break;
            case SANGRANDO:
                sangrar(alvo, potencia);
                break;
            case REGENERANDO:
                regenerar(alvo, potencia);
                break;
            default:
                Console.printColorido("Estado não Implementado", Cor.VERMELHO);
                break;
        }
    }

    private static void queimar(Combatente alvo, int potencia){
        int danoFogo = (int) (alvo.getVidaMaxima() * 0.1) + potencia;
        alvo.receberDano(danoFogo);
        Console.print(alvo.getNome() + " está "+Estados.QUEIMADO.getMensagem());
    }

    private static void envenenar(Combatente alvo, int potencia){
        int danoVeneno = (int) (alvo.getVidaMaxima() * 0.15) + potencia;
        alvo.receberDano(danoVeneno);
        Console.print(alvo.getNome() + " está "+ Estados.ENVENENADO.getMensagem());
    }

    private static void sangrar(Combatente alvo, int potencia){
        int danoSangramento = (int) (alvo.getVidaMaxima() * 0.2) + potencia;
        alvo.receberDano(danoSangramento);
        Console.print(alvo.getNome() + " está "+ Estados.SANGRANDO.getMensagem());
    }

    private static void regenerar(Combatente alvo, int potencia){
        int regeneracao = (int) (alvo.getVidaMaxima() * 0.2) + potencia;
        alvo.curar(regeneracao);
        Console.print(alvo.getNome() + " está "+ Estados.REGENERANDO.getMensagem());

    }
}
