package co.zw.gotour.server.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {
    @Value("${couchbase.url}")
    private String url ;

    @Value("${couchbase.username}")
    private String username ;

    @Value("${couchbase.password}")
    private String password ;

    @Value("${couchbase.bucketname}")
    private String bucket;

    @Override
    public String getConnectionString() {

        return url;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return bucket;
    }
}
