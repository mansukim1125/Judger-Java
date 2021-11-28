package com.mansu.judger.util.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Connector {
//    refer to: https://juyoung-1008.tistory.com/29
    private static BasicDataSource dataSource = getDataSource();

    private static BasicDataSource getDataSource() {
        if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl("jdbc:mariadb://localhost/Judge");
            ds.setUsername("mansu");
            ds.setPassword("05300530");

            dataSource = ds;
        }
        return dataSource;
    }

    public static Connection getConnection(boolean autoCommit) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(autoCommit);
        return connection;
    }
}
