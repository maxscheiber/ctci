package com.maxscheiber.ctci;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class Chapter5Test {
	@Test
	public void testSetBinarySubstring() {
		assertEquals("CTCI example", 1108, Chapter5.setBinarySubstring(1024, 21, 2, 6));
	}
	
	@Test
	public void testParseDecimal() {
		assertNull("Null string", Chapter5.parseDecimal(null));
		assertEquals("Integer", "101", Chapter5.parseDecimal("5"));
		assertEquals("Rational number", "11.10111000010100011110101110000101", 
				Chapter5.parseDecimal("3.72"));
	}
	
	@Test
	public void testConvert() {
		assertEquals(2, Chapter5.convert(31, 14));
	}
	
	@Test
	public void testPairwiseSwap() {
		assertEquals(0, Chapter5.pairwiseSwap(0));
		assertEquals(2, Chapter5.pairwiseSwap(1));
		assertEquals(3, Chapter5.pairwiseSwap(3));
		assertEquals(6, Chapter5.pairwiseSwap(9));
	}
}
