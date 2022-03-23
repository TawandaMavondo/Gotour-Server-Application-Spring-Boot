package co.zw.gotour.server.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;
import org.springframework.core.convert.converter.Converter;

@Configuration
@EnableReactiveCouchbaseRepositories
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {
    @Value("${couchbase.url}")
    private String url;

    @Value("${couchbase.username}")
    private String username;

    @Value("${couchbase.password}")
    private String password;

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
