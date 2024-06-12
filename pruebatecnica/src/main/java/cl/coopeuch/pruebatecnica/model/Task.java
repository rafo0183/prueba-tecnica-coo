package cl.coopeuch.pruebatecnica.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import cl.coopeuch.pruebatecnica.dto.TaskDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false) 
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description", nullable = false)
	private String description;
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = true)
	private Date createdAt;
	@Column(name = "valid")
	private Boolean valid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Boolean getValid() {
		return valid;
	}
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", createdAt=" + createdAt
				+ ", valid=" + valid + "]";
	}
	public TaskDTO toDTO() {
		TaskDTO dto = new TaskDTO();
		dto.setId(getId());
		dto.setName(getName());
		dto.setDescription(getDescription());
		dto.setValid(getValid());
		return dto;
	}
	
	
}
