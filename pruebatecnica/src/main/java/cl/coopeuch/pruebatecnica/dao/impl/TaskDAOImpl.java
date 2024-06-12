package cl.coopeuch.pruebatecnica.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cl.coopeuch.pruebatecnica.dao.TaskDAO;
import cl.coopeuch.pruebatecnica.model.Task;
import cl.coopeuch.pruebatecnica.repository.TaskRepository;

@Repository
public class TaskDAOImpl implements TaskDAO{
	
	@Autowired
	TaskRepository taskRepository;

	@Override
	public Task insert(Task t) {
		return taskRepository.save(t);
	}

	@Override
	public void update(Task t) {
		taskRepository.save(t);
		
	}

	@Override
	public void delete(Task t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Task> getAll() {
		return taskRepository.findAll();
	}

	@Override
	public Task get(Long id) {
		Optional<Task> opt = taskRepository.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

}
