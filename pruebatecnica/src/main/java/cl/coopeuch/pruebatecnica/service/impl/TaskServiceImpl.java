package cl.coopeuch.pruebatecnica.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import cl.coopeuch.pruebatecnica.dao.TaskDAO;
import cl.coopeuch.pruebatecnica.dto.TaskDTO;
import cl.coopeuch.pruebatecnica.model.Task;
import cl.coopeuch.pruebatecnica.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	TaskDAO taskdao;
	
	@Override
	public TaskDTO get(Long taskId) {
		try{
			if(taskId==null)
				throw new Exception("Id not assigned");
			
			Task task = taskdao.get(taskId);
			if(task==null)
				throw new Exception("Task not found");
			return taskdao.get(taskId).toDTO();
		}catch(Exception e) {
			logger.error("Task not found");
			return null;
		}
		
	}
	
	@Override
	public List<TaskDTO> getAll() {
		List<TaskDTO> lst = new ArrayList<>();
		taskdao.getAll().forEach((taskModel) -> {
			lst.add(taskModel.toDTO());
		});
		return lst;
	}
	
	@Override
	public List<TaskDTO> getAllNotDeleted() {
		List<TaskDTO> lst = new ArrayList<>();
		taskdao.getAll().forEach((taskModel) -> {
			lst.add(taskModel.toDTO());
		});
		List<TaskDTO> filteredList = lst.stream()
                .filter(task -> !task.getIsDeleted())
                .collect(Collectors.toList());
		return filteredList;
	}

	@Override
	public TaskDTO add(TaskDTO taskRequest) {
		Boolean valid = taskRequest.getValid() != null ? taskRequest.getValid() : false;
		
		Task task = new Task();
		task.setName(taskRequest.getName());
		task.setDescription(taskRequest.getDescription());
		task.setValid(valid);
		task.setIsDeleted(Boolean.FALSE);
		return taskdao.insert(task).toDTO(); 
	}

	@Override
	public TaskDTO replace(TaskDTO taskRequest) {
		try {
			if(taskRequest.getId()==null)
				throw new Exception("Id not assigned");
			
			Task task = taskdao.get(taskRequest.getId());
			
			if(task == null)
				throw new Exception("Task not found");
			
			task.setName(taskRequest.getName());
			task.setDescription(taskRequest.getDescription());
			task.setValid(taskRequest.getValid());
			task = taskdao.update(task);
			return task.toDTO();
			
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public boolean update(Long taskId, Map<String, Object> taskParams) {
		Task task = taskdao.get(taskId);
		
		try {
			if(task == null)
				throw new Exception("Task not found");
			
			taskParams.forEach((key,value) -> {
				Field field = ReflectionUtils.findField(Task.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, task, value);
			});
			
			return taskdao.insert(task) != null;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public void delete(Long taskId) {
		Task task = taskdao.get(taskId);
		task.setIsDeleted(Boolean.TRUE);
		taskdao.update(task);
	}

	

	

}
