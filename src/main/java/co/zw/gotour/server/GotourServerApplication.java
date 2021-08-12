package co.zw.gotour.server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class GotourServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GotourServerApplication.class, args);
    }

}
