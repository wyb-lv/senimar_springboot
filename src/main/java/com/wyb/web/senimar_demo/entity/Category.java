package com.wyb.web.senimar_demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "CATEGORY")
public class Category {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", columnDefinition = "NVARCHAR(255)")
	@NotBlank(message = "Name is required!")  //validation từ form nhập đưa lên
	@Pattern(
			regexp = "^(\\p{Lu}\\p{Ll}+)(\\s\\p{Lu}\\p{Ll}+)*$",
			message = "Each word must start with a capital letter, contain only letters (Unicode), no numbers or special characters, and no extra whitespace"
	)

	private String name;

	public Category(String name) {
		this.name = name;
	}

	public Category() {}

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
