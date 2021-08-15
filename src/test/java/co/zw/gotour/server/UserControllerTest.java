package co.zw.gotour.server;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import co.zw.gotour.server.Service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        userService = mock(UserService.class);

    }

}
