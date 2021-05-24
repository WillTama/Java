package Ejemplo;

/**
 * 
 * @author Guillermo Tamajón Hernández
 * 
 * 
 * 
 * Juega a adivinar un número del 1 al 100 generado por el juego.
 * 10 intentos en total.
 * Pista en cada inento acerca de si es un número mayor o menor.
 * 
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinarNumero extends Application {

  int numeroGenerado;
  int intentos = 9;
  Label textoPista = new Label();
  //boolean partidaAcabada = false;

  @Override
  public void start(Stage primaryStage) {

    generarNumero();

    Label textoTitulo = new Label();
    textoTitulo.setText("Adivina el número, ¡tienes 10 intentos!");

    TextField textoEntrada = new TextField();

    Label textoRespuesta = new Label();

    Button boton = new Button("Aceptar");
    boton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {

        try {
          if (intentos >= 1) {
            if (Integer.parseInt(textoEntrada.getText()) != numeroGenerado) {
              
              textoRespuesta.setText("Has fallado, te quedan " + intentos + " intentos.");
              darPista(Integer.parseInt(textoEntrada.getText()));
              intentos--;
            } else {
              textoRespuesta.setText("ACERTASTE, el número era " + numeroGenerado);
              textoPista.setText("Nuevo número generado.");
              generarNumero();
              intentos = 9;
              //partidaAcabada = true;
            } 
          }
          else {
            textoRespuesta.setText("PERDISTE, el número era " + numeroGenerado);
            textoPista.setText("Nuevo número generado.");
            generarNumero();
            intentos = 9;
            //partidaAcabada = true;
          }

        } catch (NumberFormatException excep) {
          textoRespuesta.setText("¡No es un entero!");
        }
      }
    });
    // Crear un layout
    VBox vBox = new VBox();
    vBox.setStyle("-fx-padding: 10;" +
        //"-fx-border-style: dashed double none;" +
        "-fx-border-width: 3;" +
        "-fx-border-insets: 5;" +
        "-fx-border-radius: 20;" +
        "-fx-border-color: white;"+
        "-fx-font-size: 24;"+
        "-fx-background-image: url('https://steamuserimages-a.akamaihd.net/ugc/708526305132654960/B48B2A593B7C76205C510AD1AA3200004C7D1486/')");

    
    
    vBox.getChildren().addAll(textoTitulo, textoEntrada, boton, textoRespuesta, textoPista);

    //        
    //        if (partidaAcabada) {
    //        vBox.getChildren().removeAll(textoTitulo, textoEntrada, boton, textoPista);
    //        vBox.getChildren().add(jugarOtra());
    //        partidaAcabada = false;
    //        }

    Scene scene = new Scene(vBox, 700, 300);

    primaryStage.setTitle("Números Primos");

    primaryStage.setScene(scene);

    primaryStage.show();
  }

  private void generarNumero() {
    this.numeroGenerado = (int) (Math.random() * 101) ;
  }

  private void darPista(int num) {
    if (num < numeroGenerado) {
      textoPista.setText(num + " es menor que el número a adivinar");
    } else {
      textoPista.setText(num + " es mayor que el número a adivinar");
    }
  }

//  private static Label jugarOtra() {
//    Label jugarOtra = new Label();
//    jugarOtra.setText("¿Quieres jugar otra?");
//    return jugarOtra;
//  }

  public static void main(String[] args) {
    
    launch(args);
  }
}