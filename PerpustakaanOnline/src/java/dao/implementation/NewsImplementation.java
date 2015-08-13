package dao.implementation;

import dao.NewsDao;
import entity.News;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author joker
 */
public class NewsImplementation implements NewsDao {

    private Connection connection;

    public NewsImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void Insert(News news) throws Exception {

        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO newsupdate SET id_news = null, news_name = ?, news_place = ?, news_desc = ?, news_time = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, news.getNewsName());
            statement.setString(2, news.getNewsPlace());
            statement.setString(3, news.getNewsDesc());
            statement.setString(4, news.getNewsDateTimeStr());

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
    public void Update(News news) throws Exception {

        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE newsupdate SET news_name = ?, news_place = ?, news_desc = ?, news_time = ? WHERE id_news = ?;";

            statement = connection.prepareStatement(sql);

            statement.setString(1, news.getNewsName());
            statement.setString(2, news.getNewsPlace());
            statement.setString(3, news.getNewsDesc());
            statement.setString(4, news.getNewsDateTimeStr());
            statement.setInt(5, news.getIdNews());

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
    public void Delete(Integer id) throws Exception {

        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM newsupdate WHERE id_news = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

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
    public List<News> selectAllWithLimit(int offset, int noOfRecords) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<News> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM newsupdate LIMIT " + offset + "," + noOfRecords + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                News news = new News();

                news.setIdNews(resultSet.getInt("id_news"));
                news.setNewsName(resultSet.getString("news_name"));
                news.setNewsPlace(resultSet.getString("news_place"));
                news.setNewsDesc(resultSet.getString("news_desc"));
                String dateStr = resultSet.getString("news_time");
                Date date;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (dateStr != null) {
                    try {
                        date = sdf.parse(dateStr);
                        DateTime dateTime = new DateTime(date);
                        news.setNewsDateTime(dateTime);
                    } catch (ParseException e) {
                    }
                }

                list.add(news);
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
    public News selectLastRecord() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        News news = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM newsupdate ORDER BY id_news DESC LIMIT 0,1;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                news = new News();

                news.setIdNews(resultSet.getInt("id_news"));
                news.setNewsName(resultSet.getString("news_name"));
                news.setNewsPlace(resultSet.getString("news_place"));
                news.setNewsDesc(resultSet.getString("news_desc"));
                String dateStr = resultSet.getString("news_time");
                Date date;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (dateStr != null) {
                    try {
                        date = sdf.parse(dateStr);
                        DateTime dateTime = new DateTime(date);
                        news.setNewsDateTime(dateTime);
                    } catch (ParseException e) {
                    }
                }

            }

            connection.setAutoCommit(false);
            connection.commit();
            return news;
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
    public Integer getNoOfRecord() throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM newsupdate;";
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
    public News selectById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        News news = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM newsupdate WHERE id_news = " + id + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                news = new News();

                news.setIdNews(resultSet.getInt("id_news"));
                news.setNewsName(resultSet.getString("news_name"));
                news.setNewsPlace(resultSet.getString("news_place"));
                news.setNewsDesc(resultSet.getString("news_desc"));
                String dateStr = resultSet.getString("news_time");
                Date date;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (dateStr != null) {
                    try {
                        date = sdf.parse(dateStr);
                        DateTime dateTime = new DateTime(date);
                        news.setNewsDateTime(dateTime);
                    } catch (ParseException e) {
                    }
                }

            }

            connection.setAutoCommit(false);
            connection.commit();
            return news;
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
