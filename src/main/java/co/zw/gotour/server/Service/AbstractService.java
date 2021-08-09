package co.zw.gotour.server.Service;

import co.zw.gotour.server.Model.Model;
import co.zw.gotour.server.Util.DocumentType;
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
            if (e instanceof IndexOutOfBoundsException ){
               throw new IllegalArgumentException("The @DocumentType Annotation is required for all Model Classes");
            }
            throw new Exception(e);
        }
    }


}
