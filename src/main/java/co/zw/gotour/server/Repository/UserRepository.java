package co.zw.gotour.server.Repository;

import co.zw.gotour.server.Model.User;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,String> {

    public User findByUsername(String username);
}
