package BoggleGuillermo;

/**
 * 
 * @author Guillermo Tamajón Hernández
 * 
 */


import java.util.ArrayList;
import java.util.HashSet;

public class Tablero {
  
  //El tablero consta de un array de 16 caracteres, pero se mostrará como un 4x4
  char[] tableroDeJuego = new char[16];
  
  
  Tablero(){
    this.llenarTablero();
  }


  //Tiro los dados en el tablero
  public void llenarTablero() {
    for(int i = 0; i < tableroDeJuego.length; i++) {
      tableroDeJuego[i] = (new Dado()).getCaraAleatoria();
    }
  }
  
  
  //Comprueba si está la palabra en el tablero, siguiento las normas
  public boolean estaPalabraEnTablero(String palabra) {
    
    //Si la palabra es una cadena vacía devuelvo false
    if (palabra.length() == 0) {
      return false;
    }
    
    palabra = palabra.toUpperCase();
    
    //ArrayList que guardan posiciones
    ArrayList<Integer> posiciones1 = new ArrayList<Integer>();
    ArrayList<Integer> posiciones2 = new ArrayList<Integer>();
    
    //Bucle para guardar en un arraylist las posiciones de las coincidencias del primer caracter
    for(int i = 0; i < tableroDeJuego.length; i++) {
      if(palabra.charAt(0) == tableroDeJuego[i]) {
        posiciones1.add(i);
      }
    }
    
    //Recorro mi palabra
    for(int i = 1; i<palabra.length(); i++) {
      //Recorro desde los indices de los anteriores aciertos
      for(int j = 0; j<posiciones1.size(); j++) {
        //recorro las posiciones contiguas a estos indices
        for(int k = 0; k < (devuelvePosicionesContiguas(posiciones1.get(j)).size()); k++) {
          //Si hay coincidencia en el tablero, guardo los nuevos indices en otro arraylist
          if(tableroDeJuego[(devuelvePosicionesContiguas(posiciones1.get(j)).get(k))] == palabra.charAt(i)) {
            posiciones2.add(devuelvePosicionesContiguas(posiciones1.get(j)).get(k));
          }
        }
      }
      //guardo en el primer arraylist las nuevas posiciones de partida, y vacío el segundo
      posiciones1 = posiciones2;
      posiciones2 = new ArrayList<Integer>();
    }
    //Si resulta que no está vacío el arraylist de índices coincidentes al final de todo el bucle,
    //quiere decir que la palabra está y cumple las reglas
    return !posiciones1.isEmpty();
    
  }
  
  //Comprueba que la palabra está en la RAE. Le paso las palabras en una colección para agilizar la busqueda de coincidencias
  public boolean estaPalabraEnLaRAE(String palabra, HashSet<String> palabrasRAE) {
    
    return palabrasRAE.contains(palabra.toLowerCase());
  }
  
  
  //Finalmente compruebo si la palabra es correcta, haciendose cumplir las dos condiciones
  public boolean esPalabraCorrecta(String palabra, HashSet<String> palabrasRAE) {
    
    if(estaPalabraEnTablero(palabra) && estaPalabraEnLaRAE(palabra, palabrasRAE)) {
      return true;
    }
    
    return false;
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
