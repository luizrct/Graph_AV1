import java.io.BufferedWriter;
import java.io.FileWriter;
public class Teste {
    public static void main(String[] args){
        String caminho_saida = "saida.txt";
        int[][] matriz = Main.entradaParaMatriz("entradas/entrada.txt");
        Main.saida(matriz, caminho_saida);
    }
}

