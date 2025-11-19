package com.wyb.web.senimar_demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", columnDefinition = "NVARCHAR(255)")
	@NotBlank(message = "Name is required!")  //validation từ form  nhập đưa lên
	@Size(min = 5, max = 50, message = "Name length must be between 5...50 characters")
	@Pattern(
			regexp = "^(\\p{Lu}\\p{Ll}+)(\\s\\p{Lu}\\p{Ll}+)*$",
			message = "Each word must start with a capital letter, contain only letters (Unicode), no numbers or special characters, and no extra whitespace"
	)
	private String name;

	@Column(name = "image_url")
	private String image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@NotBlank(message = "Quantity is required!")
	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "price")
	@NotBlank(message = "Price is required!")
	private Integer price;

	@Column(name = "weight")
	@NotBlank(message = "Weight is required!")
	private Integer weight;

	@Column(name = "description", columnDefinition = "NVARCHAR(255)")
	@NotBlank(message = "Quantity is required!")
	@Size(min = 5, max = 250, message = "Name length must be between 5...250 characters")
	@Pattern(
			regexp = "^(\\p{Lu}\\p{Ll}+)(\\s\\p{Lu}\\p{Ll}+)*$",
			message = "Each word must start with a capital letter, contain only letters (Unicode), no numbers or special characters, and no extra whitespace"
	)
	private String description;

	public Product() {}

	public Product(String name, String image, Category category, Integer quantity, Integer price, Integer weight, String description) {
		this.name = name;
		this.image = image;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
		this.weight = weight;
		this.description = description;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
