package co.zw.gotour.server.Repository;

import java.util.List;
import java.util.Optional;

import co.zw.gotour.server.Model.Model;

public interface BaseRepository<T extends Model> {

    public List<T> query(String where, Class<T> entityClass);

    
	<S extends T> S save(S entity);

	
	Optional<T> findById(String id);

	
	boolean existsById(String id);

	
	Iterable<T> findAll();

		
	long count();

	
	void deleteById(String id);

	
	void delete(T entity);

	
	void deleteAllById(Iterable<? extends String> ids);

	
	void deleteAll(Iterable<? extends T> entities);

	
	void deleteAll();

}
