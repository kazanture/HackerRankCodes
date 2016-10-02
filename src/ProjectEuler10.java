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
public class ProjectEuler10 {


    public void run() throws IOException {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTestCases = Integer.parseInt(bi.readLine());
        StringBuffer result=new StringBuffer();
        findPrimeNumbersSumUntilIndex(1000);
        for (int i = 0; i < numberOfTestCases; i++) {
            int currentNumber = Integer.parseInt(bi.readLine());
            int largestIndex= findLargestPrimeNumberIndexLessThanOrEqual(currentNumber);
            long prime=getNthPrime(largestIndex++);
            long currentResult=0;
            while(prime<=currentNumber){
                currentResult=findPrimeNumbersSumUntilIndex(largestIndex);
                prime=getNthPrime(largestIndex++);
            }
            result.append(currentResult).append("\n");

        }
        System.out.print(result);


    }
    private long findPrimeNumbersSumUntilIndex(int maxPrimeIndex){
        int currentMaxIndex=totalUntilIndex.size()-1;
        long currentSum=totalUntilIndex.get(currentMaxIndex++);
        if(maxPrimeIndex==currentMaxIndex){
            return currentSum;
        }
        while(currentMaxIndex<maxPrimeIndex){
            currentSum+=getNthPrime(currentMaxIndex++);
            totalUntilIndex.add(currentSum);
        }
        return totalUntilIndex.get(maxPrimeIndex-1);

    }
    private int findLargestPrimeNumberIndexLessThanOrEqual(int currentNumber) {
        int index=primeNumbers.size()-1;
        long largest=primeNumbers.get(index--);
        while(largest>currentNumber){
            largest=primeNumbers.get(index--);
        }
        return index+1;
    }

    ArrayList<Long> totalUntilIndex=new ArrayList<>();
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


    long getNthPrime(int n) {
        long biggestPrime = primeNumbers.get(primeNumbers.size() - 1);
        long candidate=biggestPrime+1;
        while (primeNumbers.size() < n+1) {

            if (isPrime(candidate)) {
                primeNumbers.add(candidate);
            }
            candidate +=1;
        }
        return primeNumbers.get(n);

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

        totalUntilIndex.add(2L);
        totalUntilIndex.add(5L);
        totalUntilIndex.add(10L);
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
        new ProjectEuler10().main();
    }
}
