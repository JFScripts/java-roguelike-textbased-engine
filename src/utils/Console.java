package utils;

import java.util.List;
import java.util.Scanner;

import enuns.Cor;

public class Console {

    public static void limpar(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void esperar(double segundos){
        long milisegundos = (long) (segundos * 1000);
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
        }
    }

    //Sistema para o DSL
    public static void narracao(Object texto){
        System.out.println("'" + texto + "'");
    }

    public static void opcaoInt(Object texto){
        System.out.println("> " + texto);
    }

    public static void opcao(Object[] texto){
        for(int i = 0; i < texto.length; i++){
            System.out.println("> " + texto[i]);
        }
    }

    public static void opcao(List<String> texto){
        for(int i = 0; i < texto.size(); i++){
            System.out.println("> " + texto.get(i));
        }
    }

    public static void print(Object texto){
        System.out.println(texto);
    }

    
    public static void titulo(Object texto){
        System.out.println("==="+ texto + "===");
    }

    public static void pressioneENTER(Scanner input){
        System.out.println("Pressione ENTER para continuar");
        input.nextLine();
    }

    public static int getIntINPUT(String mensagem, Scanner input){     
        int digitado;
        while (true) {
            if(mensagem != null && !mensagem.isEmpty()){
                System.out.println(mensagem);
            }
            if(input.hasNextInt()){
                digitado = input.nextInt();
                input.nextLine();
                return digitado;
            } else {
                input.nextLine();
                System.out.println("Digite uma opção valida.");
            }
        }

    }

    public static int getIntINPUT(Scanner input){
        return getIntINPUT(null, input);
    }

    public static void printColorido(Object mensagem, Cor cor){
        String codigoCor = "";
        switch (cor) {
            case VERMELHO:
                codigoCor = "\u001B[31m";
                break;
            case VERDE:
                codigoCor = "\u001B[32m";
                break;
            case AMARELO:
                codigoCor = "\u001B[33m";
                break;
            case AZUL:
                codigoCor = "\u001B[34m";
                break;
            case ROXO:
                codigoCor = "\u001B[35m";
                break;
            case CIANO:
                codigoCor = "\u001B[36m";
                break;
            default:
                codigoCor = "\u001B[0m";
                break;
        }
        System.out.println(codigoCor + mensagem +"\u001B[0m");

    }

    public static void printarBarra(Object mensagem, int curValor, int valorMaximo, Cor cor){
        String barra = mensagem + ": " + curValor + " / " + valorMaximo;

        if(cor == null){
            System.out.println(barra);
        } else {
            printColorido(barra, cor);
        }

    }

    public static char getCharInput(String opcoes, Scanner input){
        while(true){
            System.out.print("> ");
            String escolha = input.nextLine().toUpperCase();
            if(escolha.length() > 0){
                char charEscolhido = escolha.charAt(0);
                if(opcoes.indexOf(charEscolhido) != -1){
                    return charEscolhido;
                } 
            }  
            printColorido("Opção Inválida, tente novamente", Cor.VERMELHO);
            pressioneENTER(input);
            
        }
    }


}
