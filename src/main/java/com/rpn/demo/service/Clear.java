package com.rpn.demo.service;

import org.springframework.stereotype.Service;

import com.rpn.demo.constant.RPNConstants;
import com.rpn.demo.support.StackContextHolder;

@Service
public class Clear implements Operation {

	@Override
	public boolean operate(String input, int index) {
		StackContextHolder.INSTANCE.backupStack();
		StackContextHolder.INSTANCE.getStack().clear();
		return true;
	}

	@Override
	public String getOperator() {
		return RPNConstants.Operators.CLEAR;
	}

}
