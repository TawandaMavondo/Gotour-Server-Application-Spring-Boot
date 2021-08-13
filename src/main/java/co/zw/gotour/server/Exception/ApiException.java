package co.zw.gotour.server.Exception;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiException {

    private final String message;
    private final HttpStatus status;
    private final ZonedDateTime timestamp;

    public ApiException(String message, HttpStatus status, ZonedDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

}
