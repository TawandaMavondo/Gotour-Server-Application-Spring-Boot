package co.zw.gotour.server.Repository;

import co.zw.gotour.server.Model.User;

import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,String> {

    @Query("#{#n1ql.selectEntity} WHERE userName = $1 AND #{#n1ql.filter}")
    public User findByUserName(String username);
}
