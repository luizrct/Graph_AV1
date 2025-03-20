import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;

public class Teste {
    public static void main(String[] args){
        String[] caminhos = {"entradas/entrada.txt", "entradas/UNIFOR_grayscale.txt", "entradas/UNIFOR_sample.txt", "entradas/UNIFOR_sample_final.txt", "saida.txt"};
        int[][] matriz = Main.entradaParaMatriz(caminhos[1]);
        Graph grafo = new Graph(matriz);
        grafo.imprimeGrafo();
    }

    public static boolean entradaParaMatriz(String caminhoArquivo){
        try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))){
            int n = 0;
            String linha;
            int nColunas = 0;
            boolean testada = false;
            while(true){
                n++;
                linha = br.readLine();
                if(linha == null){
                    break;
                }
                String[] colunas = linha.split(" ");
                if (!testada){
                    nColunas = colunas.length;
                    testada = true;
                }
                if(colunas.length != nColunas){
                    System.out.println(n);
                    return false;
                }
            }
            return true;
        }catch (Exception erro){
            System.out.println(erro);
            return false;
        }
    }
}

