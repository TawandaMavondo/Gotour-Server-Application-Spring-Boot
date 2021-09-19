
package co.zw.gotour.server.Controller;

import java.util.regex.Pattern;

import com.google.firebase.auth.FirebaseAuthException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import co.zw.gotour.server.Exception.ApiRequestException;
import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;

@RestController
@Tag(name = "Authentication Endpoints")
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    // @Autowired
    // UserService userService;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(value = "user")
    public ResponseEntity<User> createUser(@RequestHeader("Authorization") String authorization,
            @RequestBody User user) {
        var token = this.getToken(authorization);
        // logger.info(user.toString());
        try {
            throw new FirebaseAuthException(null);
            // ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUserByToken(token,user));
        } catch (FirebaseAuthException e) {
            throw new ApiRequestException(e.getMessage());
        }

    }

    // TODO: Get user by Token.
    private String getToken(String authorization) {
        return authorization.replace("Bearer ", "").trim();
    }
}
