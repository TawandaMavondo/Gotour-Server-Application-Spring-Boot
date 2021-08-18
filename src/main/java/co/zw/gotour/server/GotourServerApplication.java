package co.zw.gotour.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@EnableAutoConfiguration

public class GotourServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GotourServerApplication.class, args);
    }

}
