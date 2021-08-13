package co.zw.gotour.server.Exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.zw.gotour.server.Model.User;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleApiException(ApiRequestException e) {

        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException); // return
                                                                                 // ResponseEntity.badRequest().body(apiException);
    }
}
