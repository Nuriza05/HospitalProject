package peaksoft.repository;

import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public interface DoctorRepo {
    Doctor save(Doctor doctor);
    void deleteById(Long id);
    Doctor getById(Long id);
    void update (Long id, Doctor newDoctor);
    List<Doctor> getAll(Long id);
    void assignDepsToDoctor(Long docId,List<Long> depsId);

}
