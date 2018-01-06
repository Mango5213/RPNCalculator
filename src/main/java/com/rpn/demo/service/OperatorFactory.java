package com.rpn.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class OperatorFactory implements ApplicationContextAware {
	private static Map<String, Operation> operationBeanMap;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, Operation> map = applicationContext.getBeansOfType(Operation.class);
		operationBeanMap = new HashMap<>();
		map.forEach((key, value) -> operationBeanMap.put(value.getOperator(), value));
	}

	@SuppressWarnings("unchecked")
	public static <T extends Operation> T getOperation(String operator) {
		return (T) operationBeanMap.get(operator);
	}
}
