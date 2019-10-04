package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.SearchedWord;
import com.test.model.Users;
import com.test.repository.ArticleRepository;
import com.test.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/user")
public class AuthorizationController extends ExceptionHandlingController {
@Autowired UserService userService;
@Autowired ArticleRepository articleRepo;
//	@GetMapping("/api/test/user/saveArticle")
//	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER')")
//	public boolean addToBookMarks(Users user) {
//		Users users=userService.getUsersByUsername(user.getUserName());
//		users.getArticles().add(user.getArticles().get(0));
//		userService.saveArticle(user.getArticles());
//		return true;	
//	}
//	@GetMapping("/api/test/user/favourites")
//	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER')")
//	public List<Article> getAllBookmarks(Users user){
//			Users userArticles= userService.getAllArticlesByName(user.getUserName());
//			return userArticles.getArticles();
//	}
	
	@PostMapping("searchedWord")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER')")
	public ResponseEntity<Boolean> saveSearchedWord(@RequestBody SearchedWord searchedWord ){
		return new ResponseEntity<Boolean>(userService.saveSearchedKeyword(searchedWord),HttpStatus.CREATED);
	}
	@GetMapping("getAllSearchedWords/{userName}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER')")
	public ResponseEntity<List<SearchedWord>> getAllSearchedWords(@PathVariable String userName){
		return new ResponseEntity<List<SearchedWord>>(userService.getAllSearchedWords(userName),HttpStatus.OK);
	}
	@DeleteMapping("deleteSearchedWords/{searchedId}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER')")
	public boolean deleteSearchedWords(@PathVariable int searchedId){
		System.out.println(searchedId);
		return userService.deleteSearchedWord(searchedId);
	}
	@GetMapping("blacklist/{username}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER')")
	public List<Users> getUsers(@PathVariable String username){
		return userService.searchByUsername(username);
	}
	@PatchMapping("blacklist/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER')")
	public boolean blockUsers(@PathVariable long id){
		return userService.deleteUser(id);
	}
	
}
