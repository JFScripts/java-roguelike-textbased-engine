package enuns;

public enum Raridade {
    COMUM("Comum", 1, Cor.BRANCO),
    INCOMUM("Incomum", 1.5, Cor.CIANO),
    RARO("Rar", 2, Cor.VERDE),
    EPICO("Epic", 2.5, Cor.ROXO),
    LENDARIO("Lendari", 3, Cor.AMARELO);

    private final String nome;
    private final double multiplicacaoDano;
    private final Cor corRaridade;

    private Raridade(String nome, double multiplicacaoDano, Cor corRaridade) {
        this.nome = nome;
        this.multiplicacaoDano = multiplicacaoDano;
        this.corRaridade = corRaridade;
    }

    public String getNome() {
        return nome;
    }

    public double getMultiplicacaoDano() {
        return multiplicacaoDano;
    }

    public Cor getCorRaridade(){
        return corRaridade;
    }


}
