package com.rpn.demo;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import org.mockito.internal.util.io.IOUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rpn.demo.service.RPNCalculatorService;

@SpringBootApplication
public class RPNCalculator {

	public static void main(String[] args) {
		SpringApplication.run(RPNCalculator.class, args);
	}

	@Bean
	public CommandLineRunner calculatorInput(RPNCalculatorService calculatorService) {
		Scanner sc = new Scanner(System.in);
		Stack<Double> stack = new Stack<>();
		LinkedList<Stack<Double>> copyList = new LinkedList<Stack<Double>>();
		try {
			while (true) {
				System.out.println(calculatorService.process(sc.nextLine(), stack, copyList));
			}
		} finally {
			IOUtil.closeQuietly(sc);
		}

	}
}
