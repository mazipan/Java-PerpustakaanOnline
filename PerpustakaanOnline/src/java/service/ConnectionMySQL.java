package service;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import dao.AdministratorDao;
import dao.BookDao;
import dao.CategoryDao;
import dao.MemberDao;
import dao.NewsDao;
import dao.PeminjamanDao;
import dao.implementation.AdministratorImplementation;
import dao.implementation.BookImplementation;
import dao.implementation.CategoryImplementation;
import dao.implementation.MemberImplementation;
import dao.implementation.NewsImplementation;
import dao.implementation.PeminjamanImplementation;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author joker
 */
public class ConnectionMySQL {

    private static Connection connection;
    private static AdministratorDao administratorDao;
    private static CategoryDao categoryDAO;
    private static MemberDao memberDAO;
    private static BookDao bookDAO;
    private static PeminjamanDao peminjamanDAO;
    private static NewsDao newsDao;

    public ConnectionMySQL() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                MysqlDataSource dataSource = new MysqlDataSource();
                /**
                 * THIS IS CONECCTION CONFIGURATION, PLEASE FIX WITH YOUR DB
                 * SETTING
                 *
                 */
                dataSource.setUrl("jdbc:mysql://localhost:3306/perpusweb");
                dataSource.setUser("root");
                dataSource.setPassword("");

                connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Database connection Error " + e.getMessage());
            }

        }

        return connection;
    }

    public static AdministratorDao getAdministratorDao() throws SQLException {
        if (administratorDao == null) {
            administratorDao = new AdministratorImplementation(getConnection());
        }
        return administratorDao;
    }

    public static MemberDao getMemberDao() throws SQLException {
        if (memberDAO == null) {
            memberDAO = new MemberImplementation(getConnection());
        }
        return memberDAO;
    }

    public static CategoryDao getCategoryDao() throws SQLException {
        if (categoryDAO == null) {
            categoryDAO = new CategoryImplementation(getConnection());
        }
        return categoryDAO;
    }

    public static BookDao getBookDao() throws SQLException {
        if (bookDAO == null) {
            bookDAO = new BookImplementation(getConnection());
        }
        return bookDAO;
    }

    public static PeminjamanDao getPeminjamanDao() throws SQLException {
        if (peminjamanDAO == null) {
            peminjamanDAO = new PeminjamanImplementation(getConnection());
        }
        return peminjamanDAO;
    }

    public static NewsDao getNewsDao() throws SQLException {
        if (newsDao == null) {
            newsDao = new NewsImplementation(getConnection());
        }
        return newsDao;
    }
}
