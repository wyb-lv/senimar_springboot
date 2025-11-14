package com.wyb.web.senimar_demo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username", unique = true, nullable = false, columnDefinition = "NVARCHAR(255)")
	private String username;

	@Column(name = "email", unique = true, nullable = false, columnDefinition = "NVARCHAR(255)")
	private String email;

	@Column(name = "password", nullable = false, columnDefinition = "NVARCHAR(255)")
	private String password;

	@Column(name = "role", columnDefinition = "NVARCHAR(50)")
	private String role;

	@Column(name = "address", nullable = true, columnDefinition = "NVARCHAR(500)")
	private String address;

	public Account(String username, String email, String password, String role, String address) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.address = address;
	}

	public Account() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}