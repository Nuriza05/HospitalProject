package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.repository.DepartmentRepo;
import peaksoft.repository.DoctorRepo;
import peaksoft.repository.HospitalRepo;
import peaksoft.repository.repositoryImpl.DepartmentRepoImpl;
import peaksoft.repository.repositoryImpl.DoctorRepoImpl;
import peaksoft.service.DoctorService;

import javax.print.Doc;
import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;
    private final HospitalRepo hospitalRepo;
    private final DepartmentRepo departmentRepo;

    @Override
    public Doctor save(Doctor newDoctor) {
        Doctor doctor = new Doctor();
        doctor.setId(newDoctor.getId());
        doctor.setFirstName(newDoctor.getFirstName());
        doctor.setLastName(newDoctor.getLastName());
        doctor.setEmail(newDoctor.getEmail());
        doctor.setPosition(newDoctor.getPosition());
        doctor.setImageLink(newDoctor.getImageLink());
        doctor.getDepartmentId().forEach(s -> doctor.getDepartments().add(departmentRepo.getById(s)));
        return doctorRepo.save(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepo.getAll();
    }

    @Override
    public void deleteById(Long id) {
        doctorRepo.deleteById(id);
    }

    @Override
    public Doctor getById(Long id) {
        return doctorRepo.getById(id);
    }

    @Override
    public void update(Long id, Doctor newDoctor) {
        doctorRepo.update(id, newDoctor);
    }
}
