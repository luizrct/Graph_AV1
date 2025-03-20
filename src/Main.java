
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String[] caminhos = {"entradas/entrada.txt", "entradas/UNIFOR_grayscale.txt", "entradas/UNIFOR_sample.txt", "entradas/UNIFOR_sample_final.txt", "saida.txt"};
        int[][] matriz = entradaParaMatriz(caminhos[1]);
        int nColunas =  matriz[0].length;
        int nLinhas = matriz.length;
        Graph grafo = new Graph(matriz);
        DepthFirstPaths dfs = new DepthFirstPaths(grafo, new int[]{0, 0}, nColunas, 9, nLinhas);
        grafo.saida(caminhos[caminhos.length - 1], nColunas);
    }

    //Função que pega a matriz no formato de String e transforma em um vetor duplo Int
    public static int[][] matrizBuilder(String matrizString){
        String[] linhas = matrizString.split("\n");
        int[][] matriz = new int[linhas.length][];
        for(int i = 0; i < linhas.length; i++){
            String[] colunas = linhas[i].split(" ");
            matriz[i] = new int[colunas.length];
            for(int j = 0; j < colunas.length; j++){
                matriz[i][j] = Integer.parseInt(colunas[j]);
            }
        }
        return matriz;
    }

    public static void elementoMatriz(int[][] matriz, Scanner input){
        while(true){
            System.out.print("Digite a linha: ");
            int linha = input.nextInt();
            if(linha < 0){
                break;
            }
            System.out.print("Digite a coluna: ");
            int coluna = input.nextInt();
            int elemento = matriz[linha][coluna];
            System.out.println("Elemento: "+elemento);
        }
    }

    public static void imprimirMatrizConvertida(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    //Função percorre uma matriz e verifica se é correspondente com uma String
    public static boolean testeMatriz(String matrizString, int[][] matriz){
        String[] linhas = matrizString.split("\n");
        for(int i = 0; i < matriz.length; i++){
            String[] colunas = linhas[i].split(" ");
            for(int j = 0; j < matriz[0].length; j++){
                if(!colunas[j].equals(String.valueOf(matriz[i][j]))){
                    return false;
                }
            }
        }
        return true;
    }


    public static void percorrendoMatriz(int[][] matriz){
        int quantidadeColunas = matriz[0].length;
        int V = matriz.length * quantidadeColunas;
        int c = 0;
        for(int i = 0; i < V; i++){
            c++;
            int linha = i / quantidadeColunas;
            int coluna = i - (quantidadeColunas * (i / quantidadeColunas));
            if(c < quantidadeColunas){
                System.out.print(matriz[linha][coluna]+" ");
            }else if(c == quantidadeColunas){
                System.out.print(matriz[linha][coluna]);
                System.out.println();
                c = 0;
            }
        }
    }


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

    public static void saida(int[][] matriz, String caminhoArquivo){
        try{
            FileWriter escritor = new FileWriter(caminhoArquivo, false);
            BufferedWriter bf = new BufferedWriter(escritor);
            for(int i = 0; i < matriz.length; i++){
                String linha = "";
                for(int j = 0; j < matriz[0].length; j++){
                    if(j < (matriz[0].length - 1)){
                        linha += String.valueOf(matriz[i][j])+" ";
                    }else{
                        linha += String.valueOf(matriz[i][j]);
                    }
                }
                if(i == matriz.length - 1){
                    bf.write(linha);
                }else{
                    bf.write(linha);
                    bf.newLine();
                }
            }
            bf.close();
        }catch (Exception erro){
            System.out.println("OCORREU UM ERRO AO ESCREVER A SAIDA");
            System.out.println(erro);
        }
    }
}
