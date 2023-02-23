package peaksoft.service;

import peaksoft.model.Doctor;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public interface DoctorService {
    Doctor save(Long id,Doctor doctor);
    List<Doctor> getAll(Long hospitalId);
    void deleteById(Long id);
    Doctor getById(Long id);
    void update (Long id, Doctor newDoctor);
}
