import java.util.Iterator;
import java.util.function.Function;

public class SucesionGeneral implements Iterable<Long> {
  
  private int totalElementos;
  Function<Integer, Long> lambda;

  public SucesionGeneral(int totalElementos, Function<Integer, Long> lambda) {
    if (totalElementos < 1) {
      throw new IllegalArgumentException(totalElementos + " es menor que 1.");
    }
    this.totalElementos = totalElementos;
    this.lambda = lambda;
  }

  @Override
  public Iterator<Long> iterator() {
    Iterator<Long> iterator = new Iterator<Long>() {
      int index = 1;

      @Override
      public boolean hasNext() {
        return index <= totalElementos;
      }

      @Override
      public Long next() {
        return lambda.apply(index++);
      }
    };
    
    return iterator;
  }

}