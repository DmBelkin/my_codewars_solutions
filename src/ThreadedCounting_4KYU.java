
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class ThreadedCounting_4KYU {

//    DESCRIPTION:
//    Your task is simple. You have to call counter.count(int) with the numbers 1 to 100 inclusive. So a simple solution might look like this:
//
//            for (int i = 1; i <= 100; i++) {
//        counter.count(i);
//    }
//    But there's a catch. Your solution also has to satisfy the following conditions:
//
//    Three different threads must be used
//    Numbers of the form 3n (multiples of 3) must be called in one thread
//    Numbers of the form 3n + 1 must be called in a second
//    Numbers of the form 3n + 2 must be called in a third
//    The numbers have to be called in sequence 1 to 100
//    Also, make sure your method doesn't return until all three threads have completed. Otherwise the tests may not work even if your solution is correct.


    public static void countInThreads(Counter counter) {
        Verify verify = new Verify();
        ThisThread thread = new ThisThread(counter, verify);
        thread.start();
        ThisThread thread1 = new ThisThread(counter, verify);
        thread1.start();
        ThisThread thread2 = new ThisThread(counter, verify);
        thread2.start();
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0) {
                thread1.setNumber(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (i % 3 == 2) {
                thread2.setNumber(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                thread.setNumber(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


class Verify {

    private List<Integer> list = new CopyOnWriteArrayList<>();


    public void count(int var) {
        list.add(var);
    }

    public List<Integer> getSet() {
        return list;
    }

}

class ThisThread extends Thread {

    private AtomicInteger number;

    private Counter counter;

    private Verify verify;

    public ThisThread(Counter counter, Verify verify) {
        this.counter = counter;
        this.verify = verify;
    }

    @Override
    public void run()  {
        for (;;) {
            if (number != null) {
                this.pusher();
            } else if (verify.getSet().size() == 100) {
                this.stop();
            }
        }
    }

    public synchronized void pusher() {
        counter.count(number.intValue());
        verify.count(number.intValue());
        number = null;
    }

    public void setNumber(int number) {
        this.number = new AtomicInteger(number);
    }
}

class Counter{

    public void count(int value){

    }
}

