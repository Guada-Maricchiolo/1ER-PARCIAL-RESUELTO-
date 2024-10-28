import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            int dimension = obtenerDimension(scanner);
            int[][] matriz = new int[dimension][dimension];

            llenarUltimaFila(matriz, scanner, dimension);
            llenarRestantesFilas(matriz, dimension);
            mostrarMatriz(matriz);

            int[] valoresCentrales = obtenerValoresCentrales(matriz);
            mostrarMatriz(valoresCentrales);

            ordenarMatriz(valoresCentrales);
            mostrarMatriz(valoresCentrales);

            double promedioFinal = calcularPromedioDeMatrices(matriz, valoresCentrales);
            System.out.println("Promedio calculado: " + promedioFinal);

            continuar = preguntarReiniciar(scanner);
        }

        scanner.close();
    }

    public static int obtenerDimension(Scanner scanner) {
        int dimension;
        do {
            System.out.print("Ingrese la dimensión de la matriz (impar entre 3 y 15): ");
            dimension = scanner.nextInt();
        } while (dimension < 3 || dimension > 15 || dimension % 2 == 0);
        return dimension;
    }

    public static void llenarUltimaFila(int[][] matriz, Scanner scanner, int dimension) {
        System.out.println("Ingrese los valores enteros no nulos para la última fila (entre 10 y 99):");
        for (int j = 0; j < dimension; j++) {
            int valor;
            do {
                System.out.print("Elemento [" + (dimension - 1) + "][" + j + "]: ");
                valor = scanner.nextInt();
            } while (valor < 10 || valor > 99 || valor == 0);
            matriz[dimension - 1][j] = valor;
        }
    }

    public static void llenarRestantesFilas(int[][] matriz, int dimension) {
        Random random = new Random();
        for (int i = 0; i < dimension - 1; i++) {
            for (int j = 0; j < dimension; j++) {
                matriz[i][j] = random.nextInt(90) + 10; // Números aleatorios entre 10 y 99
            }
        }
    }

    public static void mostrarMatriz(int[][] matriz) {
        System.out.println("Matriz:");
        for (int[] fila : matriz) {
            for (int elemento : fila) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }
    }

    public static void mostrarMatriz(int[] matriz) {
        System.out.println("Valores centrales:");
        for (int elemento : matriz) {
            System.out.print(elemento + " ");
        }
        System.out.println();
    }

    public static int[] obtenerValoresCentrales(int[][] matriz) {
        int dimension = matriz.length;
        int[] valoresCentrales = new int[5];

        int centro = dimension / 2;
        valoresCentrales[0] = matriz[centro][centro]; // Centro
        valoresCentrales[1] = matriz[centro - 1][centro - 1]; // Arriba izquierda
        valoresCentrales[2] = matriz[centro - 1][centro]; // Arriba
        valoresCentrales[3] = matriz[centro - 1][centro + 1]; // Arriba derecha
        valoresCentrales[4] = matriz[centro][centro - 1]; // Izquierda

        return valoresCentrales;
    }

    public static void ordenarMatriz(int[] matriz) {
        for (int i = 0; i < matriz.length - 1; i++) {
            for (int j = 0; j < matriz.length - 1 - i; j++) {
                if (matriz[j] > matriz[j + 1]) {
                    // Intercambio
                    int temp = matriz[j];
                    matriz[j] = matriz[j + 1];
                    matriz[j + 1] = temp;
                }
            }
        }
    }

    public static double calcularPromedioDeMatrices(int[][] matriz, int[] matrizValoresCentral) {
        double sumaMatriz = 0;
        int contadorMatriz = 0;

        for (int[] fila : matriz) {
            for (int elemento : fila) {
                sumaMatriz += elemento;
                contadorMatriz++;
            }
        }

        double promedioMatriz = sumaMatriz / contadorMatriz;

        double sumaValoresCentrales = 0;
        for (int valor : matrizValoresCentral) {
            sumaValoresCentrales += valor;
        }
        double promedioValoresCentrales = sumaValoresCentrales / matrizValoresCentral.length;

        System.out.println("Suma total de la matriz: " + sumaMatriz);
        System.out.println("Cantidad de elementos en la matriz: " + contadorMatriz);
        System.out.println("Promedio de la matriz: " + promedioMatriz);

        return (promedioMatriz + promedioValoresCentrales) / 2;
    }

    public static boolean preguntarReiniciar(Scanner scanner) {
        System.out.print("Desea iniciar nuevamente la ejecución del programa? (SI/NO): ");
        String respuesta = scanner.next().toUpperCase();
        return respuesta.equals("SI");
    }
}

