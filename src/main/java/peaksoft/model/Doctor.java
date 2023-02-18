package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Entity
@Table(name = "doctors")
@Getter @Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @SequenceGenerator(name = "generator_gen",sequenceName = "doctors_seq",allocationSize = 1,initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "doctor_gen")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String position;
    @Column(unique = true)
    private String email;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE})
    private List<Department> departments;
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE})
    private Hospital hospital;
    private String imageLink;

}
