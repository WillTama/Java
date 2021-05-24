package BoggleJavaFX;

/**
 * 
 * @author Guillermo Tamajón Hernández
 * 
 */


import java.util.ArrayList;

public class Jugador {
  
  ArrayList<String> palabrasAcertadas = new ArrayList<String>();
  int puntos;

  
  Jugador(){
    puntos = 0;
  }
  
  public int getPuntos() {
    return puntos;
  }

  //Añado palabra al arrayList de palabras acertadas
  public void addPalabra(String palabra) {
    
    //Si la palabra no está repetida, la añado
    //Será la clase Tablero la que verifique si una palabra es válida
    if (!palabrasAcertadas.contains(palabra.toUpperCase())) {
      palabrasAcertadas.add(palabra.toUpperCase());
    
    sumarPuntos(palabra);
    }
  }
  
  //Suma de puntos según tamaño de palabra
  public void sumarPuntos(String palabra) {
    
    if (palabra.length() >= 8) {
      puntos = puntos + 4;
    }
    
    else if (palabra.length() == 7) {
      puntos = puntos + 3;
    }
    
    else if (palabra.length() == 6) {
      puntos = puntos + 2;
    }
    
    else {
      puntos = puntos + 1;
    }
    
  }
  
  public int devolverPuntos() {
    return puntos;
  }
  
  //Devuelvo las palabras acertadas de un jugador
  public String devolvePalabrasAcertadas() {
    
    String palabrasADevolver = "";
    for(int i = 0; i<palabrasAcertadas.size(); i++) {
      palabrasADevolver = palabrasADevolver + palabrasAcertadas.get(i) + ", ";
    }
    return palabrasADevolver;
  }

}
