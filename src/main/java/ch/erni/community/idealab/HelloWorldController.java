package ch.erni.community.idealab;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author rap
 */
@Controller
public class HelloWorldController {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

}
