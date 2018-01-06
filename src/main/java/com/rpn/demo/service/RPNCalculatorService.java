package com.rpn.demo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rpn.demo.constant.RPNConstants;
import com.rpn.demo.param.InputWrapper;
import com.rpn.demo.support.StackContextHolder;

@Service
public class RPNCalculatorService {

	private static final String REAL_NUMBER_PATTERN = "-?[0-9]+.*[0-9]*";
	private static final String INFINITY = "Infinity";
	private static final String STACK = "stack: ";

	public String process(String input) {
		return input == null || input.length() > 100 ? RPNConstants.ErrorMsg.INPUT_LENGTH_NOT_VALID
				: process(new InputWrapper(input, input.indexOf(" "), 0, new StringBuilder().append(STACK)));
	}

	public String process(InputWrapper wrapper) {
		if (StringUtils.isEmpty(wrapper.getInput())) {
			// no element
			StackContextHolder.INSTANCE.getStack()
					.forEach(a -> wrapper.getBuilder().append(
							new BigDecimal(a).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString())
							.append(" "));
			return wrapper.getBuilder().toString().trim();
		} else if (wrapper.getIndex() == -1) {
			doOperate(wrapper, wrapper.getInput());
			StackContextHolder.INSTANCE.getStack()
					.forEach(a -> wrapper.getBuilder().append(
							new BigDecimal(a).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString())
							.append(" "));
			return wrapper.getBuilder().toString().trim();
		} else {
			// recursive
			String element = wrapper.getInput().trim().substring(0, wrapper.getIndex());
			wrapper.setInput(wrapper.getInput().trim().substring(wrapper.getIndex()).trim());
			String input = doOperate(wrapper, element);
			wrapper.setCount(wrapper.getCount() + wrapper.getIndex() + 1);
			wrapper.setInput(input);
			wrapper.setIndex(input.indexOf(" "));
			return process(wrapper);

		}

	}

	private String doOperate(InputWrapper wrapper, String element) {
		if (!operate(element, wrapper.getIndex())) {
			wrapper.getBuilder().insert(0,
					String.format(RPNConstants.ErrorMsg.INSUCIENT_PARAMETERS, element,
					wrapper.getCount() + 1));
			wrapper.setInput("");
			StackContextHolder.INSTANCE.getStack().clear();
			StackContextHolder.INSTANCE.getStack().addAll(StackContextHolder.INSTANCE.getBackupList().getLast());
		}
		return wrapper.getInput();
	}

	private boolean operate(String element, int index) {
		Operation opr = OperatorFactory.getOperation(element);
		if (opr == null) {
			// not an operator
			if (element.matches(REAL_NUMBER_PATTERN) && !INFINITY.equals(new Double(element.trim()).toString())) {
				// check if it's a real number
				StackContextHolder.INSTANCE.backupStack();
				StackContextHolder.INSTANCE.getStack().push(new Double(element.trim()));
			}
			return true;
		} else {
			return opr.operate(element, index);
		}

	}

}
