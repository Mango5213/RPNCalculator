package com.rpn.demo.constant;

public enum RPNConstants {

	INSTANCE;

	public static class Operators {

	public static final String ADDITION = "+";
	public static final String SUBTRACTION = "-";
	public static final String MULTIPLICATION = "*";
	public static final String DIVISION = "/";
	public static final String SQUAREROOT = "sqrt";
	public static final String UNDO = "undo";
	public static final String CLEAR = "clear";
	}
	
	public static class ErrorMsg {
		public static final String INPUT_LENGTH_NOT_VALID = "Input length should between 1 to 100";
		public static final String INSUCIENT_PARAMETERS = "operator %s (position: %s): insucient parameters\n";
	}

}
