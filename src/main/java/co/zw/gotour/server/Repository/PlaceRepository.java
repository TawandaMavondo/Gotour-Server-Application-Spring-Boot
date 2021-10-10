package co.zw.gotour.server.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import co.zw.gotour.server.Model.User;

@Service
public class PlaceRepository extends AbstractCouchbaseRepository<User> {

    PlaceRepository() {
        super(User.class, "user");
    }

}
