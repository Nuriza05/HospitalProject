package peaksoft.service;

import peaksoft.model.Department;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public interface DepartmentService {
    Department save(Department department);
    List<Department> getAll();
    void deleteById(Long id);
    Department getById(Long id);
    void update (Long id, Department newDepartment);
}