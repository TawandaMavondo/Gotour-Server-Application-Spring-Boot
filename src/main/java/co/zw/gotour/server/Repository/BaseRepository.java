package co.zw.gotour.server.Repository;

import java.util.List;

import co.zw.gotour.server.Model.Model;

public interface BaseRepository<T extends Model> {

    public List<T> query(String where, Class<T> entityClass);

}
