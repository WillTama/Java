package Ejemplo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalcularRectangulo extends Application {

  private Label labelBase = new Label();
  private Label labelAltura = new Label();
  private TextField cajaBase = new TextField();
  private TextField cajaAltura = new TextField();
  private TextArea cajaResultado = new TextArea();
  private Button cajaBoton = new Button();

  @Override
  /**
   * Método init() donde controlamos la visibilidad del boton Calcular
   */
  public void init() {
    cajaBoton.setDisable(true);

    cajaBase.textProperty().addListener((observable, oldValue, newValue) -> {
      validarDato(cajaBase, oldValue, newValue);
    });
    
    cajaAltura.textProperty().addListener((observable, oldValue, newValue) -> {
      validarDato(cajaAltura, oldValue, newValue);
    });
  }

  private void validarDato(TextField dato, String oldValue, String newValue) {
    if (!newValue.matches("^[0-9]*$")) {
      //System.out.println("Número inválido: " + dato.getText());
      dato.setText(oldValue);
    }
    cajaBoton.setDisable(cajaBase.getText().isBlank() || cajaAltura.getText().isBlank());
  }

  @Override
  public void start(Stage primaryStage) {
    cearEscenario(primaryStage);
    primaryStage.show();
  }

  private void cearEscenario(Stage primaryStage) {
    primaryStage.setTitle("!Tu calculo rectangular¡");
    Label labelPresentacion = new Label("Programa para el calculo del área y perímetro de "
        + "un rectangulo");

    // Relleno las cajas internas
    labelBase.setText("Introduce la base: ");
    labelAltura.setText("Introduce la altura: ");
    cajaBoton.setText("!Calcular¡");


    cajaBoton.setOnAction(evento -> {
      calcularResultados();
    });

    HBox cajaHorizontalSuperior = new HBox(10,labelBase,cajaBase);
    cajaHorizontalSuperior.setAlignment(Pos.CENTER);

    HBox cajaHorizontalInferior = new HBox(10,labelAltura,cajaAltura);
    cajaHorizontalInferior.setAlignment(Pos.CENTER);

    VBox cajaPrincipal = new VBox(5,labelPresentacion,cajaHorizontalSuperior,cajaHorizontalInferior, cajaBoton, cajaResultado);
    cajaPrincipal.setAlignment(Pos.CENTER);
    cajaPrincipal.setPadding(new Insets(25));
    cajaPrincipal.setSpacing(10);

    Scene pantallaPrincipal = new Scene(cajaPrincipal, 500, 300);

    primaryStage.setScene(pantallaPrincipal);
  }

  private void calcularResultados() {
    try {
      double area = calcularArea();
      double perimetro = calcularPerimetro();

      cajaResultado.setText("El area de tu rectangulo es " + area + "\n" + 
          "El perimetro de tu rectangulo  es " + perimetro);
    } catch (Exception e) {
      if (cajaBase.getText().isEmpty()) {
        cajaResultado.setText("Tienes que introducir la base.");
      } else if (cajaAltura.getText().isEmpty()){
        cajaResultado.setText("Tienes que introducir la altura.");
      }
    }
  }

  private double calcularPerimetro() {
    return 2 * (Double.parseDouble(cajaBase.getText()) 
        + Double.parseDouble(cajaAltura.getText()));
  }

  private double calcularArea() {
    return Double.parseDouble(cajaBase.getText()) * Double.parseDouble(cajaAltura.getText());
  }

  public static void main(String[] args) {
    launch(args);
  }
}
