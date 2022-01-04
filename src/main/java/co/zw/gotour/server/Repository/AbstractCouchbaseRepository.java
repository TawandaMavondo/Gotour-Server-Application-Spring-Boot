package co.zw.gotour.server.Repository;

import com.couchbase.client.core.error.CollectionExistsException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.manager.collection.CollectionManager;
import com.couchbase.client.java.manager.collection.CollectionSpec;
import com.couchbase.client.java.manager.collection.CreateCollectionOptions;

import co.zw.gotour.server.Configuration.CouchbaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.zw.gotour.server.Model.Model;

public abstract class AbstractCouchbaseRepository<T extends Model> {

    private Bucket bucket;

    @Autowired(required = true)
    protected Cluster cluster;

    @Autowired(required = true)
    CouchbaseConfiguration configuration;

    private Collection collection;

    AbstractCouchbaseRepository(String collectionName,Cluster cluster) {
        this.cluster = cluster;
        this.bucket = this.cluster.bucket(this.configuration.getBucketName());
        CollectionManager collectionManager = this.bucket.collections();
        try {
            CollectionSpec collectionSpec = CollectionSpec.create(collectionName,
                    this.bucket.defaultCollection().scopeName());
            // collectionManager.createCollection(collectionSpec);
            Thread.sleep(15000);
        } catch (CollectionExistsException e) {
            // Collection Exists
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.collection = this.bucket.collection(collectionName);

    }
}
