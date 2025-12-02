package enuns;

public enum AdjetivoArma {
    VELHO("Velh", -1),
    NOVO("Nov", 1),
    BRUTAL("Brut", 3),
    MAGICA("Mágic", 2);
    
    private final String nome;
    private final int dano;

    private AdjetivoArma(String nome, int dano) {
        this.nome = nome;
        this.dano = dano;
    }

    public String getNome() {
        return nome;
    }

    public int getDano() {
        return dano;
    }

    
}
