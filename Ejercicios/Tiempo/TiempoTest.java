package Tiempo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class TiempoTest {

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {}

  @AfterEach
  void tearDown() throws Exception {}

  
  //Pruebo la normalización
  @Test
  void testNormaliza() throws CantidadNegativaException {
    Tiempo tiempo = new Tiempo(0,24,80,61);
    assertEquals(1,tiempo.getDias());
    assertEquals(1,tiempo.getHoras());
    assertEquals(21,tiempo.getMinutos());
    assertEquals(1,tiempo.getSegundos());

  }
  
  
  
  //Compruebo si ingreso unidades negativas salta la excepcion CantidadNegativaException
  @Test
  void testFechaError() throws CantidadNegativaException {
    try {
      Assertions.assertThrows(CantidadNegativaException.class, () -> {
        new Tiempo(-100,30,60,-60);
      });
    } catch (Exception e) {
      fail("Aquí no debería saltar una excepción");
    }
  }
  
  //Testeo la suma
  @Test
  void testSuma() throws CantidadNegativaException{
    Tiempo tiempo = new Tiempo(1,20,10,10);
    
    tiempo.suma(2,2,2,2);
    
    assertEquals(3,tiempo.getDias());
    assertEquals(22,tiempo.getHoras());
    assertEquals(12,tiempo.getMinutos());
    assertEquals(12,tiempo.getSegundos());
    
    
  }
  
  //Test de la suma de tiempos 
  @Test
  void testSumaTiempos() throws CantidadNegativaException {
    Tiempo tiempo1 = new Tiempo(1,20,20,20);
    Tiempo tiempo2 = new Tiempo(1,1,20,20);
    
    tiempo1.suma(tiempo2);
    
    assertEquals(40, tiempo1.suma(tiempo2).getSegundos());
    assertEquals(40, tiempo1.suma(tiempo2).getMinutos());
    assertEquals(21, tiempo1.suma(tiempo2).getHoras());
    assertEquals(2, tiempo1.suma(tiempo2).getDias());

  }
 
  

}

