module holajavafx {
  requires transitive javafx.graphics;  
  requires javafx.controls;  
  requires javafx.fxml;  
  
  opens BoggleJavaFX to javafx.fxml;  
  
  exports BoggleJavaFX;
 
}
  
