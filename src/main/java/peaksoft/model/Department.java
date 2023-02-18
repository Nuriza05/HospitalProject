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
@Table(name = "departments")
@Getter @Setter
@NoArgsConstructor
public class Department {
    @Id
    @SequenceGenerator(name = "department_gen",sequenceName = "department_seq",allocationSize = 1,initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "department_gen")
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "departments",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    private List<Doctor> doctors;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE})
    private Hospital hospital;
    @Transient
    private Long hospitalId;
}
