package com.nwnx.hibernate;

import org.hibernate.dialect.MySQL57Dialect;
import org.hibernate.type.StandardBasicTypes;

import java.sql.Types;

public class MyMySQL57Dialect extends MySQL57Dialect {
    public MyMySQL57Dialect() {
        super();
        registerHibernateType(Types.BIGINT, StandardBasicTypes.LONG.getName());
    }
}
