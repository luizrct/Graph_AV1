import java.io.FileWriter;
import java.io.BufferedWriter;

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
    //Complexidade: O(N), para N = n X m (n para linhas e m para colunas de uma matriz)
    public Graph(int[][] matriz){
        int nColunas = matriz[0].length;
        this.V = matriz.length * nColunas;
        this.E = 0;
        this.adj = new Bag[V];
        int[][] ligacoes = {{-1, 1}, {0, 1}, {1, 1}, {1, 0}};
        for(int i = 0; i < V; i++){
            this.adj[i] = new Bag<ElementoMatriz>();
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
    //Complexidade O(V+E) (número de vertices V e arestas E)
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

    // Complexidade O(1)
    public int[] posicaoMatriz(int posicaoElemento, int nColunas){
        int linha = posicaoElemento / nColunas;
        //a = b*c + r
        //r = a - b*c
        int coluna = posicaoElemento - (nColunas * (posicaoElemento / nColunas));
        return new int[]{linha, coluna};
    }
    // Complexidade O(1)
    public int posicaoGrafo(int linha, int coluna, int nColunas){
        int posicao = linha * nColunas + coluna;
        return posicao;
    }
    // Complexidade O(1)
    public boolean elementoExiste(int[][] matriz, int linha, int coluna){
        if(linha < 0 || coluna < 0){
            return false;
        }
        if(linha >= matriz.length || coluna >= matriz[0].length){
            return false;
        }
        return true;
    }

    // Complexidade O(Δ), onde Δ é o grau do vértice v no pior caso
    public int retornaValor(int v){
        validateVertex(v);
        int[] vizinhos =  {-1, 1};
        for(int i = 0; i < vizinhos.length; i++){
            if(verticeValido(v + vizinhos[i])){
                for(ElementoMatriz w : adj[v + vizinhos[i]]){
                    if(w.posicaoElemento == v){
                        return w.valorElemento;
                    }
                }
            }
        }
        return v;
    }
    // Complexidade O(1)
    public boolean verticeValido(int v){
        if(v < 0 || v > V){
            return false;
        }else{
            return true;
        }
    }
    //Complexidade O(1)
    public Bag<ElementoMatriz> adj(int v){
        return this.adj[v];
    }

    //função que recebe a posição do vertice e muda seu valor na bag dos vertices vizinhos
    // Complexidade O(1), pois cada vértice tem no máximo 8 vizinhos
    public void mudaValorElemento(int v, int novoValor, int nLinhas, int nColunas){
        for(ElementoMatriz w : adj[v]){ //no maximo 8 vezes
            int posicao = w.posicaoElemento;
            for(ElementoMatriz e : adj[posicao]){
                if(e.posicaoElemento == v){
                    e.valorElemento = novoValor;
                }
            }
        }
    }

    //metodo de saida
    //COMPLEXIDADE: theta(n)
    public void saida(String caminhoArquivo, int nColunas){
        try{
            FileWriter escritor = new FileWriter(caminhoArquivo, false);
            BufferedWriter br = new BufferedWriter(escritor);
            int c = 0;
            String linha = "";
            for(int i = 0; i < V; i++){
                c++;
                String elemento = String.valueOf(retornaValor(i));
                if(c < nColunas){
                    linha += elemento + " ";
                }else{
                    linha += elemento;
                    br.write(linha);
                    if(i < V - 1){
                        br.newLine();
                    }
                    linha = "";
                    c = 0;
                }
            }
            br.close();
        }catch (Exception erro){
            System.out.println("OCORREU UM ERRO AO ESCREVER A SAIDA");
            System.out.println(erro);
        }
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
     */

    public static void main(String[] args){

    }
}
