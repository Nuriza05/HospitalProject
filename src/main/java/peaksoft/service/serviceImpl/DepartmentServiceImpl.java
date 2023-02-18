package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Department;
import peaksoft.repository.DepartmentRepo;
import peaksoft.service.DepartmentService;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }


    @Override
    public Department save(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepo.getAll();
    }

    @Override
    public void deleteById(Long id) {
       departmentRepo.deleteById(id);
    }

    @Override
    public Department getById(Long id) {
        return departmentRepo.getById(id);
    }

    @Override
    public void update(Long id, Department newDepartment) {
      departmentRepo.update(id,newDepartment);
    }
}
