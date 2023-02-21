package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
import peaksoft.repository.DepartmentRepo;
import peaksoft.repository.HospitalRepo;
import peaksoft.service.DepartmentService;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final HospitalRepo hospitalRepo;
    private final DepartmentRepo departmentRepo;

    @Autowired
    public DepartmentServiceImpl(HospitalRepo hospitalRepo, DepartmentRepo departmentRepo) {
        this.hospitalRepo = hospitalRepo;
        this.departmentRepo = departmentRepo;

    }


    @Override
    public Department save(Long hospitalId,Department newDepartment) {
        Hospital hospital = hospitalRepo.getById(hospitalId);
        newDepartment.setHospital(hospital);
        return departmentRepo.save(newDepartment);
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
        departmentRepo.update(id, newDepartment);
    }

    @Override
    public List<Department> getAll(Long id) {
        return departmentRepo.getAll(id);
    }
}
