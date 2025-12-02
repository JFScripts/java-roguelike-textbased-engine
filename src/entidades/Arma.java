package entidades;

import java.util.Random;

import enuns.Materiais;
import enuns.Raridade;

public class Arma extends Item{
    
    private int danoMinimo;
    private int danoMaximo;
    private int hitPorTurno;
    private Materiais material;

    public Arma(String nome, int preco, Raridade raridade, int danoMinimo, int danoMaximo, int hitPorTurno, Materiais material) {
        super(nome, preco, raridade);
        
        this.danoMinimo = danoMinimo;
        this.danoMaximo = danoMaximo;
        this.hitPorTurno = hitPorTurno;
        this.material = material;
    }

    public int rolarDano(){
        Random random = new Random();
        int danoTotal = 0;
        for(int i = 0; i < this.hitPorTurno; i++){
            danoTotal += this.danoMinimo + random.nextInt(this.danoMaximo - this.danoMinimo +1);
        }
        return danoTotal;
    }

    public int getDanoMinimo() {
        return danoMinimo;
    }

    public void setDanoMinimo(int danoMinimo) {
        this.danoMinimo = danoMinimo;
    }

    public int getDanoMaximo() {
        return danoMaximo;
    }

    public void setDanoMaximo(int danoMaximo) {
        this.danoMaximo = danoMaximo;
    }

    public int getHitPorTurno() {
        return hitPorTurno;
    }

    public void setHitPorTurno(int hitPorTurno) {
        this.hitPorTurno = hitPorTurno;
    }

    @Override
    public String toString() {
        String cabecalho = super.getNome();
        String status = " | Dano: " + danoMinimo + " - " + danoMaximo;
        if(hitPorTurno > 1){
            status += " x" + hitPorTurno;
        }
        status += " | Valor: " + super.getPreco()+" PO";
        return cabecalho + status;
    }

    public Materiais getMaterial() {
        return material;
    }

    public void setMaterial(Materiais material) {
        this.material = material;
    } 

}
