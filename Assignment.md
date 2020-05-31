1. SQL:

a>​ ​Write an SQL query to find nth largest salary along with employee name.
Ans- SELECT e.name, s1.salary FROM Salary s1, Employee e WHERE N-1 = (SELECT COUNT(DISTINCT s1.salary) FROM Salary s2 WHERE s2.salary > s1.salary) and s1.EmpID = e.EmpID;

b> Write a query to update salary of employees to 5000 whose age is 30+
Ans- UPDATE Salary SET Salary = 5000 WHERE EmpID =( Select EmpID from Employee where (TIMESTAMPDIFF(YEAR, Date_of_birth, CURDATE()))>30);


2. Test Scenarios:

Write all the possible test scenarios ​“To applies Promo code”​. Promo code will give a discount of 30% (on the total billing amount) to the new user once applied on a minimum transaction of Rs. 1000 and above. Below are some specifications for the promo code:
Promo:​ NEW30 Character limit:​ 7 Maximum cap: ​Rs.​ ​300
* Make any possible assumptions if required. Do mention those assumptions (if made any) before writing the test scenarios.
** Write all positive, negative and edge case scenarios.


assumptions:
1. User is newly registred from mobile app.
2. or user is newly registred from web app.


Test cases:
1. User applies the promo code "NEW30" for purchase of Rs. 1000, should get a discount of Rs. 300
2. User applies the promo code "NEW30" for purchase of Rs. 10000, should get a discount of Rs. 300 not Rs. 3000.
3. User applies the promo code "NEW30" for purchase of Rs. 999.99, should not get any discount with the promo code applied.
4. User applies the promo code "NEW30" for purchase of Rs. 1001, should get a discount of Rs. 300
5. User applies the promo code "NEW30" for purchase of Rs. 1000 and above for first 7th time, should get a discount of Rs. 300
6. User applies the promo code "NEW30" for purchase of Rs. 1000 and above for 8th time, should not get a discount of Rs. 300
7. User logged in from web app and try to apply the promo code "NEW30" for purchase of Rs. 1000 for 1st time to 7th time, should get a discount of Rs. 300
8. User trying to apply the promo code from both mobile  and web for 7 times, should get a discount of Rs. 300 (every time he applies code and proceed should considered and a 1 transaction)
9. 7th time user try to login from multiple devices and try to applies the promo simultaniously. Only once user should get a discount of Rs. 300
10. User applied the promo and transaction got failed, that should not be considered as a promo code transaction. and user should able reapplies the code.
11. user try to use the promo code for different types of bills, it should work for all if code is correct and limit is not crossed.
12. 

assumptions: 
2. user 
Test cases: 
1. try to create account from diffrent mobile no and email id combinations




4) Coding:

> Write a program to find the first 100 Prime numbers. Write in either Java or Node.

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


> Now write all possible test cases to test your program. (Manual test cases: covering both positive and negative scenarios)

1. count the numbers, it should be 100.
2. validate if all the nos are prime or not.
3. check if 0 or 1 is not added.
4. check if 2 is the 1st prime no or not.
5. check if 100th prime no is 541.