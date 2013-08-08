package com.maxscheiber.ctci;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;

import org.junit.Test;

import com.maxscheiber.ctci.Chapter3.MinStack;
import com.maxscheiber.ctci.Chapter3.SetOfStacks;
import com.maxscheiber.ctci.Chapter3.ThreeStackArray;

public class Chapter3Test {
	@Test
	public void testThreeStackArray() {
		ThreeStackArray stacks = new ThreeStackArray(3);
		
		try {
			new ThreeStackArray(0);
			fail("Did not catch invalid stack size initialization");
		} catch (IllegalArgumentException e) {
			assertTrue("Correctly caught invalid stack size initialization", true);
		}
		
		assertTrue("Empty stack 0", stacks.empty(0));
		assertTrue("Empty stack 1", stacks.empty(1));
		assertTrue("Empty stack 2", stacks.empty(2));
		
		assertTrue("Successful push to stack 0", stacks.push(1, 0));
		assertEquals("Top element is 1", 1, stacks.peek(0));
		assertFalse("Non-empty stack 0", stacks.empty(0));
		assertTrue("Empty stack 1", stacks.empty(1));
		assertTrue("Empty stack 2", stacks.empty(2));
		
		assertTrue("Successful push to stack 0", stacks.push(2, 0));
		assertEquals("Top element is 2", 2, stacks.peek(0));
		assertFalse("Non-empty stack 0", stacks.empty(0));
		assertTrue("Empty stack 1", stacks.empty(1));
		assertTrue("Empty stack 2", stacks.empty(2));
		
		assertTrue("Successful push to stack 0", stacks.push(3, 0));
		assertFalse("Stack 0 is full", stacks.push(4, 0));
		assertTrue("Successful push to stack 2", stacks.push(100, 2));
		assertEquals("Top element is 3", 3, stacks.peek(0));
		assertEquals("Top element is 100", 100, stacks.peek(2));
		
		assertEquals("Pop stack 0", 3, stacks.pop(0));
		assertEquals("Pop stack 0", 2, stacks.pop(0));
		assertEquals("Pop stack 0", 1, stacks.pop(0));
		assertTrue("Empty stack 0", stacks.empty(0));
		assertTrue("Empty stack 1", stacks.empty(1));
		assertFalse("Non-empty stack 2", stacks.empty(2));
	}
	
	@Test
	public void testMinStack() {
		MinStack stack = new MinStack();
		
		try {
			stack.min();
			fail("Did not catch empty stack min()");
		} catch (EmptyStackException e) {
			assertTrue("Correctly caught empty stack min()", true);
		}
		
		stack.push(0);
		assertEquals("only element is min", 0, stack.min());
		stack.push(1);
		assertEquals("min is still first element", 0, stack.min());
		stack.push(-1);
		assertEquals("new min", -1, stack.min());
		stack.push(1);
		assertEquals("same min", -1, stack.min());
		stack.pop();
		assertEquals("same min", -1, stack.min());
		stack.pop();
		assertEquals("reverts to old min", 0, stack.min());
		stack.pop();
		assertEquals("only element is min", 0, stack.min());
		stack.pop();
		assertTrue("stack is now empty", stack.empty());
	}
	
	@Test
	public void testSetOfStacks() {
		SetOfStacks<Integer> stack = new SetOfStacks<Integer>(2);
		
		try {
			stack.pop();
			fail("Did not catch empty stack pop()");
		} catch (EmptyStackException e) {
			assertTrue("Correctly caught empty stack pop()", true);
		}
		
		stack.push(0);
		stack.push(1);
		stack.push(2);
		assertTrue("pop first stack", 1 == stack.popAt(0));
		assertTrue("pop second stack", 2 == stack.pop());
		assertTrue("pop last element", 0 == stack.pop());
		try {
			stack.pop();
			fail("Did not catch empty stack pop()");
		} catch (EmptyStackException e) {
			assertTrue("Correctly caught empty stack pop()", true);
		}
	}
}
