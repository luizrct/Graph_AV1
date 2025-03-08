import java.util.Scanner;
public class Teste {
    public static void main(String[] args){
        Bag<ElementoMatriz>[] adj;
        adj = new Bag[5];
        for(int i = 0; i < 5; i++){
            adj[i] = new Bag<ElementoMatriz>();
        }
    }

    public static int[][] matrizBuilder(String matrizString){
        String[] linhas = matrizString.split("\n");
        int[][] matriz = new int[linhas.length][];
        for(int i = 0; i < linhas.length; i++){
            String[] elementos = linhas[i].trim().split(" ");
            matriz[i] = new int[elementos.length];
            for(int j = 0; j < elementos.length; j++){
                matriz[i][j] =  Integer.parseInt(elementos[j]);
            }
        }
        return matriz;
    }
}

