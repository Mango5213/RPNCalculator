package com.rpn.demo.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rpn.demo.support.StackContextHolder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RPNCalculatorService.class, OperatorFactory.class })
public class RPNTest {
	@Autowired
	protected RPNCalculatorService service;

	@Before
	public void before() {
		StackContextHolder.INSTANCE.getStack().clear();
		StackContextHolder.INSTANCE.getBackupList().clear();
	}

	@Test
	public void testRpn1() {
		String str = service.process("5 4 3 2");
		assertEquals(str, "stack: 5 4 3 2");
		str = service.process("undo undo *");
		assertEquals(str, "stack: 20");
		str = service.process("5 *");
		assertEquals(str, "stack: 100");
		str = service.process("undo");
		assertEquals(str, "stack: 20 5");
	}

	@Test
	public void testRpn2() {
		String str = service.process("1 2 3 * 5 + * * 6 5");
		assertEquals(str, "operator * (position: 15): insucient parameters\nstack: 11");
	}

}
