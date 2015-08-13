/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Member;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface MemberDao {

    public void Insert(Member member) throws Exception;

    public void Update(Member member) throws Exception;

    public void Delete(Integer idMember) throws Exception;

    public List<Member> selectAll() throws Exception;

    public List<Member> selectAllWithLimit(int offset, int noOfRecords) throws Exception;

    public Integer getNoOfRecords() throws Exception;

    public Member getMemberById(Integer idMember) throws Exception;

    public Member getMemberByNameAndPass(String Name, String Password) throws Exception;

}
