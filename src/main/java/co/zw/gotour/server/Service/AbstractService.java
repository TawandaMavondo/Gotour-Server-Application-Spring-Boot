package co.zw.gotour.server.Service;

import co.zw.gotour.server.Configuration.CouchbaseConfiguration;
import co.zw.gotour.server.Model.Model;
import co.zw.gotour.server.Util.DocumentType;
import co.zw.gotour.server.types.QueryParam;
import io.sentry.Sentry;
import io.sentry.spring.tracing.SentryTransaction;

import java.util.ArrayList;
// import com.couchbase.client.java.query.N1qlQuery;
import java.util.List;
import java.util.Optional;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryResult;
import com.couchbase.client.java.query.QueryScanConsistency;
import com.couchbase.client.java.search.SearchScanConsistency;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.couchbase.core.CouchbaseOperations;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.query.N1QLQuery;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

public abstract class AbstractService<T extends Model> {

    private final CrudRepository<T, String> repository;
    private final Class<T> entityClass;
    @Autowired
    private Cluster cluster;

    @Autowired
    CouchbaseConfiguration couchbaseConfiguration;

    @Autowired
    CouchbaseTemplate couchbaseTemplate;

    AbstractService(CrudRepository<T, String> repository, Class<T> entityClass) {
        this.repository = repository;
        this.entityClass = entityClass;
    }

    @Transactional
    public T save(T entity) throws Exception {
        try {
            var annotation = this.entityClass.getAnnotationsByType(DocumentType.class)[0];
            entity.entityType = annotation.type();
            return this.repository.save(entity);
        } catch (Exception e) {
            if (e instanceof IndexOutOfBoundsException) {
                throw new IllegalArgumentException("The @DocumentType Annotation is required for all Model Classes");
            }
            Sentry.captureException(e);
            throw new Exception(e);
        }
    }

    @SentryTransaction(operation = "FindAll")
    public Iterable<T> find() {
        return this.repository.findAll();
    }

    public T findById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("The Id Parameter is required");
        }

        Optional<T> document = this.repository.findById(id);
        if (document.isEmpty())
            throw new NotFoundException("Document assocciated with id: " + id + " is not found");

        return document.get();
    }

    public void delete(String id) throws Exception {
        if (id == null)
            throw new IllegalArgumentException("The Id Parameter is required");

        // TODO: Apply a disable field on User Objects when the Delete method is called.
        try {
            this.repository.deleteById(id);
        } catch (DataRetrievalFailureException e) {
            throw new Exception("Document not found");
        }

    }

    public T update(T entity) throws Exception {

        if (entity.getId() == null)
            throw new IllegalArgumentException("The Id Parameter is required");

        Optional<T> findEntity = this.repository.findById(entity.getId());

        if (findEntity.isEmpty())
            throw new NotFoundException("Document assocciated with id: " + entity.getId() + " is not found");

        return this.save(entity);
    }

    public Iterable<T> query(String params) {
        ObjectMapper objectMapper = new ObjectMapper();

        QueryParam queryParam = null;
        try {
            queryParam = objectMapper.readValue(params, QueryParam.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Bucket bucket = this.cluster.bucket(this.couchbaseConfiguration.getBucketName());
        String queryString = "select * from " + bucket.name() + " where _class=?";

        QueryResult values = this.cluster.query(queryString,
                QueryOptions.queryOptions().parameters(JsonArray.from(entityClass.getName())));

        return this.mapToTypeT(values.rowsAsObject(), bucket.name());

        // return List.of();
    }

    private List<T> mapToTypeT(List<JsonObject> values, String bucketName) {

        if (values.isEmpty() && bucketName == null) {
            return null;
        }

        List<T> TObjects = new ArrayList<>();

        for (JsonObject obj : values) {
            var row = obj.get(bucketName);
            ObjectMapper objectMapper = new ObjectMapper();

            T entyity = objectMapper.convertValue(row, entityClass);
            TObjects.add(entyity);
        }

        return TObjects;

    }

   
}
