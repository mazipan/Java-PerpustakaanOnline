package dao;

import entity.Category;
import java.util.List;

/**
 *
 * @author joker
 */
public interface CategoryDao {

    public void Insert(Category category) throws Exception;

    public void Update(Category category) throws Exception;

    public void Delete(Integer idCategory) throws Exception;

    public List<Category> selectAll() throws Exception;

    public List<Category> selectAllWithLimit(int offset, int noOfRecords) throws Exception;

    public Integer getNoOfRecords() throws Exception;

    public Category selectCategotyById(Integer idCategory) throws Exception;

    public Category selectCategotyByName(String categoryName) throws Exception;

}
