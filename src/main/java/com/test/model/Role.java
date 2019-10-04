package com.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	String roleName;

	public int getId() {
		return id;
	}

	public Role() {
		
	}

	public Role(int id, String roleName) {
		
		this.id = id;
		this.roleName = roleName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
