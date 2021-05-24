package BoggleJavaFX;



import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class Hola extends Application {

  public void start(Stage primaryStage) {
    primaryStage.setTitle("Mi primera app");
    primaryStage.setScene(new Scene(new Label("hola java")));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
