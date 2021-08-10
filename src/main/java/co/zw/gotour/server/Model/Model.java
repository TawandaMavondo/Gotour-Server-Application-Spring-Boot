package co.zw.gotour.server.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.couchbase.core.mapping.Field;

@Data
public class Model {
    @Field
    @JsonIgnore
    public String entityType;
}
