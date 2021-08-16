package co.zw.gotour.server.Controller;

import java.util.regex.Pattern;

import com.google.firebase.auth.FirebaseAuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.zw.gotour.server.Exception.ApiRequestException;
import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Authentication Endpoints")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping(value = "user")
    public ResponseEntity<User> createUser(@RequestHeader("Authorization") String authorization) {
        var token = this.getToken(authorization);

        try {
            User user = this.userService.createUserByToken(token);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (FirebaseAuthException e) {
            throw new ApiRequestException(e.getMessage());
        }

    }
    // TODO: Get user by Token.

    private String getToken(String authorization) {
        return authorization.replace(Pattern.compile("^Bearer ").pattern(), "");
    }
}
