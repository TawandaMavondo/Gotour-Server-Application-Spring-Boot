package co.zw.gotour.server.Service;

import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {

    UserRepository repository;

    @Autowired
    UserService(UserRepository userRepository) {
        super(userRepository, User.class);
        this.repository = userRepository;
    }

    @Override
    public User save(User entity) throws Exception {
        User user = this.repository.findByUserName(entity.getUserName());
        
        // if (!user.equals(null)) return null;
            //throw new Exception("User with username " + entity.getUserName() + " already exists");

        return super.save(entity);
    }
}
