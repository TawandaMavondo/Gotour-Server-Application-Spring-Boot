package co.zw.gotour.server.Repository;

import co.zw.gotour.server.Model.User;

import com.couchbase.client.java.query.QueryScanConsistency;
import org.springframework.context.annotation.Scope;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.couchbase.repository.Collection;

@Repository("userRepository")
@Scope("default")
@Collection("user")
//@ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
public interface UserRepository extends CrudRepository<User, String>, BaseRepository<User> {

    public User findByUsername(String username);

    public User findByFirebaseId(String id);

    public User findByEmail(String email);
}
