package com.maxscheiber.ctci;

public class Chapter4 {
	public static class BST {
		private int v;
		private BST l, r;
		
		public BST(int v) {
			this.v = v;
		}
		
		private BST(BST l, int v, BST r) {
			this.l = l;
			this.v = v;
			this.r = r;
		}
		
		public BST l() {
			return l;
		}
		
		public int v() {
			return v;
		}
		
		public BST r() {
			return r;
		}
	}
	
	/**
	 * Determine if a value exists in a binary search tree
	 * @param x value to check for existence of
	 * @param t BST in which to check
	 * @return true if x is in t, false otherwise
	 */
	public static boolean exists(int x, BST t) {
		if (t == null) {
			return false;
		} else if (x < t.v) {
			return exists(x, t.l);
		} else if (x > t.v) {
			return exists(x, t.r);
		} else {
			return true;
		}
	}
	
	/**
	 * Insert into a binary search tree
	 * @param x value to add
	 * @param t tree to add to
	 * @return BST with added node
	 */
	public static BST insert(int x, BST t) {
		if (t == null) {
			return new BST(null, x, null);
		}
		
		if (x < t.v) {
			return new BST(insert(x, t.l), t.v, t.r);
		} else if (x > t.v) {
			return new BST(t.l, t.v, insert(x, t.r));
		} else {
			return t;
		}
	}
	 /**
	  * Delete from a binary search tree
	  * @param x value to delete
	  * @param t tree from which to delete
	  * @return BST with deleted node
	  */
	public static BST delete(int x, BST t) {
		if (t == null) {
			return null;
		}
		
		if (x < t.v) {
			return new BST(delete(x, t.l), t.v, t.r);
		} else if (x > t.v) {
			return new BST(t.l, t.v, delete(x, t.r));
		} else if (t.l == null && t.r == null) {
			return null;
		} else if (t.l == null) {
			return t.r;
		} else if (t.r == null) {
			return t.l;
		} else {
			int min = min(t.r);
			t.r = delete(min, t.r);
			t.v = min;
			return t;
		}
	}
	
	/**
	 * Grabs the smallest element in a binary search tree
	 * @param t tree in which to search for the min
	 * @return min of the tree
	 */
	private static int min(BST t) {
		while (t.l != null) t = t.l;
		return t.v;
	}
}
