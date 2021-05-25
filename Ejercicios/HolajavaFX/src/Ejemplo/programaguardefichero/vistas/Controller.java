package BoggleJavaFX.programaguardefichero.vistas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private TextField archivo1;

    @FXML
    private Button buscarArchivo1;

    @FXML
    private TextField archivo2;

    @FXML
    private Button buscarArchivo2;

    @FXML
    private TextArea visor;

    @FXML
    private Button mezclar;
    
    @FXML
    private TextField archivoDefinitivo;
    
    
    
    
    @FXML
    void buscarArchivo(ActionEvent event) throws IOException {    
      if ( ((Button) event.getSource()).getId() == buscarArchivo1.getId() ) {
        
        FileChooser fileChooser1 = new FileChooser();
        chooser(fileChooser1, archivo1);
      
      } else if ( ((Button) event.getSource()).getId() == buscarArchivo2.getId()) {
        
        FileChooser fileChooser2 = new FileChooser();
        chooser(fileChooser2, archivo2);
      
      }   
    }

    private void chooser(FileChooser fileChooser, TextField archivo) throws IOException {
      
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("Text Files", "*.txt")
          );
      
      Stage stage = (Stage) visor.getScene().getWindow();      
      File file = fileChooser.showOpenDialog(stage);
      
      archivo.setText(file.getAbsolutePath());
    }
    
    
    @FXML
    void mezclarArchivos(ActionEvent event) throws IOException {
      
      BufferedReader lectura1 = new BufferedReader(new FileReader(archivo1.getText()));
      BufferedReader lectura2 = new BufferedReader(new FileReader(archivo2.getText()));
      BufferedWriter mezcla = new BufferedWriter(new FileWriter(archivoDefinitivo.getText()));
      String linea1 = lectura1.readLine();
      String linea2 = lectura2.readLine();

      while (linea1 != null && linea2 != null) {
        escribeMezcla(mezcla, linea1, linea2);
        linea1 = lectura1.readLine();
        linea2 = lectura2.readLine();
      }

      if ( linea1 == null ) {
        while ( linea2 != null ) {
          mezcla.write(linea2);
          mezcla.newLine();
          linea2 = lectura2.readLine();
        }
      } else {
        while ( linea1 != null ) {
          mezcla.write(linea1);
          mezcla.newLine();
          linea2 = lectura1.readLine();
        }
      }

      // Escribimos en el visor
      System.out.println("Mezcla hecha" + archivoDefinitivo.getText());
      
      imprimir(archivoDefinitivo);

      // Cerramos ficheros
      lectura1.close();
      lectura2.close();
      mezcla.close();
      
    }


    private void escribeMezcla(BufferedWriter mezcla, String linea1, String linea2) throws IOException {
      
      mezcla.write(linea1);
      mezcla.newLine();
      
      mezcla.write(linea2);
      mezcla.newLine();
      
    }

    private void imprimir(TextField archivoDefinitivo2) throws IOException {
      BufferedReader lectura3 = new BufferedReader(new FileReader(archivoDefinitivo2.getText()));
      String linea3 = lectura3.readLine();
      System.out.println("asad");
      while (linea3 != null) {
        visor.appendText(linea3);
        
      }
      
    }
}
