package jsonjwt;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;


import com.test.model.Article;
import com.test.model.Role;
import com.test.model.Users;
import com.test.repository.ArticleRepository;
import com.test.repository.RoleRepository;
import com.test.repository.UserRepository;
import com.test.service.UserService;

public class UserServiceMockitoTest {
	private static final Logger logger=LoggerFactory.getLogger(ControllerTestMockMvc.class);
	@InjectMocks
	UserService userService;
	@Mock
	UserRepository userRepo;
	@Mock
	RoleRepository roleRepo;
	@Mock
	ArticleRepository articleRepo;
	
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUserRegistration(){
		
		Users user=new Users();
		user.setName("testuser");
		user.setUserName("usertest123");
		user.setUserEmail("test@gmail.com");
		user.setPassword("user@123456");
		user.setRoles(2);
		userService.save(user);
		verify(userRepo,times(1)).save(user);
	}
	
	@Test
	public void testGetUserByUsername(){
		Users user1=new Users();
		user1.setName("testuser");
		user1.setUserName("usertest123");
		user1.setUserEmail("test@gmail.com");
		user1.setPassword("user@123456");
		user1.setRoles(2);
		when(userRepo.findByUserName("usertest123")).thenReturn(new Users("testuser","usertest123","test@gmail.com","user@123456",2));
		Users test=userService.getUsersByUsername("usertest123");
		assertEquals("testuser",test.getName());
		assertEquals("usertest123",test.getUserName());
		assertEquals("test@gmail.com",test.getUserEmail());
		
	}
	@Test
	public void testGetRoleById(){
		Role role=new Role(1,"ROLE_USER");
		when(roleRepo.findById(1)).thenReturn(role);
		Role roles=userService.getRoleById(1);
		assertEquals(1,roles.getId());
		assertEquals("ROLE_USER",roles.getRoleName());
	}
	
	@Test
	public void testForExistEmail(){
		Users user1=new Users();
		user1.setName("testuser");
		user1.setUserName("usertest123");
		user1.setUserEmail("test@gmail.com");
		user1.setPassword("user@123456");
		user1.setRoles(2);
		when(userRepo.existsByUserEmail("test@gmail.com")).thenReturn(true);
		boolean status=userService.existByEmail("test@gmail.com");
		assertEquals(true,status);
	}
	
	@Test
	public void testForExistUsername(){
		Users user1=new Users();
		user1.setName("testuser");
		user1.setUserName("usertest123");
		user1.setUserEmail("test@gmail.com");
		user1.setPassword("user@123456");
		user1.setRoles(2);
		when(userRepo.existsByUserName("usertest123")).thenReturn(true);
		boolean status=userService.existByUsername("usertest123");
		assertEquals(true,status);
	}
	
	@Test
	public void saveArticleTest(){
		List<Article> article=new ArrayList<Article>();
		Article article0=new Article("test","gvdgvhvhdvvcnvxngdhvndvhgvfghdfghgvgdfdhghjgd","gfhghdjgfdgd","hdgdhsghghdg","abcde","gdgdggdg");
	    article.add(article0);
	   userService.saveArticle(article);
	   verify(articleRepo,times(1)).saveAll(article);
	}
}
