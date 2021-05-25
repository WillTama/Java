package BoggleJavaFX.programaguardefichero;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Mezcla extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxml = new FXMLLoader(getClass().getResource("vistas/Mezcla.fxml"));
    try {
      var root = fxml.<VBox>load();
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setTitle(getClass().getSimpleName());
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Escena mal generada");
    }
    
  }

  public static void main(String[] args) {
    launch(args);
  }
  
}
