package ch.kym.springtest.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import ch.kym.springtest.model.Employee;
import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    List<Employee> findByName(String name);
    List<Employee> findBynameAndLocation(String name, String location);
    List<Employee> findByNameContaining(String keyword, Sort sort);
    Employee save(Employee employee);
    Optional<Employee> findById(Long id);
    void deleteById(Long id);

    @Query("FROM Employee WHERE name = :name AND location = :location")
    List<Employee> getEmployeByNameAndLocation(String name, String location);

    @Transactional
    @Modifying
    @Query("DELETE FROM Employee WHERE name = :name")
    Integer deleteEmployeeByName(String name); 

    List<Employee> findByTeamName(String name);

    @Query("FROM Employee WHERE team.name = :name")
    List<Employee> getEmployeeByTeamName(String name);
}
