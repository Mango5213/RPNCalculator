package com.rpn.demo;

import java.util.Scanner;

import org.mockito.internal.util.io.IOUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.rpn.demo.service.RPNCalculatorService;

@SpringBootApplication
@ComponentScan("com.rpn.demo")
public class RPNCalculator {

	public static void main(String[] args) {
		SpringApplication.run(RPNCalculator.class, args);
	}

	@Bean
	public CommandLineRunner calculatorInput(RPNCalculatorService calculatorService) {
		Scanner sc = new Scanner(System.in);
		try {
			while (true) {
				System.out.println(calculatorService.process(sc.nextLine()));
			}
		} finally {
			IOUtil.closeQuietly(sc);
		}

	}
}
