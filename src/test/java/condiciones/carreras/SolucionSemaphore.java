package condiciones.carreras;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;

public class SolucionSemaphore {

    private static int counter = 0;
    private static Semaphore semaphore = new Semaphore(1);

    @Test
    @RepeatedTest(100)
    public void methodTest() {
        counter = 0;
        // Crea dos hilos
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    try {
                        semaphore.acquire();
                        counter++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    try {
                        semaphore.acquire();
                        counter++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
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

        Assertions.assertEquals(20000, counter);

    }

}
