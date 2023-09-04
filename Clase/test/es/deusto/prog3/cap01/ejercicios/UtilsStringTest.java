package es.deusto.prog3.cap01.ejercicios;

import static org.junit.Assert.*;
import org.junit.Test;

public class UtilsStringTest {

	@Test
	public void quitarTabsYSaltosLinea() {
		String prueba = "Hola\nEsto es un string con tres líneas\ny\tvarios\ttabuladores.";
		String prueba2 = "Hola#Esto es un string con tres líneas#y|varios|tabuladores.";
		assertEquals(prueba2, UtilsString.quitarTabsYSaltosLinea(prueba));
		assertEquals("", UtilsString.quitarTabsYSaltosLinea(""));
		assertEquals("hola", UtilsString.quitarTabsYSaltosLinea("hola"));
		assertEquals("||", UtilsString.quitarTabsYSaltosLinea("\t\t"));

//		if (prueba2.equals(quitarTabsYSaltosLinea(prueba))) {
//			System.out.println( "OK" );
//		} else {
//			System.out.println( "FAIL" );
//		}
	}
	
	@Test
	public void quitarTabsYSaltosLinea2() {
		assertEquals(null, UtilsString.quitarTabsYSaltosLinea(null));
		assertNull(UtilsString.quitarTabsYSaltosLinea(null));
	}
	
	@Test
	public void wrapString() {
		assertEquals("Andoni", UtilsString.wrapString("Andoni", 10));
		assertEquals("Ando...", UtilsString.wrapString("Andoni", 4));
		assertEquals("", UtilsString.wrapString("", 4));
		assertEquals("Andoni\nMar...", UtilsString.wrapString("Andoni\nMaría", 10));
	}
	
	@Test
	public void wrapString2() {
		assertEquals(null, UtilsString.wrapString(null, 10));
	}
	
	@Test
	public void wrapStringNegativo() {
		try {
			UtilsString.wrapString("Andoni", -5);
			fail( "No excepción" );
			// 1
		} catch (IndexOutOfBoundsException e) {
			// 2
			// assertTrue( true );
		}
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void wrapStringNegativo2() {
		UtilsString.wrapString("Andoni", -5);
	}
	
}
