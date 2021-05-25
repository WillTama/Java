package BoggleJavaFX.comentarios;

import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Comentario extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    
    FXMLLoader fxml = new FXMLLoader(getClass().getResource("vistas/ComentariosFXML.fxml"));
    try {
      var root = fxml.<VBox>load();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("vistas/estilos.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setTitle(getClass().getSimpleName());
      primaryStage.show();
            
      primaryStage.addEventHandler(KeyEvent.KEY_TYPED, e -> { 
        
        if (confirmaSalir(e)) {
          primaryStage.close();
        }
      });
      
      
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Error en la escena");
    }
    
  }

  private boolean confirmaSalir(KeyEvent e) {
    if (e.getCharacter().equals("ESC")) {
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Confirmación");
      alert.setHeaderText("¿Deseas terminar?");

      Optional<ButtonType> result = alert.showAndWait();
      return (result.get() == ButtonType.OK) ;
    }
    return false;
  }

  public static void main(String[] args) {
    launch(args);
  }

}
