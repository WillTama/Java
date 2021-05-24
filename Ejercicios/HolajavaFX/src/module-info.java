module holajavafx {
  requires transitive javafx.graphics;  
  requires javafx.controls;  
  requires javafx.fxml;  
  
  opens Ejemplo to javafx.fxml;  
  
  exports Ejemplo;
 
}
  
