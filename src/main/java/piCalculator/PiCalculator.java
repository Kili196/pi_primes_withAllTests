package piCalculator;

import java.util.ArrayList;
import java.util.Arrays;

public class PiCalculator {

    public static double calcPiWithNThreads(int limit, int threadCnt) {
        if(limit < 0 || threadCnt < 1){
            throw new IllegalArgumentException();
        }
        int from ;
        int to ;
        ArrayList<PartialSumCalculator> runningThreads = new ArrayList<>();
        double pi = 0;


        for (int i = 0; i < threadCnt ; i++) {
            from = i * limit / threadCnt;
            to = (i + 1) * limit / threadCnt;
            if( i != 0){
                ++from;
            }

            PartialSumCalculator partialSumCalculator = new PartialSumCalculator(from, to);
            partialSumCalculator.setName("Thread- " + i);
            runningThreads.add(partialSumCalculator);
            partialSumCalculator.start();
        }
        for(PartialSumCalculator partialSumCalculator : runningThreads){
            try {
                partialSumCalculator.join();
                pi += partialSumCalculator.getSum();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return pi * 4;
    }

    public static void main(String[] args) {
        System.out.println(calcPiWithNThreads(99_999, 7));

    }
}
