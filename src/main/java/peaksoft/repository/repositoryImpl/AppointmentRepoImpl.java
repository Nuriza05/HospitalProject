package peaksoft.repository.repositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.*;
import peaksoft.repository.AppointmentRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @Transactional
    @Override
    public Appointment save(Appointment appointment) {
        entityManager.persist(appointment);
        return appointment;
    }

    @Override
    public List<Appointment> getAll(Long id) {
        return entityManager.createQuery("select h from Hospital l join l.appointments h where l.id=:id ", Appointment.class).setParameter("id",id).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        List<Hospital> hospitals = entityManager.createQuery("select l from Hospital l", Hospital.class).getResultList();
        hospitals.forEach(h-> h.getAppointments().removeIf(a->a.getId().equals(id)));
        entityManager.remove(entityManager.find(Appointment.class,id));


    }

    @Override
    public Appointment getById(Long id) {
        return entityManager.find(Appointment.class, id);
    }

    @Override
    public void update(Long id, Appointment newAppointment) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        appointment.setDate(newAppointment.getDate());
        appointment.setPatient(entityManager.find(Patient.class,newAppointment.getPatientId()));
        appointment.setDoctor(entityManager.find(Doctor.class,newAppointment.getPatientId()));
        appointment.setDepartment(entityManager.find(Department.class,newAppointment.getPatientId()));

    }
}
