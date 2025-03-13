public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    private int antigoValor;
    private int novoValor;
    private int nColunas;
    private int nLinhas;

    public DepthFirstPaths(Graph G, int[] posicaoMatriz, int nColunas, int novoValor, int nLinhas){
        s = G.posicaoGrafo(posicaoMatriz[0], posicaoMatriz[1], nColunas);
        antigoValor = G.retornaValor(s);
        this.novoValor = novoValor;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        this.nLinhas = nLinhas;
        this.nColunas = nColunas;
        validateVertex(s);
        dfs(G, s);
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void dfs(Graph G, int v){
        marked[v] = true;
        G.mudaValorElemento(v, novoValor, nLinhas, nColunas);
        for (ElementoMatriz w : G.adj(v)){
            if(!marked[w.posicaoElemento]){
                edgeTo[w.posicaoElemento] = v;
                if(w.valorElemento == antigoValor){
                    dfs(G, w.posicaoElemento);
                }
            }
        }
    }



    public int getS(){
        return s;
    }

    public void teste(Graph G){

    }

    public void consegueChegar(){
        for(int i = 0; i < marked.length; i++){
            if(marked[i]){
                System.out.println(i);
            }
        }
    }
}
