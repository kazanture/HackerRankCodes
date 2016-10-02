import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ebayram on 1.10.2016.
 */
@SuppressWarnings("Duplicates")
public class ProjectEuler12 {


    public void run() throws IOException {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTestCases = Integer.parseInt(bi.readLine());
        StringBuffer result=new StringBuffer();
        for (int i = 0; i < numberOfTestCases; i++) {
            int currentNumber = Integer.parseInt(bi.readLine());
            int largestPowerOf2=largestPowerOf2(currentNumber);
            Long triangleStart=minimumNumbersWithMaximumDivisorCounts.get(new Long(largestPowerOf2));
            long index=(long)Math.sqrt(2*(double)triangleStart);
            long triangleIndex=index;
            long triangleNumber=(long)((double)index*(double)(index+1)/2.0);
            long divisorCount=getDivisorCount(triangleNumber,++triangleIndex-1);
            while(divisorCount<=currentNumber){
                triangleNumber=triangleNumber+triangleIndex++;
                divisorCount=getDivisorCount(triangleNumber);
            }
            result.append(triangleNumber).append("\n");

        }
        System.out.print(result);


    }
    static int largestPowerOf2 (long n)
    {
        int res = 2;
        while (res < n) {
            res *= 2;
        }
        if(res>n){
            res=res/2;
        }
        return res;
    }
    List<Long> primeNumbers = new ArrayList<>();
    Map<Long,Long> minimumNumbersWithMaximumDivisorCounts = new HashMap<>();

    long getNthPrime(int n) {
        long biggestPrime = primeNumbers.get(primeNumbers.size() - 1);
        long candidate=biggestPrime+1;
        while (primeNumbers.size() < n) {

            if (isPrime(candidate)) {
                primeNumbers.add(candidate);
            }
            candidate +=1;
        }
        return primeNumbers.get(n-1);

    }

    long getNthTriangleNumber(long n){

        return n*(n+1)/2;
    }
    Map<Long,Long> divisorCountMap=new HashMap<>();
    long getDivisorCount(long n){
        if(n==1)
        {
            return 1;
        }
        if(isPrime(n)){
            return 2;
        }
        Long divisorCount=divisorCountMap.get(n);
        if(divisorCount==null) {
            divisorCount=new Long(1);
            int index=1;
            long nClone=n;
            while(nClone!=1) {
                long prime = getNthPrime(index);

                long currentCount = 0;
                while (nClone % prime == 0) {
                    nClone = nClone / prime;
                    currentCount++;
                }
                divisorCount*=(currentCount+1);
                index++;
            }
            divisorCountMap.put(n,divisorCount);
        }

        return  divisorCount;
    }
    long getDivisorCount(long n,long div){
        if(div%2==0){
            return  getDivisorCount(div/2)*getDivisorCount(div+1);
        }else{
            return  getDivisorCount(div)*getDivisorCount((div+1)/2);
        }
    }

    boolean isPrime(long primeCandidate) {
        BigInteger candidate=BigInteger.valueOf(primeCandidate);
        if (!candidate.isProbablePrime(3)) {
            return false;
        } else {
            int n = 1;
            long nthPrime = getNthPrime(n++);
            long sqrt=(long)Math.sqrt(primeCandidate);
            while (nthPrime<sqrt) {
                if (primeCandidate%nthPrime == 0) {
                    return false;
                }
                nthPrime = getNthPrime(n++);
            }
            return true;
        }
    }
    void main() throws IOException {
        init();
        run();
    }

    private void init() {
        minimumNumbersWithMaximumDivisorCounts.put(1L,3L);
        minimumNumbersWithMaximumDivisorCounts.put(2L,6L);
        minimumNumbersWithMaximumDivisorCounts.put(4L,28L);
        minimumNumbersWithMaximumDivisorCounts.put(8L,36L);
        minimumNumbersWithMaximumDivisorCounts.put(16L,300L);
        minimumNumbersWithMaximumDivisorCounts.put(32L,2016L);
        minimumNumbersWithMaximumDivisorCounts.put(64L,25200L);
        minimumNumbersWithMaximumDivisorCounts.put(128L,437580L);
        minimumNumbersWithMaximumDivisorCounts.put(256L,2162160L);
        minimumNumbersWithMaximumDivisorCounts.put(512L,76576500L);

        primeNumbers.add(2L);
        primeNumbers.add(3L);
        primeNumbers.add(5L);
        primeNumbers.add(7L);
        primeNumbers.add(11L);
        primeNumbers.add(13L);
        primeNumbers.add(17L);
        primeNumbers.add(19L);
        primeNumbers.add(23L);
        primeNumbers.add(29L);
    }

    public static void main(String[] args) throws IOException {
        new ProjectEuler12().main();
    }
}
