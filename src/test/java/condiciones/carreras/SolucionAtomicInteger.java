package condiciones.carreras;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class SolucionAtomicInteger {

    private static AtomicInteger counter = new AtomicInteger();

    @Test
    @RepeatedTest(100)
    public void methodTest() {
        counter.set(0);
        // Crea dos hilos
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.incrementAndGet();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.incrementAndGet();
                }
            }
        });

        // Inicia los hilos
        thread1.start();
        thread2.start();

        // Espera a que los hilos terminen
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(20000, counter.get());

    }

}
