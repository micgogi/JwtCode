package jsonjwt;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.ApplicationMain;
import com.test.model.JwtResponse;
import com.test.model.ResponseMessage;
import com.test.model.Users;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ApplicationMain.class,webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerTestMockMvc {
	private static final Logger logger=LoggerFactory.getLogger(ControllerTestMockMvc.class);
	
	@Autowired TestRestTemplate testTemplate;

//	@Test
//	public void testSuccesfullLogin(){
//		Users user=new Users();
//		user.setUserName("");
//		
//	}
	
	@Test
	public void testSuccesfulSignUp(){
		Users user=new Users("userfgf1234","user12ff3456","userfffasdfgh@gmail.com","user@123456",2);
		ResponseEntity<ResponseMessage> response=testTemplate.postForEntity("/api/auth/signup", user, ResponseMessage.class);
		logger.info("Response-{}",response.getBody().getMessage());
		assertThat(response.getBody().getMessage(),containsString("user registration succesful"));
	}
	@Test
	public void testExistingUrname(){
		Users user=new Users("usergff1234","user123456","usdfferasdfgh@gmail.com","user@123456",2);
		ResponseEntity<ResponseMessage> response=testTemplate.postForEntity("/api/auth/signup", user, ResponseMessage.class);
		logger.info("Response-{}",response.getBody().getMessage());
		assertThat(response.getBody().getMessage(),containsString("username already exists"));
	}
	@Test
	public void testExistingemail(){
		Users user=new Users("user1234","user123456","userasdfgh@gmail.com","user@123456",2);
		ResponseEntity<ResponseMessage> response=testTemplate.postForEntity("/api/auth/signup", user, ResponseMessage.class);
		logger.info("Response-{}",response.getBody().getMessage());
		assertThat(response.getBody().getMessage(),containsString("email already exists"));
	}
	@Test
	public void testSuccesfulLogin(){
		Users user=new Users("user123456","user@123456");
		ResponseEntity<JwtResponse> response=testTemplate.postForEntity("/api/auth/signin",user, JwtResponse.class);
		logger.info("Response->{}",response.getStatusCode());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	@Test
	public void testUnsuccesfulLogin(){
		Users user=new Users("user123456","usghfdgjer@123456");
		ResponseEntity<JwtResponse> response=testTemplate.postForEntity("/api/auth/signin",user, JwtResponse.class);
		logger.info("Response->{}",response.getStatusCode());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.BAD_REQUEST));
	}
	
	
	@Test
	public void testEmptyUserName(){
		Users user=new Users("userfgf123fhhj4","","ussderfffasdfgh@gmail.com","user@123456",2);
		ResponseEntity<ResponseMessage> response=testTemplate.postForEntity("/api/auth/signup", user, ResponseMessage.class);
		logger.info("Response->{}", response.getBody().getMessage());
		assertThat(response.getBody().getMessage(),containsString("Error"));
	}
	@Test
	public void testEmptyName(){
		Users user=new Users("","fdghjkfdkhfkdhjfjk","ussderfffasdfgh@gmail.com","user@123456",2);
		ResponseEntity<ResponseMessage> response=testTemplate.postForEntity("/api/auth/signup", user, ResponseMessage.class);
		logger.info("Response->{}", response.getBody().getMessage());
		assertThat(response.getBody().getMessage(),containsString("Error:"));
	}
	@Test
	public void testEmptyPassword(){
		Users user=new Users("gfbghkh","fdghjgjkkfdkhfkdhjfjk","ufssderfffasdfgh@gmail.com","",2);
		ResponseEntity<ResponseMessage> response=testTemplate.postForEntity("/api/auth/signup", user, ResponseMessage.class);
		logger.info("Response->{}", response.getBody().getMessage());
		assertThat(response.getBody().getMessage(),containsString("Error:"));
	}
	@Test
	public void testSmallPassword(){
		Users user=new Users("userfgf1234","tst","userfffffasdfgh@gmail.com","user",2);
		ResponseEntity<ResponseMessage> response=testTemplate.postForEntity("/api/auth/signup", user, ResponseMessage.class);
		logger.info("Response-{}",response.getBody().getMessage());
		assertThat(response.getBody().getMessage(),containsString("Error:"));
	}
	
	
	
}
