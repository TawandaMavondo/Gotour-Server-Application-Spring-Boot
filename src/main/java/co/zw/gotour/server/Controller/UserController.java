package co.zw.gotour.server.Controller;

import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Repository.UserRepository;
import co.zw.gotour.server.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User Endpoints")
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<User> getUser() throws Exception {
        User user = new User();
        user.setLastname("Mavondo");
        user.setFirstname("Tawanda");
        var saved = userService.save(user);
        return ResponseEntity.ok(saved);
    }

}
