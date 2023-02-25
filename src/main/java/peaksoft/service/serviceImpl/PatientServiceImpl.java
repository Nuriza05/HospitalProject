package peaksoft.service.serviceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.exceptions.MyException;
import peaksoft.model.Appointment;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repository.AppointmentRepo;
import peaksoft.repository.HospitalRepo;
import peaksoft.repository.PatientRepo;
import peaksoft.service.PatientService;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final HospitalRepo hospitalRepo;
    private final AppointmentRepo appointmentRepo;

    @Autowired
    public PatientServiceImpl(PatientRepo patientRepo, HospitalRepo hospitalRepo, AppointmentRepo appointmentRepo) {
        this.patientRepo = patientRepo;
        this.hospitalRepo = hospitalRepo;
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public Patient save(Long id,Patient newpatient) throws MyException {
        if(newpatient.getPhoneNumber().startsWith("+996") && newpatient.getPhoneNumber().chars().count()==13) {
            Hospital hospital = hospitalRepo.getById(id);
            newpatient.setHospital(hospital);
            return patientRepo.save(newpatient);
        }else {
            throw new MyException("Phone number should be starts with +996 and size = 13!");
        }
    }

    @Override
    public List<Patient> getAll(Long id) {
        return patientRepo.getAll(id);
    }

    @Override
    public void deleteById(Long id) {
        Patient patient = patientRepo.getById(id);
        List<Appointment> appointments = patient.getAppoitmentList();
        if (appointments != null) {
            List<Appointment> appointmentList = appointments.stream().filter(s -> s.getPatient().getId().equals(id)).toList();
            appointmentList.forEach(s -> appointmentRepo.deleteById(s.getId()));
        }
        List<Patient> patients = patient.getHospital().getPatients();
        patients.removeIf(s -> s.getId().equals(id));

        patientRepo.deleteById(id);

    }

    @Override
    public Patient getById(Long id) {
        return patientRepo.getById(id);
    }

    @Override
    public void update(Long id, Patient newPatient) throws MyException {
        patientRepo.update(id, newPatient);
    }
}
