package co.zw.gotour.server.Service;

import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Repository.UserRepository;
import co.zw.gotour.server.Util.DocumentType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {

    @Autowired
    UserService(UserRepository userRepository) {
        super(userRepository,User.class);
    }

}
