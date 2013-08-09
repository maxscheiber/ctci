package com.maxscheiber.ctci;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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
	
	public static int[] inorder(BST t) {
		if (t == null) {
			return new int[0];
		} else if (isLeaf(t)) {
			return new int[]{t.v};
		}
		int[] l = inorder(t.l);
		int[] r = inorder(t.r);
		int[] out = new int[l.length + 1 + r.length];
		System.arraycopy(l, 0, out, 0, l.length);
		out[l.length] = t.v;
		System.arraycopy(r, 0, out, l.length + 1, r.length);
		return out;
	}
	
	public enum State {
		NEW, CURRENT, OLD
	}
	
	public static class Node<V> {
		private V val;
		private Set<Node<V>> edges;
		private State state;
		
		public Node(V val) {
			this.val = val;
			this.edges = new HashSet<Node<V>>();
			state = State.NEW;
		}
		
		public void directedEdge(Node<V> v) {
			this.edges.add(v);
		}
		
		public void undirectedEdge(Node<V> v) {
			this.directedEdge(v);
			v.directedEdge(this);
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
	
	public static BST insertArray(int[] xs, BST t) {
		for (int x : xs) {
			t = insert(x, t);
		}
		return t;
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
			return new BST(t.l, min, delete(min, t.r));
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
	
	/**
	 * Implement a function to check if a tree is balanced. For the purposes of
	 * this question, a balanced tree is defined to be a tree such that no two
	 * leaf nodes differ in distance from the root by more than one. 4.1
	 * @param t tree to check for balance
	 * @return true if t is balanced, false otherwise
	 */
	public static boolean isBalanced(BST t) {
		if (t == null) {
			return false;
		}
		
		// base case - given a leaf
		if (isLeaf(t)) {
			return true;
		}
		
		// base case - left is one above right
		if (t.l == null && isLeaf(t.r)) {
			return true;
		}
		
		// base case - right is one above left
		if (isLeaf(t.l) && t.r == null) {
			return true;
		}
		
		return Math.abs(depth(t.l) - depth(t.r)) <= 1 && 
				isBalanced(t.l) && isBalanced(t.r);
	}
	
	public static int depth(BST t) {
		return depth(t, 0);
	}
	
	private static int depth(BST t, int n) {
		if (t == null) {
			return n;
		}
		return Math.max(depth(t.l, n+1), depth(t.r, n+1));
	}
	
	private static boolean isLeaf(BST t) {
		if (t == null) {
			return false;
		}
		return t.l == null && t.r == null;
	}
	
	/**
	 * Given a directed graph, design an algorithm to find out whether there is
	 * a route between two nodes. 4.2
	 * @param s starting node
	 * @param t ending node
	 * @return true if there exists a path from s to t, false otherwise
	 */
	public static <V> boolean pathExists(Node<V> s, Node<V> t) {
		if (s == null || t == null) {
			return false;
		}
		
		Queue<Node<V>> q = new LinkedList<Node<V>>();
		s.state = State.OLD;
		q.add(s);
		
		// iterative DFS
		while (!q.isEmpty()) {
			Node<V> u = q.remove();
			for (Node<V> v : u.edges) {
				if (t == v) {
					return true;
				} else if (v.state == State.NEW) {
					v.state = State.OLD;
					q.add(v);
				}
			}
		}
		
		return false;
	}
	
	public static <V> void resetVisits(Node<V>... nodes) {
		for (Node<V> node : nodes) {
			node.state = State.NEW;
		}
	}
	
	/**
	 * Given a sorted (increasing order) array, write an algorithm to create a
	 * binary tree with minimal height. 4.3
	 * @param xs sorted array to convert into a BST
	 * @return minimal-depth BST
	 */
	public static BST fromArray(int[] xs) {
		if (xs == null) {
			return null;
		}
		
		return divideBST(0, xs.length-1, xs);
	}
	
	private static BST divideBST(int lo, int hi, int[] xs) {
		if (lo == hi) {
			return new BST(xs[lo]);
		} else if (lo > hi) {
			return null;
		}
		int mid = lo + (hi-lo)/2;
		return new BST(divideBST(lo, mid-1, xs), xs[mid], divideBST(mid+1, hi, xs));
	}
}
