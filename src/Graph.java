import assets.In;
import assets.Stack;
import java.util.NoSuchElementException;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Bag<ElementoMatriz>[] adj;
    //PRIMEIRO CONSTRUTOR
    /*public Graph(int V){
        if(V < 0){
            throw new IllegalArgumentException("O número de vertices deve ser não negativo");
        }
        this.V = V;
        this.E = 0;
        this.adj = new Bag[V];
        for(int i = 0; i < V; i++){
            this.adj[i] = new Bag<Integer>();
        }
    }

     */

    //QUARTO CONSTRUTOR
    //Esse construtor recebe uma matriz e transforma em grafo
    public Graph(int[][] matriz){
        int nColunas = matriz[0].length;
        this.V = matriz.length * nColunas;
        this.E = 0;
        this.adj = new Bag[V];
        int[][] ligacoes = {{-1, 1}, {0, 1}, {1, 1}, {1, 0}};
        for(int i = 0; i < V; i++){
            this.adj[i] = new Bag<ElementoMatriz>();
            /*int[] posicaoMatriz = ElementoMatriz.posicaoMatriz(i, nColunas);
            ElementoMatriz elemento = new ElementoMatriz(matriz[posicaoMatriz[0]][posicaoMatriz[1]], i);
            /*for(int j = 0; j < ligacoes.length; j++){
                int linha = posicaoMatriz[0] + ligacoes[j][0];
                int coluna = posicaoMatriz[1] + ligacoes[j][1];
                if(ElementoMatriz.elementoExiste(matriz, linha, coluna)){
                    addEdge(elemento, new ElementoMatriz(matriz[linha][coluna], ElementoMatriz.posicaoGrafo(linha, coluna, nColunas)));
                }
            }

             */
        }
        for(int i = 0; i < V; i++){
            int[] posicaoMatriz = posicaoMatriz(i, nColunas);
            ElementoMatriz elementoMatriz = new ElementoMatriz(matriz[posicaoMatriz[0]][posicaoMatriz[1]], i);
            for(int j = 0; j < ligacoes.length; j++){
                int linha = posicaoMatriz[0] + ligacoes[j][0];
                int coluna = posicaoMatriz[1] + ligacoes[j][1];
                if(elementoExiste(matriz, linha, coluna)){
                    addEdge(elementoMatriz, new ElementoMatriz(matriz[linha][coluna], posicaoGrafo(linha, coluna, nColunas)));
                }
            }
        }
    }

    public int V(){
        return this.V;
    }

    public int E(){
        return this.E;
    }

    private void validateVertex(int v){
        if(v < 0 || v >= this.V){
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }

    public void addEdge(ElementoMatriz v, ElementoMatriz w){
        this.adj[v.posicaoElemento].add(w);
        this.adj[w.posicaoElemento].add(v);
        this.E++;
    }


    /*public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

     */

    public int dagree(int v){
        validateVertex(v);
        return this.adj[v].size();
    }
    public void imprimeGrafo(){
        for(int i = 0; i < this.V; i++){
            System.out.print(i+" -> ");
            String a = "";
            for(ElementoMatriz elemento : adj[i]){
                a += String.valueOf(elemento.valorElemento)+" ";
            }
            System.out.print(a);
            System.out.println();
        }
    }

    public static int[] posicaoMatriz(int posicaoElemento, int nColunas){
        int linha = posicaoElemento / nColunas;
        //a = b*c + r
        //r = a - b*c
        int coluna = posicaoElemento - (nColunas * (posicaoElemento / nColunas));
        return new int[]{linha, coluna};
    }

    public static int posicaoGrafo(int linha, int coluna, int nColunas){
        int posicao = linha * nColunas + coluna;
        return posicao;
    }

    public static boolean elementoExiste(int[][] matriz, int linha, int coluna){
        if(linha < 0 || coluna < 0){
            return false;
        }
        if(linha >= matriz.length || coluna >= matriz[0].length){
            return false;
        }
        return true;
    }
    /*public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

     */

    /*public String toDot() {
        StringBuilder s = new StringBuilder();
        s.append("graph {" + NEWLINE);
        s.append("node[shape=circle, style=filled, fixedsize=true, width=0.3, fontsize=\"10pt\"]" + NEWLINE);
        int selfLoops = 0;
        for (int v = 0; v < V; v++) {
            for (int w : adj[v]) {
                if (v < w) {
                    s.append(v + " -- " + w + NEWLINE);
                }
                else if (v == w) {
                    // include only one copy of each self loop (self loops will be consecutive)
                    if (selfLoops % 2 == 0) {
                        s.append(v + " -- " + w + NEWLINE);
                    }
                    selfLoops++;
                }
            }
        }
        s.append("}" + NEWLINE);
        return s.toString();
    }

    //RETORNA UM VETOR COM UM BOOLEANO IDENTIFICANDO SE AQUELE VETOR CONSEGUE CHEGAR EM DETERMINADO VETOR
    /*public boolean[] consegueChegar(int v, boolean[] check, Stack<Integer> chamadas, boolean primeira){
        //o que acontece na primeira chamada
        boolean[] check1 = check;
        Stack<Integer> chamadas1 = chamadas;
        //caso base
        if(!primeira && chamadas.isEmpty()){
            return check1;
        } else if (primeira) {
            check1[v] = true;
            for(int i : adj[v]){
                check1[i] = true;
                chamadas1.push(i);
            }
            consegueChegar(v, check1, chamadas1, false);
        }else {
            int elemento = chamadas1.pop();
            for(int i : adj[elemento]){
                if(!check1[i]){
                    check1[i] = true;
                    chamadas1.push(i);
                }
            }
            consegueChegar(v, check1, chamadas1, false);
        }
        return check1;
    }

     */

    public static void main(String[] args){

    }
}
