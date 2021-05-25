package Ficheros;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class EscribirFichero {
  
  public static void main(String [] args) {
    

    try (var fichero = new BufferedWriter(new FileWriter("fichero.txt", false))) {
     
       fichero.write("holaaa");

        }
    catch(IOException e){
       e.printStackTrace();
    }
 }

}
