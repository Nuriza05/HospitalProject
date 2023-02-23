package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.DoctorRepo;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Repository
@Transactional
public class DoctorRepoImpl implements DoctorRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public DoctorRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Doctor save(Doctor doctor) {
        entityManager.merge(doctor);
        return doctor;
    }



    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Doctor.class,id));
    }

    @Override
    public Doctor getById(Long id) {
        return entityManager.find(Doctor.class, id);
    }

    @Override
    public void update(Long id, Doctor newDoctor) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        doctor.setFirstName(newDoctor.getFirstName());
        doctor.setLastName(newDoctor.getLastName());
        doctor.setPosition(newDoctor.getPosition());
        doctor.setEmail(newDoctor.getEmail());
        doctor.setDepartments(newDoctor.getDepartments());



    }

    @Override
    public List<Doctor> getAll(Long id) {
        return entityManager.createQuery("select l from Doctor l join l.hospital h where h.id=:id",Doctor.class).setParameter("id",id).getResultList();
    }
}
