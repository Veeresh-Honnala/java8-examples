package java8;

//ls out/production/java8-examples/java8/  (u can see 8 anonymous inner classes got generated with $1 etc.)
public class PriorLambda {

    public static void main(String[] args) {
        //run the code in another thread
        Thread t1 = new Thread();
        t1.start();

        //method is having
        //1.name
        //2.formal parameters
        //3.body
        //4.return type -- in below example method name got inferred.
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


        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("another thread");
            }
        });

        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("another thread");
            }
        });
        Thread t6 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("another thread");
            }
        });
        Thread t7 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("another thread");
            }
        });
        Thread t8 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("another thread");
            }
        });

        System.out.println("main");
        //see generated class files if we have more anonymous that many inner classes will generate and that much work to load class and object creation and garbage cleaning .
    }
}
