package peaksoft.repository;

import peaksoft.model.Appointment;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public interface AppointmentRepo {
    Appointment save(Appointment appointment);
    List<Appointment> getAll(Long id);
    void deleteById(Long id);
    Appointment getById(Long id);
    void update (Long id, Appointment newAppointment);

}
