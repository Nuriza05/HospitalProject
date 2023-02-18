package peaksoft.service;

import peaksoft.model.Appointment;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public interface AppointmentService {
    Appointment save(Appointment appointment);
    List<Appointment> getAll();
    void deleteById(Long id);
    Appointment getById(Long id);
    void update (Long id, Appointment newAppointment);
}
