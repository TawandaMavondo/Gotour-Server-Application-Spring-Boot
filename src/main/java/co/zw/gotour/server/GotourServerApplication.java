package co.zw.gotour.server;

import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GotourServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GotourServerApplication.class, args);
    }

}
