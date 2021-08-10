package co.zw.gotour.server.Controller;

import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import com.couchbase.client.core.msg.Response;

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
    public ResponseEntity<User> getUser() throws Exception {
        User user = new User();
        user.setLastName("Mavondo");
        user.setFirstName("Tawanda");
        user.setEmail("email@g.com");
        user.setUserName("tawawandag");
        var saved = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping()
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity.ok(this.userService.find());
    }

}
