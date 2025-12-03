package enuns;

public enum Estados {
    QUEIMADO("Em Chamas", Cor.VERMELHO, false),
    ENVENENADO("Envenenado", Cor.VERDE, false),
    SANGRANDO("Sangrando", Cor.VERMELHO, false),
    REGENERANDO("Regenerando", Cor.AMARELO, true);

    private final String Mensagem;
    private final Cor CorMensagem;
   
    private final boolean ehBenefico;

    private Estados(String mensagem, Cor corMensagem, boolean ehBenefico) {
        Mensagem = mensagem;
        CorMensagem = corMensagem;
        this.ehBenefico = ehBenefico;
    }    
    public String getMensagem() {
        return Mensagem;
    }

    public Cor getCorMensagem() {
        return CorMensagem;
    }

    public boolean isEhBenefico() {
       return ehBenefico;
    }
}
