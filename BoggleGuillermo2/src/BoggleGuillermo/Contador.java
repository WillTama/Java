package BoggleGuillermo;


/**
 * 
 * @author Guillermo Tamajón Hernández
 * 
 */



import java.util.Timer;
import java.util.TimerTask;

public class Contador {
  
   int intervalo;
   Timer timer;
 
  public void cuentaAtras(int segundos) {
    
    int delay = 1000;
    int periodo = 1000;
    timer = new Timer();
    intervalo = segundos;
    //System.out.println("Quedan "+ intervalo +" segundos" ); 
    //Inicio un contador que cada segundo disminuye los segundos
    timer.scheduleAtFixedRate(new TimerTask() {

      public void run() {
          setInterval();

      }
      
      
  }, delay, periodo);
  }
  

  //Set del tiempo restante
  private  int setInterval() {
    if (intervalo == 1)
        timer.cancel();
    return --intervalo;
}
  
}
