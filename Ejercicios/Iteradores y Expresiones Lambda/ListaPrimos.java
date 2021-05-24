import java.util.Iterator;

public class ListaPrimos implements Iterable<Long> {
  
  private long maximo;
  
  
  public static boolean esPrimo(long primoCandidato) {
    if (primoCandidato < 0 || (primoCandidato%2 == 0 && primoCandidato > 2)) {
      return false;
    }
    boolean esPrimo = true;
    for (int divisor = 3; esPrimo && divisor <= Math.sqrt(primoCandidato); divisor += 2) {
      esPrimo = (primoCandidato%divisor != 0);
    }
    return esPrimo;
  }

  public ListaPrimos(long maximo) {
    if (maximo < 2) {
      throw new IllegalArgumentException(maximo + " es un valor inferior al primer primo.");
    }
    this.maximo = maximo;
  }

  @Override
  public Iterator<Long> iterator() {
    Iterator<Long> iterator = new Iterator<Long>() {
      long primoSiguiente = 2;

      @Override
      public boolean hasNext() {
        return primoSiguiente <= maximo;
      }

      @Override
      public Long next() {
        long primo = primoSiguiente;
        calculaPrimoSiguiente();
        return primo;
      }

      private void calculaPrimoSiguiente() {
        primoSiguiente++;
        while (! esPrimo(primoSiguiente)) {
          primoSiguiente++;
        }
      }
    };
    
    return iterator;
  }

}