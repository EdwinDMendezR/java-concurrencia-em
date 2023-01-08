package condiciones.carreras;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class SolucionSynchronized {

    static int counterProblema = 0;
    static Object lock = new Object();

    @Test
    @RepeatedTest(100)
    public void problema() {

        counterProblema = 0;

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counterProblema++;
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counterProblema++;
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**Este código crea dos hilos que ambos incrementan un contador en un bucle de 10000 veces. Sin embargo,
         * al no sincronizar el acceso al contador, es posible que los hilos no incrementen el contador de
         * manera consistente y el resultado final puede ser menor o mayor de lo esperado.
         * Esto se conoce como una condición de carrera.*/

        // Ejecutar varias veces la prueba
        Assertions.assertEquals(20000, counterProblema);

    }

}
