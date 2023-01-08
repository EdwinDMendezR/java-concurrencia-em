package deadlock;

import org.junit.jupiter.api.Test;

public class SolucionDeadLock {

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

        /**En este caso, hemos creado un objeto de bloqueo común para ambos objetos de la clase Variable, y
         * siempre adquirimos el bloqueo antes de llamar a cualquiera de los métodos sincronizados.
         * De esta manera, ambos hilos adquieren el mismo bloqueo en el mismo orden, lo que evita el deadlock.**/

    }

    static class Variable {
        private final String name;
        private final Object lock = new Object();

        public Variable(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public void bow(Variable bower) {
            synchronized (lock) {
                System.out.format("%s: %s %n", this.name, bower.getName());
                bower.bowBack(this);
            }
        }
        public void bowBack(Variable bower) {
            synchronized (lock) {
                System.out.format("%s: %s %n", this.name, bower.getName());
            }
        }
    }

}
