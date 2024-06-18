package cl.coopeuch.pruebatecnica.service;

import java.util.List;
import java.util.Map;

import cl.coopeuch.pruebatecnica.dto.TaskDTO;

public interface TaskService {
	TaskDTO add(TaskDTO taskRequest);
	
	TaskDTO replace(TaskDTO taskRequest) ;
	
	boolean update(Long taskId, Map<String, Object> taskParams);
	
	TaskDTO get(Long taskId);
	
	List<TaskDTO> getAll();
	
	void delete(Long taskId) ;

	List<TaskDTO> getAllNotDeleted();
}
