package condiciones.carreras;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class CondicionesCarrera {

    static Object lock = new Object();
    static int counterSolucion = 0;

    @Test
    @RepeatedTest(100)
    public void solucion() {
        counterSolucion = 0;
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (lock) {
                    counterSolucion++;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (lock) {
                    counterSolucion++;
                }
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

        Assertions.assertEquals(20000, counterSolucion);

    }


}
