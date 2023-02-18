package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Entity
@Table(name = "patients")
@Getter @Setter
@NoArgsConstructor
public class Patient {
    @Id
    @SequenceGenerator(name = "patient_gen",sequenceName = "patient_seq",allocationSize = 1,initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patient_gen")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true)
    private String email;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST})
    private Hospital hospital;
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Appointment> appoitmentList;
}
