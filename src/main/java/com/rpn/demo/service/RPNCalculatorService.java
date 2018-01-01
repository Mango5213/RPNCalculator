package com.rpn.demo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.Stack;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RPNCalculatorService {

	public String process(@Nullable String input, Stack<Double> stack, LinkedList<Stack<Double>> copyList) {
		return process(input.trim(), input.indexOf(" "), 0, new StringBuilder().append("stack: "), stack, copyList);
	}

	public String process(@Nullable String input, int index, int count, StringBuilder builder, Stack<Double> stack,
			LinkedList<Stack<Double>> copyList) {
		if (input == null || input.length() > 100) {
			// assume input no longer than 100
			return "";
		}
		if (StringUtils.isEmpty(input)) {
			// no element
			stack.forEach(a -> builder
					.append(new BigDecimal(a).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString())
					.append(" "));
			return builder.toString().trim();
		} else if (index == -1) {
			input = doOperate(input, index, count, builder, stack, copyList, input);
			stack.forEach(a -> builder
					.append(new BigDecimal(a).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString())
					.append(" "));
			return builder.toString().trim();
		} else {
			// recursive
			String element = input.trim().substring(0, index);
			input = input.trim().substring(index).trim();
			input = doOperate(input, index, count, builder, stack, copyList, element);
			return process(input.trim(), input.indexOf(" "), count + index + 1, builder, stack, copyList);

		}
	}

	private String doOperate(String input, int index, int count, StringBuilder builder, Stack<Double> stack,
			LinkedList<Stack<Double>> copyList, String element) {
		if (!operate(element, index, stack, copyList)) {
			builder.insert(0, String.format("operator %s (position: %s): insucient parameters\n", element, count + 1));
			input = "";
			stack.clear();
			stack.addAll(copyList.getLast());
		}
		return input;
	}

	private boolean operate(String input, int index, Stack<Double> stack, LinkedList<Stack<Double>> copyList) {
		Double secondNum;
		Double firstNum;
		// could use generic
		switch (input.trim()) {
		case "+":
			backupStack(stack, copyList);
			if (stack.empty()) {
				return false;
			}
			secondNum = stack.pop();
			if (stack.empty()) {
				return false;
			}
			firstNum = stack.pop();
			stack.push(firstNum + secondNum);
			break;
		case "-":
			backupStack(stack, copyList);
			if (stack.empty()) {
				return false;
			}
			secondNum = stack.pop();
			if (stack.empty()) {
				return false;
			}
			firstNum = stack.pop();
			stack.push(firstNum - secondNum);
			break;
		case "*":
			backupStack(stack, copyList);
			if (stack.empty()) {
				return false;
			}
			secondNum = stack.pop();
			if (stack.empty()) {
				return false;
			}
			firstNum = stack.pop();
			stack.push(firstNum * secondNum);
			break;
		case "/":
			backupStack(stack, copyList);
			if (stack.empty()) {
				return false;
			}
			secondNum = stack.pop();
			if (stack.empty()) {
				return false;
			}
			firstNum = stack.pop();
			stack.push(firstNum / secondNum);
			break;
		case "sqrt":
			backupStack(stack, copyList);
			if (stack.empty()) {
				return false;
			}
			firstNum = stack.pop();
			stack.push(Math.sqrt(firstNum));
			break;
		case "undo":
			stack.clear();
			stack.addAll(copyList.getLast());
			copyList.removeLast();
			break;
		case "clear":
			backupStack(stack, copyList);
			stack.clear();
			break;
		default:
			if (input.matches("-?[0-9]+.*[0-9]*")) {
				backupStack(stack, copyList);
				stack.push(new Double(input.trim()));
			}
			break;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	private void backupStack(Stack<Double> stack, LinkedList<Stack<Double>> copyList) {
		if (copyList.size() == 10) {
			// no more than 10
			copyList.removeFirst();
		}
		copyList.add((Stack<Double>) stack.clone());
	}

}
