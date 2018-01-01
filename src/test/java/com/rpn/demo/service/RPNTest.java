package com.rpn.demo.service;


import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rpn.demo.service.RPNCalculatorService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RPNCalculatorService.class })
public class RPNTest {
	@Autowired
	protected RPNCalculatorService service;

	// 太多case了 写两个意思意思得了

	Stack<Double> stack = new Stack<>();
	LinkedList<Stack<Double>> copyList = new LinkedList<Stack<Double>>();

	@Before
	public void before() {
		stack = new Stack<>();
		copyList = new LinkedList<Stack<Double>>();
	}

	@Test
	public void testRpn1() {
		String str = service.process("5 4 3 2", stack, copyList);
		assertEquals(str, "stack: 5 4 3 2");
		str = service.process("undo undo *", stack, copyList);
		assertEquals(str, "stack: 20");
		str = service.process("5 *", stack, copyList);
		assertEquals(str, "stack: 100");
		str = service.process("undo", stack, copyList);
		assertEquals(str, "stack: 20 5");
	}

	@Test
	public void testRpn2() {
		String str = service.process("1 2 3 * 5 + * * 6 5", stack, copyList);
		assertEquals(str, "operator * (position: 15): insucient parameters\nstack: 11");
	}

}
