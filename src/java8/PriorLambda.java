package java8;

public class PriorLambda {

    public static void main(String[] args) {
        //run the code in another thread
        Thread t1 = new Thread();
        t1.start();


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("another thread");
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("another thread");
            }
        });


        System.out.println("main");
    }
}
