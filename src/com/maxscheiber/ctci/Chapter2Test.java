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
		
		Node<Integer> ll1 = new Node<Integer>(0, null);
		assertEquals("Single-element LinkedList", ll1, Chapter2.removeDups(ll1));
		
		Node<Integer> ll2 = new Node<Integer>(0, null);
		ll1 = new Node<Integer>(0, ll2);
		Chapter2.removeDups(ll1);
		assertTrue("Two identical elements", 0 == ll1.v);
		assertNull("Two identical elements", ll1.next);
		
		Node<Integer> ll3 = new Node<Integer>(0, null);
		ll2 = new Node<Integer>(1, ll3);
		ll1 = new Node<Integer>(0, ll2);
		Chapter2.removeDups(ll1);
		assertTrue("Duplicate and unique", 0 == ll1.v);
		assertTrue("Duplicate and unique", 1 == ll1.next.v);
		assertNull("Duplicate and unique", ll1.next.next);
		
		ll2 = new Node<Integer>(1, null);
		ll1 = new Node<Integer>(0, ll2);
		Chapter2.removeDups(ll1);
		assertTrue("Two unique elements", 0 == ll1.v);
		assertTrue("Duplicate and unique", 1 == ll1.next.v);
		assertNull("Duplicate and unique", ll1.next.next);
	}
	
	@Test
	public void testRemoveDups2() {
		assertNull("Null LinkedList", Chapter2.removeDups2(null));
		
		Node<Integer> ll1 = new Node<Integer>(0, null);
		assertEquals("Single-element LinkedList", ll1, Chapter2.removeDups2(ll1));
		
		Node<Integer> ll2 = new Node<Integer>(0, null);
		ll1 = new Node<Integer>(0, ll2);
		Chapter2.removeDups2(ll1);
		assertTrue("Two identical elements", 0 == ll1.v);
		assertNull("Two identical elements", ll1.next);
		
		Node<Integer> ll3 = new Node<Integer>(0, null);
		ll2 = new Node<Integer>(1, ll3);
		ll1 = new Node<Integer>(0, ll2);
		Chapter2.removeDups2(ll1);
		assertTrue("Duplicate and unique", 0 == ll1.v);
		assertTrue("Duplicate and unique", 1 == ll1.next.v);
		assertNull("Duplicate and unique", ll1.next.next);
		
		ll2 = new Node<Integer>(1, null);
		ll1 = new Node<Integer>(0, ll2);
		Chapter2.removeDups2(ll1);
		assertTrue("Two unique elements", 0 == ll1.v);
		assertTrue("Duplicate and unique", 1 == ll1.next.v);
		assertNull("Duplicate and unique", ll1.next.next);
	}
}
