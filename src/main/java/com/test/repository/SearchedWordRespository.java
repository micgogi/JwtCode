package com.test.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.test.model.SearchedWord;

public interface SearchedWordRespository extends CrudRepository<SearchedWord,Integer> {

	List<SearchedWord> findByUserName(String userName);

}
