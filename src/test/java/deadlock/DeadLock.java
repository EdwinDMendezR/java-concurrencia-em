package deadlock;

import org.junit.jupiter.api.Test;

public class DeadLock {

    @Test
    public void methodTest() {

        final Variable variableA = new Variable("AAAA");
        final Variable variableB = new Variable("BBBB");

        new Thread(new Runnable() {
            public void run() { variableA.bow(variableB); }
        }).start();

        new Thread(new Runnable() {
            public void run() { variableB.bow(variableA); }
        }).start();

        /**Este código crea dos objetos de la clase Variable, llamados variableA y variableB,
         * y luego lanza dos hilos que llaman a los métodos bow de cada objeto. Los métodos bow y bowBack
         * están sincronizados, lo que significa que sólo un hilo puede ejecutar cualquiera de ellos a la vez.
         * Sin embargo, el problema es que cada hilo llama al método bow del otro hilo, lo que significa que ambos
         * hilos intentan adquirir el bloque de sincronización del otro hilo al mismo tiempo.
         * Esto resulta en un deadlock, ya que ningún hilo puede continuar hasta que el otro hilo libere su bloque
         * de sincronización, pero ningún hilo lo hará hasta que el otro hilo libere su bloque de sincronización.*/


    }

    static class Variable {
        private final String name;

        public Variable(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Variable bower) {
            System.out.format("%s: %s %n", this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Variable bower) {
            System.out.format("%s: %s %n", this.name, bower.getName());
        }
    }

}
