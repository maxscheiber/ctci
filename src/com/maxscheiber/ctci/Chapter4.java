package com.maxscheiber.ctci;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
	
	/**
	 * Given a binary search tree, design an algorithm which creates a linked
	 * list of all the nodes at each depth (i.e., if you have a tree with depth
	 * D, you'll have D linked lists). 4.4
	 * @param t inputted BST
	 * @return BFS traversal list
	 */
	public static List<List<Integer>> levelorder(BST t) {
		if (t == null) {
			return new LinkedList<List<Integer>>();
		}
		
		Queue<BST> now = new LinkedList<BST>();
		Queue<BST> next;
		List<List<Integer>> depth = new LinkedList<List<Integer>>();
		
		// BST BFS
		now.add(t);
		while (!now.isEmpty()) {
			List<Integer> vals = new LinkedList<Integer>();
			next = new LinkedList<BST>();
			while (!now.isEmpty()) {
				if (now.peek().l != null) {
					next.add(now.peek().l);
				}
				if (now.peek().r != null) {
					next.add(now.peek().r);
				}
				vals.add(now.poll().v);
			}
			depth.add(vals);
			now = next;
		}
		
		return depth;
	}
	
	public static class BinaryTree<T> {
		private BinaryTree<T> l;
		private T v;
		private BinaryTree<T> r;
		
		public BinaryTree(T v) {
			this.v = v;
		}
		
		public BinaryTree(BinaryTree<T> l, T v, BinaryTree<T> r) {
			this.l = l;
			this.v = v;
			this.r = r;
		}
		
		public BinaryTree<T> l() {
			return l;
		}
		
		public T v() {
			return v;
		}
		
		public BinaryTree<T> r() {
			return r;
		}
	}
	
	/**
	 * Design an algorithm and write code to find the first common ancestor of
	 * two nodes in a binary tree. Avoid storing additional nodes in a data
	 * structure. 4.6
	 * @param a first node
	 * @param b second node
	 * @param t binary tree in which to search
	 * @return value of the common ancestor of a and b
	 */
	public static <T> T ancestor(T a, T b, BinaryTree<T> t) {
		if (t == null) {
			return null;
		}
		return ancestor(a, b, t.v, t);
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @param p parent of the current node being considered; will never be null
	 * @param t
	 * @return
	 */
	private static <T> T ancestor(T a, T b, T p, BinaryTree<T> t) {
		// base case, null tree
		if (t == null || a == null || b == null) {
			return null;
		}
		
		T al = ancestor(a, b, t.v, t.l); // left ancestor
		T ar = ancestor(a, b, t.v, t.r); // right ancestor
		
		// a is the ancestor of b, or vice versa
		if (t.v.equals(al) || t.v.equals(ar)) {
			return t.v;
		}
		// no longer have to look any deeper
		else if (t.v.equals(a) || t.v.equals(b)) {
			return p;
		}
		
		if (ar == null) {
			return al; // both are on the left
		} else if (al == null) {
			return ar; // both are on the right
		} else if (al.equals(ar)) {
			return t.v; // found the closest ancestor
		} else {
			return p; // what do, Misc?
		}
	}
	
	/**
	 * You have two very large binary trees: T1, with millions of nodes, and T2,
	 * with hundreds of nodes. Create an algorithm to decide if T2 is a subtree
	 * of T1. 4.7
	 * @param t1 tree, ~ 10^6 nodes
	 * @param t2 tree, ~ 10^2 nodes
	 * @return true if t2 is a subtree of t1, false otherwise
	 */
	public static <T> boolean isSubtree(BinaryTree<T> t1, BinaryTree<T> t2) {
		if (t2 == null) {
			return true;
		} else if (t1 == null) {
			return false;
		}
		
		// basically, having a big thunk is better than O(n) space for t1
		if (t1.v.equals(t2.v)) {
			return isSubtree(t1.l, t2.l) && isSubtree(t1.r, t2.r);
		} else {
			return isSubtree(t1.l, t2.l) || isSubtree(t1.r, t2.r);
		}
	}
}
