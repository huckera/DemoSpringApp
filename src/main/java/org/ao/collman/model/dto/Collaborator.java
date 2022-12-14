package org.ao.collman.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity // This tells Hibernate to make a table out of this class;
		// parameter "name" assigns the logical name
@Table(name = "Collaborator") // Sets the table name (default table name is the POJO class name)
public class Collaborator {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

	@NotBlank(message = "Name is mandatory")
    private String name;

	@NotBlank(message = "Email is mandatory")
    private String email;

	public Collaborator() {

	}

	public Collaborator(Integer id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Collaborator [id=" + id + ", name=" + name + ", email=" + email + "]";
	}

}