package peaksoft.repository;

import peaksoft.model.Hospital;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public interface HospitalRepo {
    Hospital save(Hospital hospital);
    List<Hospital> getAll();
    List<Hospital> search(String keyWord);
    void deleteById(Long id);
    Hospital getById(Long id);
    void update (Long id, Hospital newHospital);


}
