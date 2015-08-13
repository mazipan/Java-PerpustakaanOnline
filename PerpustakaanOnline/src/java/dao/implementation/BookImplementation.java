package dao.implementation;

import dao.BookDao;
import dao.CategoryDao;
import entity.Book;
import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import service.ConnectionMySQL;

/**
 *
 * @author joker
 */
public class BookImplementation implements BookDao {

    private Connection connection;

    public BookImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void Insert(Book book) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO book SET id_book = null, id_cat = ?, book_title = ?, pengarang = ?, thn_terbit = ?;";

            statement = connection.prepareStatement(sql);

            statement.setInt(1, book.getCategory().getIdCategory());
            statement.setString(2, book.getBookTitle());
            statement.setString(3, book.getPengarang());
            statement.setString(4, book.getTerbitDateStr());

            System.out.println(statement.toString());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public void Update(Book book) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE book SET id_cat = ?, book_title = ?, pengarang = ?, thn_terbit = ? WHERE id_book = ?;";

            statement = connection.prepareStatement(sql);

            statement.setInt(1, book.getCategory().getIdCategory());
            statement.setString(2, book.getBookTitle());
            statement.setString(3, book.getPengarang());
            statement.setString(4, book.getTerbitDateStr());
            statement.setInt(5, book.getIdBook());

            System.out.println(statement.toString());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public void Delete(Integer idBook) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM book WHERE id_book = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, idBook);

            System.out.println(statement.toString());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public List<Book> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit, COUNT(1) AS stock,\n"
                    + "CASE \n"
                    + "WHEN B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam \n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "ON A.id_book = B.id_book GROUP BY A.book_title, A.id_cat ORDER BY A.book_title";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();

                book.setIdBook(resultSet.getInt("id_book"));

                Category category;
                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                book.setCategory(category);
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPengarang(resultSet.getString("pengarang"));
                book.setTerbitDate(resultSet.getDate("thn_terbit"));
                book.setIsDipinjam(resultSet.getString("isDipinjam"));
                book.setStock(resultSet.getInt("stock"));

                list.add(book);
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public List<Book> selectByCategory(Integer idCategory) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit, COUNT(1) AS stock,\n"
                    + "CASE\n"
                    + "WHEN B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam \n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE A.id_cat = " + idCategory + " GROUP BY A.book_title, A.id_cat ORDER BY A.book_title;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();

                book.setIdBook(resultSet.getInt("id_book"));

                Category category;
                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                book.setCategory(category);
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPengarang(resultSet.getString("pengarang"));
                book.setTerbitDate(resultSet.getDate("thn_terbit"));
                book.setIsDipinjam(resultSet.getString("isDipinjam"));
                book.setStock(resultSet.getInt("stock"));

                list.add(book);
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    
    @Override
    public boolean isExistByCategory(Integer idCategory) throws Exception {
        PreparedStatement statement;
        boolean result = false;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT id_book FROM book WHERE id_cat = " + idCategory + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = true;
            }

            connection.setAutoCommit(false);
            connection.commit();
            return result;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    
    @Override
    public Book getBookById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit,\n"
                    + "CASE\n"
                    + "WHEN B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam \n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE A.id_book = " + id + ";";

            System.out.println(sql);

            Book book = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                book = new Book();

                book.setIdBook(resultSet.getInt("id_book"));

                Category category;
                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                book.setCategory(category);
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPengarang(resultSet.getString("pengarang"));
                book.setTerbitDate(resultSet.getDate("thn_terbit"));
                book.setIsDipinjam(resultSet.getString("isDipinjam"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return book;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public List<Book> selectByYear(String year) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit, COUNT(1) AS stock,\n"
                    + "CASE\n"
                    + "WHEN B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam \n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE YEAR(A.thn_terbit) = " + year + " GROUP BY A.book_title, A.id_cat ORDER BY A.book_title;";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();

                book.setIdBook(resultSet.getInt("id_book"));

                Category category;
                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                book.setCategory(category);
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPengarang(resultSet.getString("pengarang"));
                book.setTerbitDate(resultSet.getDate("thn_terbit"));
                book.setIsDipinjam(resultSet.getString("isDipinjam"));
                book.setStock(resultSet.getInt("stock"));

                list.add(book);
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public List<Book> selectWhereNotDipinjam() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit, COUNT(1) AS stock,\n"
                    + "CASE\n"
                    + "WHEN B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam\n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "LEFT JOIN peminjaman C ON B.id_book = C.id_book AND C.isKembali = 0\n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE (B.isKembali = 1 OR B.isKembali IS NULL) AND C.id_book IS NULL GROUP BY A.book_title, A.id_cat ORDER BY A.book_title;";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                if (resultSet.getString("isDipinjam").equalsIgnoreCase("Available")) {
                    Book book = new Book();

                    book.setIdBook(resultSet.getInt("id_book"));

                    Category category;
                    CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                    category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                    book.setCategory(category);
                    book.setBookTitle(resultSet.getString("book_title"));
                    book.setPengarang(resultSet.getString("pengarang"));
                    book.setTerbitDate(resultSet.getDate("thn_terbit"));
                    book.setIsDipinjam(resultSet.getString("isDipinjam"));
                    book.setStock(resultSet.getInt("stock"));

                    list.add(book);
                }
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public List<Book> selectWhereDipinjam() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit , COUNT(1) AS stock,\n"
                    + "CASE\n"
                    + "WHEN B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam\n"
                    + "FROM book A \n"
                    + "LEFT JOIN peminjaman B \n"
                    + "ON A.id_book = B.id_book \n"
                    + "WHERE B.isKembali = 0 GROUP BY A.book_title, A.id_cat ORDER BY A.book_title;";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();

                book.setIdBook(resultSet.getInt("id_book"));

                Category category;
                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                book.setCategory(category);
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPengarang(resultSet.getString("pengarang"));
                book.setTerbitDate(resultSet.getDate("thn_terbit"));
                book.setIsDipinjam(resultSet.getString("isDipinjam"));
                book.setStock(resultSet.getInt("stock"));

                list.add(book);
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public List<Book> selectWhereNotDipinjamByCategory(Integer idCategory) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit, COUNT(1) AS stock,\n"
                    + "CASE\n"
                    + "WHEN AND B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam\n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "LEFT JOIN peminjaman C ON B.id_book = C.id_book AND C.isKembali = 0\n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE (B.isKembali = 1 OR B.isKembali IS NULL) AND C.id_book IS NULL AND A.id_cat = " + idCategory + " GROUP BY A.book_title, A.id_cat ORDER BY A.book_title;";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                if (resultSet.getString("isDipinjam").equalsIgnoreCase("Available")) {
                    Book book = new Book();

                    book.setIdBook(resultSet.getInt("id_book"));

                    Category category;
                    CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                    category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                    book.setCategory(category);
                    book.setBookTitle(resultSet.getString("book_title"));
                    book.setPengarang(resultSet.getString("pengarang"));
                    book.setTerbitDate(resultSet.getDate("thn_terbit"));
                    book.setIsDipinjam(resultSet.getString("isDipinjam"));
                    book.setStock(resultSet.getInt("stock"));

                    list.add(book);
                }
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public List<Book> selectWhereNotDipinjamByYear(String Year) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit, COUNT(1) AS stock,\n"
                    + "CASE\n"
                    + "WHEN B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam\n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "LEFT JOIN peminjaman C ON B.id_book = C.id_book AND C.isKembali = 0\n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE (B.isKembali = 1 OR B.isKembali IS NULL) AND C.id_book IS NULL AND YEAR(A.thn_terbit) = " + Year + " GROUP BY A.book_title, A.id_cat ORDER BY A.book_title;";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                if (resultSet.getString("isDipinjam").equalsIgnoreCase("Available")) {
                    Book book = new Book();

                    book.setIdBook(resultSet.getInt("id_book"));

                    Category category;
                    CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                    category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                    book.setCategory(category);
                    book.setBookTitle(resultSet.getString("book_title"));
                    book.setPengarang(resultSet.getString("pengarang"));
                    book.setTerbitDate(resultSet.getDate("thn_terbit"));
                    book.setIsDipinjam(resultSet.getString("isDipinjam"));
                    book.setStock(resultSet.getInt("stock"));

                    list.add(book);
                }
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public List<Book> selectAllWithLimit(int offset, int noOfRecords) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit, COUNT(1) AS stock,\n"
                    + "CASE \n"
                    + "WHEN B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam \n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "ON A.id_book = B.id_book GROUP BY A.book_title, A.id_cat ORDER BY A.book_title LIMIT " + offset + "," + noOfRecords + ";";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();

                book.setIdBook(resultSet.getInt("id_book"));

                Category category;
                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                book.setCategory(category);
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPengarang(resultSet.getString("pengarang"));
                book.setTerbitDate(resultSet.getDate("thn_terbit"));
                book.setIsDipinjam(resultSet.getString("isDipinjam"));
                book.setStock(resultSet.getInt("stock"));

                list.add(book);
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public Integer getNoOfRecords() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM (\n"
                    + "	SELECT COUNT(*) \n"
                    + "	FROM book GROUP BY book_title, id_cat \n"
                    + " ) AS SQ;";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            connection.setAutoCommit(false);
            connection.commit();

            return count;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public List<Book> selectByCategoryWithLimit(Integer idCategory, int offset, int noOfRecords) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit, COUNT(1) AS stock,\n"
                    + "CASE\n"
                    + "WHEN B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam \n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE A.id_cat = " + idCategory + " GROUP BY A.book_title, A.id_cat ORDER BY A.book_title LIMIT " + offset + "," + noOfRecords + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();

                book.setIdBook(resultSet.getInt("id_book"));

                Category category;
                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                book.setCategory(category);
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPengarang(resultSet.getString("pengarang"));
                book.setTerbitDate(resultSet.getDate("thn_terbit"));
                book.setIsDipinjam(resultSet.getString("isDipinjam"));
                book.setStock(resultSet.getInt("stock"));

                list.add(book);
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public Integer getNoOfRecordsByCategory(Integer idCategory) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM (\n"
                    + "	SELECT COUNT(*) \n"
                    + "	FROM book \n"
                    + "	WHERE id_cat = " + idCategory + " GROUP BY book_title, id_cat\n"
                    + " ) AS SQ;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            connection.setAutoCommit(false);
            connection.commit();

            return count;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public List<Book> selectByYearWithLimit(String Year, int offset, int noOfRecords) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit, COUNT(1) AS stock,\n"
                    + "CASE\n"
                    + "WHEN B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam \n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE YEAR(A.thn_terbit) = " + Year + " GROUP BY A.book_title, A.id_cat ORDER BY A.book_title LIMIT " + offset + "," + noOfRecords + ";";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();

                book.setIdBook(resultSet.getInt("id_book"));

                Category category;
                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                book.setCategory(category);
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPengarang(resultSet.getString("pengarang"));
                book.setTerbitDate(resultSet.getDate("thn_terbit"));
                book.setIsDipinjam(resultSet.getString("isDipinjam"));
                book.setStock(resultSet.getInt("stock"));

                list.add(book);
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public Integer getNoOfRecordsByYear(String Year) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM (\n"
                    + "	SELECT COUNT(*) \n"
                    + "	FROM book \n"
                    + "	WHERE YEAR(thn_terbit) = " + Year + " GROUP BY book_title, id_cat\n"
                    + "     ) AS SQ;";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            connection.setAutoCommit(false);
            connection.commit();

            return count;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public List<Book> selectWhereNotDipinjamWithLimit(int offset, int noOfRecords) throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit, COUNT(1) AS stock,\n"
                    + "CASE\n"
                    + "WHEN B.isKembali IS NOT NULL AND B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam\n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "LEFT JOIN peminjaman C ON B.id_book = C.id_book AND C.isKembali = 0\n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE (B.isKembali = 1 OR B.isKembali IS NULL) AND C.id_book IS NULL GROUP BY A.book_title, A.id_cat ORDER BY A.book_title LIMIT " + offset + "," + noOfRecords + ";";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                if (resultSet.getString("isDipinjam").equalsIgnoreCase("Available")) {
                    Book book = new Book();

                    book.setIdBook(resultSet.getInt("id_book"));

                    Category category;
                    CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                    category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                    book.setCategory(category);
                    book.setBookTitle(resultSet.getString("book_title"));
                    book.setPengarang(resultSet.getString("pengarang"));
                    book.setTerbitDate(resultSet.getDate("thn_terbit"));
                    book.setIsDipinjam(resultSet.getString("isDipinjam"));
                    book.setStock(resultSet.getInt("stock"));

                    list.add(book);
                }
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public Integer getNoOfRecordsWhereNotDipinjam() throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM (  \n"
                    + "SELECT COUNT(*)\n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "LEFT JOIN peminjaman C ON B.id_book = C.id_book AND C.isKembali = 0\n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE (B.isKembali = 1 OR B.isKembali IS NULL) AND C.id_book IS NULL GROUP BY book_title, id_cat \n"
                    + ") AS SQ;";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            connection.setAutoCommit(false);
            connection.commit();

            return count;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public List<Book> selectWhereNotDipinjamByCategoryWithLimit(Integer idCategory, int offset, int noOfRecords) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit, COUNT(1) AS stock,\n"
                    + "CASE\n"
                    + "WHEN B.isKembali IS NOT NULL AND B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam\n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "LEFT JOIN peminjaman C ON B.id_book = C.id_book AND C.isKembali = 0\n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE (B.isKembali = 1 OR B.isKembali IS NULL) AND C.id_book IS NULL AND A.id_cat = " + idCategory + " GROUP BY A.book_title, A.id_cat ORDER BY A.book_title LIMIT " + offset + "," + noOfRecords + ";";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                if (resultSet.getString("isDipinjam").equalsIgnoreCase("Available")) {
                    Book book = new Book();

                    book.setIdBook(resultSet.getInt("id_book"));

                    Category category;
                    CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                    category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                    book.setCategory(category);
                    book.setBookTitle(resultSet.getString("book_title"));
                    book.setPengarang(resultSet.getString("pengarang"));
                    book.setTerbitDate(resultSet.getDate("thn_terbit"));
                    book.setIsDipinjam(resultSet.getString("isDipinjam"));
                    book.setStock(resultSet.getInt("stock"));

                    list.add(book);
                }
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public Integer getNoOfRecordsWhereNotDipinjamByCategory(Integer idCategory) throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);
            String sql = "SELECT COUNT(*) FROM ( \n"
                    + "SELECT COUNT(*)\n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "LEFT JOIN peminjaman C ON B.id_book = C.id_book AND C.isKembali = 0\n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE (B.isKembali = 1 OR B.isKembali IS NULL) AND C.id_book IS NULL AND A.id_cat = " + idCategory + " GROUP BY A.book_title, A.id_cat\n"
                    + ") AS SQ;";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            connection.setAutoCommit(false);
            connection.commit();

            return count;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public List<Book> selectWhereNotDipinjamByYearWithLimit(String Year, int offset, int noOfRecords) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Book> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit, COUNT(1) AS stock,\n"
                    + "CASE\n"
                    + "WHEN B.isKembali IS NOT NULL AND B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam\n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "LEFT JOIN peminjaman C ON B.id_book = C.id_book AND C.isKembali = 0\n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE (B.isKembali = 1 OR B.isKembali IS NULL) AND C.id_book IS NULL AND YEAR(A.thn_terbit) = " + Year + " GROUP BY A.book_title, A.id_cat ORDER BY A.book_title LIMIT " + offset + "," + noOfRecords + ";";

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                if (resultSet.getString("isDipinjam").equalsIgnoreCase("Available")) {
                    Book book = new Book();

                    book.setIdBook(resultSet.getInt("id_book"));

                    Category category;
                    CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                    category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                    book.setCategory(category);
                    book.setBookTitle(resultSet.getString("book_title"));
                    book.setPengarang(resultSet.getString("pengarang"));
                    book.setTerbitDate(resultSet.getDate("thn_terbit"));
                    book.setIsDipinjam(resultSet.getString("isDipinjam"));
                    book.setStock(resultSet.getInt("stock"));

                    list.add(book);
                }
            }

            connection.setAutoCommit(false);
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    @Override
    public Integer getNoOfRecordsWhereNotDipinjamByYear(String Year) throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);
            String sql = "SELECT COUNT(*) FROM (\n"
                    + "SELECT COUNT(*)\n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "LEFT JOIN peminjaman C ON B.id_book = C.id_book AND C.isKembali = 0\n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE (B.isKembali = 1 OR B.isKembali IS NULL) AND C.id_book IS NULL AND YEAR(A.thn_terbit) = " + Year + " GROUP BY A.book_title, A.id_cat\n"
                    + " ) AS SQ;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            connection.setAutoCommit(false);
            connection.commit();

            return count;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    @Override
    public Book selectLastRecord() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_book, A.book_title, A.id_cat, A.pengarang, A.thn_terbit,\n"
                    + "CASE\n"
                    + "WHEN B.isKembali = 0 THEN 'Sedang dipinjam'\n"
                    + "ELSE 'Available'\n"
                    + "END AS isDipinjam \n"
                    + "FROM book AS A LEFT JOIN peminjaman AS B \n"
                    + "ON A.id_book = B.id_book\n"
                    + "ORDER BY A.id_book DESC LIMIT 0,1";

            System.out.println(sql);

            Book book = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                book = new Book();

                book.setIdBook(resultSet.getInt("id_book"));

                Category category;
                CategoryDao categoryDao = ConnectionMySQL.getCategoryDao();
                category = categoryDao.selectCategotyById(resultSet.getInt("id_cat"));

                book.setCategory(category);
                book.setBookTitle(resultSet.getString("book_title"));
                book.setPengarang(resultSet.getString("pengarang"));
                book.setTerbitDate(resultSet.getDate("thn_terbit"));
                book.setIsDipinjam(resultSet.getString("isDipinjam"));

            }
            connection.setAutoCommit(false);
            connection.commit();
            return book;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new Exception(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

}
