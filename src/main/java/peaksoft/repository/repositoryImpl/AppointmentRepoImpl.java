package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Appointment;
import peaksoft.repository.AppointmentRepo;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Repository
@Transactional
public class AppointmentRepoImpl implements AppointmentRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired

    public AppointmentRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Appointment save(Appointment appointment) {
        entityManager.persist(appointment);
        return appointment;
    }

    @Override
    public List<Appointment> getAll() {
        return entityManager.createQuery("select a from Appointment a", Appointment.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Appointment.class, id));
    }

    @Override
    public Appointment getById(Long id) {
        return entityManager.find(Appointment.class, id);
    }

    @Override
    public void update(Long id, Appointment newAppointment) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        appointment.setDate(newAppointment.getDate());
        appointment.setPatient(newAppointment.getPatient());
        appointment.setDoctor(newAppointment.getDoctor());
        appointment.setDepartment(newAppointment.getDepartment());
    }
}
