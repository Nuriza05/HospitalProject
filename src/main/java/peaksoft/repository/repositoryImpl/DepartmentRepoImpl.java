package peaksoft.repository.repositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Department;
import peaksoft.repository.DepartmentRepo;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
@Repository
@Transactional
public class DepartmentRepoImpl implements DepartmentRepo {
    @PersistenceContext
    private final EntityManager entityManager;


    @Autowired
    public DepartmentRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Department save(Department department) {
        entityManager.persist(department);
        return department;
    }
    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from Department d where d.id =: id").setParameter("id",id).executeUpdate();
    }

    @Override
    public Department getById(Long id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public void update(Long id, Department newDepartment)  {
                Department department = entityManager.find(Department.class, id);
                department.setName(newDepartment.getName());
    }

    @Override
    public List<Department> getAll(Long id) {
        return entityManager.createQuery("select l from Department l join l.hospital h where h.id=:id",Department.class).setParameter("id",id).getResultList();
    }


}
