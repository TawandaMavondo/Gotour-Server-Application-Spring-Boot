package co.zw.gotour.server.Setup;

import java.util.List;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.manager.query.QueryIndex;
import com.google.api.client.util.Value;

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

    @Value("${couchbase.bucketname}")
    private String bucket;

    Logger logger = LoggerFactory.getLogger(ApplicationSetup.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.bucket = this.couchbaseConfiguration.getBucketName();
        this.createIndexIfNotExist("Index_entityType", List.of("entityType"));
        this.createIndexIfNotExist("Index_username", List.of("userName"));
        this.createIndexIfNotExist("index_class", List.of("_class"));

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

}
