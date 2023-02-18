package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Doctor;
import peaksoft.repository.DoctorRepo;
import peaksoft.repository.repositoryImpl.DoctorRepoImpl;
import peaksoft.service.DoctorService;

import javax.print.Doc;
import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepo doctorRepo;

    @Override
    public Doctor save(Doctor doctor) {
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
        doctorRepo.update(id,newDoctor);
    }
}
