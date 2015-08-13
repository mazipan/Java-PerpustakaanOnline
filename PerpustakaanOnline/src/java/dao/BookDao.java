package dao;

import entity.Book;
import java.util.List;

/**
 *
 * @author joker
 */
public interface BookDao {

    public void Insert(Book book) throws Exception;

    public void Update(Book book) throws Exception;

    public void Delete(Integer idBook) throws Exception;

    public List<Book> selectAll() throws Exception;

    public List<Book> selectAllWithLimit(int offset, int noOfRecords) throws Exception;

    public Integer getNoOfRecords() throws Exception;

    public List<Book> selectByCategory(Integer idCategory) throws Exception;    
    
    public boolean isExistByCategory(Integer idCategory) throws Exception;

    public List<Book> selectByCategoryWithLimit(Integer idCategory, int offset, int noOfRecords) throws Exception;

    public Integer getNoOfRecordsByCategory(Integer idCategory) throws Exception;

    public List<Book> selectByYear(String Year) throws Exception;

    public List<Book> selectByYearWithLimit(String Year, int offset, int noOfRecords) throws Exception;

    public Integer getNoOfRecordsByYear(String Year) throws Exception;

    public List<Book> selectWhereDipinjam() throws Exception;

    public List<Book> selectWhereNotDipinjam() throws Exception;

    public List<Book> selectWhereNotDipinjamWithLimit(int offset, int noOfRecords) throws Exception;

    public Integer getNoOfRecordsWhereNotDipinjam() throws Exception;

    public List<Book> selectWhereNotDipinjamByCategory(Integer idCategory) throws Exception;

    public List<Book> selectWhereNotDipinjamByCategoryWithLimit(Integer idCategory, int offset, int noOfRecords) throws Exception;

    public Integer getNoOfRecordsWhereNotDipinjamByCategory(Integer idCategory) throws Exception;

    public List<Book> selectWhereNotDipinjamByYear(String Year) throws Exception;

    public List<Book> selectWhereNotDipinjamByYearWithLimit(String Year, int offset, int noOfRecords) throws Exception;

    public Integer getNoOfRecordsWhereNotDipinjamByYear(String Year) throws Exception;

    public Book getBookById(Integer idBook) throws Exception;

    public Book selectLastRecord() throws Exception;
}
