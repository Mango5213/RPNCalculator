package com.rpn.demo.support;

import java.util.LinkedList;
import java.util.Stack;

public enum StackContextHolder {
	INSTANCE;

	private ThreadLocal<Stack<Double>> stack = new ThreadLocal<Stack<Double>>() {
		@Override
		public Stack<Double> initialValue() {
			return new Stack<Double>();
		}
	};
	private ThreadLocal<LinkedList<Stack<Double>>> backupList = new ThreadLocal<LinkedList<Stack<Double>>>() {
		@Override
		public LinkedList<Stack<Double>> initialValue() {
			return new LinkedList<Stack<Double>>();
		}
	};

	public Stack<Double> getStack() {
		return INSTANCE.stack.get();
	}

	public LinkedList<Stack<Double>> getBackupList() {
		return INSTANCE.backupList.get();
	}

	public void setStack(Stack<Double> stack) {
		INSTANCE.stack.set(stack);
	}

	public void setBackupList(LinkedList<Stack<Double>> backupList) {
		INSTANCE.backupList.set(backupList);
	}

	@SuppressWarnings("unchecked")
	public void backupStack() {
		if (StackContextHolder.INSTANCE.getBackupList().size() == 10) {
			// no more than 10
			StackContextHolder.INSTANCE.getBackupList().removeFirst();
		}
		StackContextHolder.INSTANCE.getBackupList().add((Stack<Double>) StackContextHolder.INSTANCE.getStack().clone());
	}

}
