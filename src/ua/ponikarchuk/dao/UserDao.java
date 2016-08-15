package ua.ponikarchuk.dao;

import ua.ponikarchuk.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for working with entity User.
 */
public class UserDao {
    DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> getAll() {
        final String SQL_GET_ALL = "SELECT * FROM user;";
        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setIdRole(resultSet.getInt("id_role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserByLogin(String login) {
        String SQL = "SELECT * FROM user WHERE login = ?";
        User user = null;

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setIdRole(resultSet.getInt("id_role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void saveUser(User user) {
        final String SQL_SAVE_USER =
                "INSERT INTO user (name, surname, login, password, id_role) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SAVE_USER)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getIdRole());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
