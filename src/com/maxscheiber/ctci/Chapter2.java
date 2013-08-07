package com.maxscheiber.ctci;

import java.util.HashSet;
import java.util.Set;

public class Chapter2 {
	public static class Node<A> {
		public Node<A> next;
		public A v;
		
		public Node(A v, Node<A> next) {
			this.next = next;
			this.v = v;
		}
	}
	
	/**
	 * Write code to remove duplicates from an unsorted linked list. 2.1
	 * @param in inputted LinkedList
	 * @return LinkedList with no duplicates
	 */
	public static <A> Node<A> removeDups(Node<A> in) {
		if (in == null) {
			return null;
		}
		
		Set<A> seen = new HashSet<A>();
		Node<A> prev = null;
		for (Node<A> n = in; n != null; n = n.next) {
			if (seen.contains(n.v)) {
				// prev cannot be null since 1st element is not a duplicate
				prev.next = n.next;
			} else {
				seen.add(n.v);
			}
			prev = n;
		}
		
		return in;
	}
	
	/**
	 * How would you solve this problem if a temporary buffer is not allowed? 2.1
	 * @param in inputted LinkedList
	 * @return LinkedList with no duplicates
	 */
	public static <A> Node<A> removeDups2(Node<A> in) {
		if (in == null) {
			return null;
		}
		
		Node<A> prev = in;
		for (Node<A> n = in; n != null; n = n.next) {
			for (Node<A> o = n.next; o != null; o = o.next) {
				if (n.v.equals(o.v)) {
					prev.next = o.next;
				}
				prev = prev.next;
			}
		}
		
		return in;
	}
}
