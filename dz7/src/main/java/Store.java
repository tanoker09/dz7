import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

public class Store {
    private ConcurrentHashMap<Integer, BigInteger> store;
    public Store(){
        store = new ConcurrentHashMap<>();
    }

    public void put(Integer key, BigInteger value){
        System.out.println("Put key =" + key + " in map");
        store.putIfAbsent(key, value);
    }

    public BigInteger get(int key){
        return store.get(key);
    }
}
