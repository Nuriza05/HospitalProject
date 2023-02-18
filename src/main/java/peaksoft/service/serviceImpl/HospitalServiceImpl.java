package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Hospital;
import peaksoft.repository.HospitalRepo;
import peaksoft.repository.repositoryImpl.HospitalRepoImpl;
import peaksoft.service.HospitalService;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepo hospitalRepo;
    @Override
    public Hospital save(Hospital hospital) {
        return hospitalRepo.save(hospital);
    }

    @Override
    public List<Hospital> getAll() {
        return hospitalRepo.getAll();
    }

    @Override
    public void deleteById(Long id) {
      hospitalRepo.deleteById(id);
    }

    @Override
    public Hospital getById(Long id) {
        return hospitalRepo.getById(id);
    }

    @Override
    public void update(Long id, Hospital newHospital) {
     hospitalRepo.update(id,newHospital);
    }
}
