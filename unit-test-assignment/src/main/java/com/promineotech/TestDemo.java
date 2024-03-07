package com.promineotech;

import java.util.Random;

public class TestDemo {

	public int addPositive(int a, int b) {
		
		/*
			a. Create an instance method (not static) named addPositive. It should take two int parameters and return an int.
				public int addPositive(int a, int b) {}
			b.If both parameters are positive (greater than zero) return the sum of the parameters. 
			If either parameter is zero or negative, 
			throw an IllegalArgumentException with the message "Both parameters must be positive!". 
			IllegalArgumentException is in the java.lang package so you won't need an import statement.
		*/
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Both parameters must be positive!");
        }
        return a + b;
	}
	
	
	//1.  Write a method in TestDemo.java -- any method is fine!
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
	
    /*
    	a.      randomNumberSquared should return an int and not take any parameters.
    	b.      It should call another method in the same class named getRandomInt. This method takes no parameters and must be package visibility so that the test can see it. getRandomInt should look like this:
    	
    		int getRandomInt() {
    		    Random random = new Random();
    		    return random.nextInt(10) + 1;
    		}
    	The Random class is in the java.util package.
     */
    public int randomNumberSquared() {
        int number = getRandomInt();
        return number * number;
      }

    int getRandomInt() {
    	Random random = new Random();
    	return random.nextInt(10) + 1;
    }
	
    

	
}
