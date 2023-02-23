package peaksoft.repository;


import peaksoft.exceptions.MyException;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public interface PatientRepo {
    Patient save(Patient patient);
    List<Patient> getAll(Long id);
    void deleteById(Long id);
    Patient getById(Long id);
    void update (Long id, Patient newPatient) throws MyException;

}
