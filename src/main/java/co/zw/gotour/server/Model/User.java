package co.zw.gotour.server.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

//import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;

@Data
@EqualsAndHashCode
@Document
@Collection("user")
public class User extends Model {

    @Field
    private String firstname;

    @Field
    private String lastname;

    @Field
    private String email;

    @Field()
    private String username;

    @Field
    private String[] roles;

    @Field
    private String firebaseId;

}
