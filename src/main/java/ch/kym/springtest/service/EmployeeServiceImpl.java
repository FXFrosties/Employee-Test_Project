package ch.kym.springtest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.kym.springtest.model.Employee;
import ch.kym.springtest.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired 
    EmployeeRepository eRepo; 

    @Override
    public List<Employee> getEmployees() {
        return eRepo.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee){
        return eRepo.save(employee);
    }

    @Override
    public Employee getEmployee(long id) {
        Optional<Employee> employee = eRepo.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        throw new RuntimeException("Employee not found");

    }

    @Override
    public void deleteEmployee(long id) {
        eRepo.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return eRepo.save(employee);
    }
    
}
