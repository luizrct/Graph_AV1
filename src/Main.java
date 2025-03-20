
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String[] caminhos = {"entradas/entrada.txt", "entradas/UNIFOR_grayscale.txt", "entradas/UNIFOR_sample.txt", "entradas/UNIFOR_sample_final.txt", "saida.txt"};
        int[][] matriz = entradaParaMatriz(caminhos[2]);
        int nColunas =  matriz[0].length;
        int nLinhas = matriz.length;
        Graph grafo = new Graph(matriz);
        DepthFirstPaths dfs = new DepthFirstPaths(grafo, new int[]{0, 0}, nColunas, 9, nLinhas);
        grafo.saida(caminhos[caminhos.length - 1], nColunas);
    }
    //Complexidade O(N) onde N = n×m
    public static int[][] entradaParaMatriz(String caminhoArquivo){
        try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))){
            LinkedList<String> linhas = new LinkedList<>();
            String linha;
            while(true){
                linha = br.readLine();
                if(linha == null){
                    break;
                }
                linhas.add(linha);
            }
            int[][] matriz = new int[linhas.size()][];
            int c = 0;
            for(String elemento : linhas){
                String[] colunas = elemento.split(" ");
                matriz[c] = new int[colunas.length];
                for(int i = 0; i < colunas.length; i++){
                    matriz[c][i] = Integer.valueOf(colunas[i]);
                }
                c++;
            }
            return matriz;
        }catch (Exception erro){
            System.out.println(erro);
            return new int[0][];
        }
    }
}
