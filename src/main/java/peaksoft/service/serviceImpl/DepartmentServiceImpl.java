package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.exceptions.MyException;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
import peaksoft.repository.AppointmentRepo;
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
    private final AppointmentRepo appointmentRepo;

    @Autowired
    public DepartmentServiceImpl(HospitalRepo hospitalRepo, DepartmentRepo departmentRepo, AppointmentRepo appointmentRepo) {
        this.hospitalRepo = hospitalRepo;
        this.departmentRepo = departmentRepo;

        this.appointmentRepo = appointmentRepo;
    }


    @Override
    public Department save(Long hospitalId, Department newDepartment) throws MyException {
//        if (departmentRepo.getAll(hospitalId).isEmpty()) {
            Hospital hospital = hospitalRepo.getById(hospitalId);
            newDepartment.setHospital(hospital);
            return departmentRepo.save(newDepartment);
//        } else {
//            for (Department department : departmentRepo.getAll(hospitalId)) {
//                if (department.getName().equals(newDepartment.getName())) {
//                    throw new MyException("Department name is already exit!");
//                }
//                else {
//                    Hospital hospital = hospitalRepo.getById(hospitalId);
//                    newDepartment.setHospital(hospital);
//                    return departmentRepo.save(newDepartment);
//                }
//            }
//            return null;
//        }
    }


    @Override
    public void deleteById(Long id) {
        Department department = departmentRepo.getById(id);
        List<Appointment> appointments = department.getHospital().getAppointments();

        if (appointments != null) {
            List<Appointment> appointmentList = appointments.stream().filter(s -> s.getDepartment().getId().equals(id)).toList();
            appointmentList.forEach(s -> appointmentRepo.deleteById(s.getId()));
        }

        List<Department> departments = department.getHospital().getDepartments();
        departments.removeIf(s -> s.getId().equals(id));

        departmentRepo.deleteById(id);
    }

    @Override
    public Department getById(Long id) {
        return departmentRepo.getById(id);
    }

    @Override
    public void update(Long id, Department newDepartment) throws MyException {
        Department byId = departmentRepo.getById(id);
        for (Department department : departmentRepo.getAll(byId.getHospital().getId())) {
            List<Department> departments = departmentRepo.getAll(byId.getHospital().getId());
            departments.remove(byId);
            if (!department.getName().equals(newDepartment.getName())) {
                departmentRepo.update(id, newDepartment);
            } else {
                throw new MyException("Department name is already exit!");
            }
        }

    }

    @Override
    public List<Department> getAll(Long id) {
        return departmentRepo.getAll(id);
    }
}
