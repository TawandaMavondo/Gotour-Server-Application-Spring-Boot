package co.zw.gotour.server.Controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
public interface IController {

    @GetMapping("query")
    public ResponseEntity<?> query(@RequestParam List<String> fields);
    
}
