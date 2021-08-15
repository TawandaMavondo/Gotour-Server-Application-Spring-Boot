package co.zw.gotour.server;

import java.util.List;

import co.zw.gotour.server.Model.User;

public class MockData {

    static List<User> getUsers() {
        User user = new User();
        user.setEmail("testemail@gmail.com");
        user.setFirstname("Testname");
        user.setLastname("testlastname");
        user.setUsername("testusername");
        return List.of(user);
    }

}
