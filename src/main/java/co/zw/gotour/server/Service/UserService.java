package co.zw.gotour.server.Service;

import co.zw.gotour.server.Model.User;
import co.zw.gotour.server.Repository.UserRepository;
import co.zw.gotour.server.Util.FirebaseUserMapper;

import com.couchbase.client.java.Cluster;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {

    UserRepository repository;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    FirebaseAuth firebaseAuth;

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository, User.class);
        this.repository = userRepository;
    }

    @Override
    public User save(User entity) throws Exception {
        entity = (User) entity;
        User user = this.repository.findByUsername(entity.getUsername());
        if (user == null)
            return super.save(entity);

        if (user.getUsername().equals(entity.getUsername()) && entity.getId() == null)
            throw new Exception("Username already exists");

        return super.save(entity);
    }

    public User createUserByToken(String token, User userDto) throws FirebaseAuthException {
        FirebaseToken firebaseToken = this.getFirebaseToken(token);
        var firebaseUser = firebaseAuth.getUser(firebaseToken.getUid());

        var existingUser = this.repository.findByEmail(firebaseUser.getEmail());

        if (existingUser != null) {
            return existingUser;
        }

        User user = FirebaseUserMapper.mapToUser(firebaseUser);
        user.setUsername(userDto.getUsername());

        user = this.repository.save(user);

        return user;

    }

    // @Override
    // public Iterable<User> query(String params) {

    //     return this.repository.query("WHERE _class='" + User.class.getName() + "'", User.class);
    // }

    private FirebaseToken getFirebaseToken(String token) {
        try {
            return this.firebaseAuth.verifyIdToken(token);

        } catch (FirebaseAuthException e) {
            throw new SecurityException("Unauthorized");
        }
    }

}
