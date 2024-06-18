package cl.coopeuch.pruebatecnica.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskDTO {
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	private Boolean valid;
	private Boolean isDeleted;
	
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
	public Boolean getValid() {
		return valid;
	}
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public String toString() {
		return "TaskDTO [id=" + id + ", name=" + name + ", description=" + description + ", valid=" + valid
				+ ", isDeleted=" + isDeleted + "]";
	}
}
