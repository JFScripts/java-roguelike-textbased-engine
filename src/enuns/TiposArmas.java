package enuns;

public enum TiposArmas {

    ESPADA("Espada", 5, 1),
    MACHADO("Machado", 10, 1),
    ADAGA("Adaga", 2, 3),
    MARTELO("Martelo", 12, 1);

    private final String nome;
    private final int danoPorTipo;
    private final int qntDeHit;

    private TiposArmas(String nome, int danoPorTipo, int qntDeHit) {
        this.nome = nome;
        this.danoPorTipo = danoPorTipo;
        this.qntDeHit = qntDeHit;
    }
    
    public String getNome() {
        return nome;
    }
    public int getDanoPorTipo() {
        return danoPorTipo;
    }
    public int getQntDeHit() {
        return qntDeHit;
    }

}
