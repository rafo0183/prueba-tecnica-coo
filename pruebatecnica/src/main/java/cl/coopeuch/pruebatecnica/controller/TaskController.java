package cl.coopeuch.pruebatecnica.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import cl.coopeuch.pruebatecnica.response.ResponseHttp;

import cl.coopeuch.pruebatecnica.dto.TaskDTO;
import cl.coopeuch.pruebatecnica.service.TaskService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
	private static final Logger log = Logger.getLogger(TaskController.class);
	
	private static final String MSG_TASK_ADDED = "Task added";
	private static final String MSG_TASK_UPDATED = "Task updated";
	private static final String WRONG_DATA = "Wrong data";
	
	@Autowired
	TaskService taskService;  

	
	@GetMapping("/get/{taskId}")
	@ResponseBody
	public Optional<TaskDTO> get(@PathVariable("taskId") Long taskId) {
		return Optional.ofNullable(taskService.get(taskId));
	} 
	
	@GetMapping("/getAll")
	@ResponseBody
	public List<TaskDTO> getAll() {
		return taskService.getAll();
	} 
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody TaskDTO task) {
		log.info("adding task " + task.toString());
		if(taskService.add(task)) {
			return ResponseEntity.ok(new ResponseHttp(ResponseHttp.STATUS_DONE, MSG_TASK_ADDED));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseHttp(ResponseHttp.STATUS_ERROR, WRONG_DATA));
		}
		
	}
	
	@PutMapping("/replace")
	public ResponseEntity<?> replace(@Valid @RequestBody TaskDTO task) {
		log.info("replace task " + task.toString());
		if(taskService.replace(task)) {
			return ResponseEntity.ok(new ResponseHttp(ResponseHttp.STATUS_DONE, MSG_TASK_UPDATED));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseHttp(ResponseHttp.STATUS_ERROR, WRONG_DATA));
		}
	}
	
	@PatchMapping("/update/{taskId}")
	public ResponseEntity<?> update(@RequestBody Map<String, Object> taskParams, @PathVariable("taskId") String taskId) {
		log.info("update task " + taskParams.toString());
		if(taskService.update(Long.parseLong(taskId), taskParams)) {
			return ResponseEntity.ok(new ResponseHttp(ResponseHttp.STATUS_DONE, MSG_TASK_UPDATED));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseHttp(ResponseHttp.STATUS_ERROR, WRONG_DATA));
		}
	}
}
