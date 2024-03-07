package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoJUnitTest {

	/*
	In TestDemoJUnitTest.java, add a private instance variable of type TestDemo named testDemo.  
	Remember that instance variables are non-static variables which are defined in a class, 
	but outside of any method, constructor or a block.
	*/
	  private TestDemo testDemo;

	  /*
	  a. In the setUp() method, create the TestDemo object. 
	  This will ensure that a new TestDemo object is created before each test.
	   */
	@BeforeEach
	void setUp() throws Exception {

		testDemo = new TestDemo();
	}

	/*
		b. Change "@Test" to "@ParameterizedTest". Add the import statement for org.junit.jupiter.params.ParameterizedTest
		c. Change the name of method "test" to "assertThatTwoPositiveNumbersAreAddedCorrectly".
		d. Add four parameters to assertThatTwoPositiveNumbersAreAddedCorrectly as shown:
		e. Write the test. 
			i.  Remove the "fail" line. 
			ii. Test the value of expectException. If it is false, assert that when TestDemo.addPositive is called 
			with values a and b, that the result is the same as the parameter expected. 
			iii. The assertion should look like this:
				if(!expectException) {
  					assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
				}
		f. Add the test for the thrown exception in an else clause. Use assertThatThrownBy for this. 
			Add the following static import:
			org.assertj.core.api.Assertions.assertThatThrownBy
		g. As a parameter to assertThatThrownBy, add a Lambda expression with no parameters. 
		The Lambda body should be the method call to testDemo.addPositive.
		h.  Use the assertion isInstanceOf(IllegalArgumentException.class) to ensure that the correct exception is thrown.
		i.        If this is too confusing, you can "cheat" and copy this:
			assertThatThrownBy(() ->
    			testDemo.addPositive(a, b))
        			.isInstanceOf(IllegalArgumentException.class);

			k.   Just below the @ParameterizedTest annotation, add the annotation @MethodSource. 
			Pass a single parameter to @MethodSource. It must be the fully-qualified (includes package) 
			class name of the test followed by a # sign followed by the name of the method that supplies the parameters. 
			Remember that the test is in the com.promineotech package. So, the package name needs to be in the following annotation:
				@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")

	 */
	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {

		if(!expectException) {
			  assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		}else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}	
	}
	
	/*
        j. Add the parameter source method.
        	i.  Create a static method named argumentsForAddPositive. It should not have any parameters 
        	and it should return a Stream of Arguments. The imports are: 
				java.util.stream.Stream
				org.junit.jupiter.params.provider.Arguments
			ii.     The method should return a Stream as in Stream.of();
			iii.   Each parameter set should be wrapped in an arguments() method call. Add the static import for arguments: 
				org.junit.jupiter.params.provider.Arguments.arguments
			iv.   So, if you are adding 2 and 4 to get the value of 6 and are not expecting an exception, you need to do:
				arguments(2, 4, 6, false)
			v. Add as many arguments lines as needed to test the addPositive method thoroughly. 
			Make sure to add some zero or negative arguments.
	*/
	static Stream<Arguments> argumentsForAddPositive(){
		return Stream.of(
                // Test Positives 
                Arguments.of(2, 4, 6, false),
                Arguments.of(5, 3, 8, false),
                // Test Zero or negatives
                Arguments.of(0, 5, 0, true),
                Arguments.of(-2, 3, 0, true),
                Arguments.of(2, -1, 0, true));
	}
	
	/*
		In TestDemoJUnitTest.java,  write a test for addPositive.  
		Create a method annotated with @Test named assertThatPairsOfPositiveNumbersAreAddedCorrectly. 
		The method must have package visibility (not public!) 
		or JUnit won't find it. The annotation @Test is in the org.junit.jupiter.api package.
		Use assertThat to test that the value returned from addPositive is equal to the correct value.
		assertThat(testDemo.addPositive(4,5)).isEqualTo(9);
			assertThat(testDemo.addPositive(40,50)).isEqualTo(90);
	*/
	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
	    assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
	    assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
	    assertThat(testDemo.addPositive(25, 75)).isEqualTo(100);
	    // Add more assertions as needed
	}
	
	/*
		2.  Write a test in TestDemoJUnitTest.java and see 
			if you can figure out how to test your new functionality!  Options:  
			This test can use the @Test annotation, or create a parameterized test using the @ParameterizedTest annotation.
	*/
    static Stream<Arguments> argumentsForIsEven() {
        return Stream.of(
                Arguments.of(4, true),
                Arguments.of(5, false),
                Arguments.of(0, true) // Add test for zero
        );
    }
    @ParameterizedTest
    @MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForIsEven")
    void assertThatIsEven(int number, boolean expected) {
        if (expected) {
            assertTrue(testDemo.isEven(number));
        } else {
            assertFalse(testDemo.isEven(number));
        }
    }
    
    /*
		a.      Create a method annotated with @Test named assertThatNumberSquaredIsCorrect. 
		The method must have package visibility (not public!) or JUnit won't find it. T
		he annotation @Test is in the following location:
			org.junit.jupiter.api package
		b.      To mock the TestDemo class, use Mockito.spy. The spy method can be imported with a static 
		import of org.mockito.Mockito.spy.
			TestDemo mockDemo = spy(testDemo);
		c.       Program the mocked TestDemo object to return 5 when the getRandomInt method is called. Remember 
		to use the form: doReturn(aValue).when(mockedObject).methodCall(). You can use a static import for 
		doReturn: import static org.mockito.Mockito.doReturn;
			doReturn(5).when(mockDemo).getRandomInt();
		d.      Call the method randomNumberSquared on the mocked TestDemo object. 
		This will call the stubbed out (mocked) method getRandomInt, which now should return the value 5.
			int fiveSquared = mockDemo.randomNumberSquared();
		e.      Use assertThat to test that the value returned from randomNumberSquared is equal to 5 squared.
			assertThat(fiveSquared).isEqualTo(25);
		f.        You don't need to verify the mocked method call – you know it was called since the return value is correct.
	*/
    @Test
    void assertThatNumberSquaredIsCorrect() {
    	
    	TestDemo mockDemo = spy(testDemo);
    	doReturn(5).when(mockDemo).getRandomInt();
    	int fiveSquared  = mockDemo.randomNumberSquared();
    	assertThat(fiveSquared).isEqualTo(25);
    }
	

}//TestDemoJUnitTest
