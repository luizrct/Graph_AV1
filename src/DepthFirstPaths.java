import java.util.*;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    private int antigoValor;
    private int novoValor;
    private int nColunas;
    private int nLinhas;
    private int contador = 0;
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
        //dfsIterativa(G, s);
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

    public void dfsIterativa(Graph G, int s){
        Stack<Integer> pilha = new Stack<Integer>();
        pilha.push(s);

        while(!pilha.isEmpty()){
            int v = pilha.pop();
            if(!marked[v]){
                marked[v] = true;
                G.mudaValorElemento(v, novoValor, nLinhas, nColunas);
                ElementoMatriz[] vizinhos = new ElementoMatriz[G.adj(v).size()];
                int contador = 0;
                for(ElementoMatriz w : G.adj(v)){
                    vizinhos[contador] = w;
                    contador++;
                }
                for(int i = vizinhos.length - 1; i >= 0; i--){
                    if(!marked[vizinhos[i].posicaoElemento]){
                        edgeTo[vizinhos[i].posicaoElemento] = v;
                        if(vizinhos[i].valorElemento == antigoValor){
                            pilha.push(vizinhos[i].posicaoElemento);
                        }
                    }
                }
            }
        }
    }

    /*
    public void dfsIterativa(Graph G, int v) {
        Stack<Integer> pilha = new Stack<>();
        pilha.push(v);
        marked[v] = true;
        while (!pilha.isEmpty()) {
            int u = pilha.pop();
            G.mudaValorElemento(u, novoValor, nLinhas, nColunas);
            for (ElementoMatriz w : G.adj(u)) {
                if (!marked[w.posicaoElemento]) {
                    marked[w.posicaoElemento] = true;
                    edgeTo[w.posicaoElemento] = u;
                    if (w.valorElemento == antigoValor) {
                        pilha.push(w.posicaoElemento);
                    }
                }
            }
        }
    }

     */


    public int getS(){
        return s;
    }

    public void consegueChegar(){
        for(int i = 0; i < marked.length; i++){
            if(marked[i]){
                System.out.println(i);
            }
        }
    }
}
