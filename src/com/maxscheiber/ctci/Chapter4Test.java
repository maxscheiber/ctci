package com.maxscheiber.ctci;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.maxscheiber.ctci.Chapter4.BST;
import com.maxscheiber.ctci.Chapter4.Node;

public class Chapter4Test {
	@Test
	public void testBST() {
		BST t;
		t = Chapter4.insert(2, null);
		t = Chapter4.insert(1, t);
		t = Chapter4.insert(3, t);
		assertEquals("Node is 2", 2, t.v());
		assertEquals("Left is 1", 1, t.l().v());
		assertEquals("Right is 3", 3, t.r().v());
		t = Chapter4.delete(2, t);
		assertEquals("Node is 3", 3, t.v());
		assertEquals("Left is 1", 1, t.l().v());
		assertNull("Right is null", t.r());
	}
	
	@Test
	public void testIsBalanced() {
		assertFalse("Null tree", Chapter4.isBalanced(null));
		assertTrue("Singleton tree", Chapter4.isBalanced(new BST(0)));
		assertTrue("Two-element tree", Chapter4.isBalanced(Chapter4.insert(1, 
				new BST(0))));
		assertTrue("Three-element balanced tree", Chapter4.isBalanced(
				Chapter4.insertArray(new int[]{2, 1, 3}, null)));
		assertTrue("Four-element balanced tree", Chapter4.isBalanced(
				Chapter4.insertArray(new int[]{2, 1, 3, 4}, null)));
		assertFalse("Queue tree", Chapter4.isBalanced(
				Chapter4.insertArray(new int[]{1, 2, 3}, null)));
		assertFalse("Large unbalanced tree", Chapter4.isBalanced(
				Chapter4.insertArray(new int[]{0, -2, -3, -1, 10, 5, 15, 3, 8, 
						12, 18, 14}, null)));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPathExists() {
		Node<Integer> n1 = new Node<Integer>(1);
		Node<Integer> n2 = new Node<Integer>(2);
		Node<Integer> n3 = new Node<Integer>(3);
		Node<Integer> n4 = new Node<Integer>(4);
		Node<Integer> n5 = new Node<Integer>(5);
		Node<Integer> n6 = new Node<Integer>(6);
		Node<Integer> n7 = new Node<Integer>(7);
		Node<Integer> n8 = new Node<Integer>(8);
		n1.directedEdge(n2);
		n1.directedEdge(n3);
		n2.directedEdge(n4);
		n2.directedEdge(n5);
		n3.directedEdge(n6);
		n3.directedEdge(n7);
		n5.directedEdge(n4);
		n8.directedEdge(n4);
		
		assertTrue("path from 1 to 2", Chapter4.pathExists(n1, n2));
		Chapter4.resetVisits(n1, n2, n3, n4, n5, n6, n7, n8);
		assertTrue("path from 1 to 3", Chapter4.pathExists(n1, n3));
		Chapter4.resetVisits(n1, n2, n3, n4, n5, n6, n7, n8);
		assertTrue("path from 1 to 4", Chapter4.pathExists(n1, n4));
		Chapter4.resetVisits(n1, n2, n3, n4, n5, n6, n7, n8);
		assertTrue("path from 1 to 7", Chapter4.pathExists(n1, n5));
		Chapter4.resetVisits(n1, n2, n3, n4, n5, n6, n7, n8);
		assertFalse("no path from 1 to 8", Chapter4.pathExists(n1, n8));
		Chapter4.resetVisits(n1, n2, n3, n4, n5, n6, n7, n8);
	}
	
	@Test
	public void testFromArray() {
		assertNull("Null array", Chapter4.fromArray(null));
		testFromArray(new int[]{1});
		testFromArray(new int[]{1, 2});
		testFromArray(new int[]{1, 2, 3});
		testFromArray(new int[]{1, 2, 3, 4});
		testFromArray(new int[]{1, 2, 3, 4, 5});
		testFromArray(new int[]{1, 2, 3, 4, 5, 6});
		testFromArray(new int[]{1, 2, 3, 4, 5, 6, 7});
		testFromArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
	}
	
	private void testFromArray(int[] xs) {
		BST t = Chapter4.fromArray(xs);
		int[] back = Chapter4.inorder(t);
		int N = xs.length;
		for (int i = 0; i < N; i++) {
			assertEquals("Legnth " + N, xs[i], back[i]);
		}
		assertEquals("Length " + N, 1 + Math.ceil((int) (Math.log(N) / Math.log(2))), 
				Chapter4.depth(t), 0.01);
	}
}
