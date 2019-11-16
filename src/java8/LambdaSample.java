package java8;

import java.util.Random;

//javap -c out/production/java8-examples/java8/LambdaSample.class
//ls out/production/java8-examples/java8/  (no anonymous classes generated for this)
public class LambdaSample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("t1"));
        Thread t2 = new Thread(() -> System.out.println("t2"));
        Thread t3 = new Thread(() -> System.out.println("t3"));
        Thread t4 = new Thread(() -> System.out.println("t4"));
        Thread t5 = new Thread(() -> System.out.println("t5"));
    }
}
