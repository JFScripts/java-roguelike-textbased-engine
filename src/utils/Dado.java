package utils;
import java.util.Random;

public class Dado{
    private int faces;
    //Construtor de inicialização
    public Dado(int faces) {
        this.faces = faces;
    }

    //Função responsável por gerar o número aleatório
    public int rolagem(){
        Random gerador = new Random();
        return gerador.nextInt(this.faces) + 1;
    }

    //Getters e Setters
    public int getFaces() {
        return faces;
    }

    public void setFaces(int faces) {
        this.faces = faces;
    }
    
}