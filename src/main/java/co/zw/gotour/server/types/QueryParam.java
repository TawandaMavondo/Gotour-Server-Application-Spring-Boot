package co.zw.gotour.server.types;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class QueryParam implements Serializable{

    public enum SORT {
        ASC, DESC
    }

    private List<QueryFilter> filter;
    private SORT sort;
    private int limit;
    private int offset;
    private int page;

}
