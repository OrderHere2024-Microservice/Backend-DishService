package com.orderhere.dish.DishService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(properties = "spring.profiles.active=default")
class DishServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void applicationStarts() {
		assertDoesNotThrow(() -> SpringApplication.run(DishServiceApplication.class));
	}

}
