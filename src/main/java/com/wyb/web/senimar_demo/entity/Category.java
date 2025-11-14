package com.wyb.web.senimar_demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class Category {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", columnDefinition = "NVARCHAR(255)")
	private String name;

	public Category(String name) {
		this.name = name;
	}

	public Category() {
		// Default constructor required by JPA
	}

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
	
}
