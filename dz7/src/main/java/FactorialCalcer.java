import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
*Класс для расчета массива факториалов
*/

public class FactorialCalcer implements Callable<List<BigInteger>>, Factoriable{

    String name;
    Store store;
    ArrayList<Integer> nums;

    public FactorialCalcer(Store store){
        this.store = store;
    }

    public FactorialCalcer(Store store, ArrayList<Integer> nums){
        this.store = store;
        this.nums = nums;
    }

    public FactorialCalcer(String name, Store store, ArrayList<Integer> nums){
        this.name= name;
        this.store = store;
        this.nums = nums;
    }

    @Override
    public List<BigInteger> call() {
        ArrayList<BigInteger> out = new ArrayList<>();
        for(Integer num : nums){
            BigInteger res = calculateFactorial(num, store);
            out.add(res);
        }
        return out;
    }

    public String getName() {
        return name;
    }
}
