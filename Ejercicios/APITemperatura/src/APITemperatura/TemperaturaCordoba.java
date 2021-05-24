package APITemperatura;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import com.google.gson.Gson;
import APITemperatura.DTO.Dto;

public class TemperaturaCordoba {

  private static final String ROOT_ENDPOINT = "https://api.openweathermap.org";
  private static final String PATH = "/data/2.5/forecast";
  private static final String PARAMS = "?q=Cordoba,ES&appid=ccc4789ccad6f45e60a752e349cdd9a1&units=metric&";

  public static void main(String[] args) {
    try {
      //System.out.println(TemperaturaCordoba.getRespuesta());
      TemperaturaCordoba.generadorTiempo5Dias(TemperaturaCordoba.getRespuesta());

    } catch (IOException | InterruptedException e) {
      System.out.println("Error al acceder al hacer la petición: " + e.getMessage());
    }
  }

  private static String getRespuesta() throws IOException, InterruptedException {
    String endpoint = ROOT_ENDPOINT + PATH + PARAMS;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endpoint))
        .GET()
        .build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    return response.body();
  }

  public static void generadorTiempo5Dias(String json) {
    Gson gson = new Gson();
    Dto dto = gson.fromJson(json, Dto.class);

    String fecha = dto.list.get(0).getDtTxt().substring(0, 10);
    int counter = 0;
    double tempMax = Double.MIN_VALUE;
    double tempMin = Double.MAX_VALUE;
    double temp = 0;
    double allTemps = 0;

    for(int i = 0; i<=dto.list.size(); i++) {  
      //System.out.println(dto.getList().get(i));
      if(i == dto.list.size() || !fecha.equals(dto.list.get(i).getDtTxt().substring(0, 10))) {

        System.out.println("La predicción para el día " + fecha + " en " + dto.getCity().getName() +  " es:");
        System.out.println("Temperatura Máxima: " + tempMax);
        System.out.println("Temperatura Mínima: " + tempMin);
        System.out.println("Temperatura Media: " + temp/counter);
        System.out.println("Mediciones: " + counter);
        System.out.println();

        counter = 0;
        tempMax = Double.MIN_VALUE;
        tempMin = Double.MAX_VALUE;
        temp = 0;  
      }

      if(i != dto.list.size()) {

        counter++;
        if (tempMax < dto.list.get(i).getMain().getTempMax()) {
          tempMax = dto.list.get(i).getMain().getTempMax();
        } 
        if (tempMin > dto.list.get(i).getMain().getTempMin()) {
          tempMin = dto.list.get(i).getMain().getTempMin();
        }
        fecha = dto.list.get(i).getDtTxt().substring(0, 10);
        temp += dto.list.get(i).getMain().getTemp();
        allTemps += dto.list.get(i).getMain().getTemp();
      }     
    }
    System.out.println("Temperatura media total: " + allTemps/dto.list.size());  

    //Test

    //for(int i = 0; i<dto.list.size(); i++) {  
      //System.out.println(dto.getList().get(i));
    //}
  } 
}



