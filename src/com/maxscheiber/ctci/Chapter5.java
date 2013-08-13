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
	
	/**
	 * Write a function to determine the number of bits required to convert
	 * integer A to integer B. 5.5
	 * @param A starting int
	 * @param B ending int
	 * @return # of bits that must change to get from A to B
	 */
	public static int convert(int A, int B) {
		int C = A ^ B;
		int s = 0;
		while (C > 0) {
			s += C % 2;
			C >>= 1;
		}
		return s;
	}
	
	/**
	 * Write a program to swap odd and even bits in an integer with as few
	 * instructions as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and
	 * bit 3 are swapped, etc.). 5.6
	 * @param n number to swap bits on
	 * @return n with bits swapped as described
	 */
	public static int pairwiseSwap(int n) {
		int o = 0;
		for (int i = 0; i < 32; i += 2) {
			int t = (n & (3 << i)) >> i; // grab bits i and i+1
			n &= ~(3 << i); // clear bits i and i+1
			n |= ((t >> 1) << i);
			n |= (t & 1) << (i+1);
		}
		return n;
	}
}
