package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.exceptions.MyException;
import peaksoft.model.Appointment;
import peaksoft.model.Hospital;
import peaksoft.repository.*;
import peaksoft.service.AppointmentService;

import java.time.LocalDate;
import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final HospitalRepo hospitalRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final DepartmentRepo departmentRepo;

    @Transactional
    @Override
    public Appointment save(Long id, Appointment appointment) throws MyException {
        Hospital hospital = hospitalRepo.getById(id);
        Appointment appointment1 = new Appointment();
        appointment1.setId(appointment.getId());
        appointment1.setPatient(patientRepo.getById(appointment.getPatientId()));
        appointment1.setDoctor(doctorRepo.getById(appointment.getDoctorId()));
        appointment1.setDepartment(departmentRepo.getById(appointment.getDepartmentId()));
        appointment1.setDate(appointment.getDate());
        hospital.addAppoint(appointment1);
        if (appointment1.getDate().isAfter(LocalDate.now())) {
            return appointmentRepo.save(appointment1);
        } else {
            throw new MyException("Date should be in future time!");
        }
    }

    @Override
    public List<Appointment> getAll(Long id) {
        return appointmentRepo.getAll(id);
    }

    @Override
    public void deleteById(Long hId, Long id) {
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
