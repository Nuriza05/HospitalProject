package peaksoft.repository;

import jakarta.persistence.EntityManager;
import peaksoft.exceptions.MyException;
import peaksoft.model.Department;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public interface DepartmentRepo {
    Department save(Department department);
    void deleteById(Long id);
    Department getById(Long id);
    void update (Long id, Department newDepartment) ;
    List<Department> getAll(Long id);


}
