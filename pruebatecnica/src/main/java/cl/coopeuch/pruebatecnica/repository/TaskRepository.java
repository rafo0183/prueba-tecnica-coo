package cl.coopeuch.pruebatecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.coopeuch.pruebatecnica.model.Task;

public interface TaskRepository  extends JpaRepository<Task, Long>{
	
}
