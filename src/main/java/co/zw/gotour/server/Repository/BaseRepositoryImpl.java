package co.zw.gotour.server.Repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.zw.gotour.server.Configuration.CouchbaseConfiguration;
import co.zw.gotour.server.Model.Model;

@Repository
public class BaseRepositoryImpl<T extends Model> implements BaseRepository<T> {


    @Autowired
    private Cluster cluster;

    @Autowired
    CouchbaseConfiguration couchbaseConfiguration;

    @Override
    public List<T> query(String where, Class<T> entityClass) {
        return null;
    }

    @Override
    public <S extends T> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<T> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterable<T> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(T entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

}
