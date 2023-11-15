package es.deusto.prog3.cap00.ejercicios;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

/** Clase de contador para ejercicio de concurrencia con hilos 0.10
 * @author andoni.eguiluz @ ingenieria.deusto.es
 * 
 * Permite instanciar contadores que incrementan o decrementan su valor
 */
public class Contador  {
	// Parte static - main de prueba (ejercicio de hilo)
	
	private static long MS_PAUSA = 1; // Milisegundos de pausa en el incremento / decremento
	private static int repeticiones=1000;
	private static JProgressBar pbprogesohilo1;
	private static JProgressBar pbprogesohilo2;
	public static void main(String[] args) {
		// Creamos un contador
		Contador cont = new Contador();
		Runnable tarea_incr= new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i=0; i<repeticiones; i++) {
					cont.inc(1);
					if (MS_PAUSA>0) try { Thread.sleep(MS_PAUSA); } catch (InterruptedException e) { } // Pausa entre iteraciones si procede
				}
			}
		};
		Runnable tarea_dec= new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i=0; i<repeticiones; i++) 
				{
					cont.dec(1);
					if (MS_PAUSA>0) try { Thread.sleep(MS_PAUSA); } catch (InterruptedException e) { }
					// Pausa entre iteraciones si procede
				}
			}
			
			
		};
		System.out.println( "Valor inicial de contador: " + cont );
		// Incrementamos 1.000 veces
		
		// Decrementamos 1.000 veces
		Thread Thread_incr= new Thread(tarea_incr);
		Thread Thread_dec= new Thread(tarea_dec);
		Thread_dec.start();
		Thread_incr.start();
		{
			try {
				Thread_incr.join();

		}catch (InterruptedException e){
          e.printStackTrace();
      }
		}
		{
			try {
				Thread_dec.join();

		}catch (InterruptedException e){
          e.printStackTrace();
      }
		}
	JFrame ventana_cierre= new JFrame("Ventana de cierre, Cierrala para cerrar los hilos");
	ventana_cierre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
	ventana_cierre.setSize(600,100);
	pbprogesohilo1= new JProgressBar(0,repeticiones-1);
	pbprogesohilo1.setStringPainted( true );
	pbprogesohilo2= new JProgressBar(0,repeticiones-1);
	pbprogesohilo2.setStringPainted(true);
	ventana_cierre.add(pbprogesohilo1, BorderLayout.NORTH);
	ventana_cierre.add(pbprogesohilo2, BorderLayout.SOUTH);
	
	
		System.out.println( "Valor final de contador: " + cont );
	}
	
	// Clase para instancias - parte no static
	
	/** Crea un contador con valor inicializado a 0
	 */
	public Contador() {
		valor = 0;
	}
	
	private int valor;
	/** Incrementa el valor del contador
	 * @param incremento	Cantidad a incrementar (debe ser positiva)
	 */
	public synchronized void inc( int incremento ) {
		int temporal = valor;
		temporal = temporal + incremento;
		valor = temporal;
	}
	/** Decrementa el valor del contador
	 * @param decremento	Cantidad a decrementar (debe ser positiva, se restará del valor)
	 */
	public synchronized void dec( int decremento ) {
		int temporal = valor;
		temporal = temporal - decremento;
		valor = temporal;
	}
	 Thread Thread_dec=new Thread();
	
	
	@Override
	public String toString() {
		return "" + valor;
	}
}
