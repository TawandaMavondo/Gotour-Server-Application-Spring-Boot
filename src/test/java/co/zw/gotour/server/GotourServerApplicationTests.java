package co.zw.gotour.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Service.UserService;

@SpringBootTest
@Transactional
@ContextConfiguration
class UserServiceTest {
	@Autowired
	UserService userService;

	@Test
	void checkUserServiceIsNotNull() {
		assertThat(userService).isNotNull();
	}

	@Test
	@Rollback(true)
	void testSaveUserDocument() throws Exception{
		User user = new User();

		user.setEmail("testemail@gmail.com");
		user.setFirstname("Testname");
		user.setLastname("testlastname");
		user.setUsername("testusername");
		User savedUser = this.userService.save(user);

		assertThat(savedUser.getId()).isNotNull();
		assertThat(savedUser.entityType).isNotNull();

		
		

	}

}
