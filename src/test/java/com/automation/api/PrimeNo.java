package com.automation.api;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class PrimeNo {
    /**
     * check if the number is prime
     *
     * @param n
     * @return
     */
    public boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * finding first n numbers which are prime
     *
     * @param n
     * @return
     */
    public List firstNPrimeNos(int n) {
        int i = 1, count = 0;
        List primeNos = new ArrayList<>();
        while (count < n+1) {
            if (isPrime(i)) {
                System.out.println(i);
                primeNos.add(i);
                count++;
            }
            i++;
        }
        return primeNos;
    }

    @Test
    public void testPrime() {
        firstNPrimeNos(100);
    }

}
