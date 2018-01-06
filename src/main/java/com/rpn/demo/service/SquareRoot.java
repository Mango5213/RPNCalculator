package com.rpn.demo.service;

import org.springframework.stereotype.Service;

import com.rpn.demo.support.StackContextHolder;

@Service
public class SquareRoot implements Operation {

	@Override
	public boolean operate(String input, int index) {
		Double firstNum;
		StackContextHolder.INSTANCE.backupStack();
		if (StackContextHolder.INSTANCE.getStack().empty()) {
			return false;
		}
		firstNum = StackContextHolder.INSTANCE.getStack().pop();
		StackContextHolder.INSTANCE.getStack().push(Math.sqrt(firstNum));
		return true;
	}

	@Override
	public String getOperator() {
		return "sqrt";
	}

}
