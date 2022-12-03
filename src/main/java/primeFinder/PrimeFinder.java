package primeFinder;

import java.util.Collection;
import java.util.Vector;
import java.util.stream.Stream;

public class PrimeFinder {

    private Collection<Long> primes;
    private int delay;
    private int from;
    private int to;
    private int count;

    public PrimeFinder() {
    }

    public PrimeFinder(int delay) {
        this.delay = delay;
    }

    public PrimeFinder(int delay, int from, int to) {
        this.delay = delay;
        this.from = from;
        this.to = to;
        this.primes = new Vector<>();
        this.count = 0;
    }

    public synchronized void countRunningCheckersIncrease() {
        count++;
    }

    public synchronized void countRunningCheckersDecrease() {
        count--;
    }

    public int countRunningCheckers(){
        return count;
    }

    public int getDelay() {
        return delay;
    }

    /**
     * Teilt alle Zahlen in [from, to[ auf Threads auf,
     * welche die Primalität der jeweiligen Zahl bestimmen
     */
    public void findPrimes() {
        for (int i = from; i <= to; i++) {
            PrimeChecker primeChecker = new PrimeChecker(i, this);
            Thread thread = new Thread(primeChecker);
            thread.start();
        }
    }

    public Stream<Long> getPrimes() {
        return primes.stream();
    }

    public void addToColl(Long candidate) {
            primes.add(candidate);
    }
}
