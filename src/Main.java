import assets.In;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        StringBuilder matrizString = new StringBuilder();
        String linha;
        while (!(linha = input.nextLine()).equalsIgnoreCase(".")) {
            matrizString.append(linha).append("\n");
        }
        int[][] matriz = matrizBuilder(matrizString.toString());
        Graph grafo = new Graph(matriz);
        DepthFirstPaths dfs = new DepthFirstPaths(grafo, new int[]{0, 0}, matriz[0].length, 256);
        grafo.imprimeGrafo();
        int[][] imagemAlterada = grafo.converterParaMatriz(matriz.length, matriz[0].length);
        imprimirMatrizConvertida(imagemAlterada);
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
}
