package Ficheros;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class DesencripacionFichero {

  private static String encriptaCesarInversa(String cadena, int desplazamiento) {
    String letras = "—‹⁄”Õ…¡Ò¸˙ÛÌÈ·ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba";
    String cadenaEncriptada = "";
    for (int i=0; i<cadena.length(); i++) {
      String caracter = cadena.substring(i, i+1);
      // si el car·cter es alfabÈtico, encriptamos
      if (letras.contains(caracter)) {
        int posicionDondeEsta = letras.indexOf(caracter);
        int posicionCaracterEncriptado = ((posicionDondeEsta + desplazamiento) % letras.length());
        caracter = letras.substring(posicionCaracterEncriptado, posicionCaracterEncriptado+1);
      } 
      cadenaEncriptada += caracter;
    }
    return cadenaEncriptada;
  }


  private static int averiguarCodigo(HashSet<String> palabrasRAE, BufferedReader fichero) throws IOException {
    String letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ·ÈÌÛ˙¸Ò¡…Õ”⁄‹—";
    String linea = "";
    String palabra = "";
    ArrayList<String> palabrasEncontradas = new ArrayList<String>();
    

    //Bucle para guardar en un arrayList todas las palabras del fichero a desencriptar
    while((linea=fichero.readLine()) != null) {
      //Paso por todos los caracteres de la linea a leer
      for(int i = 0; i < linea.length(); i++) {
        //Si el array de letras contiene el caracter, lo concateno
        if (letras.contains(Character.toString(linea.charAt(i))) || i == (linea.length()-1) ) {
          palabra += linea.charAt(i);
          //Cuando llega a la ultima linea
          if(i == (linea.length()-1) ) {
            palabrasEncontradas.add(palabra);
            palabra = "";
          }
        }
        //Por el contrario, aÒado la palabra al array y reseteo palabra
        else {
          palabrasEncontradas.add(palabra);
          palabra = "";
        }
      }
    }

    //Voy a usar un hashmap para el conteo de los codigos
    HashMap<Integer, Integer> codigos = new HashMap<Integer, Integer>();


    //Me inicializo el hashmap
    for(int i = 0; i < letras.length(); i++) {
      codigos.put(i, 0);
    }

    //Un nuevo bucle para comprobar cÛdigos, empiezo recorriendo el arraylist de palabras de antes
    for(int i = 0; i < palabrasEncontradas.size(); i++) {
      //Ahora me paseo por un bucle tantas veces como caracteres de letra haya
      for(int j = 0; j < letras.length(); j++) {
          //Si la palabra encriptada est· en la rae, sumo acierto
          if(palabrasRAE.contains(encriptaCesarInversa(palabrasEncontradas.get(i), j).toLowerCase())) {
            codigos.put(j, codigos.get(i) + 1);
          }  
      }
    }

    //Ahora compruebo el entero que m·s se repite
    int codigoDefinitivo = 0;
    for(int i = 1; i< codigos.size(); i++) {

      if (codigos.get(i) > codigos.get(codigoDefinitivo)) {
        codigoDefinitivo = i;
      }
    }

    
    return codigoDefinitivo;

  }

  public static void main(String[] args) {

    try {
      
      //Abro ficheros
      BufferedReader ficheroLectura = new BufferedReader(new FileReader("textoEncriptado.txt"));
      var ficheroEscritura = new BufferedWriter(new FileWriter("ficheroDesencriptado.txt", false));
      BufferedReader ficheroRAE = new BufferedReader(new FileReader("RAE.txt"));
      String linea;//Declaro para usar luego
      
      
      //AÒado las palabras de la rae a una colecciÛn
      HashSet<String> palabrasRAE = new HashSet<String>();
      while((linea=ficheroRAE.readLine()) != null) {
        palabrasRAE.add(linea);
      }
      
      // Averiguo el cÛdigo con el mÈtodo averiguarCodigo
      int codigo = averiguarCodigo(palabrasRAE, ficheroLectura);
      System.out.println("El cÛdigo era: ");
      System.out.println(codigo);

      //Cierro y abro de nuevo ficheroLectura para leer desde la primera linea otra vez
      ficheroLectura.close();
      ficheroLectura = new BufferedReader(new FileReader("textoEncriptado.txt"));

      //Guardo en ficheroEscritura la desencriptaciÛn con encriptaCesarInversa
      while((linea=ficheroLectura.readLine()) != null) {
        ficheroEscritura.write(encriptaCesarInversa(linea, codigo) + "\n");
      }

      //Cierro todos los ficheros
      ficheroLectura.close();
      ficheroEscritura.close();
      ficheroRAE.close();

      System.out.println("Desencriptado con Èxito");

    } catch (IOException e) {
      System.out.println("No se ha podido acceder al fichero.");
    }
  }
}
