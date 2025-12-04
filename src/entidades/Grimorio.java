package entidades;

import java.util.ArrayList;
import java.util.List;

public class Grimorio {
    
    private List<Magia> magias;

    public Grimorio() {
        this.magias = new ArrayList<>();
    }

    /**Método para aprender uma magia nova
     * @param magiaNova
     */
    public void aprenderMagia(Magia magiaNova){
        magias.add(magiaNova);
    }


    /**Método que retorna as magias que estão na lista
     * @return
     */
    public List<Magia> getMagias(){
        return this.magias;
    }

    /**Método para buscar uma magia pelo índice
     * @param i Se i < 0, i se torna 0 e se i > o tamanho da lista, i se torna o tamanho da lista
     * @return
     */
    public Magia getMagiaIndex(int i){
        if(i < 0){
            i = 0;
        }
        if(i > magias.size()){
            i = magias.size() - 1;
        }
        return this.magias.get(i);
    }
    
    
}
