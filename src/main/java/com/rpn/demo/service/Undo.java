package com.rpn.demo.service;

import org.springframework.stereotype.Service;

import com.rpn.demo.constant.RPNConstants;
import com.rpn.demo.support.StackContextHolder;

@Service
public class Undo implements Operation {

	@Override
	public boolean operate(String input, int index) {
		StackContextHolder.INSTANCE.getStack().clear();
		StackContextHolder.INSTANCE.getStack().addAll(StackContextHolder.INSTANCE.getBackupList().getLast());
		StackContextHolder.INSTANCE.getBackupList().removeLast();
		return true;
	}

	@Override
	public String getOperator() {
		return RPNConstants.Operators.UNDO;
	}

}
