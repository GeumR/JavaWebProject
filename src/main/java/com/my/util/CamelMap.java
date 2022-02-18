package com.my.util;

import java.util.HashMap;

import org.apache.ibatis.type.Alias;
import org.springframework.jdbc.support.JdbcUtils;

@Alias("camel")
public class CamelMap extends HashMap<Object, Object> {
    private static final long serialVersionUID = -7700790403928325865L;

    @Override
    public Object put(Object key, Object value) {
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName((String) key), value);
    }
}