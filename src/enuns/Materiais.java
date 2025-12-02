package enuns;

public enum Materiais {
    MADEIRA("de Madeira", 1),
    PEDRA("de Pedra", 3),
    OSSO("de Osso", 5),
    COBRE("de Cobre", 8),
    BRONZE("de Bronze", 12),
    FERRO("de Ferro", 15),
    ACO("de Aço", 22),
    PRATA("Prateada", 38),
    DIAMANTE("de Diamante", 45),
    ADAMANTITE("de Adamantite", 65);

    private final String nome;
    private final int danoBase;
  

    private Materiais(String nome, int danoBase) {
        this.nome = nome;
        this.danoBase = danoBase;
    }

    public String getNome() {
        return nome;
    }


    public int getDanoBase() {
        return danoBase;
    }
}
