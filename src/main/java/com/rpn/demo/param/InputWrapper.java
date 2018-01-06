package com.rpn.demo.param;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputWrapper {

	private String input;
	private int index;
	private int count;
	private StringBuilder builder;
	
}
