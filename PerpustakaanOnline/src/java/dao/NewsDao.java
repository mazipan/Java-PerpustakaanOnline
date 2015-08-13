package dao;

import entity.News;
import java.util.List;

/**
 *
 * @author joker
 */
public interface NewsDao {

    public void Insert(News news) throws Exception;

    public void Update(News news) throws Exception;

    public void Delete(Integer id) throws Exception;

    public List<News> selectAllWithLimit(int offset, int noOfRecords) throws Exception;

    public News selectById(Integer id) throws Exception;

    public Integer getNoOfRecord() throws Exception;

    public News selectLastRecord() throws Exception;
}
