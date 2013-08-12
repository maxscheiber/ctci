package com.maxscheiber.ctci;

public class Chapter5 {
	/**
	 * You are given two 32-bit numbers, N and M, and two bit positions, i and
	 * j. Write a method to set all bits between i and j in N equal to M (e.g.,
	 * M becomes a substring of N located at i and starting at j). 5.1
	 * @param N number in which we're setting
	 * @param M substring we want to set
	 * @param i LSB
	 * @param j MSB
	 * @return N with M set at fields j to i
	 */
	public static int setBinarySubstring(int N, int M, int i, int j) {
		for (int k = i; k <= j; k++) {
			int b = (M >> k-i) & 1; // what to set the bit in N to
			N = N | (b << k); // sets the bit itself
		}
		
		return N;
	}
	
	/**
	 * Given a (decimal - e.g. 3.72) number that is passed in as a string, print
	 * the binary representation. If the number can not be represented
	 * accurately in binary, print "ERROR". 5.2
	 * @param n String to convert from decimal to binary
	 * @return converted String represntation of number in binary
	 */
	public static String parseDecimal(String n) {
		if (n == null) {
			return null;
		}
		
		String[] split = n.split("\\.");
		int pre;
		double post;
		
		if (split.length == 1) {
			try {
				pre = Integer.parseInt(n);
			} catch (NumberFormatException e) {
				return "ERROR";
			}
		} else if (split.length == 2) {
			try {
				pre = Integer.parseInt(split[0]);
			} catch (NumberFormatException e) {
				return "ERROR";
			}
		} else {
			return "ERROR";
		}
		
		String str = "";
		while (pre > 0) {
			str = pre % 2 + str; // copy pre-decimal point bits into string
			pre = pre >> 1;
		}
		
		if (split.length == 1) {
			return str; // no decimal point
		}
		
		try {
			post = Double.parseDouble("0." + split[1]);
		} catch (NumberFormatException e) {
			return "ERROR";
		}
		
		StringBuilder sb = new StringBuilder(".");
		while (post > 0) {
			if (sb.length() > 32) {
				return str + sb.toString();
			}
			if (post == 1) {
				sb.append((int)post);
				return str + sb.toString();
			}
			double d = post * 2;
			if (d >= 1) {
				sb.append(1);
				post = d - 1;
			} else {
				sb.append(0);
				post = d;
			}
		}
		
		return str + sb.toString();
	}
}
