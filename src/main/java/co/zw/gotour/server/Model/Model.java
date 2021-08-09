package co.zw.gotour.server.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.couchbase.core.mapping.Field;

public class Model {
    @Field
    @JsonIgnore
    public String entityType;
}
