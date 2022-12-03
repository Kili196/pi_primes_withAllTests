package primeFinder;

public class Daemon extends Thread{
    PrimeFinder finder;
    public Daemon(PrimeFinder finder){
        this.finder = finder;
    }

    public void run(){
        synchronized (this){
            System.out.println(finder.countRunningCheckers());
            System.out.println(finder.getPrimes());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
