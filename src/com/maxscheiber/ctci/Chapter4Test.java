package com.maxscheiber.ctci;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.maxscheiber.ctci.Chapter4.BST;
import com.maxscheiber.ctci.Chapter4.Node;
import com.maxscheiber.ctci.Chapter4.BinaryTree;

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
	
	@Test
	public void testLevelorder() {
		assertEquals("Empty tree", 0, Chapter4.levelorder(null).size());
		
		BST t = new BST(0);
		assertEquals("One-level tree", 1, Chapter4.levelorder(t).size());
		assertEquals("One-level tree", 1, Chapter4.levelorder(t).get(0).size());
		assertTrue("One-level tree", 0 == Chapter4.levelorder(t).get(0).get(0));
		
		t = Chapter4.insert(-2, t);
		t = Chapter4.insert(2, t);
		assertEquals("Two-level tree", 2, Chapter4.levelorder(t).size());
		assertEquals("Two-level tree", 1, Chapter4.levelorder(t).get(0).size());
		assertEquals("Two-level tree", 2, Chapter4.levelorder(t).get(1).size());
		
		t = Chapter4.insert(-3, t);
		t = Chapter4.insert(-1, t);
		t = Chapter4.insert(1, t);
		assertEquals("Three-level tree", 3, Chapter4.levelorder(t).size());
		assertEquals("Three-level tree", 1, Chapter4.levelorder(t).get(0).size());
		assertEquals("Three-level tree", 2, Chapter4.levelorder(t).get(1).size());
		assertEquals("Three-level tree", 3, Chapter4.levelorder(t).get(2).size());
	}
	
	@Test
	public void testAncestor() {
		assertNull("Null tree", Chapter4.ancestor(null, null, null));
		
		/*
		 *         0
		 *     -2     2
		 *  -3   -1 1   3
		 */
		BinaryTree<Integer> t = new BinaryTree<Integer>(
				new BinaryTree<Integer>(
						new BinaryTree<Integer>(-3), -2, new BinaryTree<Integer>(-1)
				), 
				0, 
				new BinaryTree<Integer>(
						new BinaryTree<Integer>(1), 2, new BinaryTree<Integer>(3)
				)
		);
		assertTrue(0 == Chapter4.ancestor(0, -1, t));
		assertTrue(0 == Chapter4.ancestor(0, -2, t));
		assertTrue(0 == Chapter4.ancestor(0, 3, t));
		assertTrue(0 == Chapter4.ancestor(-2, 3, t));
		assertTrue(0 == Chapter4.ancestor(-3, 1, t));
		assertTrue(0 == Chapter4.ancestor(0, -1, t));
		assertTrue(-2 == Chapter4.ancestor(-3, -2, t));
		assertTrue(-2 == Chapter4.ancestor(-3, -1, t));
		assertTrue(2 == Chapter4.ancestor(2, 1, t));
		assertTrue(2 == Chapter4.ancestor(1, 3, t));
	}
	
	@Test
	public void testIsSubtree() {
		assertFalse("Null t1", Chapter4.isSubtree(null, new BinaryTree<Integer>(1)));
		assertTrue("Null t2", Chapter4.isSubtree(new BinaryTree<Integer>(1), null));
		assertTrue("Null t1 and t2", Chapter4.isSubtree(null, null));
		
		/*
		 *         0
		 *     -2     2
		 *  -3   -1 1   3
		 */
		BinaryTree<Integer> t1 = new BinaryTree<Integer>(
				new BinaryTree<Integer>(
						new BinaryTree<Integer>(-3), -2, new BinaryTree<Integer>(-1)
				), 
				0, 
				new BinaryTree<Integer>(
						new BinaryTree<Integer>(1), 2, new BinaryTree<Integer>(3)
				)
		);
		assertTrue(Chapter4.isSubtree(t1, new BinaryTree<Integer>(1)));
		assertTrue(Chapter4.isSubtree(t1, new BinaryTree<Integer>(
				new BinaryTree<Integer>(1), 2, new BinaryTree<Integer>(3))));
		assertTrue(Chapter4.isSubtree(t1, new BinaryTree<Integer>(
				new BinaryTree<Integer>(1), 2, new BinaryTree<Integer>(4))));
	}
	
	@Test
	public void testPrintSumPaths() {
		Chapter4.printSumPaths(null, 0); // should print nothing
		
		/*
		 *         0
		 *     -2     2
		 *  -3   -1 1   3
		 */
		BinaryTree<Integer> t1 = new BinaryTree<Integer>(
				new BinaryTree<Integer>(
						new BinaryTree<Integer>(-3), -2, new BinaryTree<Integer>(-1)
				), 
				0, 
				new BinaryTree<Integer>(
						new BinaryTree<Integer>(1), 2, new BinaryTree<Integer>(3)
				)
		);
		
		Chapter4.printSumPaths(t1, 0); // should print leading node
		Chapter4.printSumPaths(t1, -5); // should print 0 - -2 - -3 and -2 - -3
		Chapter4.printSumPaths(t1, 3); // should print 0 - 2 - 1, 2 - 1, and 3
	}
}
