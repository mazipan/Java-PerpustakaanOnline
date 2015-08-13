/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implementation;

import dao.MemberDao;
import entity.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.AES;

/**
 *
 * @author Administrator
 */
public class MemberImplementation implements MemberDao {

    private Connection connection;

    public MemberImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void Insert(Member member) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO member SET id_member = null, nik = ?, nama = ?, alamat = ?, telp = ?, bagian = ?, lokasi = ?, ext = ?, password = ? ;";

            statement = connection.prepareStatement(sql);
            statement.setString(1, member.getMemberNik());
            statement.setString(2, member.getMemberName());
            statement.setString(3, member.getMemberAlamat());
            statement.setString(4, member.getMemberTelp());
            statement.setString(5, member.getMemberBagian());
            statement.setString(6, member.getMemberLokasi());
            statement.setString(7, member.getMemberExt());
            statement.setString(8, member.getMemberPassword());

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
    public void Update(Member member) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE member SET nik = ?, nama = ?, alamat = ?, telp = ?, bagian = ?, lokasi = ?, ext = ?, password = ? WHERE id_member = ?;";

            statement = connection.prepareStatement(sql);
            statement.setString(1, member.getMemberNik());
            statement.setString(2, member.getMemberName());
            statement.setString(3, member.getMemberAlamat());
            statement.setString(4, member.getMemberTelp());
            statement.setString(5, member.getMemberBagian());
            statement.setString(6, member.getMemberLokasi());
            statement.setString(7, member.getMemberExt());
            statement.setString(8, member.getMemberPassword());
            statement.setInt(9, member.getMemberId());

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
    public void Delete(Integer idMember) throws Exception {
        PreparedStatement statement;
        statement = null;
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM member WHERE id_member = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, idMember);

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
    public List<Member> selectAll() throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Member> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM member;";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();

                member.setMemberId(resultSet.getInt("id_member"));
                member.setMemberNik(resultSet.getString("nik"));
                member.setMemberName(resultSet.getString("nama"));
                member.setMemberAlamat(resultSet.getString("alamat"));
                member.setMemberTelp(resultSet.getString("telp"));
                member.setMemberBagian(resultSet.getString("bagian"));
                member.setMemberLokasi(resultSet.getString("lokasi"));
                member.setMemberExt(resultSet.getString("ext"));
                //member.setMemberPassword(AES.decrypt(resultSet.getString("password")));

                list.add(member);
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
    public Member getMemberById(Integer idMember) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM member where id_member = " + idMember + " ;";
            System.out.println(sql);

            Member member = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                member = new Member();

                member.setMemberId(resultSet.getInt("id_member"));
                member.setMemberNik(resultSet.getString("nik"));
                member.setMemberName(resultSet.getString("nama"));
                member.setMemberAlamat(resultSet.getString("alamat"));
                member.setMemberTelp(resultSet.getString("telp"));
                member.setMemberBagian(resultSet.getString("bagian"));
                member.setMemberLokasi(resultSet.getString("lokasi"));
                member.setMemberExt(resultSet.getString("ext"));
                member.setMemberPassword(AES.decrypt(resultSet.getString("password")));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return member;
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
    public Member getMemberByNameAndPass(String Name, String Password) throws Exception {
        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;

        try {
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM member where nama = '" + Name + "' and password = '" + Password + "' ;";
            System.out.println(sql);

            Member member = null;

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                member = new Member();

                member.setMemberId(resultSet.getInt("id_member"));
                member.setMemberNik(resultSet.getString("nik"));
                member.setMemberName(resultSet.getString("nama"));
                member.setMemberAlamat(resultSet.getString("alamat"));
                member.setMemberTelp(resultSet.getString("telp"));
                member.setMemberBagian(resultSet.getString("bagian"));
                member.setMemberLokasi(resultSet.getString("lokasi"));
                member.setMemberExt(resultSet.getString("ext"));
                member.setMemberPassword(AES.decrypt(resultSet.getString("password")));

            }

            connection.setAutoCommit(false);
            connection.commit();
            return member;
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
    public List<Member> selectAllWithLimit(int offset, int noOfRecords) throws Exception {

        PreparedStatement statement;
        ResultSet resultSet;
        statement = null;
        resultSet = null;
        List<Member> list;
        try {
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM member LIMIT " + offset + "," + noOfRecords + ";";
            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Member member = new Member();

                member.setMemberId(resultSet.getInt("id_member"));
                member.setMemberNik(resultSet.getString("nik"));
                member.setMemberName(resultSet.getString("nama"));
                member.setMemberAlamat(resultSet.getString("alamat"));
                member.setMemberTelp(resultSet.getString("telp"));
                member.setMemberBagian(resultSet.getString("bagian"));
                member.setMemberLokasi(resultSet.getString("lokasi"));
                member.setMemberExt(resultSet.getString("ext"));
                //member.setMemberPassword(AES.decrypt(resultSet.getString("password")));

                list.add(member);
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

            String sql = "SELECT COUNT(*) FROM member;";
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
