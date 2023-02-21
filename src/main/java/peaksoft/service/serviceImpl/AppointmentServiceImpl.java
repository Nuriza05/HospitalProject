package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.repository.AppointmentRepo;
import peaksoft.repository.repositoryImpl.AppointmentRepoImpl;
import peaksoft.service.AppointmentService;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepo appointmentRepo;

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepo.getAll();
    }

    @Override
    public void deleteById(Long id) {
        appointmentRepo.deleteById(id);
    }

    @Override
    public Appointment getById(Long id) {
        return appointmentRepo.getById(id);
    }

    @Override
    public void update(Long id, Appointment newAppointment) {
        appointmentRepo.update(id, newAppointment);
    }
}
