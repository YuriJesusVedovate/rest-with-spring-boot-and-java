package br.com.yuri.controllers;

import java.util.concurrent.atomic.AtomicLong;

import br.com.yuri.models.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

    @GetMapping("/api/greeting")
	public Greeting greeting(
			@RequestParam(defaultValue = "World") String name) 
	{
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
