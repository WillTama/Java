import java.util.ArrayList;
import java.util.Iterator;

public class ListaPrimos2 implements Iterable<Long> {
  
  private long maximo;
  private ArrayList<Long> listaPrimos = new ArrayList<Long>();

  public ListaPrimos2(long maximo) {
    if (maximo < 2) {
      throw new IllegalArgumentException(maximo + " es un valor inferior al primer primo.");
    }
    this.maximo = maximo;
    generaListaPrimos();
  }

  private void generaListaPrimos() {
    inicializaListaPrimos();
    cribaListaPrimos();
  }

  private void inicializaListaPrimos() {
    for (long i = 2; i <= maximo; i++) {
      listaPrimos.add(i);
    }
  }
  
  private void cribaListaPrimos() {
    int index = 0;
    while (Math.pow(listaPrimos.get(index),2) <= maximo) {
      long primo = listaPrimos.get(index);
      listaPrimos.subList(index+1, listaPrimos.size()).removeIf(n -> (n % primo) == 0);
      index++;
    }
  }

  @Override
  public Iterator<Long> iterator() {
    return listaPrimos.iterator();
  }

}