package com.maxscheiber.ctci;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Chapter3 {
	/**
	 * Describe how you could use a single array to implement three stacks. 3.1
	 * 
	 * Java generics suck, so we're just going to do this with ints.
	 */
	public static class ThreeStackArray {
		private int[] stacks;
		private int[] sizes = new int[3];
		private int N; //
		
		/**
		 * Construct three new stacks.
		 * @param N size of each stack
		 */
		public ThreeStackArray(int N) {
			if (N < 1) {
				throw new IllegalArgumentException("Stack size must be positive");
			}
			this.N = N;
			stacks = new int[3*N];
		}
		
		/**
		 * Push a new value onto one of the three stacks.
		 * @param v value to push onto the stack
		 * @param stack which stack you want to push to (0, 1, or 2)
		 * @return true if successful push, false otherwise
		 */
		public boolean push(int v, int stack) {
			// at capacity, cannot push
			if (sizes[stack] >= N) {
				return false;
			}
			
			stacks[N * stack + sizes[stack]] = v;
			sizes[stack]++;
			return true;
		}
		
		/**
		 * Pop a value from one of the three stacks.
		 * @param stack which stack you want to pop from (0, 1, or 2)
		 * @return value popped from the stack
		 */
		public int pop(int stack) {
			// cannot pop an empty stack
			if (sizes[stack] <= 0) {
				throw new EmptyStackException();
			}
			
			int v = stacks[N * stack + (sizes[stack]-1)];
			sizes[stack]--;
			return v;
		}
		
		/**
		 * Return the top value from a stack without popping it.
		 * @param stack which stack you want to pop from (0, 1, or 2)
		 * @return top value from the stack
		 */
		public int peek(int stack) {
			// cannot peek on an empty stack
			if (sizes[stack] <= 0) {
				throw new EmptyStackException();
			}
			
			return stacks[N * stack + (sizes[stack] - 1)];
		}
		
		/**
		 * Whether the stack in question is empty
		 * @param stack which stack you want to check (0, 1, or 2)
		 * @return true if stack is empty, false otherwise
		 */
		public boolean empty(int stack) {
			return sizes[stack] == 0;
		}
	}
	
	/**
	 * How would you design a stack which, in addition to push and pop, also has
	 * a function min which returns the minimum element? Push, pop and min
	 * should all operate in O(1) time. 3.2
	 */
	public static class MinStack {
		private int min = Integer.MAX_VALUE;
		private Stack<Integer> stack = new Stack<Integer>();
		private Stack<Integer> mins = new Stack<Integer>();
		
		public boolean empty() {
			return stack.empty();
		}
		
		public int min() {
			if (empty()) {
				throw new EmptyStackException();
			}
			return min;
		}
		
		public int peek() {
			return stack.peek();
		}
		
		public int pop() {
			int v = stack.pop();
			if (v == min) {
				min = mins.pop();
			}
			return v;
		}
		
		public int push(int v) {
			stack.push(v);
			if (v <= min) {
				mins.push(min);
				min = v;
			}
			return v;
		}
		
		public int search(int v) {
			return stack.search(v);
		}
	}
	
	/**
	 * Imagine a (literal) stack of plates If the stack gets too high, it might
	 * topple. Therefore, in real life, we would likely start a new stack when
	 * the previous stack exceeds some threshold. Implement a data structure
	 * SetOfStacks that mimics this. 3.3
	 */
	public static class SetOfStacks<A> {
		private List<Stack<A>> stacks = new ArrayList<Stack<A>>();
		private final int N;
		
		/**
		 * @param N maximum number of elements per stack
		 */
		public SetOfStacks(int N) {
			this.N = N;
		}
		
		public A pop() {
			if (stacks.isEmpty()) {
				throw new EmptyStackException();
			}
			
			if (stacks.get(stacks.size()-1).isEmpty()) {
				stacks.remove(stacks.size()-1);
				return this.pop();
			} else {
				return stacks.get(stacks.size()-1).pop();
			}
		}
		
		public A push(A v) {
			if (stacks.size() == 0 || stacks.get(stacks.size()-1).size() >= N) {
				Stack<A> stack = new Stack<A>();
				stack.push(v);
				stacks.add(stack);
			} else {
				stacks.get(stacks.size()-1).push(v);
			}
			return v;
		}
		
		public A popAt(int index) {
			if (stacks.size() <= index) {
				throw new IllegalArgumentException("Cannot pop from an uninitialized stack");
			}
			return stacks.get(index).pop();
		}
	}
	
	/**
	 * Implement Towers of Hanoi. 3.4
	 * @param N number of disks to start with
	 * @return the target stack, representing the finished game
	 */
	public static Stack<Integer> towersOfHanoi(int N) {
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();
		Stack<Integer> s3 = new Stack<Integer>();

		if (N <= 0) {
			throw new IllegalArgumentException("Must have a positive number "
					+ "of disks to play Towers of Hanoi");
		}

		// initialize s1 with the appropriate integers
		for (int i = N; i >= 1; i--) {
			s1.push(i);
		}

		move(N, s1, s2, s3);
		/*
		 * System.out.print("Stack 1: "); printStack(s1);
		 * System.out.print("Stack 2: "); printStack(s2);
		 * System.out.print("Stack 3: "); printStack(s3);
		 */

		return s3;
	}
	
	/**
	 * Moves the top element of one stack onto another stack.
	 * @param src stack to pop from
	 * @param tgt stack to push onto
	 */
	private static void move(Stack<Integer> src, Stack<Integer> tgt) {
		if (tgt.empty()) {
			// System.out.println("Moving " + src.peek() + " to empty stack");
		} else {
			// System.out.println("Moving " + src.peek() + " on top of " +
			// tgt.peek());
		}
		tgt.push(src.pop());
	}
     /**
      * Given a stack of N consecutive integer disks, move them from src pole
      * to tgt pole, using mid pole for transfers.
      * @param N number of consecutive integer disks (i.e. no skips)
      * @param src starting pole to move from
      * @param mid pole used for passing
      * @param tgt target pole to move to
      */
	private static void move(int N, Stack<Integer> src, Stack<Integer> mid,
			Stack<Integer> tgt) {
		if (N == 1) {
			move(src, tgt);
			return;
		}
		move((N - 1), src, tgt, mid);
		move(src, tgt);
		move((N - 1), mid, src, tgt);
		if (tgt.size() == N) {
			return;
		}
	}
    
    /**
     * Prints the stack, top to bottom.
     * @param s stack to print
     */
	private static void printStack(Stack<Integer> s) {
		Stack<Integer> cp = new Stack<Integer>();
		while (!s.empty()) {
			cp.push(s.pop());
			System.out.print(cp.peek() + " ");
		}
		while (!cp.empty()) {
			s.push(cp.pop());
		}
		System.out.print("\n");
	}
	
	/**
	 * Implement a MyQueue class which implements a queue using two stacks. 3.5
	 */
	public static class MyQueue<A> {
		Stack<A> head = new Stack<A>();
		Stack<A> tail = new Stack<A>();
		
		public boolean add(A v) {
			tail.push(v);
			return true;
		}
		
		public A element() {
			if (head.empty()) {
				transfer();
			}
			try {
				return head.peek();
			} catch (EmptyStackException e) {
				throw new NoSuchElementException();
			}
		}
		
		public boolean offer(A v) {
			return add(v);
		}
		
		public A peek() {
			try {
				return this.element();
			} catch (NoSuchElementException e) {
				return null;
			}
		}
		
		public A poll() {
			try {
				return this.remove();
			} catch (NoSuchElementException e) {
				return null;
			}
		}
		
		public A remove() {
			if (head.empty()) {
				transfer();
			}
			try {
				return head.pop();
			} catch (EmptyStackException e) {
				throw new NoSuchElementException();
			}
		}
		
		public int size() {
			return head.size() + tail.size();
		}
		
		private void transfer() {
			while (!tail.empty()) {
				head.push(tail.pop());
			}
		}
	}
}
