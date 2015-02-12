package ch.erni.community.idealab.controller;

import ch.erni.community.idealab.dto.Greeting;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author rap
 */
@RestController
public class HelloWorldController {

	private static final String template = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(method = RequestMethod.GET)
	public
	@ResponseBody
	Greeting sayHello(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
