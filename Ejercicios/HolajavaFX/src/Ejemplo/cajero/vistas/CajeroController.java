package BoggleJavaFX.cajero.vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CajeroController {

  @FXML
  private TextField euros;

  @FXML
  private TextArea visor;

  @FXML
  private Button boton;

  @FXML
  void calcularDinero(ActionEvent event) {

    double dinero = Double.parseDouble(euros.getText());
    double [] monedas= {500, 200, 100, 50, 20, 10, 5, 2, 1, 0.5, 0.20, 0.10, 0.05, 0.02, 0.01};
    double [] devolver= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    for(int i = 0 ; i < monedas.length ; i++) {
      if(dinero >= monedas[i]) {
        devolver[i] = Math.floor(dinero/monedas[i]);
        dinero = dinero-(devolver[i] * monedas[i]);
      }
    }

    for(int i=0;i<monedas.length;i++) {      
      if(devolver[i]>0) {
        if(monedas[i]>2) {
          visor.appendText(String.valueOf("Hay " + (int) devolver[i] + " billetes de: " + monedas[i] + " Euros" + "\n"));
        } else {
          visor.appendText(String.valueOf("Hay " + (int) devolver[i] + " monedas de: " + monedas[i] + " Euros" + "\n"));
        }
      }
    }


  }


}


