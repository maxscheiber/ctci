package com.maxscheiber.ctci;

import java.util.HashSet;
import java.util.Set;

public class Chapter1 {
	/**
	 * Implement an algorithm to determine if a string has all unique 
	 * characters. 1.1
	 * @param in inputted string to check
	 * @return <code>true</code> if <code>in</code> has all unique characters, 
	 * <code>false</code> otherwise
	 */
	public static boolean hasUniqueChars(String in) {
		if (in == null) {
			return false;
		}
		Set<Character> chars = new HashSet<Character>();
		for (int i = 0; i < in.length(); i++) {
			Character c = in.charAt(i);
			if (chars.contains(c)) {
				return false;
			}
			chars.add(c);
		}
		return true;
	}
	
	/**
	 * What if you cannot use additional data structures? 1.1
	 * @param in inputted string to check
	 * @return <code>true</code> if <code>in</code> has all unique characters, 
	 * <code>false</code> otherwise
	 */
	public static boolean hasUniqueChars2(String in) {
		if (in == null) {
			return false;
		}
		
		/*
		 * We could hypothetically sort the string in-place and check if
		 * in.charAt(i) == in.charAt(i+1) for all i. This would give O(nlogn),
		 * which beats the brute force O(n^2) solution.
		 */
		for (int i = 0; i < in.length(); i++) {
			for (int j = i+1; j < in.length(); j++) {
				if (in.charAt(i) == in.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Write a method to decide if two strings are anagrams or not. 1.4
	 * @param one first anagram contendor
	 * @param two second anagram contendor
	 * @return <code>true</code> if <code>one</code> and <code>two</code> are
	 * anagrams, <code>false</code> otherwise
	 */
	public static boolean areAnagrams(String one, String two) {
		return isStringSubset(one, two) && isStringSubset(two, one);
	}
	
	/**
	 * Returns <code>true</code> if the <code>Character</code>s in
	 * <code>one</code> compose a subset of the <code>Character</code>s in
	 * <code>two</code>, <code>false</code> otherwise
	 * @param one <code>String</code> to check if is a subset
	 * @param two <code>String</code> that may be a superset
	 * @return <code>true</code> if the <code>Character</code>s in
	 * <code>one</code> compose a subset of the <code>Character</code>s in
	 * <code>two</code>, <code>false</code> otherwise
	 */
	private static boolean isStringSubset(String one, String two) {
		if (one == null || two == null) {
			return false;
		}
		
		// anagrams, by definition, have the same length
		if (one.length() != two.length()) {
			return false;
		}
		
		// all chars that String two has
		Set<Character> chars = new HashSet<Character>();
		for (int i = 0; i < two.length(); i++) {
			Character c = two.charAt(i);
			chars.add(c);
		}
		
		for (int i = 0; i < one.length(); i++) {
			Character c = one.charAt(i);
			// all one's chars must be in two for it to be a subset
			if (!chars.contains(c)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Write a method to replace all spaces in a string with Ô%20Õ. 1.5
	 * @param in <code>String</code> to sanitize
	 * @return sanitized <code>String</code>
	 */
	public static String sanitizeSpaces(String in) {
		if (in == null) {
			return null;
		}
		/* Java libraries, not even once. In real life, I'd just use sed,
		 * maybe :%s/ /%20/g if scripting in vim.
		 */
		return in.replace(" ", "%20");
	}
	
	/**
	 * Given an image represented by an NxN matrix, where each pixel in the 
	 * image is 4 bytes, write a method to rotate the image by 90 degrees.
	 * @param in two-dimensional <code>int</code> array to rotate clockwise
	 * @return clockwise rotated matrix
	 */
	public static int[][] rotateMatrix(int[][] in) {
		if (in == null) {
			return null;
		}
		
		final int N = in.length; // NxN matrix
		int[][] out = new int[N][N];
		
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				out[y][N-1-x] = in[x][y];
			}
		}
		return out;
	}
	
	/**
	 * Write an algorithm such that if an element in an MxN matrix is 0, its 
	 * entire row and column is set to 0. 1.7
	 * @param in matrix to zero out
	 * @return matrix with rows and columns zeroed as appropriate
	 */
	public static int[][] zeroifyMatrix(int[][] in) {
		if (in == null) {
			return null;
		}
		
		if (in.length == 0) {
			return in;
		}
		
		final int M = in.length;
		final int N = in[0].length;
		Set<Integer> rows = new HashSet<Integer>();
		Set<Integer> cols = new HashSet<Integer>();
		
		// O(n^2) space, O(n) time
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (in[i][j] == 0) {
					rows.add(i);
					cols.add(j);
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (rows.contains(i) || cols.contains(j)) {
					in[i][j] = 0;
				}
			}
		}
		
		return in;
	}
	
	/**
	 * Assume you have a method isSubstring which checks if one word is a 
	 * substring of another. Given two strings, s1 and s2, write code to check
	 * if s2 is a rotation of s1 using only one call to isSubstring (i.e., 
	 * ÒwaterbottleÓ is a rotation of ÒerbottlewatÓ). 1.8
	 * 
	 * Note that we use contains in lieu of "isSubstring."
	 * @param s1 
	 * @param s2
	 * @return <code>true</code> if <code>s2</code> is a rotation of
	 * <code>s1</code>, <code>false</code> otherwise
	 */
	public static boolean isRotation(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return false;
		}
		return (s1 + s1).contains(s2) && s1.length() == s2.length();
	}
}
