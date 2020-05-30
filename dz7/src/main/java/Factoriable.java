import java.math.BigInteger;

public interface Factoriable {

    default BigInteger calculateFactorial(int n, Store store){
        BigInteger result = BigInteger.valueOf(1);
        for (int i = n; i > 0; i--){
            if(!(store == null)){
                BigInteger num = store.get(i);
                if(num == null){
                    result = result.multiply(BigInteger.valueOf(i));
                }
                else{
                    result = result.multiply(num);
                    break;
                }
            }
            else{
                result = result.multiply(BigInteger.valueOf(i));
            }
        }

        if(!(store == null)){
            store.put(n, result);
        }
        return result;
    }
}
