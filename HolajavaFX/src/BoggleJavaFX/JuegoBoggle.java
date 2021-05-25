package BoggleJavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JuegoBoggle extends Application {


  private Tablero tablero;
  private Button[] botones = new Button[16];
  private TextField palabraEscrita = new TextField();
  private int posDelFor;
  private DiccionarioRAE diccionario = new DiccionarioRAE();
  private Button botonGuardarPalabra = new Button();
  private Jugador jugador = new Jugador();
  private Label puntos = new Label();
  private Button botonMostrarResultados = new Button();
  private GridPane panelBotones = new GridPane();
  private final ImageView miImagenView = new ImageView();   
  private Image miImagen = new Image(JuegoBoggle.class.getResourceAsStream("titulo.png"));
  
  public void activarBotones() {
    for (int i=0;i<botones.length;i++) {
      botones[i].setDisable(false);
    }
  }


  private void crearPanelLetras() {
    // Llenar array de botones
    for (int i=0;i<botones.length;i++) {
      posDelFor = i;  
      botones[i] = new Button();

      // Pongo letra en boton
      botones[i].setText(Character.toString(tablero.getTableroDeJuego()[i]));

      // Creo la funcionalidad de la desactivación
      botones[i].setOnAction(new EventHandler<ActionEvent>() {
        private int laPos = posDelFor;
        @Override
        public void handle(ActionEvent event) {

          activarBotones();
          // Que se muestre la letra del boton en el textarea al pulsar
          palabraEscrita.setText(palabraEscrita.getText() + Character.toString(tablero.getTableroDeJuego()[laPos]));

          // Desactivo botones no contiguos
          ArrayList<Integer> posicionesContiguas = Tablero.devuelvePosicionesContiguas(laPos);
          for (int i = 0; i <=15; i++) {
            if (!posicionesContiguas.contains(i)) {
              botones[i].setDisable(true);
            }
          }
        }   
      });        
    }

    // Lleno cuadro de botones
    int fila = 0;
    int columna = 0;
    for(int i =0; i<botones.length; i++) {
      panelBotones.add(botones[i], fila, columna);
      fila++;
      if(fila ==4) {
        fila=0;
        columna++;
      }
    }
  }


  private Scene crearEscenaInicial(Stage primaryStage) {
    Button boton = new Button();
    boton.setText("Jugar");

    // Evento que me lleva a la escena de la partida
    boton.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        primaryStage.setScene(crearEscenaPrincipal(primaryStage));
        primaryStage.show();
      }
    });

    VBox vb = new VBox(20);
    vb.getChildren().addAll(miImagenView, boton);
    Scene scene = new Scene(vb, 800, 520);
    scene.getStylesheets().add(getClass().getResource("estilooscuro.css").toExternalForm());
    return scene; 
  }


  private Scene crearEscenaPrincipal(Stage primaryStage) {
    crearPanelLetras();
    
    palabraEscrita.setEditable(false);

    botonGuardarPalabra.setText("▶");
    
    // Evento que suma puntos si la palabra es correcta
    botonGuardarPalabra.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        if(diccionario.esPalabraCorrecta(palabraEscrita.getText()) && palabraEscrita.getText().length()>=2) {
          jugador.addPalabra(palabraEscrita.getText());
          puntos.setText("PUNTOS: " + jugador.getPuntos());
        }
        palabraEscrita.setText("");
        activarBotones();
      }    
    });

    botonMostrarResultados.setText("Terminar");
    
    // Evento que cambia de escena y muestra resultados a partir del botón
    botonMostrarResultados.setOnAction(new EventHandler<ActionEvent>() 
        {
      @Override
      public void handle(ActionEvent e) {
        primaryStage.setScene(crearEscenaResultados(primaryStage));
        primaryStage.show();
      }
        });
    

    HBox hbox = new HBox();
    hbox.getChildren().addAll(palabraEscrita, botonGuardarPalabra);
    VBox cajaPrincipal = new VBox();
    cajaPrincipal.getChildren().addAll(panelBotones, hbox, puntos, botonMostrarResultados);
    Scene scene = new Scene(cajaPrincipal, 800, 520);
    scene.getStylesheets().add(getClass().getResource("estilooscuro.css").toExternalForm());
    return scene;
  }


  private Scene crearEscenaResultados(Stage primaryStage) {
    Button boton = new Button();
    boton.setText("Jugar de nuevo");

    boton.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        
        // Reset de objetos
        jugador = new Jugador();
        tablero = new Tablero();
        botones = new Button[16];
        puntos = new Label();
        palabraEscrita = new TextField();
        panelBotones = new GridPane();
        
        primaryStage.setScene(crearEscenaPrincipal(primaryStage));
        primaryStage.show();
        
      }
    });

    Label palabras = new Label();
    palabras.setText(jugador.devolvePalabrasAcertadas());

    // Rellenar resultado
    Label texto = new Label();
    texto.setText("¡" + puntos.getText() + "! Acertaste las palabras: ");
    if (puntos.getText().equals("")) {
      texto.setText("¡No acertaste ninguna palabra!");
    }
    
    
    VBox vb = new VBox(20);
    vb.getChildren().addAll(texto, palabras, boton);
    Scene scene = new Scene(vb, 800, 520);
    scene.getStylesheets().add(getClass().getResource("estilooscuro.css").toExternalForm());
    return scene; 
  }

  @Override
  public void init() throws IOException {
    
    
    miImagenView.setImage(miImagen);
    miImagenView.setFitHeight(150);
    miImagenView.setFitWidth(490);
    
  }

  @Override
  public void start(Stage primaryStage) {

    tablero = new Tablero();
    Scene scene = crearEscenaInicial(primaryStage);
    primaryStage.setTitle("BOGGLE");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
