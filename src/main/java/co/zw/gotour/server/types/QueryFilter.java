package co.zw.gotour.server.types;

import lombok.Data;

@Data
public class QueryFilter {

    private String field;
    private String operator;
    private String value;

}
