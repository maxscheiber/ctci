package com.maxscheiber.ctci;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.maxscheiber.ctci.Chapter2.Node;

public class Chapter2Test {
	@Test
	public void testRemoveDups() {
		assertNull("Null LinkedList", Chapter2.removeDups(null));
		
		Node<Integer> n1 = new Node<Integer>(0, null);
		assertEquals("Single-element LinkedList", n1, Chapter2.removeDups(n1));
		
		Node<Integer> n2 = new Node<Integer>(0, null);
		n1 = new Node<Integer>(0, n2);
		Chapter2.removeDups(n1);
		assertTrue("Two identical elements", 0 == n1.v);
		assertNull("Two identical elements", n1.next);
		
		Node<Integer> n3 = new Node<Integer>(0, null);
		n2 = new Node<Integer>(1, n3);
		n1 = new Node<Integer>(0, n2);
		Chapter2.removeDups(n1);
		assertTrue("Duplicate and unique", 0 == n1.v);
		assertTrue("Duplicate and unique", 1 == n1.next.v);
		assertNull("Duplicate and unique", n1.next.next);
		
		n2 = new Node<Integer>(1, null);
		n1 = new Node<Integer>(0, n2);
		Chapter2.removeDups(n1);
		assertTrue("Two unique elements", 0 == n1.v);
		assertTrue("Duplicate and unique", 1 == n1.next.v);
		assertNull("Duplicate and unique", n1.next.next);
	}
	
	@Test
	public void testRemoveDups2() {
		assertNull("Null LinkedList", Chapter2.removeDups2(null));
		
		Node<Integer> n1 = new Node<Integer>(0, null);
		assertEquals("Single-element LinkedList", n1, Chapter2.removeDups2(n1));
		
		Node<Integer> n2 = new Node<Integer>(0, null);
		n1 = new Node<Integer>(0, n2);
		Chapter2.removeDups2(n1);
		assertTrue("Two identical elements", 0 == n1.v);
		assertNull("Two identical elements", n1.next);
		
		Node<Integer> n3 = new Node<Integer>(0, null);
		n2 = new Node<Integer>(1, n3);
		n1 = new Node<Integer>(0, n2);
		Chapter2.removeDups2(n1);
		assertTrue("Duplicate and unique", 0 == n1.v);
		assertTrue("Duplicate and unique", 1 == n1.next.v);
		assertNull("Duplicate and unique", n1.next.next);
		
		n2 = new Node<Integer>(1, null);
		n1 = new Node<Integer>(0, n2);
		Chapter2.removeDups2(n1);
		assertTrue("Two unique elements", 0 == n1.v);
		assertTrue("Duplicate and unique", 1 == n1.next.v);
		assertNull("Duplicate and unique", n1.next.next);
	}
	
	@Test
	public void testDeleteNode() {
		Node<Integer> n3 = new Node<Integer>(3, null);
		Node<Integer> n2 = new Node<Integer>(2, n3);
		Node<Integer> n1 = new Node<Integer>(1, n2);
		Chapter2.deleteNode(n2);
		assertTrue("First node is 1", 1 == n1.v);
		assertTrue("Second node is 3", 3 == n1.next.v);
		assertNull("List of length 2 after deletion", n1.next.next);
	}
	
	@Test
	public void testAdd() {
		assertNull("Both null", Chapter2.add(null, null));
		
		assertTrue("One number, one digit", 5 == Chapter2.add(null, 
				new Node<Integer>(5, null)).v);
		
		assertTrue("One number, two digits", 1 == Chapter2.add(null, 
				new Node<Integer>(5, new Node<Integer>(1, null))).v);
		assertTrue("One number, two digits", 5 == Chapter2.add(null, 
				new Node<Integer>(5, new Node<Integer>(1, null))).next.v);
		
		assertTrue("Two numbers, no carry", 8 == Chapter2.add(
				new Node<Integer>(3, null), new Node<Integer>(5, null)).v);
		
		assertTrue("Two larger numbers, no carry", 2 == Chapter2.add(
				new Node<Integer>(2, new Node<Integer>(1, null)), 
				new Node<Integer>(2, new Node<Integer>(1, null))).v);
		assertTrue("Two larger numbers, no carry", 4 == Chapter2.add(
				new Node<Integer>(2, new Node<Integer>(1, null)), 
				new Node<Integer>(2, new Node<Integer>(1, null))).next.v);
		
		assertTrue("Two numbers, carry", 1 == Chapter2.add(
				new Node<Integer>(8, null), new Node<Integer>(4, null)).v);
		assertTrue("Two numbers, carry", 2 == Chapter2.add(
				new Node<Integer>(8, null), new Node<Integer>(4, null)).next.v);
	}
}
