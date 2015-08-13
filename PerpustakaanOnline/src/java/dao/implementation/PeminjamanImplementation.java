package dao.implementation;

import dao.BookDao;
import dao.MemberDao;
import dao.PeminjamanDao;
import entity.Book;
import entity.Member;
import entity.Peminjaman;
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
public class PeminjamanImplementation implements PeminjamanDao {

    private Connection connection;

    public PeminjamanImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void Insert(Peminjaman peminjaman) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO peminjaman SET id_pinjam = null, id_member = ?, id_book = ?, tgl_pinjam = ?, tgl_kembali = ?, isKembali = ?, isDenda = ?, tgl_pengembalian = ?;";

            statement = connection.prepareStatement(sql);

            statement.setInt(1, peminjaman.getMember().getMemberId());
            statement.setInt(2, peminjaman.getBook().getIdBook());
            statement.setString(3, peminjaman.getTglPinjamStr());
            statement.setString(4, peminjaman.getTglKembaliStr());
            if (peminjaman.isIsKembali()) {
                statement.setInt(5, 1);
            } else {
                statement.setInt(5, 0);
            }
            if (peminjaman.isIsDenda()) {
                statement.setInt(6, 1);
            } else {
                statement.setInt(6, 0);
            }
            statement.setString(7, peminjaman.getTglPengembalianStr());

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
    public void Update(Peminjaman peminjaman) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE peminjaman SET id_member = ?, id_book = ?, tgl_pinjam = ?, tgl_kembali = ?, isKembali = ?, isDenda = ?, tgl_pengembalian = ? WHERE id_pinjam = ?;";

            statement = connection.prepareStatement(sql);

            statement.setInt(1, peminjaman.getMember().getMemberId());
            statement.setInt(2, peminjaman.getBook().getIdBook());
            statement.setString(3, peminjaman.getTglPinjamStr());
            statement.setString(4, peminjaman.getTglKembaliStr());
            if (peminjaman.isIsKembali()) {
                statement.setInt(5, 1);
            } else {
                statement.setInt(5, 0);
            }
            if (peminjaman.isIsDenda()) {
                statement.setInt(6, 1);
            } else {
                statement.setInt(6, 0);
            }
            statement.setString(7, peminjaman.getTglPengembalianStr());
            statement.setInt(8, peminjaman.getIdPinjam());

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

            String sql = "DELETE FROM peminjaman WHERE id_pinjam = ?";

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
    public List<Peminjaman> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Peminjaman> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM peminjaman ORDER BY peminjaman.isKembali ASC, peminjaman.tgl_pinjam DESC;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Peminjaman peminjaman = new Peminjaman();

                peminjaman.setIdPinjam(resultSet.getInt("id_pinjam"));

                BookDao bookDao = ConnectionMySQL.getBookDao();
                Book book = bookDao.getBookById(resultSet.getInt("id_book"));
                peminjaman.setBook(book);

                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                Member member = memberDao.getMemberById(resultSet.getInt("id_member"));
                peminjaman.setMember(member);

                peminjaman.setTglKembali(resultSet.getDate("tgl_kembali"));
                peminjaman.setTglPinjam(resultSet.getDate("tgl_pinjam"));

                int kembali = resultSet.getInt("isKembali");
                if (kembali == 1) {
                    peminjaman.setIsKembali(true);
                } else {
                    peminjaman.setIsKembali(false);
                }

                int denda = resultSet.getInt("isDenda");
                if (denda == 1) {
                    peminjaman.setIsDenda(true);
                } else {
                    peminjaman.setIsDenda(false);
                }
                peminjaman.setTglPengembalian(resultSet.getDate("tgl_pengembalian"));

                list.add(peminjaman);
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
    public List<Peminjaman> selectThatHaveNotReturn() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Peminjaman> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM peminjaman WHERE isKembali = 0 ORDER BY peminjaman.isKembali ASC, peminjaman.tgl_pinjam DESC;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Peminjaman peminjaman = new Peminjaman();

                peminjaman.setIdPinjam(resultSet.getInt("id_pinjam"));

                BookDao bookDao = ConnectionMySQL.getBookDao();
                Book book = bookDao.getBookById(resultSet.getInt("id_book"));
                peminjaman.setBook(book);

                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                Member member = memberDao.getMemberById(resultSet.getInt("id_member"));
                peminjaman.setMember(member);

                peminjaman.setTglKembali(resultSet.getDate("tgl_kembali"));
                peminjaman.setTglPinjam(resultSet.getDate("tgl_pinjam"));

                int kembali = resultSet.getInt("isKembali");
                if (kembali == 1) {
                    peminjaman.setIsKembali(true);
                } else {
                    peminjaman.setIsKembali(false);
                }

                int denda = resultSet.getInt("isDenda");
                if (denda == 1) {
                    peminjaman.setIsDenda(true);
                } else {
                    peminjaman.setIsDenda(false);
                }
                peminjaman.setTglPengembalian(resultSet.getDate("tgl_pengembalian"));

                list.add(peminjaman);
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
    public List<Peminjaman> selectThatHaveReturn() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Peminjaman> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM peminjaman WHERE isKembali = 1 ORDER BY peminjaman.isKembali ASC, peminjaman.tgl_pinjam DESC;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Peminjaman peminjaman = new Peminjaman();

                peminjaman.setIdPinjam(resultSet.getInt("id_pinjam"));

                BookDao bookDao = ConnectionMySQL.getBookDao();
                Book book = bookDao.getBookById(resultSet.getInt("id_book"));
                peminjaman.setBook(book);

                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                Member member = memberDao.getMemberById(resultSet.getInt("id_member"));
                peminjaman.setMember(member);

                peminjaman.setTglKembali(resultSet.getDate("tgl_kembali"));
                peminjaman.setTglPinjam(resultSet.getDate("tgl_pinjam"));

                int kembali = resultSet.getInt("isKembali");
                if (kembali == 1) {
                    peminjaman.setIsKembali(true);
                } else {
                    peminjaman.setIsKembali(false);
                }

                int denda = resultSet.getInt("isDenda");
                if (denda == 1) {
                    peminjaman.setIsDenda(true);
                } else {
                    peminjaman.setIsDenda(false);
                }
                peminjaman.setTglPengembalian(resultSet.getDate("tgl_pengembalian"));

                list.add(peminjaman);
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
    public List<Peminjaman> selectAllWithLimit(int offset, int noOfRecords) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Peminjaman> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM peminjaman ORDER BY peminjaman.isKembali ASC, peminjaman.tgl_pinjam DESC LIMIT " + offset + "," + noOfRecords + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Peminjaman peminjaman = new Peminjaman();

                peminjaman.setIdPinjam(resultSet.getInt("id_pinjam"));

                BookDao bookDao = ConnectionMySQL.getBookDao();
                Book book = bookDao.getBookById(resultSet.getInt("id_book"));
                peminjaman.setBook(book);

                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                Member member = memberDao.getMemberById(resultSet.getInt("id_member"));
                peminjaman.setMember(member);

                peminjaman.setTglKembali(resultSet.getDate("tgl_kembali"));
                peminjaman.setTglPinjam(resultSet.getDate("tgl_pinjam"));

                int kembali = resultSet.getInt("isKembali");
                if (kembali == 1) {
                    peminjaman.setIsKembali(true);
                } else {
                    peminjaman.setIsKembali(false);
                }

                int denda = resultSet.getInt("isDenda");
                if (denda == 1) {
                    peminjaman.setIsDenda(true);
                } else {
                    peminjaman.setIsDenda(false);
                }
                peminjaman.setTglPengembalian(resultSet.getDate("tgl_pengembalian"));

                list.add(peminjaman);
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
    public Peminjaman getPeminjamanById(Integer id) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM peminjaman where id_pinjam = " + id + " ;";
            System.out.println(sql);

            Peminjaman peminjaman = new Peminjaman();

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                peminjaman.setIdPinjam(resultSet.getInt("id_pinjam"));

                BookDao bookDao = ConnectionMySQL.getBookDao();
                Book book = bookDao.getBookById(resultSet.getInt("id_book"));
                peminjaman.setBook(book);

                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                Member member = memberDao.getMemberById(resultSet.getInt("id_member"));
                peminjaman.setMember(member);

                peminjaman.setTglKembali(resultSet.getDate("tgl_kembali"));
                peminjaman.setTglPinjam(resultSet.getDate("tgl_pinjam"));

                int kembali = resultSet.getInt("isKembali");
                if (kembali == 1) {
                    peminjaman.setIsKembali(true);
                } else {
                    peminjaman.setIsKembali(false);
                }

                int denda = resultSet.getInt("isDenda");
                if (denda == 1) {
                    peminjaman.setIsDenda(true);
                } else {
                    peminjaman.setIsDenda(false);
                }
                peminjaman.setTglPengembalian(resultSet.getDate("tgl_pengembalian"));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return peminjaman;
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
    public List<Peminjaman> getPeminjamanByIdMember(Integer idMember) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Peminjaman> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM peminjaman WHERE id_member = " + idMember + " ORDER BY peminjaman.isKembali ASC, peminjaman.tgl_pinjam DESC;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Peminjaman peminjaman = new Peminjaman();

                peminjaman.setIdPinjam(resultSet.getInt("id_pinjam"));

                BookDao bookDao = ConnectionMySQL.getBookDao();
                Book book = bookDao.getBookById(resultSet.getInt("id_book"));
                peminjaman.setBook(book);

                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                Member member = memberDao.getMemberById(resultSet.getInt("id_member"));
                peminjaman.setMember(member);

                peminjaman.setTglKembali(resultSet.getDate("tgl_kembali"));
                peminjaman.setTglPinjam(resultSet.getDate("tgl_pinjam"));

                int kembali = resultSet.getInt("isKembali");
                if (kembali == 1) {
                    peminjaman.setIsKembali(true);
                } else {
                    peminjaman.setIsKembali(false);
                }

                int denda = resultSet.getInt("isDenda");
                if (denda == 1) {
                    peminjaman.setIsDenda(true);
                } else {
                    peminjaman.setIsDenda(false);
                }
                peminjaman.setTglPengembalian(resultSet.getDate("tgl_pengembalian"));

                list.add(peminjaman);
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
    public boolean isExistByMember(Integer idMember) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        boolean result = false;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM peminjaman WHERE id_member = " + idMember + ";";
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
    public boolean isExistByBook(Integer idBook) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        boolean result = false;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM peminjaman WHERE id_book = " + idBook + ";";
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
    public Integer getCountPinjamByIdMember(Integer idMember) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(peminjaman.id_pinjam) AS TotalPinjam FROM peminjaman WHERE id_member = " + idMember + " AND peminjaman.isKembali = 0;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("TotalPinjam");
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
    public Integer getNoOfRecords() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM peminjaman;";
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
    public List<Peminjaman> getPeminjamanByIdMemberWithLimit(Integer memberId, int offset, int noOfRecords) throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Peminjaman> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM peminjaman WHERE id_member = " + memberId + " ORDER BY peminjaman.isKembali ASC, peminjaman.tgl_pinjam DESC LIMIT " + offset + "," + noOfRecords + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Peminjaman peminjaman = new Peminjaman();

                peminjaman.setIdPinjam(resultSet.getInt("id_pinjam"));

                BookDao bookDao = ConnectionMySQL.getBookDao();
                Book book = bookDao.getBookById(resultSet.getInt("id_book"));
                peminjaman.setBook(book);

                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                Member member = memberDao.getMemberById(resultSet.getInt("id_member"));
                peminjaman.setMember(member);

                peminjaman.setTglKembali(resultSet.getDate("tgl_kembali"));
                peminjaman.setTglPinjam(resultSet.getDate("tgl_pinjam"));

                int kembali = resultSet.getInt("isKembali");
                if (kembali == 1) {
                    peminjaman.setIsKembali(true);
                } else {
                    peminjaman.setIsKembali(false);
                }

                int denda = resultSet.getInt("isDenda");
                if (denda == 1) {
                    peminjaman.setIsDenda(true);
                } else {
                    peminjaman.setIsDenda(false);
                }
                peminjaman.setTglPengembalian(resultSet.getDate("tgl_pengembalian"));

                list.add(peminjaman);
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
    public Integer getNoOfRecordsByIdMember(Integer memberId) throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*) FROM peminjaman WHERE id_member = " + memberId + ";";
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
    public List<Peminjaman> FilterByBook(String bookName, int offset, int noOfRecords) throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Peminjaman> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_pinjam, A.id_member, A.tgl_pinjam, A.tgl_kembali, \n"
                    + "A.isKembali, A.id_book, A.isDenda, A.tgl_pengembalian, B.book_title \n"
                    + "FROM \n"
                    + "peminjaman A \n"
                    + "LEFT JOIN book B \n"
                    + "ON A.id_book = B.id_book \n"
                    + "WHERE B.book_title LIKE '%" + bookName + "%' \n"
                    + "ORDER BY A.isKembali ASC, A.tgl_pinjam DESC LIMIT " + offset + "," + noOfRecords + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Peminjaman peminjaman = new Peminjaman();

                peminjaman.setIdPinjam(resultSet.getInt("id_pinjam"));

                BookDao bookDao = ConnectionMySQL.getBookDao();
                Book book = bookDao.getBookById(resultSet.getInt("id_book"));
                peminjaman.setBook(book);

                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                Member member = memberDao.getMemberById(resultSet.getInt("id_member"));
                peminjaman.setMember(member);

                peminjaman.setTglKembali(resultSet.getDate("tgl_kembali"));
                peminjaman.setTglPinjam(resultSet.getDate("tgl_pinjam"));

                int kembali = resultSet.getInt("isKembali");
                if (kembali == 1) {
                    peminjaman.setIsKembali(true);
                } else {
                    peminjaman.setIsKembali(false);
                }

                int denda = resultSet.getInt("isDenda");
                if (denda == 1) {
                    peminjaman.setIsDenda(true);
                } else {
                    peminjaman.setIsDenda(false);
                }
                peminjaman.setTglPengembalian(resultSet.getDate("tgl_pengembalian"));

                list.add(peminjaman);
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
    public Integer FilterByBookCount(String bookName) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*)\n"
                    + "FROM \n"
                    + "peminjaman A \n"
                    + "LEFT JOIN book B \n"
                    + "ON A.id_book = B.id_book\n"
                    + "WHERE B.book_title LIKE '%" + bookName + "%' ;";
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
    public List<Peminjaman> FilterByMember(String memberName, int offset, int noOfRecords) throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Peminjaman> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT A.id_pinjam, A.id_member, A.tgl_pinjam, A.tgl_kembali, \n"
                    + "A.isKembali, A.id_book, A.isDenda, A.tgl_pengembalian, B.nama \n"
                    + "FROM \n"
                    + "peminjaman A \n"
                    + "LEFT JOIN member B \n"
                    + "ON A.id_member = B.id_member \n"
                    + "WHERE B.nama LIKE '%" + memberName + "%' \n"
                    + "ORDER BY A.isKembali ASC, A.tgl_pinjam DESC LIMIT " + offset + "," + noOfRecords + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Peminjaman peminjaman = new Peminjaman();

                peminjaman.setIdPinjam(resultSet.getInt("id_pinjam"));

                BookDao bookDao = ConnectionMySQL.getBookDao();
                Book book = bookDao.getBookById(resultSet.getInt("id_book"));
                peminjaman.setBook(book);

                MemberDao memberDao = ConnectionMySQL.getMemberDao();
                Member member = memberDao.getMemberById(resultSet.getInt("id_member"));
                peminjaman.setMember(member);

                peminjaman.setTglKembali(resultSet.getDate("tgl_kembali"));
                peminjaman.setTglPinjam(resultSet.getDate("tgl_pinjam"));

                int kembali = resultSet.getInt("isKembali");
                if (kembali == 1) {
                    peminjaman.setIsKembali(true);
                } else {
                    peminjaman.setIsKembali(false);
                }

                int denda = resultSet.getInt("isDenda");
                if (denda == 1) {
                    peminjaman.setIsDenda(true);
                } else {
                    peminjaman.setIsDenda(false);
                }
                peminjaman.setTglPengembalian(resultSet.getDate("tgl_pengembalian"));

                list.add(peminjaman);
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
    public Integer FilterByMemberCount(String memberName) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        Integer count = 0;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT COUNT(*)\n"
                    + "FROM \n"
                    + "peminjaman A \n"
                    + "LEFT JOIN member B \n"
                    + "ON A.id_member = B.id_member \n"
                    + "WHERE B.nama LIKE '%" + memberName + "%' ;";
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
