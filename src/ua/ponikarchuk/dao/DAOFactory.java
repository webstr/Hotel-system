package ua.ponikarchuk.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * DaoFactory is the pattern AbstractFactory that create a DAO patterns for working with DB.
 */
public class DAOFactory {
    private static DAOFactory instance = new DAOFactory();
    private UserDao userDao;
    private ApplicationDao applicationDao;
    private RoomDao roomDao;
    private DataSource source;

    {
        try {
            InitialContext context = new InitialContext();
            source = (DataSource) context.lookup("java:comp/env/jdbc/db");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private DAOFactory() {
        userDao = new UserDao(source);
        applicationDao = new ApplicationDao(source);
        roomDao = new RoomDao(source);
    }

    public synchronized static DAOFactory getInstance() {
        return instance;
    }

    public UserDao getUserDAO() {
        return userDao;
    }

    public ApplicationDao getApplicationDAO() {
        return applicationDao;
    }

    public RoomDao getRoomDAO() {
        return roomDao;
    }
}
