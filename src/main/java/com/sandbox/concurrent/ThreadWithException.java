package com.sandbox.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadWithException {

    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
        List<Future<String>> resultList = new ArrayList<>();

        Random random = new Random();

        for (int i=0; i<4; i++)
        {
            Integer number = random.nextInt(10);
            FactorialCalculator calculator  = new FactorialCalculator(number);
            Future<String> result = executor.submit(calculator);
            resultList.add(result);
        }

        for(Future<String> future : resultList)
        {
            try
            {
                System.out.println("Future result is - " + " - " + future.get(1000, TimeUnit.MILLISECONDS) + "; And Task done is " + future.isDone());
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        }
        //shut down the executor service now
        executor.shutdown();
    }

    private static class FactorialCalculator implements Callable<String>
    {

        private Integer number;

        public FactorialCalculator(Integer number) {
            this.number = number;
        }

        @Override
        public String call() throws Exception {
            int result = 1;
            if ((number == 0) || (number == 1)) {
                result = 1;
            } else {
                for (int i = 2; i <= number; i++) {
                    result *= i;
                    TimeUnit.MILLISECONDS.sleep(20);
                }
            }
            System.out.println("Result for number - " + number + " -> " + result);
            if(result>100){
                throw new IllegalStateException("ex from thread");
            }
            return String.valueOf(result);
        }
    }
}
