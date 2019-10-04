package com.test.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class Users {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 long id;
 
 @NotBlank
 @Size(min=1,max=50)
 String name;
 @NotBlank
 @Size(min=1,max=50)
 String userName;
 @NotBlank
 @Size(min=5,max=50)
 @Email
 String userEmail;
 @NotBlank
 @Size(min=8)
 String password;
 @NotNull
 int roles;
 public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
boolean status;
 
 @ManyToMany(fetch = FetchType.LAZY)
 @JoinTable(name = "user_articles", 
 	joinColumns = @JoinColumn(name = "user_id"), 
 	inverseJoinColumns = @JoinColumn(name = "article_id"))
 public List<Article> articles;
 
 public Users(@NotBlank @Size(min = 1, max = 50) String name, @NotBlank @Size(min = 1, max = 50) String userName,
		@NotBlank @Size(min = 5, max = 50) @Email String userEmail, @NotBlank @Size(min = 8) String password,
		@NotNull int roles) {
	
	this.name = name;
	this.userName = userName;
	this.userEmail = userEmail;
	this.password = password;
	this.roles = roles;
}
public List<Article> getArticles() {
	return articles;
}
public void setArticles(List<Article> articles) {
	this.articles = articles;
}
public Users(){
	 
 }
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserEmail() {
	return userEmail;
}
public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getRoles() {
	return roles;
}
public void setRoles(int roles) {
	this.roles = roles;
}
@Override
public boolean equals(Object obj) {
	Users user=(Users)obj;
	if(this.getUserEmail().equals(user.getUserEmail()))
		return true;
	if(this.getUserName().equals(user.getUserName()))
		return true;
	return false;
}
public Users(@NotBlank @Size(min = 1, max = 50) String userName, @NotBlank @Size(min = 8) String password) {
	super();
	this.userName = userName;
	this.password = password;
}

}
