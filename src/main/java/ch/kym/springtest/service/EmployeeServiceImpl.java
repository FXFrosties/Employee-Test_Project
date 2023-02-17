package ch.kym.springtest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ch.kym.springtest.model.Employee;
import ch.kym.springtest.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired 
    EmployeeRepository eRepo; 

    @Override
    public List<Employee> getEmployees(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Direction.DESC, "id");
        return eRepo.findAll(pages).getContent();
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

    @Override
    public List<Employee> getEmployeesByName(String name){
        return eRepo.findByName(name);
    }

    @Override
    public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
        //return eRepo.findBynameAndLocation(name, location);
        return eRepo.getEmployeByNameAndLocation(name, location);
    }

    @Override
    public List<Employee> getEmployeesByKeyword(String keyword) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return eRepo.findByNameContaining(keyword, sort);
    }

    @Override
    public Integer deleteEmployeesByName(String name){
        return eRepo.deleteEmployeeByName(name);
    }
    
}
