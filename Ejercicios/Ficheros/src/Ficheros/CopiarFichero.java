package Ficheros;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class CopiarFichero {
  
  
  
  private static String encriptaCesar(String cadena, int desplazamiento) {
    String letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáéíóúüñÁÉÍÓÚÜÑ";
    String cadenaEncriptada = "";
    for (int i=0; i<cadena.length(); i++) {
      String caracter = cadena.substring(i, i+1);
      // si el carácter es alfabético, encriptamos
      if (letras.contains(caracter)) {
        int posicionDondeEsta = letras.indexOf(caracter);
        int posicionCaracterEncriptado = ((posicionDondeEsta + desplazamiento) % letras.length());
        caracter = letras.substring(posicionCaracterEncriptado, posicionCaracterEncriptado+1);
      } 
      cadenaEncriptada += caracter;
    }
    return cadenaEncriptada;
  }
  
  public static void main(String[] args) {
    
    try {

      var fichero = new BufferedReader(new FileReader("fichero.txt"));
      var ficheroNuevo = new BufferedWriter(new FileWriter("ficherooo.txt"));
      String linea;
      while((linea=fichero.readLine()) != null) {
        ficheroNuevo.write(linea + "\n");
      }
      
      fichero.close();
      ficheroNuevo.close();
      
      System.out.println("acabao");
      
    } catch (IOException e) {
      System.out.println("No se ha podido abrir el fichero.");
    }
  }

}
