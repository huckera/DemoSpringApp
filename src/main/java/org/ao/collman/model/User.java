package org.ao.collman.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class;
		// parameter "name" assigns the logical name
@Table(name = "Collaborator") // Sets the table name (default table name is the POJO class name)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private String email;

	public User() {

	}

	public User(Integer id, String name, String email) {
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