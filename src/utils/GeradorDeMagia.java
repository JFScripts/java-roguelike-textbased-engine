package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entidades.Magia;
import entidades.Personagem;
import enuns.TipoEfeito;

public class GeradorDeMagia {
    private static String[] formas = {
        "Bola", "Raio", "Sopro", "Toque", "Lança", "Explosão", "Sussuro", "Escudo"
    };
    private static String[] elementos = {
        "de Fogo", "Gélido", "Venenoso", "Divino", "Sombrio", "de Ferro", "do Caos", "Vampírico"
    };

    private static String criarNome(){
        Random rand = new Random();
        int formaSize = formas.length;
        int elementoSize = elementos.length;
        String nome = formas[rand.nextInt(formaSize)] + " " +elementos[rand.nextInt(elementoSize)];

        return nome;
    }

    private static TipoEfeito escolherEfeito(){
        Random rand = new Random();
        TipoEfeito[] opcoes =TipoEfeito.values();
        int indice = rand.nextInt(opcoes.length);
        return opcoes[indice];
    }

    public static Magia gerarMagia(Personagem jogador){
        String nomeFinal = "";
        Random rand = new Random();
        int nivelJogador = jogador.getLevel();
        List<TipoEfeito> listaDeEfeitos = new ArrayList<>();

        int qntEfeito = 1;
       
        int raridade = rand.nextInt(100);
        if(raridade < 10){
            qntEfeito = 3;
            nomeFinal = "(Lendario) ";

        } else if(raridade < 40){
            qntEfeito = 2;
            nomeFinal = "(Raro) ";
        }

        for(int i = 0; i < qntEfeito; i++){
            listaDeEfeitos.add(escolherEfeito());
        }

        int poder = (nivelJogador + 1) + rand.nextInt(nivelJogador+5);

        if(poder == (nivelJogador+6)){
            nomeFinal = nomeFinal + "Gigante ";
        } else if(poder > (nivelJogador+6)/2){
            nomeFinal = nomeFinal + "Grande ";
        }

        int custo = (poder / 2) * qntEfeito;

        nomeFinal = nomeFinal + criarNome();
        if (custo > poder) {
            nomeFinal = nomeFinal + " Gulosa";
        } else if (custo < (poder / 3)) {
            nomeFinal = nomeFinal + " Econômica";
        }
        return new Magia(nomeFinal, listaDeEfeitos, poder, custo);


    }
}
