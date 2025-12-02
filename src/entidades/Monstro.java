package entidades;
import enuns.Atributos;
import utils.Dado;

public class Monstro implements Combatente{
    private String nome;
    private int vida, ac, modificador, forca;

    //Construtor
    public Monstro(String nome, int vida, int ac, int modificador, int forca) {
        this.nome = nome;
        this.vida = vida;
        this.ac = ac;
        this.modificador = modificador;
        this.forca = forca;
    }

    //Criar um clone
    public Monstro clonar(){
        return new Monstro(this.nome, this.vida, this.ac, this.modificador, this.forca);
    }

    //Métodos da Interface
    @Override
    public void atacar(Combatente alvo, Dado dado) {
        System.out.println(this.nome + " Ruge agressivamente pronto para atacar " + alvo.getNome());

        int rolagem = dado.rolagem();
        int ataqueTotal = rolagem + this.modificador;
        if(ataqueTotal >= alvo.getAc()){
            System.out.println(this.nome + " Ataca furiosamente,rolando " + ataqueTotal);
            alvo.receberDano(this.forca + this.modificador);
        } else {
            System.out.println(this.nome + " tenta atacar mas "+ alvo.getNome() + " consegue se esquivar!");
        }
    }

    @Override
    public void receberDano(int dano) {
        this.vida -= dano;
        System.out.println(this.nome + " tomou " + dano + " de dano.");
        
    }

    @Override
    public boolean estaVivo() {
        return this.vida > 0;
    }

    @Override
    public void curar(int valor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'curar'");
    }
    @Override
    public void receberBuff(Atributos atributo, int valor, int duracao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'receberBuff'");
    }

     //Getter and Setters
     public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    public int getAc() {
        return ac;
    }
    public void setAc(int ac) {
        this.ac = ac;
    }
    public int getModificador() {
        return modificador;
    }
    public void setModificador(int modificador) {
        this.modificador = modificador;
    }
    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }


}
