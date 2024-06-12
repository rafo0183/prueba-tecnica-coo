package cl.coopeuch.pruebatecnica.service;

import java.util.List;
import java.util.Map;

import cl.coopeuch.pruebatecnica.dto.TaskDTO;

public interface TaskService {
	boolean add(TaskDTO taskRequest);
	
	boolean replace(TaskDTO taskRequest) ;
	
	boolean update(Long taskId, Map<String, Object> taskParams);
	
	TaskDTO get(Long taskId);
	
	List<TaskDTO> getAll();
}
