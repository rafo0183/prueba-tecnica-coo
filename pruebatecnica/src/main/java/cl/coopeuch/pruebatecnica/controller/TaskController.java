package cl.coopeuch.pruebatecnica.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	private static Logger logger = LogManager.getLogger();
	
	
	private static final String MSG_TASK_UPDATED = "Task updated";
	private static final String WRONG_DATA = "Wrong data";
	
	@Autowired
	TaskService taskService;  

	
	@GetMapping("/get/{taskId}")
	@ResponseBody
	public Optional<TaskDTO> get(@PathVariable("taskId") Long taskId) {
		logger.info(String.format("Call of get(), taskId = %s", taskId));
		return Optional.ofNullable(taskService.get(taskId));
	} 
	
	@GetMapping("/getAll")
	@ResponseBody
	public List<TaskDTO> getAll() {
		logger.info("Call of getAll()");
		return taskService.getAllNotDeleted();
	} 
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<?> add(@Valid @RequestBody TaskDTO taskReq) {
		logger.info("Call add() , task: " + taskReq.toString());
		TaskDTO taskRes = taskService.add(taskReq);
		if(taskRes != null) {
			 return new ResponseEntity<>(taskRes, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/replace")
	@ResponseBody
	public ResponseEntity<?> replace(@Valid @RequestBody TaskDTO task) {
		logger.info("Call of replace()");
		TaskDTO taskRes = taskService.replace(task);
		if(taskRes != null) {
			return new ResponseEntity<>(taskRes, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping("/update/{taskId}")
	public ResponseEntity<?> update(@RequestBody Map<String, Object> taskParams, @PathVariable("taskId") String taskId) {
		logger.info(String.format("Call of update(), taskParams = %s , taskId = %s", taskParams, taskId));
		if(taskService.update(Long.parseLong(taskId), taskParams)) {
			return ResponseEntity.ok(new ResponseHttp(ResponseHttp.STATUS_DONE, MSG_TASK_UPDATED));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseHttp(ResponseHttp.STATUS_ERROR, WRONG_DATA));
		}
	}
	
	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<?> delete(@PathVariable("taskId") String taskId) {
		logger.info(String.format("Call of delete() taskId = %s", taskId));
		if(taskId==null) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseHttp(ResponseHttp.STATUS_ERROR, WRONG_DATA));
		
		taskService.delete(Long.parseLong(taskId));
		return ResponseEntity.ok(new ResponseHttp(ResponseHttp.STATUS_DONE, MSG_TASK_UPDATED));
	}
}
