package condiciones.carreras;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class SolucionCountDownLatch {

    private static int counter = 0;
    private static CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void methodTest() {

        // Crea dos hilos
        Thread thread1 = new Thread(new MyIncrementRunnable(latch));
        Thread thread2 = new Thread(new MyDecrementRunnable(latch));

        // Inicia los hilos
        thread1.start();
        thread2.start();

        // Permite que los hilos accedan al recurso compartido
        latch.countDown();

        // Espera a que los hilos terminen
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter: " + counter);
        Assertions.assertEquals(20000, counter);

    }

    class MyIncrementRunnable implements Runnable {
        private CountDownLatch latch;

        public MyIncrementRunnable(CountDownLatch latch) {
            this.latch = latch;
        }

        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Accede al recurso compartido aquí
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        }
    }


    class MyDecrementRunnable implements Runnable {
        private CountDownLatch latch;

        public MyDecrementRunnable(CountDownLatch latch) {
            this.latch = latch;
        }

        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Accede al recurso compartido aquí
            for (int i = 0; i < 10000; i++) {
                counter--;
            }
        }
    }
}
