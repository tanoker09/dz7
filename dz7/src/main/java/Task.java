import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Task {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> nums = generateNums(100000);
        int numThreads = 4;
        int bucketsN = nums.size() / numThreads;
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(bucketsN);
        Store store = new Store();

        List<FactorialCalcer> tasks = new ArrayList<>();
        for (int i = 0; i < numThreads; i++)
        {
            int begin = i * bucketsN;
            int end = (i + 1) * bucketsN;
            if(i == numThreads-1)
                end = nums.size();

            FactorialCalcer calcer = new FactorialCalcer("Thread#" + i,  store, new ArrayList<Integer>(nums.subList(begin, end)));
            System.out.println("Created : " + calcer.getName());

            tasks.add(calcer);
        }
        Instant start = Instant.now();
        List<Future<List<BigInteger>>> futures= executor.invokeAll(tasks);
        executor.shutdown();
        Instant end = Instant.now();
        final List<BigInteger> out = new ArrayList<>();
        futures.forEach(f-> {
            try {
                out.addAll(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println(out.toString());
        System.out.println(Duration.between(start, end));
    }

    public static ArrayList<Integer> generateNums(int n){
        Random rd = new Random();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(rd.nextInt(100));
        }
        System.out.println(arr.toString());
        return  arr;
    }

}
