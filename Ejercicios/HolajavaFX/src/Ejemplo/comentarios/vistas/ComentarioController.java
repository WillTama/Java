package BoggleJavaFX.comentarios.vistas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ComentarioController {

    @FXML
    private TextField archivo;

    @FXML
    private Button buscar;

    @FXML
    private TextArea visor;

    @FXML
    private Button ejecutar;
    
    @FXML
    private Button limpiado;

    @FXML
    void buscarArchivo(ActionEvent event) {
      
      FileChooser fileChooser = new FileChooser();
      
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("JAVA Files", "*.java")
          );
      
      Stage stage = (Stage) visor.getScene().getWindow();      
      File file = fileChooser.showOpenDialog(stage);
      
      archivo.setText(file.getAbsolutePath());

    }
    
    @FXML
    void quitarComentarios(ActionEvent event) {
      try {

        
        BufferedReader bOrigen = new BufferedReader(new FileReader(archivo.getText()));

        // necesaria para detectar bloques de comentarios /* ...*/
        boolean estoyEnBloqueComentario = false;
        
        // Leo lineas del fichero origen y si no es un comentario la escribo en el destino.
        String linea = bOrigen.readLine();
        
        while (linea != null) {
      
          boolean escribeLinea = true;
          if (estoyEnBloqueComentario) {
            if (linea.trim().endsWith("*/")) {
              estoyEnBloqueComentario = false;
            }
          } else if (linea.trim().startsWith("/*")) {
            estoyEnBloqueComentario = true;
          } else {

            if (linea.contains("//")) { // Condición comentario en linea
           
              int dondeEmpiezaBarra2 = linea.indexOf("//");
              linea = linea.substring(0, dondeEmpiezaBarra2);
              // si contiene solo espacios o está vacía no la escribo.
              if (linea.trim().equals("")) {
                escribeLinea = false;
              }
            }
            // escribimos  línea sin comentarios
            if (escribeLinea && !estoyEnBloqueComentario) {
              visor.appendText(linea + "\n"); 
            }
          }

          // Leemos nueva  línea
          linea = bOrigen.readLine();
        }
        System.out.println("Archivo sin comentarios creado.");
        // Cerramos los archivos.
        bOrigen.close();

      } catch (FileNotFoundException e) {
        System.err.println("No se encuentra el archivo.");
        System.exit(1);
      } catch (IOException e) {
        System.err.println("Error de entrada/salida al manejar el fichero");
        System.exit(2);
      }
      
      
    }

    @FXML
    void limpiar(ActionEvent event) {
      visor.clear();
    }
}
