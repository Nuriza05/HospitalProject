package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Entity
@Table(name = "hospitals")
@Getter
@Setter
@NoArgsConstructor
public class Hospital {
    @Id
    @SequenceGenerator(name = "hospital_gen", sequenceName = "hospital_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_gen")
    private Long id;

    private String name;
    private String address;
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Doctor> doctors;
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Patient> patients;
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Department> departments;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    private String imageLink;
    public void addAppoint(Appointment app){
        if(appointments == null){
            appointments = new ArrayList<>();
        }
        appointments.add(app);
    }



}
