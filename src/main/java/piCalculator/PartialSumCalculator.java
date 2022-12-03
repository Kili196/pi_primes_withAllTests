package piCalculator;

public class PartialSumCalculator extends Thread {

    private int min;
    private int max;

    private double pi;


    public PartialSumCalculator(int min, int max) {
        if(min < 0){
            throw new IllegalArgumentException();
        }
        this.min = min;
        this.max = max;

    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "[ " + min + ", " + max + " ]" + "=> " +  (max - min) );
        for (int i = min; i <= max ; i++) {
            this.pi += Math.pow(-1, i) /(2 * i + 1);
        }

    }

    public double getSum() {
        return this.pi;
    }
}
