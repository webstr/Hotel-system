package ua.ponikarchuk.dao;

import ua.ponikarchuk.model.Room;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for working with entity Room.
 */
public class RoomDao {
    DataSource dataSource;

    public RoomDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Room> getAll() {
        final String SQL_GET_ALL = "SELECT * FROM room;";
        List<Room> rooms = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setStatus(resultSet.getInt("status"));
                room.setRoomNumber(resultSet.getInt("room_number"));
                room.setFloor(resultSet.getInt("floor"));
                room.setType(resultSet.getString("type"));
                room.setSize(resultSet.getInt("size"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public void saveRoom(Room room) {
        final String SQL = "INSERT INTO room (status, room_number, floor, type, size) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, room.getStatus());
            statement.setInt(2, room.getRoomNumber());
            statement.setInt(3, room.getFloor());
            statement.setString(4, room.getType());
            statement.setInt(5, room.getSize());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
