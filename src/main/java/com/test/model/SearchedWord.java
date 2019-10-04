package com.test.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="searchedWords")
public class SearchedWord {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
int searchId;
String searchedWord;
String userName;
@CreationTimestamp
LocalDateTime date;
public LocalDateTime getDate() {
	return date;
}
public void setDate(LocalDateTime date) {
	this.date = date;
}
public int getSearchId() {
	return searchId;
}
public SearchedWord() {

}
public SearchedWord(String searchedWord, String userName) {

	this.searchedWord = searchedWord;
	this.userName = userName;
}
public void setSearchId(int searchId) {
	this.searchId = searchId;
}
public String getSearchedWord() {
	return searchedWord;
}
@Override
public String toString() {
	return "SearchedWord [searchId=" + searchId + ", searchedWord=" + searchedWord + ", userName=" + userName + "]";
}
public void setSearchedWord(String searchedWord) {
	this.searchedWord = searchedWord;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
}
