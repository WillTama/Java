package Ficheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FicheroEncriptarCesar {
  
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
    Scanner s = new Scanner(System.in);

    try (var fichero = new BufferedWriter(new FileWriter("textoEncriptado.txt", false))) {
      
      
      System.out.println("¿Cuál va a ser tu código de encriptación?: ");
      
      int codigo = s.nextInt();
      s.nextLine();
      
      System.out.println("Escribe el texto a encriptar: ");
      String lineaSinEncriptar = "";
      
      for (int i = 1; i <= 5; i++) {
        lineaSinEncriptar = s.nextLine();
        fichero.write(encriptaCesar(lineaSinEncriptar, codigo));
        fichero.newLine();
      }
      System.out.println("Encriptado con éxito.");
      
      fichero.close();

    } catch (IOException e) {
      System.out.println("No se ha podido escribir en el fichero.");
    }

  }

}