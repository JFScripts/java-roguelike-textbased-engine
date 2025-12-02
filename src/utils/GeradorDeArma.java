package utils;

import java.util.Random;

import entidades.Arma;
import entidades.Personagem;
import enuns.AdjetivoArma;
import enuns.Materiais;
import enuns.Raridade;
import enuns.TiposArmas;

public class GeradorDeArma {



    public static Arma gerarArmaAleatoria(Personagem jogador){
        
        Materiais[] listaDeMateriais = Materiais.values();
        TiposArmas[] listaDeTipo = TiposArmas.values();
        AdjetivoArma[] listaDeAdjetivo = AdjetivoArma.values();
        Raridade[] litstaDeRaridade = Raridade.values();

        Random random = new Random();

        Materiais materialEscolhido = randomizarMaterial(listaDeMateriais, random, jogador.getLevel());
        TiposArmas tipoEscolhido = randomizarTiposArmas(listaDeTipo, random);
        AdjetivoArma adjetivoEscolhido = randomizarAdjetivo(listaDeAdjetivo, random);
        Raridade raridadeEscolhida = randomizarRaridade(litstaDeRaridade, random);

        String sufixo = "o";

        String materialEscolhidoNome = materialEscolhido.getNome();
        String tipoEscolhidoNome = tipoEscolhido.getNome();
        String adjetivoEscolhidoNome = adjetivoEscolhido.getNome();
        String raridadeEscolhidaNome = raridadeEscolhida.getNome();

        int materialEscolhidoDano = materialEscolhido.getDanoBase();
        int tipoEscolhidoDano = tipoEscolhido.getDanoPorTipo();
        int qntHit = tipoEscolhido.getQntDeHit();
        int adjetivoEscolhidoDano = adjetivoEscolhido.getDano();
        double raridadeEscolhidaMult = raridadeEscolhida.getMultiplicacaoDano();

    
        if(tipoEscolhidoNome.endsWith("a")|| tipoEscolhidoNome.endsWith("A")){
            sufixo = "a";
        }
        if(!raridadeEscolhidaNome.equals("Comum") && !raridadeEscolhidaNome.equals("Incomum")){
            raridadeEscolhidaNome = raridadeEscolhidaNome + sufixo;
        }

        String nomeArma = tipoEscolhidoNome + " " + raridadeEscolhidaNome + " "+materialEscolhidoNome + " " + adjetivoEscolhidoNome + sufixo + " - " + jogador.getLevel();

        int danoMinimo = jogador.getLevel() + materialEscolhidoDano + tipoEscolhidoDano + adjetivoEscolhidoDano;

        int danoMaximo = (int) (danoMinimo * raridadeEscolhidaMult);

        if(danoMinimo > danoMaximo){
            int temp = danoMaximo;
            danoMaximo = danoMinimo;
            danoMinimo = temp;
        }

        int precoArma = (int) (((danoMaximo + danoMinimo)/2) * raridadeEscolhidaMult);



        return new Arma(nomeArma, precoArma, raridadeEscolhida, danoMinimo, danoMaximo, qntHit, materialEscolhido);

    }

    public static Arma gerarArmaEspecifica(TiposArmas tipo, Materiais material, AdjetivoArma adjetivo, Raridade raridade, int nivel) {
        
        
        String sufixo = "o";
        String nomeTipo = tipo.getNome();

        if (nomeTipo.endsWith("a") || nomeTipo.endsWith("A")) {
            sufixo = "a";
        }

        String nomeRaridade = raridade.getNome();
        
        if (!nomeRaridade.equals("Comum")) {
            nomeRaridade = nomeRaridade + sufixo + " ";
        } else {
            nomeRaridade = ""; // Se for comum, não escreve nada
        }

        
        String nomeFinal = tipo.getNome() + " " + nomeRaridade + material.getNome() + " " + adjetivo.getNome() + sufixo;

        int danoBase = material.getDanoBase() + nivel + adjetivo.getDano() + tipo.getDanoPorTipo();
        
        int danoMin = danoBase;
        int danoMax = (int) (danoMin * raridade.getMultiplicacaoDano()) + 2;

        if (danoMax <= danoMin) {
            danoMax = danoMin + 1;
        }

        // 3. Preço
        int preco = (int) ((danoMax + danoMin)/2  * raridade.getMultiplicacaoDano());

        return new Arma(nomeFinal, preco, raridade, danoMin, danoMax, tipo.getQntDeHit(), material);
    }

    public static Arma gerarEspadaMadeira() {
        return gerarArmaEspecifica(
            TiposArmas.ESPADA, 
            Materiais.MADEIRA, 
            AdjetivoArma.VELHO, 
            Raridade.COMUM, 
            1 
        );
    }


    private static Materiais randomizarMaterial(Materiais[] listaDeMateriais, Random random, int curNivel){
        int indiceMaximo = (curNivel/3) + 2;
        if(random.nextInt(100) > 90){
            indiceMaximo ++;
            if(indiceMaximo > listaDeMateriais.length){
                indiceMaximo = listaDeMateriais.length;
            }
        }
        int sorteio = random.nextInt(indiceMaximo);
        return listaDeMateriais[sorteio];
    }

    private static TiposArmas randomizarTiposArmas(TiposArmas[] listaDeArmas, Random random){
        int sorteio = random.nextInt(listaDeArmas.length);
        return listaDeArmas[sorteio];
    }

    private static AdjetivoArma randomizarAdjetivo(AdjetivoArma[] listaDeAdjetivos, Random random){
        int sorteio = random.nextInt(listaDeAdjetivos.length);
        return listaDeAdjetivos[sorteio];
    }

    private static Raridade randomizarRaridade(Raridade[] listaDeRaridade, Random random){
        int probabilidade = random.nextInt(100);

        if(probabilidade > 95){
            return Raridade.LENDARIO;
        } else if(probabilidade > 85){
            return Raridade.EPICO;
        } else if(probabilidade > 70){
            return Raridade.RARO;
        } else if(probabilidade > 40){
            return Raridade.INCOMUM;
        } else {
            return Raridade.COMUM;
        }
        
    }
}
