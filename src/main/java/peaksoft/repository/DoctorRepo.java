package peaksoft.repository;

import peaksoft.model.Doctor;
import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public interface DoctorRepo {
    Doctor save(Doctor doctor);
    List<Doctor> getAll();
    void deleteById(Long id);
    Doctor getById(Long id);
    void update (Long id, Doctor newDoctor);

}
