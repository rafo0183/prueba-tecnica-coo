package cl.coopeuch.pruebatecnica.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hello-world")
public class HelloWorldController {
	
	@GetMapping("/greetings")
	public ResponseEntity<?> greetings() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("return", "Hello World");
		map.put("comment", "Ignore this service");
		return ResponseEntity.ok(map);
	} 
}
