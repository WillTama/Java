package BoggleGuillermo;


/**
 * 
 * @author Guillermo Tamajón Hernández
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JuegoTest {

  //Tiempo que durará la partida
  static int segundos = 180;

  //Método para salir del juego o empezar otro
  static boolean deseoSalir() {
    Scanner s = new Scanner(System.in);
    System.out.println("¿Quiéres jugar otra? (s/n)");
    String salir = s.nextLine();

    if (salir.toLowerCase().charAt(0) == 's') {
      return true;
    }
    return false;
  }

  //Muestra los resultados de todos los jugadores
  static void mostrarPuntos(ArrayList<Jugador> jugadores) {

    for(int i = 0; i<jugadores.size(); i++) {
      System.out.println(jugadores.get(i).getNombre() + " acertó: {"
          + jugadores.get(i).devolvePalabrasAcertadas() + "}, total de: " + jugadores.get(i).getPuntos() + " PUNTOS.");
    }    
  }

  //Muestra al ganador o ganadores de la partida
  static void mostrarGanador(ArrayList<Jugador> jugadores) {

    System.out.println("Y el trofeo es para...");

    int puntuacionMax = 0;

    for(int i = 0; i<jugadores.size(); i++) {
      if(jugadores.get(i).getPuntos() >= puntuacionMax) {
        puntuacionMax = jugadores.get(i).getPuntos();
      }
    }

    String ganadores = "";
    for(int i = 0; i<jugadores.size(); i++) {
      if(jugadores.get(i).getPuntos() == puntuacionMax) {
        ganadores = ganadores + jugadores.get(i).getNombre() + " ";
      }
    }

    System.out.println("¡¡FELICIDADES " + ganadores + "...!!");
  }


  //Metodo para crear coleccion a partir de fichero RAE
  static HashSet<String> crearColeccionRAE() throws IOException {
    BufferedReader ficheroRAE = new BufferedReader(new FileReader("RAE.txt"));
    String linea;//Declaro para usar luego


    //Añado las palabras de la rae sin tildes a una colección
    HashSet<String> palabrasRAE = new HashSet<String>();
    while((linea=ficheroRAE.readLine()) != null) {
      palabrasRAE.add(devuelveSinTildes(linea));
    }
    
    
//    for(String palabraConTildes: palabrasRAE){
//      palabraConTildes = devuelveSinTildes(palabraConTildes);
//      }

    ficheroRAE.close();
    return palabrasRAE;
  }
  
  //Transformo una cadena con tildes en otra sin tildes
  static String devuelveSinTildes(String palabra) {
    
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
    return nuevaPalabra;
    
  }

  //Creo los jugadores y los añado a un arraylist
  static ArrayList<Jugador> crearJugadores(int numeroDeJugadores) {
    Scanner s = new Scanner(System.in);
    ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

    for(int i = 0; i < numeroDeJugadores; i++) {
      System.out.println("Nombre del jugador " + (i+1) +": ");
      jugadores.add(new Jugador(s.nextLine()));
    }

    return jugadores;
  }

  //Pausa con intro
  private static void pausar() {
    Scanner s = new Scanner(System.in);
    System.out.println("Pulsa intro para comenzar...");
    s.nextLine();
  }

  //Pausa con intro
  private static void pausar2() {
    Scanner s = new Scanner(System.in);
    System.out.println("Pulsa intro para continuar...");
    s.nextLine();
  }

  //MAIN
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    try {
      //INICIALIZACIÓN
      String palabra = "";
      int jugador;
      HashSet<String> palabrasRAE = crearColeccionRAE();
      Tablero tablero;
      ArrayList<Jugador> jugadores;
      Contador contador;
      boolean salir = false;


      do {
        //EMPIEZA EL JUEGO
        System.out.println("BIENVENIDO AL BOGGLE");
        System.out.println();
        System.out.println();

        //Genero nuevo tablero
        tablero = new Tablero();

        //Creo jugadores y los guardo en un arrayList
        System.out.println("¿Cuántos jugadores serán?: ");
        jugadores = crearJugadores(s.nextInt());
        s.nextLine();

        //PARTIDA DE CADA JUGADOR
        for (int i = 0; i < jugadores.size(); i++) {

          //Mensaje de quién comienza
          System.out.println("Es el turno de " + jugadores.get(i).nombre + ": ");
          pausar();

          //Inicio contador
          contador = new Contador();
          contador.cuentaAtras(segundos);

          //Bucle para partida con condición del contador
          while (contador.intervalo > 0) {

            System.out.println(tablero);
            System.out.println("Quedan " + contador.intervalo + " segundos");

            System.out.print("Palabra: ");
            palabra = s.nextLine();

            //Compruebo si es correcta la palabra para añadirla a aciertos del jugador actual
            //También compruebo que el tiempo no se ha acabado
            if (tablero.esPalabraCorrecta(devuelveSinTildes(palabra), palabrasRAE) && contador.intervalo > 0) {
              jugadores.get(i).añadirPalabra(devuelveSinTildes(palabra));
            }

          }

          System.out.println("SE ACABÓ EL TIEMPO");
        }
        
        //Fin de partida. Muestro resultados
        pausar2();
        System.out.println("HORA DE SABER LOS GANADORES\n");
        mostrarPuntos(jugadores);
        System.out.println();
        mostrarGanador(jugadores);
        pausar2();



      } while (deseoSalir());

      System.out.println("HASTA LA PRÓXIMA");

    } 
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    catch (InputMismatchException e) {
      System.out.println("Debes insertar un número");
      pausar2();
    }
    
    



  }



}
