public class ElementoMatriz {
    int valorElemento;
    int posicaoElemento;

    public ElementoMatriz(int valorElemento, int posicaoElemento){
        this.valorElemento = valorElemento;
        this.posicaoElemento = posicaoElemento;
    }

    public String toString(){
        return String.valueOf(this.valorElemento);
    }
}
