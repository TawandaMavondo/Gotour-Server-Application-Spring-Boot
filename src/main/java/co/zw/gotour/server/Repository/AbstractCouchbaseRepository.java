package co.zw.gotour.server.Repository;

import com.couchbase.client.core.error.CollectionExistsException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.manager.collection.CollectionManager;
import com.couchbase.client.java.manager.collection.CollectionSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.zw.gotour.server.Model.Model;

public abstract class AbstractCouchbaseRepository<T extends Model> {

    @Autowired
    private Bucket bucket;

    @Autowired
    private Cluster cluster;

    private Collection collection;

    AbstractCouchbaseRepository(Class<T> entityType, String collectionName) {
        CollectionManager collectionManager = this.bucket.collections();
        try {
            CollectionSpec collectionSpec = CollectionSpec.create(collectionName,
                    this.bucket.defaultCollection().name());
            collectionManager.createCollection(collectionSpec);
            Thread.sleep(15000);
        } catch (CollectionExistsException e) {
            // Collection Exists
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.collection = this.bucket.collection(collectionName);

    }
}
