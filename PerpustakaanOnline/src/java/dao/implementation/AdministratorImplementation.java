package dao.implementation;

import dao.AdministratorDao;
import entity.Administrator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.AES;

/**
 *
 * @author joker
 */
public class AdministratorImplementation implements AdministratorDao {

    private Connection connection;

    public AdministratorImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void Insert(Administrator administrator) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO admin SET id = null, username = ?, password = ?, true_name = ? ;";

            statement = connection.prepareStatement(sql);
            statement.setString(1, administrator.getUsername());
            statement.setString(2, administrator.getPassword());
            statement.setString(3, administrator.getTruename());

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
    public void Update(Administrator administrator) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE admin SET username = ?, password = ?, true_name = ? WHERE id = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, administrator.getUsername());
            statement.setString(2, administrator.getPassword());
            statement.setString(3, administrator.getTruename());
            statement.setInt(4, administrator.getIdAdministrator());

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

            String sql = "DELETE FROM admin WHERE id = ?";

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
    public List<Administrator> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Administrator> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM admin;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Administrator administrator = new Administrator();
                administrator.setIdAdministrator(resultSet.getInt("id"));
                administrator.setTruename(resultSet.getString("true_name"));
                administrator.setUsername(resultSet.getString("username"));
                //administrator.setPassword(AES.decrypt(resultSet.getString("password")));
                list.add(administrator);
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
    public Administrator getByNameAndPass(String name, String password) throws Exception {
        Statement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Administrator administrator = null;
        String sql = "SELECT * FROM admin WHERE username = '" + name + "' AND password = '" + password + "' ;";
        System.out.println(sql);
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                administrator = new Administrator();
                administrator.setIdAdministrator(resultSet.getInt("id"));
                administrator.setTruename(resultSet.getString("true_name"));
                administrator.setUsername(resultSet.getString("username"));
                administrator.setPassword(AES.decrypt(resultSet.getString("password")));
            }

            connection.setAutoCommit(false);
            connection.commit();
            return administrator;
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
    public boolean isExist(String name) throws Exception {
        Statement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        boolean result = false;
        String sql = "SELECT * FROM admin WHERE username = '" + name + "';";
        System.out.println(sql);
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                result = true;
            }

            connection.setAutoCommit(false);
            connection.commit();
            System.out.println("result "+result);
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
    public Administrator getById(Integer id) throws Exception {
        Statement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Administrator administrator = null;
        String sql = "SELECT * FROM admin WHERE id = '" + id + "';";
        System.out.println(sql);
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                administrator = new Administrator();
                administrator.setIdAdministrator(resultSet.getInt("id"));
                administrator.setTruename(resultSet.getString("true_name"));
                administrator.setUsername(resultSet.getString("username"));
                administrator.setPassword(AES.decrypt(resultSet.getString("password")));
            }

            connection.setAutoCommit(false);
            connection.commit();
            return administrator;
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
    public List<Administrator> selectAllWithLimit(int offset, int noOfRecords) throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Administrator> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM admin LIMIT " + offset + "," + noOfRecords + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Administrator administrator = new Administrator();
                administrator.setIdAdministrator(resultSet.getInt("id"));
                administrator.setTruename(resultSet.getString("true_name"));
                administrator.setUsername(resultSet.getString("username"));
                //administrator.setPassword(AES.decrypt(resultSet.getString("password")));
                list.add(administrator);
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

            String sql = "SELECT COUNT(*) FROM admin;";
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

}
