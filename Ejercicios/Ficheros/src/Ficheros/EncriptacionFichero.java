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




public class EncriptacionFichero {




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


  private static int averiguarCodigo(BufferedReader palabras, BufferedReader fichero) throws IOException {
    String letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ·ÈÌÛ˙¸Ò¡…Õ”⁄‹—";
    String linea = "";
    String palabra = "";
    ArrayList<String> palabrasEncontradas = new ArrayList<String>();

    //Bucle para guardar en un array todas las palabras
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

    //Cierro el fichero y me olvido
    //fichero.close();

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
        //Leo el fichero donde estan las palabras de la rae
        while((linea=palabras.readLine()) != null) {
          //Si la palabra encriptada est· en la rae, guardo acierto
          if(linea.toLowerCase().equals(encriptaCesarInversa(palabrasEncontradas.get(i), j))) {
            codigos.put(j, codigos.get(i) + 1);
          }  
        } 

        //Cierro y abro de nuevo para leer otra vez desde el principio
        ////////////////////
        palabras.close();
        palabras = new BufferedReader(new FileReader("RAE.txt"));

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
      BufferedReader ficheroLectura = new BufferedReader(new FileReader("textoEncriptado.txt"));
      var ficheroEscritura = new BufferedWriter(new FileWriter("ficheroDesencriptado.txt", false));
      BufferedReader ficheroRAE = new BufferedReader(new FileReader("RAE.txt"));
      String linea;
      
      

      

      int codigo = averiguarCodigo(ficheroRAE, ficheroLectura);
      System.out.println("El cÛdigo era: ");
      System.out.println(codigo);

      //Cierro y abro de nuevo para leer desde el principio
      ficheroLectura.close();
      ficheroLectura = new BufferedReader(new FileReader("textoEncriptado.txt"));

      while((linea=ficheroLectura.readLine()) != null) {
        ficheroEscritura.write(encriptaCesarInversa(linea, codigo) + "\n");
      }

      ficheroLectura.close();
      ficheroEscritura.close();
      ficheroRAE.close();

      System.out.println("Desencriptado con Èxito");

    } catch (IOException e) {
      System.out.println("No se ha podido acceder al fichero.");
    }
  }
}
