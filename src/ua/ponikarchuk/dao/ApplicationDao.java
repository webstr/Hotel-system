package ua.ponikarchuk.dao;

import ua.ponikarchuk.model.Application;
import ua.ponikarchuk.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for working with entity Application.
 */
public class ApplicationDao {
    DataSource dataSource;

    public ApplicationDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Select all applications
     * @return List of all applications
     */
    public List<Application> getAll() {
        final String SQL_GET_ALL = "SELECT * FROM application;";
        List<Application> applications = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            while (resultSet.next()) {
                Application application = new Application();
                application.setId(resultSet.getInt("id"));
                application.setSize(resultSet.getInt("size"));
                application.setDuration(resultSet.getInt("duration"));
                application.setStatus(Application.Status.valueOf(resultSet.getString("status")));
                application.setType(resultSet.getString("type"));
                application.setIdBill(resultSet.getInt("id_bill"));
                application.setIdUser(resultSet.getInt("id_user"));
                application.setIdRoom(resultSet.getInt("id_room"));
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applications;
    }

    /**
     * Select all application for current user
     * @param user For this user selected applications
     * @return List of applications for input user
     */
    public List<Application> getApplicationsByUser(User user) {
        final String SQL = "SELECT * FROM application WHERE id_user = ?";
        List<Application> applications = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Application application = new Application();
                application.setId(resultSet.getInt("id"));
                application.setSize(resultSet.getInt("size"));
                application.setDuration(resultSet.getInt("duration"));
                application.setStatus(Application.Status.valueOf(resultSet.getString("status")));
                application.setType(resultSet.getString("type"));
                application.setIdBill(resultSet.getInt("id_bill"));
                application.setIdUser(resultSet.getInt("id_user"));
                application.setIdRoom(resultSet.getInt("id_room"));
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applications;
    }

    /**
     * Select application on id
     * @param id Application id
     * @return Application with this id
     */
    public Application getApplicationsById(int id) {
        final String SQL = "SELECT * FROM application WHERE id = ?";
        Application application = new Application();

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                application.setId(resultSet.getInt("id"));
                application.setSize(resultSet.getInt("size"));
                application.setDuration(resultSet.getInt("duration"));
                application.setStatus(Application.Status.valueOf(resultSet.getString("status")));
                application.setType(resultSet.getString("type"));
                application.setIdBill(resultSet.getInt("id_bill"));
                application.setIdUser(resultSet.getInt("id_user"));
                application.setIdRoom(resultSet.getInt("id_room"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return application;
    }

    /**
     * Save application into DB
     * @param application Application to save
     */
    public void saveApplication(Application application) {
        final String SQL =
                "INSERT INTO application (size, type, duration, status, id_bill, id_user, id_room) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, application.getSize());
            statement.setString(2, application.getType());
            statement.setInt(3, application.getDuration());
            statement.setString(4, application.getStatus().toString());
            if (application.getIdBill() != null) {
                statement.setInt(5, application.getIdBill());
            } else {
                statement.setObject(5, null);
            }
            statement.setInt(6, application.getIdUser());
            if (application.getIdRoom() != null) {
                statement.setInt(7, application.getIdRoom());
            } else {
                statement.setObject(7, null);
            }
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update information about room in application
     * @param application application to update
     * @param idRoom Id room, that coonect with application
     */
    public void updateIdRoom(Application application, int idRoom) {
        final String SQL =
                "UPDATE application SET id_room=? WHERE id=?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, idRoom);
            statement.setInt(2, application.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the status of application
     * @param application input application
     */
    public void updateStatus(Application application) {
        setNextStatus(application);
        final String SQL =
                "UPDATE application SET status=? WHERE id=?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, String.valueOf(application.getStatus()));
            statement.setInt(2, application.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setNextStatus(Application application) {
        Application.Status status = application.getStatus();
        switch (status) {
            case OPEN:
                application.setStatus(Application.Status.INPROGRESS);
            break;
            case INPROGRESS:
                application.setStatus(Application.Status.CLOSE);
                break;
            case CLOSE:
                break;
        }
    }
}
