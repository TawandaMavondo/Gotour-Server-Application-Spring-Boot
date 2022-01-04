package co.zw.gotour.server.Setup;

import java.util.List;

import com.couchbase.client.core.error.CollectionExistsException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.manager.collection.CollectionManager;
import com.couchbase.client.java.manager.collection.CollectionSpec;
import com.couchbase.client.java.manager.query.QueryIndex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import co.zw.gotour.server.Configuration.CouchbaseConfiguration;
import io.sentry.Sentry;

@Component
public class ApplicationSetup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    Cluster couchbaseCluster;

    @Autowired
    CouchbaseConfiguration couchbaseConfiguration;

    List<QueryIndex> indexes = List.of();

    private String bucket;

    Logger logger = LoggerFactory.getLogger(ApplicationSetup.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.bucket = this.couchbaseConfiguration.getBucketName();
        this.createIndexIfNotExist("Index_entityType", List.of("entityType"));
        this.createIndexIfNotExist("Index_username", List.of("username"));
        this.createIndexIfNotExist("index_class", List.of("_class"));
//        this.createCollectionIfNotExist("Nyama");

    }

    public void createIndexIfNotExist(String indexName, List<String> fields) {
        try {
            if (indexes.isEmpty() || indexes == null) {
                this.indexes = this.couchbaseCluster.queryIndexes().getAllIndexes(this.bucket);
            }

            if (this.indexes.stream().anyMatch(index -> indexName.equals(index.name()))) {
                return;
            }

            this.logger.info("Creating Index: " + indexName + " on Bucket: " + this.bucket);

            this.couchbaseCluster.queryIndexes().createIndex(this.bucket, indexName, fields);

            this.indexes = this.couchbaseCluster.queryIndexes().getAllIndexes(this.bucket);

            this.logger.info("Created Index: " + indexName + " on Bucket: " + this.bucket);

        } catch (Exception e) {
            Sentry.captureException(e);
            logger.error(e.getMessage());
        }

    }

    public void createCollectionIfNotExist(String collection) {
        String statement = "CREATE COLLECTION `" + collection + "`";
        Bucket bucket = this.couchbaseCluster.bucket(this.bucket);
        CollectionManager collectionManager = bucket.collections();

        try {
            CollectionSpec collectionSpec = CollectionSpec.create(collection, bucket.defaultCollection().scopeName());
            collectionManager.createCollection(collectionSpec);
        } catch (CollectionExistsException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // this.couchbaseCluster.query(statement);

        // if (bucket.collection(collection) == null) {
        // }
    }

}
