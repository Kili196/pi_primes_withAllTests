package primeFinder;


public record PrimeChecker(long candidate, PrimeFinder primeFinder) implements Runnable {

    @Override
    public void run() {
        primeFinder.countRunningCheckersIncrease();
        if(isPrimeWithDelay()){
            primeFinder.addToColl(candidate);
        }
        primeFinder.countRunningCheckersDecrease();
    }

    public boolean isPrimeWithDelay() {
        if(candidate  < 2){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(candidate); i++) {
            if(candidate % i == 0){
                return false;
            }
            try {
                Thread.sleep(primeFinder.getDelay());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }



}