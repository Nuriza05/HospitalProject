package peaksoft.service;

import peaksoft.exceptions.MyException;
import peaksoft.model.Department;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public interface DepartmentService {
    Department save(Long id,Department department) throws MyException;

    void deleteById(Long id);
    Department getById(Long id);
    void update (Long id, Department newDepartment) throws MyException;

    List<Department> getAll(Long id);

}
