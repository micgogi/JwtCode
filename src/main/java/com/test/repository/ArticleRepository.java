package com.test.repository;

import org.springframework.data.repository.CrudRepository;

import com.test.model.Article;

public interface ArticleRepository extends CrudRepository<Article,Integer> {

}
