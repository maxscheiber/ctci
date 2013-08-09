package com.maxscheiber.ctci;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.maxscheiber.ctci.Chapter4.BST;

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
}
