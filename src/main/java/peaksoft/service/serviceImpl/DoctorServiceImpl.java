package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.AppointmentRepo;
import peaksoft.repository.DepartmentRepo;
import peaksoft.repository.DoctorRepo;
import peaksoft.repository.HospitalRepo;
import peaksoft.repository.repositoryImpl.AppointmentRepoImpl;
import peaksoft.service.DoctorService;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;
    private final HospitalRepo hospitalRepo;
    private final DepartmentRepo departmentRepo;
    private final AppointmentRepo appointmentRepo;


    @Transactional
    @Override
    public Doctor save(Long id, Doctor newDoctor) {
        Hospital hospital = hospitalRepo.getById(id);
        Doctor doctor = new Doctor();
        doctor.setId(newDoctor.getId());
        doctor.setFirstName(newDoctor.getFirstName());
        doctor.setLastName(newDoctor.getLastName());
        doctor.setEmail(newDoctor.getEmail());
        doctor.setPosition(newDoctor.getPosition());
        doctor.setImageLink(newDoctor.getImageLink());
        doctor.setHospital(hospital);
        for (Long aLong : newDoctor.getDepartmentId()) {
            doctor.addDepartment(departmentRepo.getById(aLong));
        }


        return doctorRepo.save(doctor);
    }

    @Override
    public List<Doctor> getAll(Long hospitalId) {
        return doctorRepo.getAll(hospitalId);
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        Doctor doctor = doctorRepo.getById(id);
        List<Appointment> appointments = doctor.getAppointments();
        if (appointments != null) {
            List<Appointment> appointmentList = appointments.stream().filter(s -> s.getDoctor().getId().equals(id)).toList();
            appointmentList.forEach(s -> appointmentRepo.deleteById(s.getId()));
        }
        List<Doctor> doctors = doctor.getHospital().getDoctors();
        doctors.removeIf(d->d.getId().equals(id));

        doctorRepo.deleteById(id);
    }

    @Override
    public Doctor getById(Long id) {
        return doctorRepo.getById(id);
    }
     @Transactional
     @Override
    public void update(Long id, Doctor newDoctor) {
        doctorRepo.update(id, newDoctor);
    }
}
