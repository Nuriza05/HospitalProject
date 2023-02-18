package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Hospital;
import peaksoft.repository.HospitalRepo;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Repository
@Transactional
public class HospitalRepoImpl implements HospitalRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired

    public HospitalRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Hospital save(Hospital hospital) {
        entityManager.persist(hospital);
        return hospital;
    }

    @Override
    public List<Hospital> getAll() {
        return entityManager.createQuery("select h from Hospital h", Hospital.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Hospital.class, id));
    }

    @Override
    public Hospital getById(Long id) {
        return entityManager.find(Hospital.class, id);
    }

    @Override
    public void update(Long id, Hospital newHospital) {
        Hospital hospital = entityManager.find(Hospital.class, id);
        hospital.setName(newHospital.getName());
        hospital.setAddress(newHospital.getAddress());
        hospital.setDoctors(newHospital.getDoctors());
        hospital.setPatients(newHospital.getPatients());
        hospital.setDepartments(newHospital.getDepartments());
        hospital.setAppointments(newHospital.getAppointments());
    }
}
