package dao;

import entity.Peminjaman;
import java.util.List;

/**
 *
 * @author joker
 */
public interface PeminjamanDao {

    public void Insert(Peminjaman peminjaman) throws Exception;

    public void Update(Peminjaman peminjaman) throws Exception;

    public void Delete(Integer id) throws Exception;

    public List<Peminjaman> selectAll() throws Exception;

    public List<Peminjaman> selectThatHaveNotReturn() throws Exception;

    public List<Peminjaman> selectThatHaveReturn() throws Exception;

    public List<Peminjaman> selectAllWithLimit(int offset, int noOfRecords) throws Exception;

    public Peminjaman getPeminjamanById(Integer id) throws Exception;

    public List<Peminjaman> getPeminjamanByIdMember(Integer memberId) throws Exception;

    public List<Peminjaman> getPeminjamanByIdMemberWithLimit(Integer memberId, int offset, int noOfRecords) throws Exception;

    public Integer getNoOfRecordsByIdMember(Integer memberId) throws Exception;

    public Integer getCountPinjamByIdMember(Integer memberId) throws Exception;

    public Integer getNoOfRecords() throws Exception;
    
    public boolean isExistByBook(Integer idBook) throws Exception;
    
    public boolean isExistByMember(Integer idMember) throws Exception;    
    
    public List<Peminjaman> FilterByBook(String bookName, int offset, int noOfRecords) throws Exception;
    
    public Integer FilterByBookCount(String bookName) throws Exception;
    
    public List<Peminjaman> FilterByMember(String memberName, int offset, int noOfRecords) throws Exception;
    
    public Integer FilterByMemberCount(String memberName) throws Exception;
}
