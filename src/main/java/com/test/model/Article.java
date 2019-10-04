package com.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="articles")
public class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int articleId;
	String title;
	@Size(min=1)
	String description;
	public Article() {
		
	}
	public Article(String title, @Size(min = 1) String description, String urlToImage, String url, String author,
			String publishedAt) {

		this.title = title;
		this.description = description;
		this.urlToImage = urlToImage;
		this.url = url;
		this.author = author;
		this.publishedAt = publishedAt;
	}
	String urlToImage;
	String url;
	String author;
	String publishedAt;
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrlToImage() {
		return urlToImage;
	}
	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}
	
}
