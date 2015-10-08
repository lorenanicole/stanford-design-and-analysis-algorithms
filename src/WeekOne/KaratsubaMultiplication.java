package WeekOne;

import static java.lang.Math.max;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by lorenamesa on 10/6/15.
 */
public class KaratsubaMultiplication {

    public KaratsubaMultiplication() { }

    public Integer multiply(Integer numOne, Integer numTwo) {
        // Algorithm: http://people.cs.uchicago.edu/~laci/HANDOUTS/karatsuba.pdf
        final String numTwoString = numTwo.toString();
        final String numOneString = numOne.toString();

        Integer n = max(numOneString.length(), numTwoString.length());

        if (n <= 1) {
            return numOne * numTwo; // Step 1: Base Case
        }

        // Step 2
        String paddedNumOne = padZeros(numOneString, n);
        String paddedNumTwo = padZeros(numTwoString, n);

        if (n % 2 != 0) {
            n += 1; // (Step 5) X1, X2, Y1, Y2 each have n/2 digits.
        }

        Integer nTwo = n / 2;

        List<Integer> abDigits = computeHalves(paddedNumOne, nTwo); // Step 3
        List<Integer> cdDigits = computeHalves(paddedNumTwo, nTwo); // Step 4

        Integer a = abDigits.get(0);
        Integer b = abDigits.get(1);
        Integer c = cdDigits.get(0);
        Integer d = cdDigits.get(1);

        Integer ac = multiply(a, c); // Step 6
        Integer bd = multiply(b, d); // Step 7

        Integer abCD = multiply(a+b, c+d) - ac - bd; // Step 8 & 9

        return ((int) Math.pow(10,n) * ac) + ((int) Math.pow(10,nTwo) * abCD) + bd; // Step 10

    }

    private String padZeros(String paddedNumber, Integer n) {
        if (paddedNumber.length() < n) {
            Integer numZeros = n - paddedNumber.length();
            StringBuilder sb = new StringBuilder(paddedNumber);
            return sb.insert(0, String.join("", Collections.nCopies(numZeros, "0"))).toString();
        }
        return paddedNumber;
    }

    private List<Integer> computeHalves(String paddedNum, Integer nTwo) {
        Integer one = Integer.valueOf(paddedNum) / ((int) Math.pow(10, nTwo));
        Integer two = Integer.valueOf(paddedNum) % ((int) Math.pow(10, nTwo));
        return Arrays.asList(one, two);
    }

    public static void main(String[] args) {
        KaratsubaMultiplication karatsuba = new KaratsubaMultiplication();
        System.out.println(karatsuba.multiply(12, 51) == 612);
        System.out.println(karatsuba.multiply(1234,5678) == 7006652);
        System.out.println(karatsuba.multiply(12344,56788) == 700991072);
    }
}
