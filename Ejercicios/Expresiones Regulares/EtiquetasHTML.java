import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EtiquetasHTML {

  public static void main(String[] args) {
    validarArgumentos(args);
    String url = args[0];
    String label = args[1];

    try {
      String html = getHtml(url);
      showLabels(label, html);
      
    } catch (Exception e) {
      System.err.println("Error con la URL" + e.getMessage());
    }
    
  }

  private static void showLabels(String label, String html) {
    System.out.println("HTML donde buscar la etiqueta <" + label + ">");
    System.out.println("-------------------------------" + "-".repeat(label.length() + 1));
    System.out.println(html + "\n");
    
    System.out.println("Resultados");
    System.out.println("----------");
    String regexp = "<" + label + "[^>]*>(.*?)</" + label + ">"; // reluctant
    
    Pattern pattern = Pattern.compile(regexp, Pattern.DOTALL);   // deshabilita multilínea
    Matcher matcher = pattern.matcher(html);

    // proceso de búsqueda de etiquetas
    int n = 0;
    while (matcher.find()) {
      System.out.println("Ocurrencia nº " + ++n + ": " + matcher.group(1));
    }
    System.out.println("\nTotal etiquetas <" + label + "> encontradas: " + n);
  }

  private static void validarArgumentos(String[] args) {
    if (args.length != 2) {
      System.err.println("Error en el número de parámetros"); 
      System.exit(1);
    }
  }
  
  private static String getHtml(String url) throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    return response.body();
  }
}
