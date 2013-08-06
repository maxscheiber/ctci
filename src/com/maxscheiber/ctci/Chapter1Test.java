package com.maxscheiber.ctci;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Chapter1Test {
	@Test
	public void testHasUniqueChars() {
		assertFalse("Null string", Chapter1.hasUniqueChars(null));
		assertTrue("Empty string", Chapter1.hasUniqueChars(""));
		assertTrue("One-character string", Chapter1.hasUniqueChars("a"));
		assertTrue("Simple unique string", Chapter1.hasUniqueChars("abc"));
		assertFalse("Simple non-unique string", Chapter1.hasUniqueChars("aba"));
	}
	
	@Test
	public void testHasUniqueChars2() {
		assertFalse("Null string", Chapter1.hasUniqueChars(null));
		assertTrue("Empty string", Chapter1.hasUniqueChars2(""));
		assertTrue("One-character string", Chapter1.hasUniqueChars2("a"));
		assertTrue("Simple unique string", Chapter1.hasUniqueChars2("abc"));
		assertFalse("Simple non-unique string", Chapter1.hasUniqueChars2("aba"));
	}
	
	@Test
	public void testAreAnagrams() {
		assertFalse("Null string one", Chapter1.areAnagrams(null, null));
		assertFalse("Null string one", Chapter1.areAnagrams(null, ""));
		assertTrue("Both empty strings", Chapter1.areAnagrams("", ""));
		assertFalse("Empty string one", Chapter1.areAnagrams("", "a"));
		assertFalse("Empty string two", Chapter1.areAnagrams("a", ""));
		assertTrue("Simple anagrams", Chapter1.areAnagrams("sam", "asm"));
		assertFalse("Same-length non-anagrams", Chapter1.areAnagrams("sam", "max"));
		assertFalse("Different-length non-anagrams", Chapter1.areAnagrams("sam", "spasm"));
	}
	
	@Test
	public void testSanitizeString() {
		assertNull("Null string", Chapter1.sanitizeSpaces(null));
		assertEquals("Empty string", "", Chapter1.sanitizeSpaces(""));
		assertEquals("One space", "%20", Chapter1.sanitizeSpaces(" "));
		assertEquals("No spaces", "abc", Chapter1.sanitizeSpaces("abc"));
		assertEquals("Border spaces", "%20abc%20", Chapter1.sanitizeSpaces(" abc "));
		assertEquals("Middle space", "a%20b", Chapter1.sanitizeSpaces("a b"));
	}
	
	@Test
	public void testRotateMatrix() {
		assertNull("Null matrix", Chapter1.rotateMatrix(null));
		assertEquals("Empty matrix", new int[0][0], Chapter1.rotateMatrix(new int[0][0]));
		
		// 1x1 matrix
		int[][] matrix = new int[1][1];
		matrix[0][0] = 0;
		int[][] rotated = Chapter1.rotateMatrix(matrix);
		assertEquals("1x1 matrix at (0,0)", 0, rotated[0][0]);
		
		// 2x2 matrix
		matrix = new int[2][2];
		matrix[0][0] = 1;
		matrix[0][1] = 2;
		matrix[1][0] = 3;
		matrix[1][1] = 4;
		rotated = Chapter1.rotateMatrix(matrix);
		assertEquals("2x2 matrix at (0,0)", 3, rotated[0][0]);
		assertEquals("2x2 matrix at (0,1)", 1, rotated[0][1]);
		assertEquals("2x2 matrix at (1,0)", 4, rotated[1][0]);
		assertEquals("2x2 matrix at (1,1)", 2, rotated[1][1]);
	}
	
	@Test
	public void testZeroifyMatrix() {
		assertNull("Null matrix", Chapter1.zeroifyMatrix(null));
		assertEquals("Empty matrix", new int[0][0], Chapter1.zeroifyMatrix(new int[0][0]));
		
		// 1x1 matrix
		int[][] matrix = new int[1][1];
		matrix[0][0] = 0;
		int[][] zeroed = Chapter1.zeroifyMatrix(matrix);
		assertEquals("1x1 matrix at (0,0)", 0, zeroed[0][0]);
		
		// 2x2 matrix, one zero
		matrix = new int[2][2];
		matrix[0][0] = 0;
		matrix[0][1] = 1;
		matrix[1][0] = 1;
		matrix[1][1] = 1;
		zeroed = Chapter1.zeroifyMatrix(matrix);
		assertEquals("2x2 matrix, one zero, at (0,0)", 0, zeroed[0][0]);
		assertEquals("2x2 matrix, one zero, at (0,1)", 0, zeroed[0][1]);
		assertEquals("2x2 matrix, one zero, at (1,0)", 0, zeroed[1][0]);
		assertEquals("2x2 matrix, one zero, at (1,1)", 1, zeroed[1][1]);
		
		// 2x2 matrix, one zero
		matrix = new int[2][2];
		matrix[0][0] = 0;
		matrix[0][1] = 1;
		matrix[1][0] = 1;
		matrix[1][1] = 0;
		zeroed = Chapter1.zeroifyMatrix(matrix);
		assertEquals("2x2 matrix, one zero, at (0,0)", 0, zeroed[0][0]);
		assertEquals("2x2 matrix, one zero, at (0,1)", 0, zeroed[0][1]);
		assertEquals("2x2 matrix, one zero, at (1,0)", 0, zeroed[1][0]);
		assertEquals("2x2 matrix, one zero, at (1,1)", 0, zeroed[1][1]);
	}
	
	@Test
	public void testIsRotation() {
		assertFalse("Null string s1", Chapter1.isRotation(null, ""));
		assertFalse("Null string s2", Chapter1.isRotation("", null));
		assertTrue("Two empty strings", Chapter1.isRotation("", ""));
		assertTrue("Rotation", Chapter1.isRotation("max", "axm"));
		assertFalse("Different lengths", Chapter1.isRotation("max", "ax"));
		assertFalse("Non-rotation", Chapter1.isRotation("max", "lxa"));
	}
}
