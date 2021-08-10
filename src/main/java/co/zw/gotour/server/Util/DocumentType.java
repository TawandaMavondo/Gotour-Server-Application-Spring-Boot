package co.zw.gotour.server.Util;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentType {
    String type();
}
