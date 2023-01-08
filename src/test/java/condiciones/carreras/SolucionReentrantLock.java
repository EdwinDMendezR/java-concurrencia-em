package condiciones.carreras;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SolucionReentrantLock {

    private static int counter = 0;
    private static Lock lock = new ReentrantLock();

    @Test
    @RepeatedTest(100)
    public void methodTest() {
        counter = 0;
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    lock.lock();
                    try {
                        counter++;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    lock.lock();
                    try {
                        counter++;
                    } finally {
                        lock.unlock();
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

        /**Este código crea dos hilos que incrementan el contador counter en 10000 veces cada uno.
         * Para evitar la condición de carrera, se utiliza un objeto Lock para proteger el acceso al contador.
         * Cada vez que un hilo quiere modificar el contador, primero adquiere el bloqueo y luego modifica el contador.
         * Después de modificar el contador, el hilo libera el bloqueo. De esta manera, se garantiza que
         * solo un hilo accede al contador en cualquier momento, evitando la condición de carrera.**/

        Assertions.assertEquals(20000, counter);

    }

}
