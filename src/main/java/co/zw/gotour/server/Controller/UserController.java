package co.zw.gotour.server.Controller;

import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Service.UserService;
import io.sentry.spring.tracing.SentrySpan;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User Endpoints")
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    @SentrySpan
    public ResponseEntity<?> createUser() throws Exception {
        User user = new User();
        user.setLastName("Mavondo");
        user.setFirstName("Tawanda");
        user.setEmail("email@g.com");
        user.setUserName("tawawandag2");

        try {
            var saved = userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    @SentrySpan
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity.ok(this.userService.find());
    }

}
