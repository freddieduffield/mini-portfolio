package org.miniportfolio.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class Database {
    private static DSLContext dsl;

    public static void init() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariConfig.setUsername("user");
        hikariConfig.setPassword("password");
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        dsl = DSL.using(dataSource, SQLDialect.POSTGRES);
    }

    public static DSLContext getDsl() {
        return dsl;
    }
}
