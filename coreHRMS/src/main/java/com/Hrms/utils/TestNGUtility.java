package com.Hrms.utils;



import org.testng.Assert;



public class TestNGUtility  {

	public static void assertTrue(String expected, String actual) {
		if (actual.contains(expected)) {
			Assert.assertTrue(actual.contains(expected));
		
		} else {
		
			System.out.println(expected+" not present in "+actual);
		}
	}

	public static void assertEquals(int expected, int actual, String logMessage) {
		if (actual == expected) {
			Assert.assertEquals(actual, expected);
	
		} else {
		
			System.out.println(expected+" is not matched with "+actual);
		}
	}
	// Fails the test and logs a failure message
	public static void assertFail(String logMessage) {

		Assert.fail(logMessage);
	}

	
	
}
