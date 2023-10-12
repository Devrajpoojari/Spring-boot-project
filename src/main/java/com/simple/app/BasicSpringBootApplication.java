package com.simple.app;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class BasicSpringBootApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BasicSpringBootApplication.class, args); // ioc

//		Employee e1 = ctx.getBean(Employee.class);
//		System.out.println(e1.welcome());
//		Stream.of(-2,3,-5,10,12,7,3-10,340).filter(num->num%2==0).forEach(a-> System.out.println(a+" "));

		String string = "TelcO".chars().mapToObj(a -> (char) a).collect(Collectors.toList()).stream().map(b -> {
			if (Character.isUpperCase(b)) {
				return Character.toLowerCase(b);
			} else {
				return Character.toUpperCase(b);
			}
		}).map(String::valueOf).collect(Collectors.joining());
		System.out.println(string);
		Scanner sc = new Scanner(System.in);
		while (true) {
			int num = sc.nextInt();
			if (num>=2 && IntStream.range(2, num).noneMatch(i -> num % i == 0)) {
				System.out.println("is a Prime number");
			} else {
				System.out.println("is Not a Prime number");
			}
		}

	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.simple.app")).build();
	}

	// http://localhost:1430/swagger-ui.html#/

}
