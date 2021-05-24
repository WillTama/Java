import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ListaNumerosAleatorios {

  private static final int TOTAL_ENTEROS = 1000;
  private static final int MINIMO = -5000;
  private static final int MAXIMO = 5000;

  public static void main(String[] args) {
    List<Integer> lista = new ArrayList<>(); 
    
 
    IntStream.range(1, TOTAL_ENTEROS)
        .forEach(n -> lista.add(MINIMO + (int) (Math.random()*(MAXIMO-MINIMO)) + 1));
    
    int maximoPar = lista.stream()
        .filter(n -> n%2 == 0)
        .max((a,b) -> a.compareTo(b))
        .get();
    
    int minimoMultiplo5 = lista.stream()
        .filter(n -> n%5 == 0)
        .min((a,b) -> a-b)
        .get();
    
    int totalNegativos = (int) lista.stream()
        .filter(n -> n < 0)
        .count();
    

    System.out.println("Máximo de los números pares.: " + maximoPar);
    System.out.println("Mínimo de los múltiplos de 3: " + minimoMultiplo5);
    System.out.println("Total de números negativos..: " + totalNegativos);
  }

}