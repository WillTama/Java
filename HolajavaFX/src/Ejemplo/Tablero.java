package Ejemplo;

/**
 * 
 * @author Guillermo Tamajón Hernández
 * 
 */


import java.util.ArrayList;

public class Tablero {
  
  //El tablero consta de un array de 16 caracteres, pero se mostrará como un 4x4
  private char[] tableroDeJuego = new char[16];
  
  
  Tablero(){
    this.llenarTablero();
  }


  public char[] getTableroDeJuego() {
    return tableroDeJuego;
  }


  //Tiro los dados en el tablero
  public void llenarTablero() {
    for(int i = 0; i < tableroDeJuego.length; i++) {
      tableroDeJuego[i] = (new Dado()).getCaraAleatoria();
    }
  }
  
  //Sigo un algoritmo en el que devuelvo las posiciones contiguas, componiendo un
  //array lineal en un 4x4.
  //Con esto, que a priori parece carecer de sentido, me facilita el guardar los índices
  public static ArrayList<Integer> devuelvePosicionesContiguas(int pos) {

    ArrayList<Integer> posicionesContiguas = new ArrayList<Integer>();

    if((pos == 5 || pos == 6) || (pos==9 || pos==10)) {
      posicionesContiguas.add(pos-5);
      posicionesContiguas.add(pos-4);
      posicionesContiguas.add(pos-3);
      posicionesContiguas.add(pos-1);
      posicionesContiguas.add(pos+1);
      posicionesContiguas.add(pos+3);
      posicionesContiguas.add(pos+4);
      posicionesContiguas.add(pos+5);

    }

    else if(pos==1 || pos==2) {
      posicionesContiguas.add(pos-1);
      posicionesContiguas.add(pos+1);
      posicionesContiguas.add(pos+3);
      posicionesContiguas.add(pos+4);
      posicionesContiguas.add(pos+5);
    }

    else if(pos==4 || pos==8) {
      posicionesContiguas.add(pos-4);
      posicionesContiguas.add(pos-3);
      posicionesContiguas.add(pos+1);
      posicionesContiguas.add(pos+4);
      posicionesContiguas.add(pos+5);
    }

    else if(pos==7 || pos==11) {
      posicionesContiguas.add(pos-5);
      posicionesContiguas.add(pos-4);
      posicionesContiguas.add(pos-1);
      posicionesContiguas.add(pos+3);
      posicionesContiguas.add(pos+4);
    }

    else if(pos==13 || pos==14) {
      posicionesContiguas.add(pos-5);
      posicionesContiguas.add(pos-4);
      posicionesContiguas.add(pos-3);
      posicionesContiguas.add(pos-1);
      posicionesContiguas.add(pos+1);
    }

    else if(pos==0 ) {
      posicionesContiguas.add(pos+1);
      posicionesContiguas.add(pos+4);
      posicionesContiguas.add(pos+5);
    }

    else if(pos == 3) {
      posicionesContiguas.add(pos-1);
      posicionesContiguas.add(pos+3);
      posicionesContiguas.add(pos+4);
    }

    else if(pos == 12) {
      posicionesContiguas.add(pos-4);
      posicionesContiguas.add(pos-3);
      posicionesContiguas.add(pos+1);
    }

    else if(pos == 15) {
      posicionesContiguas.add(pos-5);
      posicionesContiguas.add(pos-4);
      posicionesContiguas.add(pos-1);
    }

    return posicionesContiguas;
  }

  
  //toString que devuelve el tablero transformado en un 4x4
  public String toString() {

    String tablero = "";
    
    for (int i = 0; i <=15; i++) {
        tablero = tablero + this.tableroDeJuego[i] + " ";
        if((i+1)%4==0) {
          tablero = tablero + "\n";
        }
        
      }
    return tablero;
  }

}
