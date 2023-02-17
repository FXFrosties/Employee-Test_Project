package ch.kym.springtest.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;

import ch.kym.springtest.model.Employee;

public interface EmployeeService {
    List<Employee> getEmployees(int pageNumber, int pageSize);
    Employee saveEmployee(Employee employee);
    Employee getEmployee(long id);
    void deleteEmployee(long id);
    Employee updateEmployee(Employee employee);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployeesByNameAndLocation(String name, String location);
    List<Employee> getEmployeesByKeyword(String name);
    Integer deleteEmployeesByName(String name);
}
