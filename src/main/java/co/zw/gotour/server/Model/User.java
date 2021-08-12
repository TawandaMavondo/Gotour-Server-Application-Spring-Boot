package co.zw.gotour.server.Model;

import co.zw.gotour.server.Util.DocumentType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

@Data
@EqualsAndHashCode
@Document
@DocumentType(type = "user")
// @TypeAlias("user")
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




}
