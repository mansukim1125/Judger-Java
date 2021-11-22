package com.mansu.judger.model.dao;

import com.mansu.judger.model.dto.UserDTO;
import com.mansu.judger.util.db.Connector;

import java.sql.*;

public class UserDAO {
    public static UserDTO getUserById(int id) {
        UserDTO user = null;
//        refer to: https://mariadb.com/kb/en/java-connector-using-gradle/
        try (Connection conn = Connector.getConnection(false)) {
            try (PreparedStatement stmt = conn.prepareStatement("SELECT id, username, email, created_at FROM users WHERE id=?")) {
                stmt.setInt(1, id);
                try (ResultSet result = stmt.executeQuery()) {

                    while (result.next()) {
                        int userId = result.getInt("id");
                        String username = result.getString("username");
                        String email = result.getString("email");
                        Timestamp createdAt = result.getTimestamp("created_at");

                        user = new UserDTO(userId, username, email, createdAt);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


}
