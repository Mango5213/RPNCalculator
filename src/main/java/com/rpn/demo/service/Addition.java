package com.rpn.demo.service;

import org.springframework.stereotype.Service;

import com.rpn.demo.support.StackContextHolder;

@Service
public class Addition implements Operation {

	@Override
	public boolean operate(String input, int index) {
		Double secondNum;
		Double firstNum;
		StackContextHolder.INSTANCE.backupStack();
		if (StackContextHolder.INSTANCE.getStack().empty()) {
			return false;
		}
		secondNum = StackContextHolder.INSTANCE.getStack().pop();
		if (StackContextHolder.INSTANCE.getStack().empty()) {
			return false;
		}
		firstNum = StackContextHolder.INSTANCE.getStack().pop();
		StackContextHolder.INSTANCE.getStack().push(firstNum + secondNum);
		return true;
	}

	@Override
	public String getOperator() {
		return "+";
	}

}
