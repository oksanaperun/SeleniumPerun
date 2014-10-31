package lesson2;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by c2411 on 20.10.2014.
 */
public class PrimeNumbers {

    @Test
    public void isPrimeTest(){
        Assert.assertEquals(isNumberPrime(5), true);
    }

    public static boolean isNumberPrime(int number) {
        if (number == 0 || number == 1)
            return false;
        for (int i = 2; i < number / 2; i++)
            if (number % i == 0)
                return false;
        return true;
    }

//    public static void main(String[] args) {
//            int number = 23;
//            if (isNumberPrime(number))
//                System.out.println("Number " + number + " is prime.");
//            else System.out.println("Number " + number + " is not prime.");
//
//    }
}
