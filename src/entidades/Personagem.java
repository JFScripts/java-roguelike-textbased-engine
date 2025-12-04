package entidades;
import utils.Dado;

import java.util.HashMap;

import static utils.Console.print;

import java.util.ArrayList;
import java.util.List;

import enuns.Atributos;
import enuns.Estados;
import enuns.TipoEfeito;
import utils.EfeitoTemporario;
import utils.ProcessadorDeEfeitos;
import utils.Console;

public class Personagem implements Combatente{
    private String nome;
    private int vida; 
    private int mana; 
    private int ac; 
    private int level = 1;
    private int vidaMaxima;
    private int manaMaxima;
    private int pecasOuro = 0;

    private Item mDireita; //Arma mão direita
    private Item mEsquerda; //Arma mão esquerda
    private List<Item> mochila = new ArrayList<>();
    private FichaAtributos ficha;
    private HashMap<Atributos, EfeitoTemporario> buffsAtivos = new HashMap<>();//Buffs
    private HashMap<Estados, EfeitoTemporario> estados = new HashMap<>();
    private List<Magia> grimorio = new ArrayList<>();

    
    // TODO: Fazer a forca influenciar na capacidade de carga

    /**Inicializa um novo personagem com os valores bases definido
     * @param nome Para imersão o jogador pode colocar um nome para o personagem
     * @param inteligencia  Capacidade magica e regeneracao de mana
     * @param forca Dano fisico do jogador
     * @param constituicao Define a vida maxima
     * @param agilidade Calcula o AC do jogador
     * @param ac A dificuldade para acertar o jogador
     * @param mEsquerda Item que o jogador está segurando na mao esquerda
     * @param mDireita Item que o jogador está segurando na mao direita
     */
    public Personagem(String nome, int inteligencia, int forca, int constituicao, int agilidade, int ac, Arma mEsquerda, Arma mDireita) {

        this.nome = nome;
        this.ficha = new FichaAtributos(inteligencia, forca, constituicao, agilidade);
        this.mEsquerda = mEsquerda;
        this.mDireita = mDireita;

        recalcularDerivados();
        
        this.vida = this.vidaMaxima;
        this.mana = this.manaMaxima;
    }

    //Funções privadas
    private void recalcularDerivados(){
        this.vidaMaxima = 10 + (getAtributos(Atributos.CONSTITUICAO) * 2);
        this.manaMaxima = 5 + (getAtributos(Atributos.INTELIGENCIA)*3);
        this.ac = 3 + (getAtributos(Atributos.AGILIDADE) / 2);
        
    }

    //Fuções Publicas
    public void realizarTeste(Dado d20, int dificuldade){
        int rolagem = d20.rolagem();
        int total = rolagem;

        System.out.println(this.nome + " Está Rolando um d"+d20.getFaces()+"...");
        System.out.println("Rolado "+rolagem);

        if(total >= dificuldade){
            System.out.println("Teste passado com sucesso! ("+rolagem+"="+total+")");
        } else {
            System.out.println("Teste falhou ("+rolagem+"="+total+")");
        }
    }


    
    /**Função responsável por aumentar o nível do jogador
     * @param atributo
     * O método aumenta o nivel do jogador, o atributo e reseta a vida e a mana para os valores máximos
     */
    public void passarDeNivel(Atributos atributo){
        
        ficha.aumentarAtributo(atributo, 1);
        this.level ++;
        recalcularDerivados();
        this.vida = this.vidaMaxima;
        this.mana = this.manaMaxima;
        Console.narracao("Você se sente revigorado! (Vida e Mana recuperados)");

    }

    public void passarTurno(){
        List<Atributos> buffParaRemover = new ArrayList<>();
        for (Atributos chave : buffsAtivos.keySet()) {
            EfeitoTemporario b = buffsAtivos.get(chave);
            b.diminuirTurno(); 
            if (b.getDuracao() <= 0) {
                buffParaRemover.add(chave);
            }
         }

        
        for (Atributos chaveMorta : buffParaRemover) {
            buffsAtivos.remove(chaveMorta);
            recalcularDerivados();
            Console.narracao("O efeito de " + chaveMorta + " acabou.");
        }

        List<Estados> estadoParaRemover = new ArrayList<>();
        for(Estados chave : estados.keySet()){
            EfeitoTemporario e = estados.get(chave);
            
            ProcessadorDeEfeitos.aplicarTick(this, chave, e.getPotencia()); 
            
            e.diminuirTurno();
            
            if(e.getDuracao() <= 0){
                estadoParaRemover.add(chave);
            }
        }
        for(Estados chaveMorta : estadoParaRemover){
            estados.remove(chaveMorta);
            recalcularDerivados();
        }
        regenerarMana(2 + (getAtributos(Atributos.INTELIGENCIA)));
    }

    public void aprenderMagia(Magia novaMagia){
        this.grimorio.add(novaMagia);
    }

    public void conjurarMagia(Combatente alvo, Magia conjuracao){
        if(conjuracao.getCustoMana() > this.mana){
            Console.narracao("Você sente a magia fluindo pelo seu corpo mas se sente muito cansado para conjura-la");
            Console.print("Custo de Mana maior que a atual");
            Console.esperar(1);
            return;
        }
        this.mana -= conjuracao.getCustoMana();

        for(TipoEfeito efeito: conjuracao.getEfeito()){
            switch (efeito) {
                case GELO:
                case RAIO:
                    alvo.receberDano(conjuracao.getPoder());
                    Console.narracao(alvo.getNome() + " se Machuca com " + conjuracao.getNome());
                    break;
                case DANO:
                    alvo.receberEfeito(Estados.SANGRANDO, 2, 4);
                    break;
                case FOGO:
                    alvo.receberEfeito(Estados.QUEIMADO, 2, 4);
                    break;
                case NECROTICO:
                    alvo.receberEfeito(Estados.ENVENENADO, 2, 4);
                    break;
                case CURA:
                    alvo.curar(conjuracao.getPoder());
                    Console.narracao("Os ferimentos de "+alvo.getNome() + " se Fecham");
                    break;
                case BUFF_AGILIDADE:
                    alvo.receberBuff(Atributos.AGILIDADE, conjuracao.getPoder(), 3);
                    Console.narracao(alvo.getNome() + " Se move mais rapido");
                    break;
                case BUFF_FORÇA:
                    alvo.receberBuff(Atributos.FORCA, conjuracao.getPoder(), 3);
                    Console.narracao(alvo.getNome() + " Fica mais Forte");
                    break;
                default:
                    Console.narracao("Você conjura a magia mas nada acontece");
                    break;
            }
            Console.esperar(0.5);
        }

    }

    public void regenerarMana(int valorRecuperado){
        int curMana = getMana();
        setMana(curMana + valorRecuperado);
        if(getMana() >= getManaMaxima()){
            setMana(getManaMaxima());
        }
        print("Mana Regenerada em " + valorRecuperado + " pontos");

    }

    public void receberItem(Item itemRecebido){
        this.mochila.add(itemRecebido);
        Console.print("Você obteve " + itemRecebido.getNome());
    }

    public void receberEfeito(Estados estado, int valor, int turnos){
        EfeitoTemporario efeito = new EfeitoTemporario(valor, turnos);
        estados.put(estado, efeito);
        System.out.print(this.nome + " esta ");
        Console.printColorido(estado.getMensagem(), estado.getCorMensagem());     
    }

    //Funções da Interface
    @Override
    public void atacar(Combatente alvo, Dado dado) {
        int danoTotal = 0;
       if(mDireita != null && mDireita instanceof Arma arma){
            danoTotal += arma.rolarDano() + getAtributos(Atributos.FORCA);
        } 
        if(mEsquerda != null && mEsquerda instanceof Arma arma){
            danoTotal += arma.rolarDano() + getAtributos(Atributos.FORCA);
        } 
        
        System.out.println(this.nome + " prepara para atacar " + alvo.getNome());

        int rolagem = dado.rolagem();
        int bonusAtaque = getAtributos(Atributos.FORCA);
        int ataqueTotal = rolagem + bonusAtaque;
        if(ataqueTotal >= alvo.getAc()){
            System.out.println("ACERTOU! (Rolou " + ataqueTotal + ")");
    
            alvo.receberDano(danoTotal);
        } else {
            System.out.println("Errou...(Rolou: "+ataqueTotal+")");
        }
    }

    public void ganharOuro(int qntGanha){
        int curOuro = getPecasOuro();
        int ouroTotal = curOuro + qntGanha;
        setPecasOuro(ouroTotal);
        Console.print("Você recebeu " + qntGanha + " peças de ouro");
    }

    public boolean removerOuro(int qntRemover, String mensagemDeFalha){
        int curOuro = getPecasOuro();
        if(qntRemover > curOuro){
            return false;
        } else {
            int ouroTotal = curOuro - qntRemover;
            setPecasOuro(ouroTotal);
            return true;
        }
    }

    public void empunharItens(Item itemNovo, boolean naDireita){
        
        if(!getMochila().contains(itemNovo)){
            Console.print("ERRO: Item não Existe na Mochila");
            return;
        }
        
        Item itemNaMaoAtual = (naDireita) ? this.mDireita : this.mEsquerda;
        String mao = (naDireita) ? "Mão Direita" : "Mão Esquerda";
        

        if(itemNaMaoAtual != null){
            this.mochila.add(itemNaMaoAtual);
            Console.print("Você guardou "+ itemNaMaoAtual.getNome() + " em sua mochila");
        }
        if(naDireita){
            this.mDireita = itemNovo;
        } else {
            this.mEsquerda = itemNovo;
        }
        this.mochila.remove(itemNovo);
        Console.print("Você empunhou " + itemNovo.getNome() + " em sua mao "+ mao);
        

    }

    @Override
    public void receberDano(int dano) {
        this.vida -= dano;
        System.out.println(this.nome + " tomou " + dano + " de dano. Vida restante: "+this.vida);
        if(!estaVivo()){
            System.out.println("XXX " + this.nome + " CAIU EM COMBATE! XXX");
        }
    }

    @Override
    public boolean estaVivo() {
        return this.vida > 0;
    }

    @Override
    public void curar(int valor) {
       this.setVida(this.getVida()+valor);
       if(this.getVida() >= this.getVidaMaxima()){
            this.setVida(this.getVidaMaxima());
        }
    }

    @Override
    public void receberBuff(Atributos atributo, int valor, int duracao) {
        EfeitoTemporario buffEscolhido = new EfeitoTemporario(valor, duracao);
        this.buffsAtivos.put(atributo, buffEscolhido);
        recalcularDerivados();

    }

    

     //Getters e Setters
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

    public Item getmDireita() {
        return mDireita;
    }
    public void setmDireita(Item mDireita) {
        this.mDireita = mDireita;
    }
    public Item getmEsquerda() {
        return mEsquerda;
    }
    public void setmEsquerda(Item mEsquerda) {
        this.mEsquerda = mEsquerda;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getManaMaxima() {
        return manaMaxima;
    }

    public void setManaMaxima(int manaMaxima) {
        this.manaMaxima = manaMaxima;
    }

    /** Método responsável por pegar o valor do atributo do jogador
     * @param escolha
     * @return Retorna o valor total COM o buff ativo
     */
    public int getAtributos(Atributos escolha) {
        int atributoTotal = ficha.getValorBase(escolha);
        EfeitoTemporario buffEncontrado = buffsAtivos.get(escolha);
        if(buffEncontrado != null){
            atributoTotal += buffEncontrado.getPotencia();
        }
        return atributoTotal;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public HashMap<Atributos, EfeitoTemporario> getBuffsAtivos() {
        return buffsAtivos;
    }

    public void setBuffsAtivos(HashMap<Atributos, EfeitoTemporario> buffsAtivos) {
        this.buffsAtivos = buffsAtivos;
    }

    public List<Magia> getGrimorio() {
        return grimorio;
    }

    public List<Item> getMochila() {
        return mochila;
    }

    public void setMochila(List<Item> mochila) {
        this.mochila = mochila;
    }

    public int getPecasOuro() {
        return pecasOuro;
    }

    public void setPecasOuro(int pecasOuro) {
        this.pecasOuro = pecasOuro;
    }


    
}
