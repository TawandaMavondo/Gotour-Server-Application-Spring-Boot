package co.zw.gotour.server.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.zw.gotour.server.Configuration.CouchbaseConfiguration;
import co.zw.gotour.server.Model.Model;

@Repository
public class BaseRepositoryImpl<T extends Model> implements BaseRepository<T> {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private Cluster cluster;

    @Autowired
    CouchbaseConfiguration couchbaseConfiguration;
    Logger logger = LoggerFactory.getLogger(BaseRepositoryImpl.class);

    @Override
    public List<T> query(String where, Class<T> entityClass) {
        Bucket bucket = this.cluster.bucket(this.couchbaseConfiguration.getBucketName());

        Query query = entityManager.createQuery("SELECT * FROM " + bucket.name() + " " + where, entityClass);

        this.logger.info("The Query Function has been excercuted");
        return null;

        // return (List<T>) query.getResultList();

    }

}
