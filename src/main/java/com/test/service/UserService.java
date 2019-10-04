package com.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.Article;
import com.test.model.Role;
import com.test.model.SearchedWord;
import com.test.model.Users;
import com.test.repository.ArticleRepository;
import com.test.repository.RoleRepository;
import com.test.repository.SearchedWordRespository;
import com.test.repository.UserRepository;

@Service
@Transactional
public class UserService  implements UserDetailsService{
	@Autowired UserRepository userRepo;
	@Autowired RoleRepository roleRepo;
	@Autowired ArticleRepository articleRepo;
	@Autowired SearchedWordRespository searchedRepository;
	
	public Users save(Users user){
		System.out.println("In Service");
		return userRepo.save(user);
	}
	
	public Users getUsersByUsername(String userName){
		return userRepo.findByUserName(userName);
	}
	
	public Role getRoleById(int roleId){
		return roleRepo.findById(roleId);
	}
	
	public List<Role> getAllRoles(){
		return (List<Role>)(roleRepo.findAll());
	}
	
	public boolean existByEmail(String email){
		return userRepo.existsByUserEmail(email);
	}
	public boolean existByUsername(String userName){
		return userRepo.existsByUserName(userName);
	}
	
	public boolean saveArticle(List<Article> list){
		if(articleRepo.saveAll(list)!=null)
			return true;
		else 
			return false;
	}
	public Users getAllArticlesByName(String userName){
		return  userRepo.findByUserName(userName);
		
	}
	public boolean saveSearchedKeyword(SearchedWord searchedWord){
		searchedRepository.save(searchedWord);
		return true;
		
	}
	public List<SearchedWord> getAllSearchedWords(String userName){
		 return searchedRepository.findByUserName(userName);
	}
	public boolean deleteSearchedWord(int searchId){
		 searchedRepository.deleteById(searchId);
		 return true;
	}
	public List<Users> searchByUsername(String username){
		return userRepo.searchUsersByUsername(username);
	}
	public boolean deleteUser(long id){
		Users user=userRepo.findById(id).get();
		user.setStatus(false);
		userRepo.save(user);
		return true;
		
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
			Users users=userRepo.findByUserName(userName);
			Role roles=roleRepo.findById(users.getRoles());
			List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
			User user=new User(users.getUserName(),users.getPassword(),authorities);
			return user;
	}
	

//	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
