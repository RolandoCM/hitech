package com.app.hitech.dto;

/**
 * @author casti
 *
 */
public class DetailsEmployee {
	
	private Long id;
	private String username;
	private String email;
	private String name;
	private String code;
	private String city;
	private String branch;
	private String profession;
	
	public DetailsEmployee() {
		// TODO Auto-generated constructor stub
	}
	public DetailsEmployee(Long id) {
		this.id=id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	@Override
	public String toString() {
		return "DetailsEmployee [id=" + id + ", username=" + username + ", email=" + email + ", name=" + name
				+ ", code=" + code + ", city=" + city + ", branch=" + branch + ", profession=" + profession + "]";
	}
	
	
	
	public DetailsEmployee username(String username) {
		this.username=username;
		return this;
	}
	public DetailsEmployee theName(String name) {
		this.name=name;
		return this;
	}
	
	public DetailsEmployee email(String email) {
		this.email=email;
		return this;
	}
	public DetailsEmployee code(String code) {
		this.code= code;
		return this;
	}
	public DetailsEmployee atCity(String city) {
		this.city= city;
		return this;
	}
	public DetailsEmployee atBranch( String branch ) {
		this.branch=branch;
		return this;
	}
	public DetailsEmployee withProfession(String profession) {
		this.profession = profession;
		return this;
	}
	public DetailsEmployee build() {
		DetailsEmployee details = new DetailsEmployee();
		details.id = this.id;
		details.username = this.username;
		details.name = this.name;
		details.email = this.email;
		details.code = this.code;
		details.city = this.city;
		details.branch = this.branch;
		details.profession = this.profession;
		
		return details;
	}
	
	
	
	
	
	
	
	
}
