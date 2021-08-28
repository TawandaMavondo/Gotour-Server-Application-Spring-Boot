package co.zw.gotour.server.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.zw.gotour.server.types.QueryParam;

@CrossOrigin
public interface IController {

    @GetMapping("query")
    public ResponseEntity<?> query(@RequestBody QueryParam params);

}
