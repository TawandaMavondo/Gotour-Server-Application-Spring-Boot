package co.zw.gotour.server.Util;

import org.springframework.core.convert.converter.Converter;

public enum CustomCouchbaseConverter implements Converter<String, String> {
    INSTANCE;

    @Override
    public String convert(String source) {
        return source;
    }

}
