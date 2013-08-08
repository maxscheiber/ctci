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
	
	/**
	 * Implement an algorithm to find the nth to last element of a singly linked
	 * list. 2.2
	 * @param in inputted LinkedList
	 * @param n # of elements from end we want to find
	 * @return nth to last element
	 */
	public static <A> Node<A> findNthToLast(Node<A> in, int n) {
		if (n <= 0) {
			return null;
		}
		
		Node<A> n1 = in;
		Node<A> n2 = in;
		for (int i = 1; i <= n; i++) {
			if (n2 == null) {
				return null;
			}
			n2 = n2.next;
		}
		
		while (n2 != null) {
			n1 = n1.next;
			n2 = n2.next;
		}
		
		return n1;
	}
	
	/**
	 * Implement an algorithm to delete a node in the middle of a single linked
	 * list, given only access to that node. 2.3
	 * @param <A>
	 * @param in Node to delete
	 */
	public static <A> void deleteNode(Node<A> in) {
		if (in == null) {
			return;
		}
		
		// in.next can never be null since node is in the middle of the LinkedList
		in.v = in.next.v;
		in.next = in.next.next;
	}
	
	/**
	 * You have two numbers represented by a linked list, where each node 
	 * contains a single digit. The digits are stored in reverse order, such 
	 * that the 1Õs digit is at the head of the list. Write a function that adds
	 * the two numbers and returns the sum as a linked list. 2.4
	 * @param l1 first number to add
	 * @param l2 second number to add
	 * @return LinkedList representation of sum of l1 and l2
	 */
	public static Node<Integer> add(Node<Integer> l1, Node<Integer> l2) {
		if (l1 == null && l2 == null) {
			return null;
		}
		
		Node<Integer> result = null;
		int carry = 0;
		
		Node<Integer> n1 = l1;
		Node<Integer> n2 = l2;
		while (n1 != null || n2 != null) {
			int sum;
			
			if (n2 == null) {
				sum = n1.v;
				n1 = n1.next;
			}
			else if (n1 == null) {
				sum = n2.v;
				n2 = n2.next;
			}
			else {
				sum = n1.v + n2.v;
				n1 = n1.next;
				n2 = n2.next;
			}
			
			result = new Node<Integer>(0, result);
			result.v = (sum % 10) + carry;
			carry = sum / 10;
		}
		
		if (carry > 0) {
			result = new Node<Integer>(carry, result);
		}
		
		return result;
	}
}
