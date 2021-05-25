package BoggleJavaFX;

/**
 * 
 * @author Guillermo Tamajón Hernández
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class DiccionarioRAE {
  
  private HashSet<String> palabrasRAE = new HashSet<String>();
  
  
  DiccionarioRAE() {
    try {
      crearColeccionRAE();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  private void crearColeccionRAE() throws IOException {
    BufferedReader ficheroRAE = new BufferedReader(new FileReader("RAE.txt"));
    String linea;//Declaro para usar luego

    //Añado las palabras de la rae sin tildes a una colección
    while((linea=ficheroRAE.readLine()) != null) {
      palabrasRAE.add(devuelveSinTildesConMayus(linea));
    }
    ficheroRAE.close();
  }
  
  static String devuelveSinTildesConMayus(String palabra) {
    String nuevaPalabra = "";
    for(int i = 0; i < palabra.length(); i++) {
      
      if (palabra.charAt(i) == 'á') {
        nuevaPalabra = nuevaPalabra + 'a';
      }
      else if (palabra.charAt(i) == 'é') {
        nuevaPalabra = nuevaPalabra + 'e';
      }
      else if (palabra.charAt(i) == 'í') {
        nuevaPalabra = nuevaPalabra + 'i';
      }
      else if (palabra.charAt(i) == 'ó') {
        nuevaPalabra = nuevaPalabra + 'o';
      }
      else if (palabra.charAt(i) == 'ú') {
        nuevaPalabra = nuevaPalabra + 'u';
      }
      else {
        nuevaPalabra = nuevaPalabra + palabra.charAt(i);
      }  
    }
    return nuevaPalabra.toUpperCase();   
  }
  
public boolean esPalabraCorrecta(String palabra) {
    if(palabrasRAE.contains(palabra)) {
      return true;
    }
    return false;
  }

}
