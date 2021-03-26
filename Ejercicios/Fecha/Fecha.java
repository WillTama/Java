
package Fecha;

public class Fecha implements Comparable<Fecha>, Cloneable{

  // Atributos ////

  private int anyo, mes, dia;

  // Constructor ////

  public Fecha (int anyo, int mes, int dia) {
    this.anyo = anyo;
    this.mes = mes;
    this.dia = dia;

    if (! this.esFechaCorrecta()) {
      System.err.println("Fecha incorrecta");
    }

  }

  /// Getter & Setters ///

  public int getAnyo() {
    return anyo;
  }

  public void setAnyo(int anyo) {
    this.anyo = anyo;
  }

  public int getMes() {
    return mes;
  }

  public void setMes(int mes) {
    this.mes = mes;
  }

  public int getDia() {
    return dia;
  }

  public void setDia(int dia) {
    this.dia = dia;
  }


  @Override
  public String toString() {
    return this.anyo + "/" + this.mes + "/" + this.dia; 
  }


  public static int diasMes(int mes, int anyo) {
    int[] diasMeses = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (esBisiesto(anyo)) {
      diasMeses[1] = 29;
    }
    return diasMeses[mes - 1];
  }

  public boolean esFechaCorrecta() {
    boolean correcta = true;
    if (this.mes > 12 || this.mes < 1 ) {
      correcta = false;
    }
    else if ( this.dia > 31 || this.dia < 1) {
      correcta = false;
    }
    else if ( this.mes == 2) {

      if (esBisiesto(anyo) && this.dia > 29) {
        correcta = false;
      }

      else if(!esBisiesto(anyo) && this.dia > 28) {
        correcta = false;
      }

    }
    else if (this.mes == 4 || this.mes == 6 || this.mes == 9 || this.mes == 11) {
      if (this.dia > 30) {
        correcta = false;
      }  
    }
    return correcta;
  }


  private static boolean esBisiesto(int anyo) {
    return (anyo % 4 == 0 && anyo % 100 != 0 || anyo % 400 == 0);

  }

  private void sumar1Dia() {

    this.dia++;

    if (this.dia > diasMes(this.mes, this.anyo)) {
      this.mes++;
      this.dia = 1;

      if (this.mes>12) { 
        this.anyo++;
        this.mes = 1;

      }
    }
  }


  public void restar1Dia() {

    dia--;
    if (dia == 0) { 
      mes--;
      if (mes == 0) { 
        mes = 12;
        anyo--;
        if (anyo == 0) {
          anyo = -1;
        }
      } 
      dia = diasMes(mes, anyo);
    }
  }

  public void sumarDias(int dias) {

    for (int i=1; i<=dias; i++) {
      this.restar1Dia();
    }

  }
  

  public void restarDias(int dias) {


    for (int i=1; i<=dias; i++) {
      this.sumar1Dia();
    }

  }




  @Override
  public int compareTo(Fecha fecha) {
    // TODO Auto-generated method stub
    return ((this.anyo*10000 + this.mes*100 + this.dia)-
        (fecha.anyo*10000 + fecha.mes*100 + fecha.dia));
  }

  public Fecha clone() {
    return new Fecha(this.dia, this.mes, this.anyo);
  }

}