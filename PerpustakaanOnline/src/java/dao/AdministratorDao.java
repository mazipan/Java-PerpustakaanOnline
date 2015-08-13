package dao;

import entity.Administrator;
import java.util.List;

/**
 *
 * @author joker
 */
public interface AdministratorDao {

    public void Insert(Administrator administrator) throws Exception;

    public void Update(Administrator administrator) throws Exception;

    public void Delete(Integer id) throws Exception;

    public List<Administrator> selectAll() throws Exception;

    public List<Administrator> selectAllWithLimit(int offset, int noOfRecords) throws Exception;

    public Integer getNoOfRecords() throws Exception;

    public Administrator getByNameAndPass(String name, String password) throws Exception;
    
    public boolean isExist(String name) throws Exception;

    public Administrator getById(Integer id) throws Exception;
}
