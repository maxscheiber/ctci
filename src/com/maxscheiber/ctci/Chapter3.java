package com.maxscheiber.ctci;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
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
}
