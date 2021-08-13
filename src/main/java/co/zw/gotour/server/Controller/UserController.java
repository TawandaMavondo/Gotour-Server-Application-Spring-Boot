package co.zw.gotour.server.Controller;

import co.zw.gotour.server.Configuration.LoggerConfig;
import co.zw.gotour.server.Exception.ApiRequestException;
import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Service.UserService;
import io.sentry.spring.tracing.SentrySpan;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User Endpoints")
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    private final Logger logger = LogManager.getLogger();

    @PostMapping()
    @SentrySpan
    @ApiResponses(value = {

            @ApiResponse(responseCode = "201", description = "Successfully Created User ", content = @Content(schema = @Schema(implementation = User.class))),

            // TODO: Later insert the content of the Global Custon APIException Handler
            // Response
            @ApiResponse(responseCode = "400", description = "Username Already Exists")

    })
    public ResponseEntity<Object> createUser(@RequestBody User user) {

        try {
            var saved = userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping()
    @SentrySpan
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity.ok(this.userService.find());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id) {
        user.setId(id);
        try {
            return ResponseEntity.ok(this.userService.update(user));
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }

}
