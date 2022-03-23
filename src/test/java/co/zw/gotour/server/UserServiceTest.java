package co.zw.gotour.server;

import com.google.firebase.auth.FirebaseAuth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Repository.UserRepository;
import co.zw.gotour.server.Service.UserService;

@SpringBootTest
class UserServiceTest {

	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private FirebaseAuth firebaseAuth;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		this.userService = mock(UserService.class);
		this.userService = new UserService(userRepository,this.firebaseAuth);
	}

	// @Test
	// void checkUserServiceIsNotNull() {
	// assertThat(userService).isNotNull();
	// }

	@Test
	void testSaveUserDocument() throws Exception {
		User user = MockData.getUsers().get(0);

		User entity = user;
		entity.setId("6b32f583-4d22-404b-9d00-a1c7fa39534d");

		doReturn(entity).when(userRepository).save(user);

		User saved = userService.save(user);
		assertThat(saved.getId()).isNotNull();

	}

}
