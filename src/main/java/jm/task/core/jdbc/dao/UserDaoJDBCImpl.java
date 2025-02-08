package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Statement statement = null;
        String sql = "create table if not exists users (" +
                "id bigint not null primary key auto_increment," +
                "name varchar(40)," +
                "lastName varchar(40)," +
                "age tinyint)";
        try {
            statement = Util.getConnection().createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (Util.getConnection() != null) {
                Util.getConnection().close();
            }
        }

    }

    public void dropUsersTable() throws SQLException {
        Statement statement = null;
        String sql = "drop table users";
        try {
            statement = Util.getConnection().createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (Util.getConnection() != null) {
                Util.getConnection().close();
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            preparedStatement = Util.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (Util.getConnection() != null) {
                Util.getConnection().close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "delete from users where id = ?";
        try {
            preparedStatement = Util.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (Util.getConnection() != null) {
                Util.getConnection().close();
            }
        }


    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM users";
        try {
            preparedStatement = Util.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (Util.getConnection() != null) {
                Util.getConnection().close();
            }
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        Statement statement = null;
        String sql = "truncate users";
        try {
            statement = Util.getConnection().createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (Util.getConnection() != null) {
                Util.getConnection().close();
            }
        }

    }
}
