package co.zw.gotour.server.Service;

import co.zw.gotour.server.Model.Model;
import co.zw.gotour.server.Util.DocumentType;
import io.sentry.Sentry;
import io.sentry.spring.tracing.SentryTransaction;

import org.springframework.data.repository.CrudRepository;

public abstract class AbstractService<T extends Model> {

    private final CrudRepository<T, String> repository;
    private final Class<T> entityClass;

    AbstractService(CrudRepository<T, String> repository, Class<T> entityClass) {
        this.repository = repository;
        this.entityClass = entityClass;
    }

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

        return this.repository.findById(id).get();
    }

    public void delete(String id) {
        if (id == null)
            throw new IllegalArgumentException("The Id Parameter is required");

        this.repository.deleteById(id);

    }

    public T update(T entity) throws Exception {

        if (entity.getId() == null)
            throw new IllegalArgumentException("The Id Parameter is required");
        
        return this.save(entity);    

    }

}
