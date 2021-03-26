package Tiempo;

/**
 * 
 * @author Guillermo Tamajón
 * 
 * 
 */
public class Tiempo {
  private int dias;
  private int horas;
  private int minutos;
  private int segundos;

  public Tiempo(int dias, int horas, int minutos, int segundos) throws CantidadNegativaException {
    setDias(dias);
    setHoras(horas);
    setMinutos(minutos);
    setSegundos(segundos);

    this.normaliza();
  }

  public int getHoras() {
    return horas;
  }

  private void setHoras(int horas) throws CantidadNegativaException {
    if (horas < 0)
      throw new CantidadNegativaException("Las horas no pueden ser negativas");
    this.horas = horas;
  }

  public int getMinutos() {
    return minutos;
  }

  private void setMinutos(int minutos) throws CantidadNegativaException {
    if (minutos < 0)
      throw new CantidadNegativaException("Los minutos no pueden ser negativos");
    this.minutos = minutos;
  }

  public int getSegundos() {
    return segundos;
  }

  private void setSegundos(int segundos) throws CantidadNegativaException {
    if (segundos < 0)
      throw new CantidadNegativaException("Los segundos no pueden ser negativos");

    this.segundos = segundos;
  }

  public int getDias() {
    return dias;
  }

  public void setDias(int dias) throws CantidadNegativaException {
    if (dias < 0)
      throw new CantidadNegativaException("Los segundos no pueden ser negativos");
    this.dias = dias;
  }

  public Tiempo normaliza() {


    while (this.horas > 23) {
      this.horas -= 24;
      this.dias++;
    }


    while (this.minutos > 59) {
      this.minutos -= 60;
      this.horas++;
    }
    while (this.segundos > 59) {
      this.segundos -= 60;
      this.minutos++;
    }

    while (this.horas < 0) {
      this.horas += 23;
      this.dias--;
    }


    while (this.minutos < 0) {
      this.minutos += 59;
      this.horas--;
    }
    while (this.segundos < 0) {
      this.segundos += 59;
      this.minutos--;
    }
    return this;
  }

  public Tiempo suma(Tiempo tiempo) throws CantidadNegativaException {
    Tiempo resultado = new Tiempo(this.dias + tiempo.dias, this.horas + tiempo.horas, this.minutos + tiempo.minutos,
        this.segundos + tiempo.segundos);
    resultado.normaliza();
    return resultado;
  }

  public void suma(int sumaDias, int sumaHoras, int sumaMinutos, int sumaSegundos) throws CantidadNegativaException {
    setDias(dias + sumaDias);
    setHoras(horas + sumaHoras);
    setMinutos(minutos + sumaMinutos);
    setSegundos(segundos + sumaSegundos);
  }

  @Override
  public String toString() {
    return "Tiempo [horas=" + horas + ", minutos=" + minutos + ", segundos=" + segundos + "]";
  }


}
