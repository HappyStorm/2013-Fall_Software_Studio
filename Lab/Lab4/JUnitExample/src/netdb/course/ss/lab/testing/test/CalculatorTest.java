package netdb.course.ss.lab.testing.test;

import org.junit.Assert;
import netdb.course.ss.lab.testing.Calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	private Calculator calculator;
	
	@Before
	public void setUp(){
		calculator = new Calculator();
	}
	
	@After
	public void tearDown(){
		calculator = null;
	}
	
	@Test
	public void calculatorShouldPlusCorrectly(){
		int result = calculator.plus(10, 5);
		Assert.assertEquals(15, result);
	}
	
	@Test
	public void calculatorShouldMinusCorrectly(){
		int result = calculator.minus(10, 5);
		Assert.assertEquals(5, result);
	}
	
}
