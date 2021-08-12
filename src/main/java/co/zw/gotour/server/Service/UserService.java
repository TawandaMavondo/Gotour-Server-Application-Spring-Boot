package co.zw.gotour.server.Service;

import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {

    UserRepository repository;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserService(UserRepository userRepository) {
        super(userRepository, User.class);
        this.repository = userRepository;
    }

    @Override
    public User save(User entity) throws Exception {
        User user = this.repository.findByUserName(entity.getUserName());

        if (user != null)
        throw new Exception("Username already exists");
        return super.save(entity);
    }
}
