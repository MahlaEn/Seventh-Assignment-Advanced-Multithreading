package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.concurrent.*;

public class PiCalculator {
    public static BigDecimal ans=new BigDecimal("0.0");
    public static class CalcPi implements Runnable{
        @Override
        public void run() {
            for(int k=0;k<10000;k++){
                BigDecimal sum=new BigDecimal("0.0");
                sum=sum.add(BigDecimal.valueOf(1.0 * 4/(8*k+1)));
                sum=sum.subtract(BigDecimal.valueOf(1.0 * 2/(8*k+4)));
                sum=sum.subtract(BigDecimal.valueOf(1.0 * 1/(8*k+5)));
                sum=sum.subtract(BigDecimal.valueOf(1.0 * 1/(8*k+6)));
                sum=sum.divide(BigDecimal.valueOf(Math.pow(16,k)), 10000, RoundingMode.HALF_EVEN);
                Add(sum);
            }
        }
        public static synchronized void Add(BigDecimal num){
            ans=ans.add(num);
        }
    }
    public String calculate(int floatingPoint){
        ExecutorService executor= Executors.newCachedThreadPool();
        Thread task=new Thread(new CalcPi());
        executor.execute(task);
        executor.shutdown();
        try {
            executor.awaitTermination(10000, TimeUnit.MILLISECONDS);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        ans = ans.setScale(floatingPoint, RoundingMode.HALF_DOWN);
        return ans.toString();
    }

    public static void main(String[] args){
        PiCalculator calc=new PiCalculator();
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        String ans=calc.calculate(n);
        System.out.println(ans);
    }
}
